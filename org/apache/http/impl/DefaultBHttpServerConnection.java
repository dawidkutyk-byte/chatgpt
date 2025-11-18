/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpEntityEnclosingRequest
 *  org.apache.http.HttpException
 *  org.apache.http.HttpMessage
 *  org.apache.http.HttpRequest
 *  org.apache.http.HttpResponse
 *  org.apache.http.HttpServerConnection
 *  org.apache.http.config.MessageConstraints
 *  org.apache.http.entity.ContentLengthStrategy
 *  org.apache.http.impl.BHttpConnectionBase
 *  org.apache.http.impl.entity.DisallowIdentityContentLengthStrategy
 *  org.apache.http.impl.io.DefaultHttpRequestParserFactory
 *  org.apache.http.impl.io.DefaultHttpResponseWriterFactory
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
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpServerConnection;
import org.apache.http.config.MessageConstraints;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.impl.BHttpConnectionBase;
import org.apache.http.impl.entity.DisallowIdentityContentLengthStrategy;
import org.apache.http.impl.io.DefaultHttpRequestParserFactory;
import org.apache.http.impl.io.DefaultHttpResponseWriterFactory;
import org.apache.http.io.HttpMessageParser;
import org.apache.http.io.HttpMessageParserFactory;
import org.apache.http.io.HttpMessageWriter;
import org.apache.http.io.HttpMessageWriterFactory;
import org.apache.http.util.Args;

public class DefaultBHttpServerConnection
extends BHttpConnectionBase
implements HttpServerConnection {
    private final HttpMessageParser<HttpRequest> requestParser;
    private final HttpMessageWriter<HttpResponse> responseWriter;

    public void flush() throws IOException {
        this.ensureOpen();
        this.doFlush();
    }

    public HttpRequest receiveRequestHeader() throws IOException, HttpException {
        this.ensureOpen();
        HttpRequest request = (HttpRequest)this.requestParser.parse();
        this.onRequestReceived(request);
        this.incrementRequestCount();
        return request;
    }

    public void sendResponseEntity(HttpResponse response) throws IOException, HttpException {
        Args.notNull((Object)response, (String)"HTTP response");
        this.ensureOpen();
        HttpEntity entity = response.getEntity();
        if (entity == null) {
            return;
        }
        OutputStream outStream = this.prepareOutput((HttpMessage)response);
        entity.writeTo(outStream);
        outStream.close();
    }

    public void receiveRequestEntity(HttpEntityEnclosingRequest request) throws IOException, HttpException {
        Args.notNull((Object)request, (String)"HTTP request");
        this.ensureOpen();
        HttpEntity entity = this.prepareInput((HttpMessage)request);
        request.setEntity(entity);
    }

    public DefaultBHttpServerConnection(int bufferSize, CharsetDecoder charDecoder, CharsetEncoder charEncoder, MessageConstraints constraints) {
        this(bufferSize, bufferSize, charDecoder, charEncoder, constraints, null, null, null, null);
    }

    public void bind(Socket socket) throws IOException {
        super.bind(socket);
    }

    public void sendResponseHeader(HttpResponse response) throws IOException, HttpException {
        Args.notNull((Object)response, (String)"HTTP response");
        this.ensureOpen();
        this.responseWriter.write((HttpMessage)response);
        this.onResponseSubmitted(response);
        if (response.getStatusLine().getStatusCode() < 200) return;
        this.incrementResponseCount();
    }

    protected void onRequestReceived(HttpRequest request) {
    }

    public DefaultBHttpServerConnection(int bufferSize, int fragmentSizeHint, CharsetDecoder charDecoder, CharsetEncoder charEncoder, MessageConstraints constraints, ContentLengthStrategy incomingContentStrategy, ContentLengthStrategy outgoingContentStrategy, HttpMessageParserFactory<HttpRequest> requestParserFactory, HttpMessageWriterFactory<HttpResponse> responseWriterFactory) {
        super(bufferSize, fragmentSizeHint, charDecoder, charEncoder, constraints, (ContentLengthStrategy)(incomingContentStrategy != null ? incomingContentStrategy : DisallowIdentityContentLengthStrategy.INSTANCE), outgoingContentStrategy);
        this.requestParser = (requestParserFactory != null ? requestParserFactory : DefaultHttpRequestParserFactory.INSTANCE).create(this.getSessionInputBuffer(), constraints);
        this.responseWriter = (responseWriterFactory != null ? responseWriterFactory : DefaultHttpResponseWriterFactory.INSTANCE).create(this.getSessionOutputBuffer());
    }

    public DefaultBHttpServerConnection(int bufferSize) {
        this(bufferSize, bufferSize, null, null, null, null, null, null, null);
    }

    protected void onResponseSubmitted(HttpResponse response) {
    }
}
