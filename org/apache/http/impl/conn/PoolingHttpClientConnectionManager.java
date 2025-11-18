/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.logging.Log
 *  org.apache.commons.logging.LogFactory
 *  org.apache.http.HttpClientConnection
 *  org.apache.http.HttpHost
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.config.ConnectionConfig
 *  org.apache.http.config.Lookup
 *  org.apache.http.config.Registry
 *  org.apache.http.config.RegistryBuilder
 *  org.apache.http.config.SocketConfig
 *  org.apache.http.conn.ConnectionPoolTimeoutException
 *  org.apache.http.conn.ConnectionRequest
 *  org.apache.http.conn.DnsResolver
 *  org.apache.http.conn.HttpClientConnectionManager
 *  org.apache.http.conn.HttpClientConnectionOperator
 *  org.apache.http.conn.HttpConnectionFactory
 *  org.apache.http.conn.ManagedHttpClientConnection
 *  org.apache.http.conn.SchemePortResolver
 *  org.apache.http.conn.routing.HttpRoute
 *  org.apache.http.conn.socket.ConnectionSocketFactory
 *  org.apache.http.conn.socket.PlainConnectionSocketFactory
 *  org.apache.http.conn.ssl.SSLConnectionSocketFactory
 *  org.apache.http.impl.conn.CPool
 *  org.apache.http.impl.conn.CPoolEntry
 *  org.apache.http.impl.conn.CPoolProxy
 *  org.apache.http.impl.conn.DefaultHttpClientConnectionOperator
 *  org.apache.http.impl.conn.PoolingHttpClientConnectionManager$ConfigData
 *  org.apache.http.impl.conn.PoolingHttpClientConnectionManager$InternalConnectionFactory
 *  org.apache.http.pool.ConnFactory
 *  org.apache.http.pool.ConnPoolControl
 *  org.apache.http.pool.PoolEntry
 *  org.apache.http.pool.PoolEntryCallback
 *  org.apache.http.pool.PoolStats
 *  org.apache.http.protocol.HttpContext
 *  org.apache.http.util.Args
 *  org.apache.http.util.Asserts
 */
package org.apache.http.impl.conn;

import java.io.Closeable;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpHost;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Lookup;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ConnectionRequest;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.HttpClientConnectionOperator;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.SchemePortResolver;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.conn.CPool;
import org.apache.http.impl.conn.CPoolEntry;
import org.apache.http.impl.conn.CPoolProxy;
import org.apache.http.impl.conn.DefaultHttpClientConnectionOperator;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.pool.ConnFactory;
import org.apache.http.pool.ConnPoolControl;
import org.apache.http.pool.PoolEntry;
import org.apache.http.pool.PoolEntryCallback;
import org.apache.http.pool.PoolStats;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;
import org.apache.http.util.Asserts;

