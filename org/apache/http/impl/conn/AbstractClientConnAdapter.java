/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpConnectionMetrics
 *  org.apache.http.HttpEntityEnclosingRequest
 *  org.apache.http.HttpException
 *  org.apache.http.HttpRequest
 *  org.apache.http.HttpResponse
 *  org.apache.http.conn.ClientConnectionManager
 *  org.apache.http.conn.ManagedClientConnection
 *  org.apache.http.conn.OperatedClientConnection
 *  org.apache.http.impl.conn.ConnectionShutdownException
 *  org.apache.http.protocol.HttpContext
 */
package org.apache.http.impl.conn;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import org.apache.http.HttpConnectionMetrics;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.impl.conn.ConnectionShutdownException;
import org.apache.http.protocol.HttpContext;

@Deprecated
public abstract class AbstractClientConnAdapter
implements HttpContext,
ManagedClientConnection {
    private final ClientConnectionManager connManager;
    private volatile long duration;
    private volatile boolean markedReusable;
    private volatile boolean released;
    private volatile OperatedClientConnection wrappedConnection;

    public void setSocketTimeout(int timeout) {
        OperatedClientConnection conn = this.getWrappedConnection();
        this.assertValid(conn);
        conn.setSocketTimeout(timeout);
    }

    public int getLocalPort() {
        OperatedClientConnection conn = this.getWrappedConnection();
        this.assertValid(conn);
        return conn.getLocalPort();
    }

    public boolean isSecure() {
        OperatedClientConnection conn = this.getWrappedConnection();
        this.assertValid(conn);
        return conn.isSecure();
    }

    @Deprecated
    protected final void assertNotAborted() throws InterruptedIOException {
        if (!this.isReleased()) return;
        throw new InterruptedIOException("Connection has been shut down");
    }

    public SSLSession getSSLSession() {
        OperatedClientConnection conn = this.getWrappedConnection();
        this.assertValid(conn);
        if (!this.isOpen()) {
            return null;
        }
        SSLSession result = null;
        Socket sock = conn.getSocket();
        if (!(sock instanceof SSLSocket)) return result;
        result = ((SSLSocket)sock).getSession();
        return result;
    }

    public synchronized void abortConnection() {
        if (this.released) {
            return;
        }
        this.released = true;
        this.unmarkReusable();
        try {
            this.shutdown();
        }
        catch (IOException iOException) {
            // empty catch block
        }
        this.connManager.releaseConnection((ManagedClientConnection)this, this.duration, TimeUnit.MILLISECONDS);
    }

    protected ClientConnectionManager getManager() {
        return this.connManager;
    }

    public void markReusable() {
        this.markedReusable = true;
    }

    public void sendRequestHeader(HttpRequest request) throws HttpException, IOException {
        OperatedClientConnection conn = this.getWrappedConnection();
        this.assertValid(conn);
        this.unmarkReusable();
        conn.sendRequestHeader(request);
    }

    public boolean isResponseAvailable(int timeout) throws IOException {
        OperatedClientConnection conn = this.getWrappedConnection();
        this.assertValid(conn);
        return conn.isResponseAvailable(timeout);
    }

    public void sendRequestEntity(HttpEntityEnclosingRequest request) throws IOException, HttpException {
        OperatedClientConnection conn = this.getWrappedConnection();
        this.assertValid(conn);
        this.unmarkReusable();
        conn.sendRequestEntity(request);
    }

    public HttpConnectionMetrics getMetrics() {
        OperatedClientConnection conn = this.getWrappedConnection();
        this.assertValid(conn);
        return conn.getMetrics();
    }

    public int getSocketTimeout() {
        OperatedClientConnection conn = this.getWrappedConnection();
        this.assertValid(conn);
        return conn.getSocketTimeout();
    }

    protected OperatedClientConnection getWrappedConnection() {
        return this.wrappedConnection;
    }

    public Socket getSocket() {
        OperatedClientConnection conn = this.getWrappedConnection();
        this.assertValid(conn);
        if (this.isOpen()) return conn.getSocket();
        return null;
    }

    public void receiveResponseEntity(HttpResponse response) throws HttpException, IOException {
        OperatedClientConnection conn = this.getWrappedConnection();
        this.assertValid(conn);
        this.unmarkReusable();
        conn.receiveResponseEntity(response);
    }

    public void setAttribute(String id, Object obj) {
        OperatedClientConnection conn = this.getWrappedConnection();
        this.assertValid(conn);
        if (!(conn instanceof HttpContext)) return;
        ((HttpContext)conn).setAttribute(id, obj);
    }

    public void flush() throws IOException {
        OperatedClientConnection conn = this.getWrappedConnection();
        this.assertValid(conn);
        conn.flush();
    }

    public boolean isOpen() {
        OperatedClientConnection conn = this.getWrappedConnection();
        if (conn != null) return conn.isOpen();
        return false;
    }

    public Object removeAttribute(String id) {
        OperatedClientConnection conn = this.getWrappedConnection();
        this.assertValid(conn);
        if (!(conn instanceof HttpContext)) return null;
        return ((HttpContext)conn).removeAttribute(id);
    }

    protected synchronized void detach() {
        this.wrappedConnection = null;
        this.duration = Long.MAX_VALUE;
    }

    public void unmarkReusable() {
        this.markedReusable = false;
    }

    protected final void assertValid(OperatedClientConnection wrappedConn) throws ConnectionShutdownException {
        if (this.isReleased()) throw new ConnectionShutdownException();
        if (wrappedConn != null) return;
        throw new ConnectionShutdownException();
    }

    protected AbstractClientConnAdapter(ClientConnectionManager mgr, OperatedClientConnection conn) {
        this.connManager = mgr;
        this.wrappedConnection = conn;
        this.markedReusable = false;
        this.released = false;
        this.duration = Long.MAX_VALUE;
    }

    public boolean isMarkedReusable() {
        return this.markedReusable;
    }

    public void setIdleDuration(long duration, TimeUnit unit) {
        this.duration = duration > 0L ? unit.toMillis(duration) : -1L;
    }

    protected boolean isReleased() {
        return this.released;
    }

    public synchronized void releaseConnection() {
        if (this.released) {
            return;
        }
        this.released = true;
        this.connManager.releaseConnection((ManagedClientConnection)this, this.duration, TimeUnit.MILLISECONDS);
    }

    public InetAddress getLocalAddress() {
        OperatedClientConnection conn = this.getWrappedConnection();
        this.assertValid(conn);
        return conn.getLocalAddress();
    }

    public Object getAttribute(String id) {
        OperatedClientConnection conn = this.getWrappedConnection();
        this.assertValid(conn);
        if (!(conn instanceof HttpContext)) return null;
        return ((HttpContext)conn).getAttribute(id);
    }

    public int getRemotePort() {
        OperatedClientConnection conn = this.getWrappedConnection();
        this.assertValid(conn);
        return conn.getRemotePort();
    }

    public HttpResponse receiveResponseHeader() throws HttpException, IOException {
        OperatedClientConnection conn = this.getWrappedConnection();
        this.assertValid(conn);
        this.unmarkReusable();
        return conn.receiveResponseHeader();
    }

    public boolean isStale() {
        if (this.isReleased()) {
            return true;
        }
        OperatedClientConnection conn = this.getWrappedConnection();
        if (conn != null) return conn.isStale();
        return true;
    }

    public InetAddress getRemoteAddress() {
        OperatedClientConnection conn = this.getWrappedConnection();
        this.assertValid(conn);
        return conn.getRemoteAddress();
    }

    public void bind(Socket socket) throws IOException {
        throw new UnsupportedOperationException();
    }
}
