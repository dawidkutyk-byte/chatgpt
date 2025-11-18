/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpClientConnection
 *  org.apache.http.HttpConnectionMetrics
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpEntityEnclosingRequest
 *  org.apache.http.HttpException
 *  org.apache.http.HttpMessage
 *  org.apache.http.HttpRequest
 *  org.apache.http.HttpResponse
 *  org.apache.http.HttpResponseFactory
 *  org.apache.http.entity.ContentLengthStrategy
 *  org.apache.http.impl.DefaultHttpResponseFactory
 *  org.apache.http.impl.HttpConnectionMetricsImpl
 *  org.apache.http.impl.entity.EntityDeserializer
 *  org.apache.http.impl.entity.EntitySerializer
 *  org.apache.http.impl.entity.LaxContentLengthStrategy
 *  org.apache.http.impl.entity.StrictContentLengthStrategy
 *  org.apache.http.impl.io.DefaultHttpResponseParser
 *  org.apache.http.impl.io.HttpRequestWriter
 *  org.apache.http.io.EofSensor
 *  org.apache.http.io.HttpMessageParser
 *  org.apache.http.io.HttpMessageWriter
 *  org.apache.http.io.HttpTransportMetrics
 *  org.apache.http.io.SessionInputBuffer
 *  org.apache.http.io.SessionOutputBuffer
 *  org.apache.http.params.HttpParams
 *  org.apache.http.util.Args
 */
package org.apache.http.impl;

import java.io.IOException;
import java.net.SocketTimeoutException;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpConnectionMetrics;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseFactory;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.HttpConnectionMetricsImpl;
import org.apache.http.impl.entity.EntityDeserializer;
import org.apache.http.impl.entity.EntitySerializer;
import org.apache.http.impl.entity.LaxContentLengthStrategy;
import org.apache.http.impl.entity.StrictContentLengthStrategy;
import org.apache.http.impl.io.DefaultHttpResponseParser;
import org.apache.http.impl.io.HttpRequestWriter;
import org.apache.http.io.EofSensor;
import org.apache.http.io.HttpMessageParser;
import org.apache.http.io.HttpMessageWriter;
import org.apache.http.io.HttpTransportMetrics;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.params.HttpParams;
import org.apache.http.util.Args;

@Deprecated
public abstract class AbstractHttpClientConnection
implements HttpClientConnection {
    private EofSensor eofSensor = null;
    private HttpMessageWriter<HttpRequest> requestWriter = null;
    private HttpConnectionMetricsImpl metrics = null;
    private SessionOutputBuffer outbuffer = null;
    private HttpMessageParser<HttpResponse> responseParser = null;
    private SessionInputBuffer inBuffer = null;
    private final EntitySerializer entityserializer = this.createEntitySerializer();
    private final EntityDeserializer entitydeserializer = this.createEntityDeserializer();

    protected void doFlush() throws IOException {
        this.outbuffer.flush();
    }

    public void flush() throws IOException {
        this.assertOpen();
        this.doFlush();
    }

    protected EntityDeserializer createEntityDeserializer() {
        return new EntityDeserializer((ContentLengthStrategy)new LaxContentLengthStrategy());
    }

    public HttpConnectionMetrics getMetrics() {
        return this.metrics;
    }

    public void sendRequestHeader(HttpRequest request) throws IOException, HttpException {
        Args.notNull((Object)request, (String)"HTTP request");
        this.assertOpen();
        this.requestWriter.write((HttpMessage)request);
        this.metrics.incrementRequestCount();
    }

    public HttpResponse receiveResponseHeader() throws HttpException, IOException {
        this.assertOpen();
        HttpResponse response = (HttpResponse)this.responseParser.parse();
        if (response.getStatusLine().getStatusCode() < 200) return response;
        this.metrics.incrementResponseCount();
        return response;
    }

    protected HttpResponseFactory createHttpResponseFactory() {
        return DefaultHttpResponseFactory.INSTANCE;
    }

    public boolean isResponseAvailable(int timeout) throws IOException {
        this.assertOpen();
        try {
            return this.inBuffer.isDataAvailable(timeout);
        }
        catch (SocketTimeoutException ex) {
            return false;
        }
    }

    protected HttpMessageParser<HttpResponse> createResponseParser(SessionInputBuffer buffer, HttpResponseFactory responseFactory, HttpParams params) {
        return new DefaultHttpResponseParser(buffer, null, responseFactory, params);
    }

    public void sendRequestEntity(HttpEntityEnclosingRequest request) throws HttpException, IOException {
        Args.notNull((Object)request, (String)"HTTP request");
        this.assertOpen();
        if (request.getEntity() == null) {
            return;
        }
        this.entityserializer.serialize(this.outbuffer, (HttpMessage)request, request.getEntity());
    }

    public boolean isStale() {
        if (!this.isOpen()) {
            return true;
        }
        if (this.isEof()) {
            return true;
        }
        try {
            this.inBuffer.isDataAvailable(1);
            return this.isEof();
        }
        catch (SocketTimeoutException ex) {
            return false;
        }
        catch (IOException ex) {
            return true;
        }
    }

    protected EntitySerializer createEntitySerializer() {
        return new EntitySerializer((ContentLengthStrategy)new StrictContentLengthStrategy());
    }

    protected HttpConnectionMetricsImpl createConnectionMetrics(HttpTransportMetrics inTransportMetric, HttpTransportMetrics outTransportMetric) {
        return new HttpConnectionMetricsImpl(inTransportMetric, outTransportMetric);
    }

    protected boolean isEof() {
        return this.eofSensor != null && this.eofSensor.isEof();
    }

    public void receiveResponseEntity(HttpResponse response) throws IOException, HttpException {
        Args.notNull((Object)response, (String)"HTTP response");
        this.assertOpen();
        HttpEntity entity = this.entitydeserializer.deserialize(this.inBuffer, (HttpMessage)response);
        response.setEntity(entity);
    }

    protected void init(SessionInputBuffer sessionInputBuffer, SessionOutputBuffer sessionOutputBuffer, HttpParams params) {
        this.inBuffer = (SessionInputBuffer)Args.notNull((Object)sessionInputBuffer, (String)"Input session buffer");
        this.outbuffer = (SessionOutputBuffer)Args.notNull((Object)sessionOutputBuffer, (String)"Output session buffer");
        if (sessionInputBuffer instanceof EofSensor) {
            this.eofSensor = (EofSensor)sessionInputBuffer;
        }
        this.responseParser = this.createResponseParser(sessionInputBuffer, this.createHttpResponseFactory(), params);
        this.requestWriter = this.createRequestWriter(sessionOutputBuffer, params);
        this.metrics = this.createConnectionMetrics(sessionInputBuffer.getMetrics(), sessionOutputBuffer.getMetrics());
    }

    protected abstract void assertOpen() throws IllegalStateException;

    protected HttpMessageWriter<HttpRequest> createRequestWriter(SessionOutputBuffer buffer, HttpParams params) {
        return new HttpRequestWriter(buffer, null, params);
    }
}