@Contract(threading=ThreadingBehavior.SAFE_CONDITIONAL)
public class PoolingHttpClientConnectionManager
implements HttpClientConnectionManager,
ConnPoolControl<HttpRoute>,
Closeable {
    private final HttpClientConnectionOperator connectionOperator;
    private final ConfigData configData;
    private final CPool pool;
    private final AtomicBoolean isShutDown;
    private final Log log = LogFactory.getLog(this.getClass());

    public PoolingHttpClientConnectionManager(Registry<ConnectionSocketFactory> socketFactoryRegistry) {
        this(socketFactoryRegistry, null, null);
    }

    PoolingHttpClientConnectionManager(CPool pool, Lookup<ConnectionSocketFactory> socketFactoryRegistry, SchemePortResolver schemePortResolver, DnsResolver dnsResolver) {
        this.configData = new ConfigData();
        this.pool = pool;
        this.connectionOperator = new DefaultHttpClientConnectionOperator(socketFactoryRegistry, schemePortResolver, dnsResolver);
        this.isShutDown = new AtomicBoolean(false);
    }

    protected void enumLeased(PoolEntryCallback<HttpRoute, ManagedHttpClientConnection> callback) {
        this.pool.enumLeased(callback);
    }

    public int getDefaultMaxPerRoute() {
        return this.pool.getDefaultMaxPerRoute();
    }

    public SocketConfig getDefaultSocketConfig() {
        return this.configData.getDefaultSocketConfig();
    }

    public PoolingHttpClientConnectionManager(Registry<ConnectionSocketFactory> socketFactoryRegistry, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory, DnsResolver dnsResolver) {
        this(socketFactoryRegistry, connFactory, null, dnsResolver, -1L, TimeUnit.MILLISECONDS);
    }

    private SocketConfig resolveSocketConfig(HttpHost host) {
        SocketConfig socketConfig = this.configData.getSocketConfig(host);
        if (socketConfig == null) {
            socketConfig = this.configData.getDefaultSocketConfig();
        }
        if (socketConfig != null) return socketConfig;
        socketConfig = SocketConfig.DEFAULT;
        return socketConfig;
    }

    public void setDefaultConnectionConfig(ConnectionConfig defaultConnectionConfig) {
        this.configData.setDefaultConnectionConfig(defaultConnectionConfig);
    }

    public void closeExpiredConnections() {
        this.log.debug((Object)"Closing expired connections");
        this.pool.closeExpired();
    }

    public PoolingHttpClientConnectionManager(HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory) {
        this(PoolingHttpClientConnectionManager.getDefaultRegistry(), connFactory, null);
    }

    private String format(CPoolEntry entry) {
        StringBuilder buf = new StringBuilder();
        buf.append("[id: ").append(entry.getId()).append("]");
        buf.append("[route: ").append(entry.getRoute()).append("]");
        Object state = entry.getState();
        if (state == null) return buf.toString();
        buf.append("[state: ").append(state).append("]");
        return buf.toString();
    }

    public int getValidateAfterInactivity() {
        return this.pool.getValidateAfterInactivity();
    }

    protected void enumAvailable(PoolEntryCallback<HttpRoute, ManagedHttpClientConnection> callback) {
        this.pool.enumAvailable(callback);
    }

    public PoolStats getStats(HttpRoute route) {
        return this.pool.getStats((Object)route);
    }

    public void setDefaultSocketConfig(SocketConfig defaultSocketConfig) {
        this.configData.setDefaultSocketConfig(defaultSocketConfig);
    }

    public PoolStats getTotalStats() {
        return this.pool.getTotalStats();
    }

    @Override
    public void close() {
        this.shutdown();
    }

    public Set<HttpRoute> getRoutes() {
        return this.pool.getRoutes();
    }

    public void setConnectionConfig(HttpHost host, ConnectionConfig connectionConfig) {
        this.configData.setConnectionConfig(host, connectionConfig);
    }

    public int getMaxPerRoute(HttpRoute route) {
        return this.pool.getMaxPerRoute((Object)route);
    }

    public ConnectionRequest requestConnection(HttpRoute route, Object state) {
        Args.notNull((Object)route, (String)"HTTP route");
        if (this.log.isDebugEnabled()) {
            this.log.debug((Object)("Connection request: " + this.format(route, state) + this.formatStats(route)));
        }
        Asserts.check((!this.isShutDown.get() ? 1 : 0) != 0, (String)"Connection pool shut down");
        Future future = this.pool.lease((Object)route, state, null);
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    public void shutdown() {
        if (!this.isShutDown.compareAndSet(false, true)) return;
        this.log.debug((Object)"Connection manager is shutting down");
        try {
            this.pool.enumLeased((PoolEntryCallback)new /* Unavailable Anonymous Inner Class!! */);
            this.pool.shutdown();
        }
        catch (IOException ex) {
            this.log.debug((Object)"I/O exception shutting down connection manager", (Throwable)ex);
        }
        this.log.debug((Object)"Connection manager shut down");
    }

    private String format(HttpRoute route, Object state) {
        StringBuilder buf = new StringBuilder();
        buf.append("[route: ").append(route).append("]");
        if (state == null) return buf.toString();
        buf.append("[state: ").append(state).append("]");
        return buf.toString();
    }

    public PoolingHttpClientConnectionManager(Registry<ConnectionSocketFactory> socketFactoryRegistry, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory) {
        this(socketFactoryRegistry, connFactory, null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void connect(HttpClientConnection managedConn, HttpRoute route, int connectTimeout, HttpContext context) throws IOException {
        ManagedHttpClientConnection conn;
        Args.notNull((Object)managedConn, (String)"Managed Connection");
        Args.notNull((Object)route, (String)"HTTP route");
        HttpClientConnection httpClientConnection = managedConn;
        synchronized (httpClientConnection) {
            CPoolEntry entry = CPoolProxy.getPoolEntry((HttpClientConnection)managedConn);
            conn = (ManagedHttpClientConnection)entry.getConnection();
        }
        HttpHost host = route.getProxyHost() != null ? route.getProxyHost() : route.getTargetHost();
        this.connectionOperator.connect(conn, host, route.getLocalSocketAddress(), connectTimeout, this.resolveSocketConfig(host), context);
    }

    public void setMaxPerRoute(HttpRoute route, int max) {
        this.pool.setMaxPerRoute((Object)route, max);
    }

    public void setValidateAfterInactivity(int ms) {
        this.pool.setValidateAfterInactivity(ms);
    }

    public void setDefaultMaxPerRoute(int max) {
        this.pool.setDefaultMaxPerRoute(max);
    }

    private String formatStats(HttpRoute route) {
        StringBuilder buf = new StringBuilder();
        PoolStats totals = this.pool.getTotalStats();
        PoolStats stats = this.pool.getStats((Object)route);
        buf.append("[total available: ").append(totals.getAvailable()).append("; ");
        buf.append("route allocated: ").append(stats.getLeased() + stats.getAvailable());
        buf.append(" of ").append(stats.getMax()).append("; ");
        buf.append("total allocated: ").append(totals.getLeased() + totals.getAvailable());
        buf.append(" of ").append(totals.getMax()).append("]");
        return buf.toString();
    }

    public PoolingHttpClientConnectionManager() {
        this(PoolingHttpClientConnectionManager.getDefaultRegistry());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void finalize() throws Throwable {
        try {
            this.shutdown();
        }
        finally {
            super.finalize();
        }
    }

    protected HttpClientConnection leaseConnection(Future<CPoolEntry> future, long timeout, TimeUnit timeUnit) throws InterruptedException, ConnectionPoolTimeoutException, ExecutionException {
        try {
            CPoolEntry entry = future.get(timeout, timeUnit);
            if (entry == null) throw new ExecutionException(new CancellationException("Operation cancelled"));
            if (future.isCancelled()) {
                throw new ExecutionException(new CancellationException("Operation cancelled"));
            }
            Asserts.check((entry.getConnection() != null ? 1 : 0) != 0, (String)"Pool entry with no connection");
            if (!this.log.isDebugEnabled()) return CPoolProxy.newProxy((CPoolEntry)entry);
            this.log.debug((Object)("Connection leased: " + this.format(entry) + this.formatStats((HttpRoute)entry.getRoute())));
            return CPoolProxy.newProxy((CPoolEntry)entry);
        }
        catch (TimeoutException ex) {
            throw new ConnectionPoolTimeoutException("Timeout waiting for connection from pool");
        }
    }

    private static Registry<ConnectionSocketFactory> getDefaultRegistry() {
        return RegistryBuilder.create().register("http", (Object)PlainConnectionSocketFactory.getSocketFactory()).register("https", (Object)SSLConnectionSocketFactory.getSocketFactory()).build();
    }

    public SocketConfig getSocketConfig(HttpHost host) {
        return this.configData.getSocketConfig(host);
    }

    public void setMaxTotal(int max) {
        this.pool.setMaxTotal(max);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void upgrade(HttpClientConnection managedConn, HttpRoute route, HttpContext context) throws IOException {
        ManagedHttpClientConnection conn;
        Args.notNull((Object)managedConn, (String)"Managed Connection");
        Args.notNull((Object)route, (String)"HTTP route");
        HttpClientConnection httpClientConnection = managedConn;
        synchronized (httpClientConnection) {
            CPoolEntry entry = CPoolProxy.getPoolEntry((HttpClientConnection)managedConn);
            conn = (ManagedHttpClientConnection)entry.getConnection();
        }
        this.connectionOperator.upgrade(conn, route.getTargetHost(), context);
    }

    public void setSocketConfig(HttpHost host, SocketConfig socketConfig) {
        this.configData.setSocketConfig(host, socketConfig);
    }

    public PoolingHttpClientConnectionManager(Registry<ConnectionSocketFactory> socketFactoryRegistry, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory, SchemePortResolver schemePortResolver, DnsResolver dnsResolver, long timeToLive, TimeUnit timeUnit) {
        this((HttpClientConnectionOperator)new DefaultHttpClientConnectionOperator(socketFactoryRegistry, schemePortResolver, dnsResolver), connFactory, timeToLive, timeUnit);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void routeComplete(HttpClientConnection managedConn, HttpRoute route, HttpContext context) throws IOException {
        Args.notNull((Object)managedConn, (String)"Managed Connection");
        Args.notNull((Object)route, (String)"HTTP route");
        HttpClientConnection httpClientConnection = managedConn;
        synchronized (httpClientConnection) {
            CPoolEntry entry = CPoolProxy.getPoolEntry((HttpClientConnection)managedConn);
            entry.markRouteComplete();
        }
    }

    public PoolingHttpClientConnectionManager(Registry<ConnectionSocketFactory> socketFactoryRegistry, DnsResolver dnsResolver) {
        this(socketFactoryRegistry, null, dnsResolver);
    }

    public ConnectionConfig getDefaultConnectionConfig() {
        return this.configData.getDefaultConnectionConfig();
    }

    public void closeIdleConnections(long idleTimeout, TimeUnit timeUnit) {
        if (this.log.isDebugEnabled()) {
            this.log.debug((Object)("Closing connections idle longer than " + idleTimeout + " " + (Object)((Object)timeUnit)));
        }
        this.pool.closeIdle(idleTimeout, timeUnit);
    }

    public int getMaxTotal() {
        return this.pool.getMaxTotal();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void releaseConnection(HttpClientConnection managedConn, Object state, long keepalive, TimeUnit timeUnit) {
        Args.notNull((Object)managedConn, (String)"Managed connection");
        HttpClientConnection httpClientConnection = managedConn;
        synchronized (httpClientConnection) {
            CPoolEntry entry = CPoolProxy.detach((HttpClientConnection)managedConn);
            if (entry == null) {
                return;
            }
            ManagedHttpClientConnection conn = (ManagedHttpClientConnection)entry.getConnection();
            try {
                if (conn.isOpen()) {
                    TimeUnit effectiveUnit = timeUnit != null ? timeUnit : TimeUnit.MILLISECONDS;
                    entry.setState(state);
                    entry.updateExpiry(keepalive, effectiveUnit);
                    if (this.log.isDebugEnabled()) {
                        String s = keepalive > 0L ? "for " + (double)effectiveUnit.toMillis(keepalive) / 1000.0 + " seconds" : "indefinitely";
                        this.log.debug((Object)("Connection " + this.format(entry) + " can be kept alive " + s));
                    }
                    conn.setSocketTimeout(0);
                }
            }
            catch (Throwable throwable) {
                this.pool.release((PoolEntry)entry, conn.isOpen() && entry.isRouteComplete());
                if (!this.log.isDebugEnabled()) throw throwable;
                this.log.debug((Object)("Connection released: " + this.format(entry) + this.formatStats((HttpRoute)entry.getRoute())));
                throw throwable;
            }
            this.pool.release((PoolEntry)entry, conn.isOpen() && entry.isRouteComplete());
            if (!this.log.isDebugEnabled()) return;
            this.log.debug((Object)("Connection released: " + this.format(entry) + this.formatStats((HttpRoute)entry.getRoute())));
        }
    }

    static /* synthetic */ Log access$100(PoolingHttpClientConnectionManager x0) {
        return x0.log;
    }

    public PoolingHttpClientConnectionManager(HttpClientConnectionOperator httpClientConnectionOperator, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory, long timeToLive, TimeUnit timeUnit) {
        this.configData = new ConfigData();
        this.pool = new CPool((ConnFactory)new InternalConnectionFactory(this.configData, connFactory), 2, 20, timeToLive, timeUnit);
        this.pool.setValidateAfterInactivity(2000);
        this.connectionOperator = (HttpClientConnectionOperator)Args.notNull((Object)httpClientConnectionOperator, (String)"HttpClientConnectionOperator");
        this.isShutDown = new AtomicBoolean(false);
    }

    public ConnectionConfig getConnectionConfig(HttpHost host) {
        return this.configData.getConnectionConfig(host);
    }

    public PoolingHttpClientConnectionManager(long timeToLive, TimeUnit timeUnit) {
        this(PoolingHttpClientConnectionManager.getDefaultRegistry(), null, null, null, timeToLive, timeUnit);
    }

    static /* synthetic */ SocketConfig access$000(PoolingHttpClientConnectionManager x0, HttpHost x1) {
        return x0.resolveSocketConfig(x1);
    }
}
