/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.ConnectionClosedException
 *  org.apache.http.Header
 *  org.apache.http.HttpConnectionMetrics
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpException
 *  org.apache.http.HttpInetConnection
 *  org.apache.http.HttpMessage
 *  org.apache.http.config.MessageConstraints
 *  org.apache.http.entity.BasicHttpEntity
 *  org.apache.http.entity.ContentLengthStrategy
 *  org.apache.http.impl.HttpConnectionMetricsImpl
 *  org.apache.http.impl.entity.LaxContentLengthStrategy
 *  org.apache.http.impl.entity.StrictContentLengthStrategy
 *  org.apache.http.impl.io.ChunkedInputStream
 *  org.apache.http.impl.io.ChunkedOutputStream
 *  org.apache.http.impl.io.ContentLengthInputStream
 *  org.apache.http.impl.io.ContentLengthOutputStream
 *  org.apache.http.impl.io.EmptyInputStream
 *  org.apache.http.impl.io.HttpTransportMetricsImpl
 *  org.apache.http.impl.io.IdentityInputStream
 *  org.apache.http.impl.io.IdentityOutputStream
 *  org.apache.http.impl.io.SessionInputBufferImpl
 *  org.apache.http.impl.io.SessionOutputBufferImpl
 *  org.apache.http.io.HttpTransportMetrics
 *  org.apache.http.io.SessionInputBuffer
 *  org.apache.http.io.SessionOutputBuffer
 *  org.apache.http.util.Args
 *  org.apache.http.util.NetUtils
 */
package org.apache.http.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.http.ConnectionClosedException;
import org.apache.http.Header;
import org.apache.http.HttpConnectionMetrics;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpInetConnection;
import org.apache.http.HttpMessage;
import org.apache.http.config.MessageConstraints;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.impl.HttpConnectionMetricsImpl;
import org.apache.http.impl.entity.LaxContentLengthStrategy;
import org.apache.http.impl.entity.StrictContentLengthStrategy;
import org.apache.http.impl.io.ChunkedInputStream;
import org.apache.http.impl.io.ChunkedOutputStream;
import org.apache.http.impl.io.ContentLengthInputStream;
import org.apache.http.impl.io.ContentLengthOutputStream;
import org.apache.http.impl.io.EmptyInputStream;
import org.apache.http.impl.io.HttpTransportMetricsImpl;
import org.apache.http.impl.io.IdentityInputStream;
import org.apache.http.impl.io.IdentityOutputStream;
import org.apache.http.impl.io.SessionInputBufferImpl;
import org.apache.http.impl.io.SessionOutputBufferImpl;
import org.apache.http.io.HttpTransportMetrics;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.util.Args;
import org.apache.http.util.NetUtils;

