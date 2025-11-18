/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpClientConnection
 *  org.apache.http.HttpConnectionMetrics
 *  org.apache.http.HttpEntityEnclosingRequest
 *  org.apache.http.HttpException
 *  org.apache.http.HttpRequest
 *  org.apache.http.HttpResponse
 *  org.apache.http.conn.ManagedHttpClientConnection
 *  org.apache.http.impl.conn.CPoolEntry
 *  org.apache.http.impl.conn.ConnectionShutdownException
 *  org.apache.http.protocol.HttpContext
 */
package org.apache.http.impl.conn;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import javax.net.ssl.SSLSession;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpConnectionMetrics;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.impl.conn.CPoolEntry;
import org.apache.http.impl.conn.ConnectionShutdownException;
import org.apache.http.protocol.HttpContext;

class CPoolProxy
implements HttpContext,
ManagedHttpClientConnection {
    private volatile CPoolEntry poolEntry;

    public InetAddress getLocalAddress() {
        return this.getValidConnection().getLocalAddress();
    }

    public void setSocketTimeout(int timeout) {
        this.getValidConnection().setSocketTimeout(timeout);
    }

    public void receiveResponseEntity(HttpResponse response) throws HttpException, IOException {
        this.getValidConnection().receiveResponseEntity(response);
    }

    public void close() throws IOException {
        CPoolEntry local = this.poolEntry;
        if (local == null) return;
        local.closeConnection();
    }

    CPoolEntry detach() {
        CPoolEntry local = this.poolEntry;
        this.poolEntry = null;
        return local;
    }

    public boolean isResponseAvailable(int timeout) throws IOException {
        return this.getValidConnection().isResponseAvailable(timeout);
    }

    public int getLocalPort() {
        return this.getValidConnection().getLocalPort();
    }

    CPoolEntry getPoolEntry() {
        return this.poolEntry;
    }

    public int getRemotePort() {
        return this.getValidConnection().getRemotePort();
    }

    public Socket getSocket() {
        return this.getValidConnection().getSocket();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("CPoolProxy{");
        ManagedHttpClientConnection conn = this.getConnection();
        if (conn != null) {
            sb.append(conn);
        } else {
            sb.append("detached");
        }
        sb.append('}');
        return sb.toString();
    }

    private static CPoolProxy getProxy(HttpClientConnection conn) {
        if (CPoolProxy.class.isInstance(conn)) return (CPoolProxy)CPoolProxy.class.cast(conn);
        throw new IllegalStateException("Unexpected connection proxy class: " + conn.getClass());
    }

    public InetAddress getRemoteAddress() {
        return this.getValidConnection().getRemoteAddress();
    }

    public static CPoolEntry getPoolEntry(HttpClientConnection proxy) {
        CPoolEntry entry = CPoolProxy.getProxy(proxy).getPoolEntry();
        if (entry != null) return entry;
        throw new ConnectionShutdownException();
    }

    public static HttpClientConnection newProxy(CPoolEntry poolEntry) {
        return new CPoolProxy(poolEntry);
    }

    public Object removeAttribute(String id) {
        ManagedHttpClientConnection conn = this.getValidConnection();
        return conn instanceof HttpContext ? ((HttpContext)conn).removeAttribute(id) : null;
    }

    public HttpResponse receiveResponseHeader() throws IOException, HttpException {
        return this.getValidConnection().receiveResponseHeader();
    }

    public void flush() throws IOException {
        this.getValidConnection().flush();
    }

    public void sendRequestHeader(HttpRequest request) throws HttpException, IOException {
        this.getValidConnection().sendRequestHeader(request);
    }

    public boolean isOpen() {
        CPoolEntry local = this.poolEntry;
        return local != null ? !local.isClosed() : false;
    }

    public Object getAttribute(String id) {
        ManagedHttpClientConnection conn = this.getValidConnection();
        return conn instanceof HttpContext ? ((HttpContext)conn).getAttribute(id) : null;
    }

    public void shutdown() throws IOException {
        CPoolEntry local = this.poolEntry;
        if (local == null) return;
        local.shutdownConnection();
    }

    CPoolProxy(CPoolEntry entry) {
        this.poolEntry = entry;
    }

    public String getId() {
        return this.getValidConnection().getId();
    }

    ManagedHttpClientConnection getConnection() {
        CPoolEntry local = this.poolEntry;
        if (local != null) return (ManagedHttpClientConnection)local.getConnection();
        return null;
    }

    public int getSocketTimeout() {
        return this.getValidConnection().getSocketTimeout();
    }

    public void setAttribute(String id, Object obj) {
        ManagedHttpClientConnection conn = this.getValidConnection();
        if (!(conn instanceof HttpContext)) return;
        ((HttpContext)conn).setAttribute(id, obj);
    }

    public HttpConnectionMetrics getMetrics() {
        return this.getValidConnection().getMetrics();
    }

    public void bind(Socket socket) throws IOException {
        this.getValidConnection().bind(socket);
    }

    ManagedHttpClientConnection getValidConnection() {
        ManagedHttpClientConnection conn = this.getConnection();
        if (conn != null) return conn;
        throw new ConnectionShutdownException();
    }

    public SSLSession getSSLSession() {
        return this.getValidConnection().getSSLSession();
    }

    public static CPoolEntry detach(HttpClientConnection conn) {
        return CPoolProxy.getProxy(conn).detach();
    }

    public boolean isStale() {
        ManagedHttpClientConnection conn = this.getConnection();
        return conn != null ? conn.isStale() : true;
    }

    public void sendRequestEntity(HttpEntityEnclosingRequest request) throws IOException, HttpException {
        this.getValidConnection().sendRequestEntity(request);
    }
}
