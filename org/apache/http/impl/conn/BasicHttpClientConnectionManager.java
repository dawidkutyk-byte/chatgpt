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
 *  org.apache.http.impl.conn.DefaultHttpClientConnectionOperator
 *  org.apache.http.impl.conn.ManagedHttpClientConnectionFactory
 *  org.apache.http.protocol.HttpContext
 *  org.apache.http.util.Args
 *  org.apache.http.util.Asserts
 *  org.apache.http.util.LangUtils
 */
package org.apache.http.impl.conn;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Date;
import java.util.concurrent.TimeUnit;
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
import org.apache.http.impl.conn.DefaultHttpClientConnectionOperator;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;
import org.apache.http.util.Asserts;
import org.apache.http.util.LangUtils;

@Contract(threading=ThreadingBehavior.SAFE_CONDITIONAL)
public class BasicHttpClientConnectionManager
implements HttpClientConnectionManager,
Closeable {
    private final Log log = LogFactory.getLog(this.getClass());
    private final HttpClientConnectionOperator connectionOperator;
    private Object state;
    private HttpRoute route;
    private ConnectionConfig connConfig;
    private ManagedHttpClientConnection conn;
    private long updated;
    private boolean leased;
    private long expiry;
    private SocketConfig socketConfig;
    private final HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory;
    private final AtomicBoolean isShutdown;

    public BasicHttpClientConnectionManager(Lookup<ConnectionSocketFactory> socketFactoryRegistry) {
        this(socketFactoryRegistry, null, null, null);
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

    public void upgrade(HttpClientConnection conn, HttpRoute route, HttpContext context) throws IOException {
        Args.notNull((Object)conn, (String)"Connection");
        Args.notNull((Object)route, (String)"HTTP route");
        Asserts.check((conn == this.conn ? 1 : 0) != 0, (String)"Connection not obtained from this manager");
        this.connectionOperator.upgrade(this.conn, route.getTargetHost(), context);
    }

    Object getState() {
        return this.state;
    }

    public void shutdown() {
        block2: {
            if (!this.isShutdown.compareAndSet(false, true)) return;
            if (this.conn == null) return;
            this.log.debug((Object)"Shutting down connection");
            try {
                this.conn.shutdown();
            }
            catch (IOException iox) {
                if (!this.log.isDebugEnabled()) break block2;
                this.log.debug((Object)"I/O exception shutting down connection", (Throwable)iox);
            }
        }
        this.conn = null;
    }

    public void connect(HttpClientConnection conn, HttpRoute route, int connectTimeout, HttpContext context) throws IOException {
        Args.notNull((Object)conn, (String)"Connection");
        Args.notNull((Object)route, (String)"HTTP route");
        Asserts.check((conn == this.conn ? 1 : 0) != 0, (String)"Connection not obtained from this manager");
        HttpHost host = route.getProxyHost() != null ? route.getProxyHost() : route.getTargetHost();
        InetSocketAddress localAddress = route.getLocalSocketAddress();
        this.connectionOperator.connect(this.conn, host, localAddress, connectTimeout, this.socketConfig, context);
    }

    public BasicHttpClientConnectionManager(Lookup<ConnectionSocketFactory> socketFactoryRegistry, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory, SchemePortResolver schemePortResolver, DnsResolver dnsResolver) {
        this((HttpClientConnectionOperator)new DefaultHttpClientConnectionOperator(socketFactoryRegistry, schemePortResolver, dnsResolver), connFactory);
    }

    public BasicHttpClientConnectionManager(HttpClientConnectionOperator httpClientConnectionOperator, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory) {
        this.connectionOperator = (HttpClientConnectionOperator)Args.notNull((Object)httpClientConnectionOperator, (String)"Connection operator");
        this.connFactory = connFactory != null ? connFactory : ManagedHttpClientConnectionFactory.INSTANCE;
        this.expiry = Long.MAX_VALUE;
        this.socketConfig = SocketConfig.DEFAULT;
        this.connConfig = ConnectionConfig.DEFAULT;
        this.isShutdown = new AtomicBoolean(false);
    }

    HttpRoute getRoute() {
        return this.route;
    }

    public synchronized ConnectionConfig getConnectionConfig() {
        return this.connConfig;
    }

    public synchronized void setConnectionConfig(ConnectionConfig connConfig) {
        this.connConfig = connConfig != null ? connConfig : ConnectionConfig.DEFAULT;
    }

    synchronized HttpClientConnection getConnection(HttpRoute route, Object state) {
        Asserts.check((!this.isShutdown.get() ? 1 : 0) != 0, (String)"Connection manager has been shut down");
        if (this.log.isDebugEnabled()) {
            this.log.debug((Object)("Get connection for route " + route));
        }
        Asserts.check((!this.leased ? 1 : 0) != 0, (String)"Connection is still allocated");
        if (!LangUtils.equals((Object)this.route, (Object)route) || !LangUtils.equals((Object)this.state, (Object)state)) {
            this.closeConnection();
        }
        this.route = route;
        this.state = state;
        this.checkExpiry();
        if (this.conn == null) {
            this.conn = (ManagedHttpClientConnection)this.connFactory.create((Object)route, this.connConfig);
        }
        this.conn.setSocketTimeout(this.socketConfig.getSoTimeout());
        this.leased = true;
        return this.conn;
    }

    public synchronized void setSocketConfig(SocketConfig socketConfig) {
        this.socketConfig = socketConfig != null ? socketConfig : SocketConfig.DEFAULT;
    }

    @Override
    public void close() {
        if (!this.isShutdown.compareAndSet(false, true)) return;
        this.closeConnection();
    }

    public synchronized void closeExpiredConnections() {
        if (this.isShutdown.get()) {
            return;
        }
        if (this.leased) return;
        this.checkExpiry();
    }

    public synchronized SocketConfig getSocketConfig() {
        return this.socketConfig;
    }

    public void routeComplete(HttpClientConnection conn, HttpRoute route, HttpContext context) throws IOException {
    }

    private static Registry<ConnectionSocketFactory> getDefaultRegistry() {
        return RegistryBuilder.create().register("http", (Object)PlainConnectionSocketFactory.getSocketFactory()).register("https", (Object)SSLConnectionSocketFactory.getSocketFactory()).build();
    }

    public BasicHttpClientConnectionManager() {
        this((Lookup<ConnectionSocketFactory>)BasicHttpClientConnectionManager.getDefaultRegistry(), null, null, null);
    }

    private synchronized void closeConnection() {
        block2: {
            if (this.conn == null) return;
            this.log.debug((Object)"Closing connection");
            try {
                this.conn.close();
            }
            catch (IOException iox) {
                if (!this.log.isDebugEnabled()) break block2;
                this.log.debug((Object)"I/O exception closing connection", (Throwable)iox);
            }
        }
        this.conn = null;
    }

    public final ConnectionRequest requestConnection(HttpRoute route, Object state) {
        Args.notNull((Object)route, (String)"Route");
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    public synchronized void closeIdleConnections(long idletime, TimeUnit timeUnit) {
        long deadline;
        Args.notNull((Object)((Object)timeUnit), (String)"Time unit");
        if (this.isShutdown.get()) {
            return;
        }
        if (this.leased) return;
        long time = timeUnit.toMillis(idletime);
        if (time < 0L) {
            time = 0L;
        }
        if (this.updated > (deadline = System.currentTimeMillis() - time)) return;
        this.closeConnection();
    }

    private void checkExpiry() {
        if (this.conn == null) return;
        if (System.currentTimeMillis() < this.expiry) return;
        if (this.log.isDebugEnabled()) {
            this.log.debug((Object)("Connection expired @ " + new Date(this.expiry)));
        }
        this.closeConnection();
    }

    public BasicHttpClientConnectionManager(Lookup<ConnectionSocketFactory> socketFactoryRegistry, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory) {
        this(socketFactoryRegistry, connFactory, null, null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public synchronized void releaseConnection(HttpClientConnection conn, Object state, long keepalive, TimeUnit timeUnit) {
        Args.notNull((Object)conn, (String)"Connection");
        Asserts.check((conn == this.conn ? 1 : 0) != 0, (String)"Connection not obtained from this manager");
        if (this.log.isDebugEnabled()) {
            this.log.debug((Object)("Releasing connection " + conn));
        }
        if (this.isShutdown.get()) {
            return;
        }
        try {
            this.updated = System.currentTimeMillis();
            if (!this.conn.isOpen()) {
                this.conn = null;
                this.route = null;
                this.conn = null;
                this.expiry = Long.MAX_VALUE;
            } else {
                this.state = state;
                this.conn.setSocketTimeout(0);
                if (this.log.isDebugEnabled()) {
                    String s = keepalive > 0L ? "for " + keepalive + " " + (Object)((Object)timeUnit) : "indefinitely";
                    this.log.debug((Object)("Connection can be kept alive " + s));
                }
                this.expiry = keepalive > 0L ? this.updated + timeUnit.toMillis(keepalive) : Long.MAX_VALUE;
            }
        }
        finally {
            this.leased = false;
        }
    }
}
