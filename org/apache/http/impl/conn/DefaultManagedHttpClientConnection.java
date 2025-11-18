/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpRequest
 *  org.apache.http.HttpResponse
 *  org.apache.http.config.MessageConstraints
 *  org.apache.http.conn.ManagedHttpClientConnection
 *  org.apache.http.entity.ContentLengthStrategy
 *  org.apache.http.impl.DefaultBHttpClientConnection
 *  org.apache.http.io.HttpMessageParserFactory
 *  org.apache.http.io.HttpMessageWriterFactory
 *  org.apache.http.protocol.HttpContext
 */
package org.apache.http.impl.conn;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.config.MessageConstraints;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.impl.DefaultBHttpClientConnection;
import org.apache.http.io.HttpMessageParserFactory;
import org.apache.http.io.HttpMessageWriterFactory;
import org.apache.http.protocol.HttpContext;

public class DefaultManagedHttpClientConnection
extends DefaultBHttpClientConnection
implements HttpContext,
ManagedHttpClientConnection {
    private volatile boolean shutdown;
    private final Map<String, Object> attributes;
    private final String id;

    public void setAttribute(String id, Object obj) {
        this.attributes.put(id, obj);
    }

    public DefaultManagedHttpClientConnection(String id, int bufferSize) {
        this(id, bufferSize, bufferSize, null, null, null, null, null, null, null);
    }

    public Object getAttribute(String id) {
        return this.attributes.get(id);
    }

    public Object removeAttribute(String id) {
        return this.attributes.remove(id);
    }

    public void bind(Socket socket) throws IOException {
        if (this.shutdown) {
            socket.close();
            throw new InterruptedIOException("Connection already shutdown");
        }
        super.bind(socket);
    }

    public DefaultManagedHttpClientConnection(String id, int bufferSize, int fragmentSizeHint, CharsetDecoder charDecoder, CharsetEncoder charEncoder, MessageConstraints constraints, ContentLengthStrategy incomingContentStrategy, ContentLengthStrategy outgoingContentStrategy, HttpMessageWriterFactory<HttpRequest> requestWriterFactory, HttpMessageParserFactory<HttpResponse> responseParserFactory) {
        super(bufferSize, fragmentSizeHint, charDecoder, charEncoder, constraints, incomingContentStrategy, outgoingContentStrategy, requestWriterFactory, responseParserFactory);
        this.id = id;
        this.attributes = new ConcurrentHashMap<String, Object>();
    }

    public String getId() {
        return this.id;
    }

    public Socket getSocket() {
        return super.getSocket();
    }

    public SSLSession getSSLSession() {
        Socket socket = super.getSocket();
        return socket instanceof SSLSocket ? ((SSLSocket)socket).getSession() : null;
    }

    public void shutdown() throws IOException {
        this.shutdown = true;
        super.shutdown();
    }
}
