/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpConnectionMetrics
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpEntityEnclosingRequest
 *  org.apache.http.HttpException
 *  org.apache.http.HttpMessage
 *  org.apache.http.HttpRequest
 *  org.apache.http.HttpRequestFactory
 *  org.apache.http.HttpResponse
 *  org.apache.http.HttpServerConnection
 *  org.apache.http.entity.ContentLengthStrategy
 *  org.apache.http.impl.DefaultHttpRequestFactory
 *  org.apache.http.impl.HttpConnectionMetricsImpl
 *  org.apache.http.impl.entity.DisallowIdentityContentLengthStrategy
 *  org.apache.http.impl.entity.EntityDeserializer
 *  org.apache.http.impl.entity.EntitySerializer
 *  org.apache.http.impl.entity.LaxContentLengthStrategy
 *  org.apache.http.impl.entity.StrictContentLengthStrategy
 *  org.apache.http.impl.io.DefaultHttpRequestParser
 *  org.apache.http.impl.io.HttpResponseWriter
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
import org.apache.http.HttpConnectionMetrics;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpServerConnection;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.impl.DefaultHttpRequestFactory;
import org.apache.http.impl.HttpConnectionMetricsImpl;
import org.apache.http.impl.entity.DisallowIdentityContentLengthStrategy;
import org.apache.http.impl.entity.EntityDeserializer;
import org.apache.http.impl.entity.EntitySerializer;
import org.apache.http.impl.entity.LaxContentLengthStrategy;
import org.apache.http.impl.entity.StrictContentLengthStrategy;
import org.apache.http.impl.io.DefaultHttpRequestParser;
import org.apache.http.impl.io.HttpResponseWriter;
import org.apache.http.io.EofSensor;
import org.apache.http.io.HttpMessageParser;
import org.apache.http.io.HttpMessageWriter;
import org.apache.http.io.HttpTransportMetrics;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.params.HttpParams;
import org.apache.http.util.Args;

@Deprecated
public abstract class AbstractHttpServerConnection
implements HttpServerConnection {
    private final EntityDeserializer entitydeserializer;
    private HttpConnectionMetricsImpl metrics = null;
    private SessionOutputBuffer outbuffer = null;
    private EofSensor eofSensor = null;
    private SessionInputBuffer inBuffer = null;
    private final EntitySerializer entityserializer = this.createEntitySerializer();
    private HttpMessageParser<HttpRequest> requestParser = null;
    private HttpMessageWriter<HttpResponse> responseWriter = null;

    protected EntityDeserializer createEntityDeserializer() {
        return new EntityDeserializer((ContentLengthStrategy)new DisallowIdentityContentLengthStrategy((ContentLengthStrategy)new LaxContentLengthStrategy(0)));
    }

    protected HttpRequestFactory createHttpRequestFactory() {
        return DefaultHttpRequestFactory.INSTANCE;
    }

    protected HttpMessageParser<HttpRequest> createRequestParser(SessionInputBuffer buffer, HttpRequestFactory requestFactory, HttpParams params) {
        return new DefaultHttpRequestParser(buffer, null, requestFactory, params);
    }

    protected HttpMessageWriter<HttpResponse> createResponseWriter(SessionOutputBuffer buffer, HttpParams params) {
        return new HttpResponseWriter(buffer, null, params);
    }

    public AbstractHttpServerConnection() {
        this.entitydeserializer = this.createEntityDeserializer();
    }

    public void receiveRequestEntity(HttpEntityEnclosingRequest request) throws HttpException, IOException {
        Args.notNull((Object)request, (String)"HTTP request");
        this.assertOpen();
        HttpEntity entity = this.entitydeserializer.deserialize(this.inBuffer, (HttpMessage)request);
        request.setEntity(entity);
    }

    protected EntitySerializer createEntitySerializer() {
        return new EntitySerializer((ContentLengthStrategy)new StrictContentLengthStrategy());
    }

    protected HttpConnectionMetricsImpl createConnectionMetrics(HttpTransportMetrics inTransportMetric, HttpTransportMetrics outTransportMetric) {
        return new HttpConnectionMetricsImpl(inTransportMetric, outTransportMetric);
    }

    public HttpRequest receiveRequestHeader() throws HttpException, IOException {
        this.assertOpen();
        HttpRequest request = (HttpRequest)this.requestParser.parse();
        this.metrics.incrementRequestCount();
        return request;
    }

    protected abstract void assertOpen() throws IllegalStateException;

    protected void doFlush() throws IOException {
        this.outbuffer.flush();
    }

    protected boolean isEof() {
        return this.eofSensor != null && this.eofSensor.isEof();
    }

    public HttpConnectionMetrics getMetrics() {
        return this.metrics;
    }

    protected void init(SessionInputBuffer inBuffer, SessionOutputBuffer outbuffer, HttpParams params) {
        this.inBuffer = (SessionInputBuffer)Args.notNull((Object)inBuffer, (String)"Input session buffer");
        this.outbuffer = (SessionOutputBuffer)Args.notNull((Object)outbuffer, (String)"Output session buffer");
        if (inBuffer instanceof EofSensor) {
            this.eofSensor = (EofSensor)inBuffer;
        }
        this.requestParser = this.createRequestParser(inBuffer, this.createHttpRequestFactory(), params);
        this.responseWriter = this.createResponseWriter(outbuffer, params);
        this.metrics = this.createConnectionMetrics(inBuffer.getMetrics(), outbuffer.getMetrics());
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
        catch (IOException ex) {
            return true;
        }
    }

    public void flush() throws IOException {
        this.assertOpen();
        this.doFlush();
    }

    public void sendResponseHeader(HttpResponse response) throws HttpException, IOException {
        Args.notNull((Object)response, (String)"HTTP response");
        this.assertOpen();
        this.responseWriter.write((HttpMessage)response);
        if (response.getStatusLine().getStatusCode() < 200) return;
        this.metrics.incrementResponseCount();
    }

    public void sendResponseEntity(HttpResponse response) throws IOException, HttpException {
        if (response.getEntity() == null) {
            return;
        }
        this.entityserializer.serialize(this.outbuffer, (HttpMessage)response, response.getEntity());
    }
}
