/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpClientConnection
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpEntityEnclosingRequest
 *  org.apache.http.HttpException
 *  org.apache.http.HttpMessage
 *  org.apache.http.HttpRequest
 *  org.apache.http.HttpResponse
 *  org.apache.http.config.MessageConstraints
 *  org.apache.http.entity.ContentLengthStrategy
 *  org.apache.http.impl.BHttpConnectionBase
 *  org.apache.http.impl.io.DefaultHttpRequestWriterFactory
 *  org.apache.http.impl.io.DefaultHttpResponseParserFactory
 *  org.apache.http.io.HttpMessageParser
 *  org.apache.http.io.HttpMessageParserFactory
 *  org.apache.http.io.HttpMessageWriter
 *  org.apache.http.io.HttpMessageWriterFactory
 *  org.apache.http.util.Args
 */
package org.apache.http.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.config.MessageConstraints;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.impl.BHttpConnectionBase;
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;
import org.apache.http.impl.io.DefaultHttpResponseParserFactory;
import org.apache.http.io.HttpMessageParser;
import org.apache.http.io.HttpMessageParserFactory;
import org.apache.http.io.HttpMessageWriter;
import org.apache.http.io.HttpMessageWriterFactory;
import org.apache.http.util.Args;

public class DefaultBHttpClientConnection
extends BHttpConnectionBase
implements HttpClientConnection {
    private final HttpMessageParser<HttpResponse> responseParser;
    private final HttpMessageWriter<HttpRequest> requestWriter;

    public boolean isResponseAvailable(int timeout) throws IOException {
        this.ensureOpen();
        try {
            return this.awaitInput(timeout);
        }
        catch (SocketTimeoutException ex) {
            return false;
        }
    }

    protected void onRequestSubmitted(HttpRequest request) {
    }

    public void flush() throws IOException {
        this.ensureOpen();
        this.doFlush();
    }

    public void receiveResponseEntity(HttpResponse response) throws IOException, HttpException {
        Args.notNull((Object)response, (String)"HTTP response");
        this.ensureOpen();
        HttpEntity entity = this.prepareInput((HttpMessage)response);
        response.setEntity(entity);
    }

    public DefaultBHttpClientConnection(int bufferSize, CharsetDecoder charDecoder, CharsetEncoder charEncoder, MessageConstraints constraints) {
        this(bufferSize, bufferSize, charDecoder, charEncoder, constraints, null, null, null, null);
    }

    public void bind(Socket socket) throws IOException {
        super.bind(socket);
    }

    public DefaultBHttpClientConnection(int bufferSize) {
        this(bufferSize, bufferSize, null, null, null, null, null, null, null);
    }

    public void sendRequestEntity(HttpEntityEnclosingRequest request) throws HttpException, IOException {
        Args.notNull((Object)request, (String)"HTTP request");
        this.ensureOpen();
        HttpEntity entity = request.getEntity();
        if (entity == null) {
            return;
        }
        OutputStream outStream = this.prepareOutput((HttpMessage)request);
        entity.writeTo(outStream);
        outStream.close();
    }

    public void sendRequestHeader(HttpRequest request) throws HttpException, IOException {
        Args.notNull((Object)request, (String)"HTTP request");
        this.ensureOpen();
        this.requestWriter.write((HttpMessage)request);
        this.onRequestSubmitted(request);
        this.incrementRequestCount();
    }

    protected void onResponseReceived(HttpResponse response) {
    }

    public HttpResponse receiveResponseHeader() throws IOException, HttpException {
        this.ensureOpen();
        HttpResponse response = (HttpResponse)this.responseParser.parse();
        this.onResponseReceived(response);
        if (response.getStatusLine().getStatusCode() < 200) return response;
        this.incrementResponseCount();
        return response;
    }

    public DefaultBHttpClientConnection(int bufferSize, int fragmentSizeHint, CharsetDecoder charDecoder, CharsetEncoder charEncoder, MessageConstraints constraints, ContentLengthStrategy incomingContentStrategy, ContentLengthStrategy outgoingContentStrategy, HttpMessageWriterFactory<HttpRequest> requestWriterFactory, HttpMessageParserFactory<HttpResponse> responseParserFactory) {
        super(bufferSize, fragmentSizeHint, charDecoder, charEncoder, constraints, incomingContentStrategy, outgoingContentStrategy);
        this.requestWriter = (requestWriterFactory != null ? requestWriterFactory : DefaultHttpRequestWriterFactory.INSTANCE).create(this.getSessionOutputBuffer());
        this.responseParser = (responseParserFactory != null ? responseParserFactory : DefaultHttpResponseParserFactory.INSTANCE).create(this.getSessionInputBuffer(), constraints);
    }
}
