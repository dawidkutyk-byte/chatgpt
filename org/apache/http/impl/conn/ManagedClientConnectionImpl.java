/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpConnectionMetrics
 *  org.apache.http.HttpEntityEnclosingRequest
 *  org.apache.http.HttpException
 *  org.apache.http.HttpHost
 *  org.apache.http.HttpRequest
 *  org.apache.http.HttpResponse
 *  org.apache.http.conn.ClientConnectionManager
 *  org.apache.http.conn.ClientConnectionOperator
 *  org.apache.http.conn.ManagedClientConnection
 *  org.apache.http.conn.OperatedClientConnection
 *  org.apache.http.conn.routing.HttpRoute
 *  org.apache.http.conn.routing.RouteTracker
 *  org.apache.http.impl.conn.ConnectionShutdownException
 *  org.apache.http.impl.conn.HttpPoolEntry
 *  org.apache.http.params.HttpParams
 *  org.apache.http.protocol.HttpContext
 *  org.apache.http.util.Args
 *  org.apache.http.util.Asserts
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
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.RouteTracker;
import org.apache.http.impl.conn.ConnectionShutdownException;
import org.apache.http.impl.conn.HttpPoolEntry;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;
import org.apache.http.util.Asserts;

@Deprecated
class ManagedClientConnectionImpl
implements ManagedClientConnection {
    private volatile HttpPoolEntry poolEntry;
    private final ClientConnectionManager manager;
    private volatile long duration;
    private volatile boolean reusable;
    private final ClientConnectionOperator operator;

    public void shutdown() throws IOException {
        HttpPoolEntry local = this.poolEntry;
        if (local == null) return;
        OperatedClientConnection conn = (OperatedClientConnection)local.getConnection();
        local.getTracker().reset();
        conn.shutdown();
    }

    public void sendRequestHeader(HttpRequest request) throws HttpException, IOException {
        OperatedClientConnection conn = this.ensureConnection();
        conn.sendRequestHeader(request);
    }

    public Object removeAttribute(String id) {
        OperatedClientConnection conn = this.ensureConnection();
        if (!(conn instanceof HttpContext)) return null;
        return ((HttpContext)conn).removeAttribute(id);
    }

    public Object getState() {
        HttpPoolEntry local = this.ensurePoolEntry();
        return local.getState();
    }

    private HttpPoolEntry ensurePoolEntry() {
        HttpPoolEntry local = this.poolEntry;
        if (local != null) return local;
        throw new ConnectionShutdownException();
    }

    public void bind(Socket socket) throws IOException {
        throw new UnsupportedOperationException();
    }

    public boolean isMarkedReusable() {
        return this.reusable;
    }

    public SSLSession getSSLSession() {
        OperatedClientConnection conn = this.ensureConnection();
        SSLSession result = null;
        Socket sock = conn.getSocket();
        if (!(sock instanceof SSLSocket)) return result;
        result = ((SSLSocket)sock).getSession();
        return result;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void open(HttpRoute route, HttpContext context, HttpParams params) throws IOException {
        OperatedClientConnection conn;
        Args.notNull((Object)route, (String)"Route");
        Args.notNull((Object)params, (String)"HTTP parameters");
        ManagedClientConnectionImpl managedClientConnectionImpl = this;
        synchronized (managedClientConnectionImpl) {
            if (this.poolEntry == null) {
                throw new ConnectionShutdownException();
            }
            RouteTracker tracker = this.poolEntry.getTracker();
            Asserts.notNull((Object)tracker, (String)"Route tracker");
            Asserts.check((!tracker.isConnected() ? 1 : 0) != 0, (String)"Connection already open");
            conn = (OperatedClientConnection)this.poolEntry.getConnection();
        }
        HttpHost proxy = route.getProxyHost();
        this.operator.openConnection(conn, proxy != null ? proxy : route.getTargetHost(), route.getLocalAddress(), context, params);
        ManagedClientConnectionImpl managedClientConnectionImpl2 = this;
        synchronized (managedClientConnectionImpl2) {
            if (this.poolEntry == null) {
                throw new InterruptedIOException();
            }
            RouteTracker tracker = this.poolEntry.getTracker();
            if (proxy == null) {
                tracker.connectTarget(conn.isSecure());
            } else {
                tracker.connectProxy(proxy, conn.isSecure());
            }
        }
    }

    public boolean isOpen() {
        OperatedClientConnection conn = this.getConnection();
        if (conn == null) return false;
        return conn.isOpen();
    }

    public int getRemotePort() {
        OperatedClientConnection conn = this.ensureConnection();
        return conn.getRemotePort();
    }

    public String getId() {
        return null;
    }

    ManagedClientConnectionImpl(ClientConnectionManager manager, ClientConnectionOperator operator, HttpPoolEntry entry) {
        Args.notNull((Object)manager, (String)"Connection manager");
        Args.notNull((Object)operator, (String)"Connection operator");
        Args.notNull((Object)entry, (String)"HTTP pool entry");
        this.manager = manager;
        this.operator = operator;
        this.poolEntry = entry;
        this.reusable = false;
        this.duration = Long.MAX_VALUE;
    }

    public InetAddress getRemoteAddress() {
        OperatedClientConnection conn = this.ensureConnection();
        return conn.getRemoteAddress();
    }

    public boolean isResponseAvailable(int timeout) throws IOException {
        OperatedClientConnection conn = this.ensureConnection();
        return conn.isResponseAvailable(timeout);
    }

    public HttpRoute getRoute() {
        HttpPoolEntry local = this.ensurePoolEntry();
        return local.getEffectiveRoute();
    }

    public boolean isStale() {
        OperatedClientConnection conn = this.getConnection();
        if (conn == null) return true;
        return conn.isStale();
    }

    public void setIdleDuration(long duration, TimeUnit unit) {
        this.duration = duration > 0L ? unit.toMillis(duration) : -1L;
    }

    public void sendRequestEntity(HttpEntityEnclosingRequest request) throws HttpException, IOException {
        OperatedClientConnection conn = this.ensureConnection();
        conn.sendRequestEntity(request);
    }

    private OperatedClientConnection ensureConnection() {
        HttpPoolEntry local = this.poolEntry;
        if (local != null) return (OperatedClientConnection)local.getConnection();
        throw new ConnectionShutdownException();
    }

    public void markReusable() {
        this.reusable = true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void abortConnection() {
        ManagedClientConnectionImpl managedClientConnectionImpl = this;
        synchronized (managedClientConnectionImpl) {
            if (this.poolEntry == null) {
                return;
            }
            this.reusable = false;
            OperatedClientConnection conn = (OperatedClientConnection)this.poolEntry.getConnection();
            try {
                conn.shutdown();
            }
            catch (IOException iOException) {
                // empty catch block
            }
            this.manager.releaseConnection((ManagedClientConnection)this, this.duration, TimeUnit.MILLISECONDS);
            this.poolEntry = null;
        }
    }

    public void setState(Object state) {
        HttpPoolEntry local = this.ensurePoolEntry();
        local.setState(state);
    }

    public void close() throws IOException {
        HttpPoolEntry local = this.poolEntry;
        if (local == null) return;
        OperatedClientConnection conn = (OperatedClientConnection)local.getConnection();
        local.getTracker().reset();
        conn.close();
    }

    public int getSocketTimeout() {
        OperatedClientConnection conn = this.ensureConnection();
        return conn.getSocketTimeout();
    }

    public void flush() throws IOException {
        OperatedClientConnection conn = this.ensureConnection();
        conn.flush();
    }

    public void unmarkReusable() {
        this.reusable = false;
    }

    private OperatedClientConnection getConnection() {
        HttpPoolEntry local = this.poolEntry;
        if (local != null) return (OperatedClientConnection)local.getConnection();
        return null;
    }

    public Socket getSocket() {
        OperatedClientConnection conn = this.ensureConnection();
        return conn.getSocket();
    }

    public void setSocketTimeout(int timeout) {
        OperatedClientConnection conn = this.ensureConnection();
        conn.setSocketTimeout(timeout);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void tunnelTarget(boolean secure, HttpParams params) throws IOException {
        OperatedClientConnection conn;
        HttpHost target;
        RouteTracker tracker;
        Args.notNull((Object)params, (String)"HTTP parameters");
        ManagedClientConnectionImpl managedClientConnectionImpl = this;
        synchronized (managedClientConnectionImpl) {
            if (this.poolEntry == null) {
                throw new ConnectionShutdownException();
            }
            tracker = this.poolEntry.getTracker();
            Asserts.notNull((Object)tracker, (String)"Route tracker");
            Asserts.check((boolean)tracker.isConnected(), (String)"Connection not open");
            Asserts.check((!tracker.isTunnelled() ? 1 : 0) != 0, (String)"Connection is already tunnelled");
            target = tracker.getTargetHost();
            conn = (OperatedClientConnection)this.poolEntry.getConnection();
        }
        conn.update(null, target, secure, params);
        managedClientConnectionImpl = this;
        synchronized (managedClientConnectionImpl) {
            if (this.poolEntry == null) {
                throw new InterruptedIOException();
            }
            tracker = this.poolEntry.getTracker();
            tracker.tunnelTarget(secure);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void layerProtocol(HttpContext context, HttpParams params) throws IOException {
        OperatedClientConnection conn;
        HttpHost target;
        RouteTracker tracker;
        Args.notNull((Object)params, (String)"HTTP parameters");
        ManagedClientConnectionImpl managedClientConnectionImpl = this;
        synchronized (managedClientConnectionImpl) {
            if (this.poolEntry == null) {
                throw new ConnectionShutdownException();
            }
            tracker = this.poolEntry.getTracker();
            Asserts.notNull((Object)tracker, (String)"Route tracker");
            Asserts.check((boolean)tracker.isConnected(), (String)"Connection not open");
            Asserts.check((boolean)tracker.isTunnelled(), (String)"Protocol layering without a tunnel not supported");
            Asserts.check((!tracker.isLayered() ? 1 : 0) != 0, (String)"Multiple protocol layering not supported");
            target = tracker.getTargetHost();
            conn = (OperatedClientConnection)this.poolEntry.getConnection();
        }
        this.operator.updateSecureConnection(conn, target, context, params);
        managedClientConnectionImpl = this;
        synchronized (managedClientConnectionImpl) {
            if (this.poolEntry == null) {
                throw new InterruptedIOException();
            }
            tracker = this.poolEntry.getTracker();
            tracker.layerProtocol(conn.isSecure());
        }
    }

    public InetAddress getLocalAddress() {
        OperatedClientConnection conn = this.ensureConnection();
        return conn.getLocalAddress();
    }

    HttpPoolEntry getPoolEntry() {
        return this.poolEntry;
    }

    public void receiveResponseEntity(HttpResponse response) throws IOException, HttpException {
        OperatedClientConnection conn = this.ensureConnection();
        conn.receiveResponseEntity(response);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void releaseConnection() {
        ManagedClientConnectionImpl managedClientConnectionImpl = this;
        synchronized (managedClientConnectionImpl) {
            if (this.poolEntry == null) {
                return;
            }
            this.manager.releaseConnection((ManagedClientConnection)this, this.duration, TimeUnit.MILLISECONDS);
            this.poolEntry = null;
        }
    }

    public boolean isSecure() {
        OperatedClientConnection conn = this.ensureConnection();
        return conn.isSecure();
    }

    public Object getAttribute(String id) {
        OperatedClientConnection conn = this.ensureConnection();
        if (!(conn instanceof HttpContext)) return null;
        return ((HttpContext)conn).getAttribute(id);
    }

    public HttpResponse receiveResponseHeader() throws IOException, HttpException {
        OperatedClientConnection conn = this.ensureConnection();
        return conn.receiveResponseHeader();
    }

    public ClientConnectionManager getManager() {
        return this.manager;
    }

    HttpPoolEntry detach() {
        HttpPoolEntry local = this.poolEntry;
        this.poolEntry = null;
        return local;
    }

    public int getLocalPort() {
        OperatedClientConnection conn = this.ensureConnection();
        return conn.getLocalPort();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void tunnelProxy(HttpHost next, boolean secure, HttpParams params) throws IOException {
        OperatedClientConnection conn;
        RouteTracker tracker;
        Args.notNull((Object)next, (String)"Next proxy");
        Args.notNull((Object)params, (String)"HTTP parameters");
        ManagedClientConnectionImpl managedClientConnectionImpl = this;
        synchronized (managedClientConnectionImpl) {
            if (this.poolEntry == null) {
                throw new ConnectionShutdownException();
            }
            tracker = this.poolEntry.getTracker();
            Asserts.notNull((Object)tracker, (String)"Route tracker");
            Asserts.check((boolean)tracker.isConnected(), (String)"Connection not open");
            conn = (OperatedClientConnection)this.poolEntry.getConnection();
        }
        conn.update(null, next, secure, params);
        managedClientConnectionImpl = this;
        synchronized (managedClientConnectionImpl) {
            if (this.poolEntry == null) {
                throw new InterruptedIOException();
            }
            tracker = this.poolEntry.getTracker();
            tracker.tunnelProxy(next, secure);
        }
    }

    public void setAttribute(String id, Object obj) {
        OperatedClientConnection conn = this.ensureConnection();
        if (!(conn instanceof HttpContext)) return;
        ((HttpContext)conn).setAttribute(id, obj);
    }

    public HttpConnectionMetrics getMetrics() {
        OperatedClientConnection conn = this.ensureConnection();
        return conn.getMetrics();
    }
}