public class BHttpConnectionBase
implements HttpInetConnection {
    private final SessionOutputBufferImpl outbuffer;
    private final ContentLengthStrategy outgoingContentStrategy;
    private final SessionInputBufferImpl inBuffer;
    private final HttpConnectionMetricsImpl connMetrics;
    private final MessageConstraints messageConstraints;
    private final ContentLengthStrategy incomingContentStrategy;
    private final AtomicReference<Socket> socketHolder;

    protected void ensureOpen() throws IOException {
        Socket socket = this.socketHolder.get();
        if (socket == null) {
            throw new ConnectionClosedException();
        }
        if (!this.inBuffer.isBound()) {
            this.inBuffer.bind(this.getSocketInputStream(socket));
        }
        if (this.outbuffer.isBound()) return;
        this.outbuffer.bind(this.getSocketOutputStream(socket));
    }

    public boolean isStale() {
        if (!this.isOpen()) {
            return true;
        }
        try {
            int bytesRead = this.fillInputBuffer(1);
            return bytesRead < 0;
        }
        catch (SocketTimeoutException ex) {
            return false;
        }
        catch (IOException ex) {
            return true;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void close() throws IOException {
        Socket socket = this.socketHolder.getAndSet(null);
        if (socket == null) return;
        try {
            this.inBuffer.clear();
            this.outbuffer.flush();
            try {
                try {
                    socket.shutdownOutput();
                }
                catch (IOException ignore) {
                    // empty catch block
                }
                try {
                    socket.shutdownInput();
                }
                catch (IOException ignore) {}
            }
            catch (UnsupportedOperationException unsupportedOperationException) {
                // empty catch block
            }
        }
        finally {
            socket.close();
        }
    }

    protected OutputStream prepareOutput(HttpMessage message) throws HttpException {
        long len = this.outgoingContentStrategy.determineLength(message);
        return this.createOutputStream(len, (SessionOutputBuffer)this.outbuffer);
    }

    public boolean isOpen() {
        return this.socketHolder.get() != null;
    }

    public InetAddress getRemoteAddress() {
        Socket socket = this.socketHolder.get();
        return socket != null ? socket.getInetAddress() : null;
    }

    protected boolean awaitInput(int timeout) throws IOException {
        if (this.inBuffer.hasBufferedData()) {
            return true;
        }
        this.fillInputBuffer(timeout);
        return this.inBuffer.hasBufferedData();
    }

    protected Socket getSocket() {
        return this.socketHolder.get();
    }

    public int getLocalPort() {
        Socket socket = this.socketHolder.get();
        return socket != null ? socket.getLocalPort() : -1;
    }

    public String toString() {
        Socket socket = this.socketHolder.get();
        if (socket == null) return "[Not bound]";
        StringBuilder buffer = new StringBuilder();
        SocketAddress remoteAddress = socket.getRemoteSocketAddress();
        SocketAddress localAddress = socket.getLocalSocketAddress();
        if (remoteAddress == null) return buffer.toString();
        if (localAddress == null) return buffer.toString();
        NetUtils.formatAddress((StringBuilder)buffer, (SocketAddress)localAddress);
        buffer.append("<->");
        NetUtils.formatAddress((StringBuilder)buffer, (SocketAddress)remoteAddress);
        return buffer.toString();
    }

    protected void incrementRequestCount() {
        this.connMetrics.incrementRequestCount();
    }

    protected SessionOutputBuffer getSessionOutputBuffer() {
        return this.outbuffer;
    }

    public int getSocketTimeout() {
        Socket socket = this.socketHolder.get();
        if (socket == null) return -1;
        try {
            return socket.getSoTimeout();
        }
        catch (SocketException ignore) {
            return -1;
        }
    }

    public InetAddress getLocalAddress() {
        Socket socket = this.socketHolder.get();
        return socket != null ? socket.getLocalAddress() : null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void shutdown() throws IOException {
        Socket socket = this.socketHolder.getAndSet(null);
        if (socket == null) return;
        try {
            socket.setSoLinger(true, 0);
        }
        catch (IOException iOException) {
        }
        finally {
            socket.close();
        }
    }

    protected InputStream getSocketInputStream(Socket socket) throws IOException {
        return socket.getInputStream();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private int fillInputBuffer(int timeout) throws IOException {
        Socket socket = this.socketHolder.get();
        int oldtimeout = socket.getSoTimeout();
        try {
            socket.setSoTimeout(timeout);
            int n = this.inBuffer.fillBuffer();
            return n;
        }
        finally {
            socket.setSoTimeout(oldtimeout);
        }
    }

    protected void doFlush() throws IOException {
        this.outbuffer.flush();
    }

    protected HttpEntity prepareInput(HttpMessage message) throws HttpException {
        Header contentEncodingHeader;
        BasicHttpEntity entity = new BasicHttpEntity();
        long len = this.incomingContentStrategy.determineLength(message);
        InputStream inStream = this.createInputStream(len, (SessionInputBuffer)this.inBuffer);
        if (len == -2L) {
            entity.setChunked(true);
            entity.setContentLength(-1L);
            entity.setContent(inStream);
        } else if (len == -1L) {
            entity.setChunked(false);
            entity.setContentLength(-1L);
            entity.setContent(inStream);
        } else {
            entity.setChunked(false);
            entity.setContentLength(len);
            entity.setContent(inStream);
        }
        Header contentTypeHeader = message.getFirstHeader("Content-Type");
        if (contentTypeHeader != null) {
            entity.setContentType(contentTypeHeader);
        }
        if ((contentEncodingHeader = message.getFirstHeader("Content-Encoding")) == null) return entity;
        entity.setContentEncoding(contentEncodingHeader);
        return entity;
    }

    public void setSocketTimeout(int timeout) {
        Socket socket = this.socketHolder.get();
        if (socket == null) return;
        try {
            socket.setSoTimeout(timeout);
        }
        catch (SocketException socketException) {
            // empty catch block
        }
    }

    protected InputStream createInputStream(long len, SessionInputBuffer inBuffer) {
        if (len == -2L) {
            return new ChunkedInputStream(inBuffer, this.messageConstraints);
        }
        if (len == -1L) {
            return new IdentityInputStream(inBuffer);
        }
        if (len != 0L) return new ContentLengthInputStream(inBuffer, len);
        return EmptyInputStream.INSTANCE;
    }

    protected BHttpConnectionBase(int bufferSize, int fragmentSizeHint, CharsetDecoder charDecoder, CharsetEncoder charEncoder, MessageConstraints messageConstraints, ContentLengthStrategy incomingContentStrategy, ContentLengthStrategy outgoingContentStrategy) {
        Args.positive((int)bufferSize, (String)"Buffer size");
        HttpTransportMetricsImpl inTransportMetrics = new HttpTransportMetricsImpl();
        HttpTransportMetricsImpl outTransportMetrics = new HttpTransportMetricsImpl();
        this.inBuffer = new SessionInputBufferImpl(inTransportMetrics, bufferSize, -1, messageConstraints != null ? messageConstraints : MessageConstraints.DEFAULT, charDecoder);
        this.outbuffer = new SessionOutputBufferImpl(outTransportMetrics, bufferSize, fragmentSizeHint, charEncoder);
        this.messageConstraints = messageConstraints;
        this.connMetrics = new HttpConnectionMetricsImpl((HttpTransportMetrics)inTransportMetrics, (HttpTransportMetrics)outTransportMetrics);
        this.incomingContentStrategy = incomingContentStrategy != null ? incomingContentStrategy : LaxContentLengthStrategy.INSTANCE;
        this.outgoingContentStrategy = outgoingContentStrategy != null ? outgoingContentStrategy : StrictContentLengthStrategy.INSTANCE;
        this.socketHolder = new AtomicReference();
    }

    protected void incrementResponseCount() {
        this.connMetrics.incrementResponseCount();
    }

    public int getRemotePort() {
        Socket socket = this.socketHolder.get();
        return socket != null ? socket.getPort() : -1;
    }

    public HttpConnectionMetrics getMetrics() {
        return this.connMetrics;
    }

    protected SessionInputBuffer getSessionInputBuffer() {
        return this.inBuffer;
    }

    protected OutputStream createOutputStream(long len, SessionOutputBuffer outbuffer) {
        if (len == -2L) {
            return new ChunkedOutputStream(2048, outbuffer);
        }
        if (len != -1L) return new ContentLengthOutputStream(outbuffer, len);
        return new IdentityOutputStream(outbuffer);
    }

    protected void bind(Socket socket) throws IOException {
        Args.notNull((Object)socket, (String)"Socket");
        this.socketHolder.set(socket);
        this.inBuffer.bind(null);
        this.outbuffer.bind(null);
    }

    protected OutputStream getSocketOutputStream(Socket socket) throws IOException {
        return socket.getOutputStream();
    }
}
