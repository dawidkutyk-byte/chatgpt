/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.logging.Log
 *  org.apache.commons.logging.LogFactory
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.conn.ClientConnectionManager
 *  org.apache.http.conn.ClientConnectionOperator
 *  org.apache.http.conn.ClientConnectionRequest
 *  org.apache.http.conn.ConnectionPoolTimeoutException
 *  org.apache.http.conn.DnsResolver
 *  org.apache.http.conn.ManagedClientConnection
 *  org.apache.http.conn.routing.HttpRoute
 *  org.apache.http.conn.scheme.SchemeRegistry
 *  org.apache.http.impl.conn.DefaultClientConnectionOperator
 *  org.apache.http.impl.conn.HttpConnPool
 *  org.apache.http.impl.conn.HttpPoolEntry
 *  org.apache.http.impl.conn.ManagedClientConnectionImpl
 *  org.apache.http.impl.conn.SchemeRegistryFactory
 *  org.apache.http.impl.conn.SystemDefaultDnsResolver
 *  org.apache.http.pool.ConnPoolControl
 *  org.apache.http.pool.PoolEntry
 *  org.apache.http.pool.PoolStats
 *  org.apache.http.util.Args
 *  org.apache.http.util.Asserts
 */
package org.apache.http.impl.conn;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.conn.DefaultClientConnectionOperator;
import org.apache.http.impl.conn.HttpConnPool;
import org.apache.http.impl.conn.HttpPoolEntry;
import org.apache.http.impl.conn.ManagedClientConnectionImpl;
import org.apache.http.impl.conn.SchemeRegistryFactory;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.pool.ConnPoolControl;
import org.apache.http.pool.PoolEntry;
import org.apache.http.pool.PoolStats;
import org.apache.http.util.Args;
import org.apache.http.util.Asserts;

@Deprecated
@Contract(threading=ThreadingBehavior.SAFE_CONDITIONAL)
public class PoolingClientConnectionManager
implements ClientConnectionManager,
ConnPoolControl<HttpRoute> {
    private final Log log = LogFactory.getLog(this.getClass());
    private final DnsResolver dnsResolver;
    private final HttpConnPool pool;
    private final ClientConnectionOperator operator;
    private final SchemeRegistry schemeRegistry;

    public PoolStats getTotalStats() {
        return this.pool.getTotalStats();
    }

    public SchemeRegistry getSchemeRegistry() {
        return this.schemeRegistry;
    }

    private String format(HttpPoolEntry entry) {
        StringBuilder buf = new StringBuilder();
        buf.append("[id: ").append(entry.getId()).append("]");
        buf.append("[route: ").append(entry.getRoute()).append("]");
        Object state = entry.getState();
        if (state == null) return buf.toString();
        buf.append("[state: ").append(state).append("]");
        return buf.toString();
    }

    public int getMaxTotal() {
        return this.pool.getMaxTotal();
    }

    public void setDefaultMaxPerRoute(int max) {
        this.pool.setDefaultMaxPerRoute(max);
    }

    private String format(HttpRoute route, Object state) {
        StringBuilder buf = new StringBuilder();
        buf.append("[route: ").append(route).append("]");
        if (state == null) return buf.toString();
        buf.append("[state: ").append(state).append("]");
        return buf.toString();
    }

    public PoolStats getStats(HttpRoute route) {
        return this.pool.getStats((Object)route);
    }

    public void closeExpiredConnections() {
        this.log.debug((Object)"Closing expired connections");
        this.pool.closeExpired();
    }

    public void shutdown() {
        this.log.debug((Object)"Connection manager is shutting down");
        try {
            this.pool.shutdown();
        }
        catch (IOException ex) {
            this.log.debug((Object)"I/O exception shutting down connection manager", (Throwable)ex);
        }
        this.log.debug((Object)"Connection manager shut down");
    }

    public PoolingClientConnectionManager(SchemeRegistry schreg) {
        this(schreg, -1L, TimeUnit.MILLISECONDS);
    }

    protected ClientConnectionOperator createConnectionOperator(SchemeRegistry schreg) {
        return new DefaultClientConnectionOperator(schreg, this.dnsResolver);
    }

    public PoolingClientConnectionManager(SchemeRegistry schemeRegistry, long timeToLive, TimeUnit timeUnit, DnsResolver dnsResolver) {
        Args.notNull((Object)schemeRegistry, (String)"Scheme registry");
        Args.notNull((Object)dnsResolver, (String)"DNS resolver");
        this.schemeRegistry = schemeRegistry;
        this.dnsResolver = dnsResolver;
        this.operator = this.createConnectionOperator(schemeRegistry);
        this.pool = new HttpConnPool(this.log, this.operator, 2, 20, timeToLive, timeUnit);
    }

    public PoolingClientConnectionManager(SchemeRegistry schemeRegistry, long timeToLive, TimeUnit timeUnit) {
        this(schemeRegistry, timeToLive, timeUnit, (DnsResolver)new SystemDefaultDnsResolver());
    }

    ManagedClientConnection leaseConnection(Future<HttpPoolEntry> future, long timeout, TimeUnit timeUnit) throws InterruptedException, ConnectionPoolTimeoutException {
        try {
            HttpPoolEntry entry = future.get(timeout, timeUnit);
            if (entry == null) throw new InterruptedException();
            if (future.isCancelled()) {
                throw new InterruptedException();
            }
            Asserts.check((entry.getConnection() != null ? 1 : 0) != 0, (String)"Pool entry with no connection");
            if (!this.log.isDebugEnabled()) return new ManagedClientConnectionImpl((ClientConnectionManager)this, this.operator, entry);
            this.log.debug((Object)("Connection leased: " + this.format(entry) + this.formatStats((HttpRoute)entry.getRoute())));
            return new ManagedClientConnectionImpl((ClientConnectionManager)this, this.operator, entry);
        }
        catch (ExecutionException ex) {
            Throwable cause = ex.getCause();
            if (cause == null) {
                cause = ex;
            }
            this.log.error((Object)"Unexpected exception leasing connection from pool", cause);
            throw new InterruptedException();
        }
        catch (TimeoutException ex) {
            throw new ConnectionPoolTimeoutException("Timeout waiting for connection from pool");
        }
    }

    public ClientConnectionRequest requestConnection(HttpRoute route, Object state) {
        Args.notNull((Object)route, (String)"HTTP route");
        if (this.log.isDebugEnabled()) {
            this.log.debug((Object)("Connection request: " + this.format(route, state) + this.formatStats(route)));
        }
        Future future = this.pool.lease((Object)route, state);
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    public int getMaxPerRoute(HttpRoute route) {
        return this.pool.getMaxPerRoute((Object)route);
    }

    public PoolingClientConnectionManager(SchemeRegistry schreg, DnsResolver dnsResolver) {
        this(schreg, -1L, TimeUnit.MILLISECONDS, dnsResolver);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void releaseConnection(ManagedClientConnection conn, long keepalive, TimeUnit timeUnit) {
        Args.check((boolean)(conn instanceof ManagedClientConnectionImpl), (String)"Connection class mismatch, connection not obtained from this manager");
        ManagedClientConnectionImpl managedConn = (ManagedClientConnectionImpl)conn;
        Asserts.check((managedConn.getManager() == this ? 1 : 0) != 0, (String)"Connection not obtained from this manager");
        ManagedClientConnectionImpl managedClientConnectionImpl = managedConn;
        synchronized (managedClientConnectionImpl) {
            HttpPoolEntry entry = managedConn.detach();
            if (entry == null) {
                return;
            }
            try {
                block12: {
                    if (managedConn.isOpen() && !managedConn.isMarkedReusable()) {
                        try {
                            managedConn.shutdown();
                        }
                        catch (IOException iox) {
                            if (!this.log.isDebugEnabled()) break block12;
                            this.log.debug((Object)"I/O exception shutting down released connection", (Throwable)iox);
                        }
                    }
                }
                if (managedConn.isMarkedReusable()) {
                    entry.updateExpiry(keepalive, timeUnit != null ? timeUnit : TimeUnit.MILLISECONDS);
                    if (this.log.isDebugEnabled()) {
                        String s = keepalive > 0L ? "for " + keepalive + " " + (Object)((Object)timeUnit) : "indefinitely";
                        this.log.debug((Object)("Connection " + this.format(entry) + " can be kept alive " + s));
                    }
                }
            }
            finally {
                this.pool.release((PoolEntry)entry, managedConn.isMarkedReusable());
            }
            if (!this.log.isDebugEnabled()) return;
            this.log.debug((Object)("Connection released: " + this.format(entry) + this.formatStats((HttpRoute)entry.getRoute())));
        }
    }

    public void setMaxTotal(int max) {
        this.pool.setMaxTotal(max);
    }

    public PoolingClientConnectionManager() {
        this(SchemeRegistryFactory.createDefault());
    }

    public void closeIdleConnections(long idleTimeout, TimeUnit timeUnit) {
        if (this.log.isDebugEnabled()) {
            this.log.debug((Object)("Closing connections idle longer than " + idleTimeout + " " + (Object)((Object)timeUnit)));
        }
        this.pool.closeIdle(idleTimeout, timeUnit);
    }

    public int getDefaultMaxPerRoute() {
        return this.pool.getDefaultMaxPerRoute();
    }

    private String formatStats(HttpRoute route) {
        StringBuilder buf = new StringBuilder();
        PoolStats totals = this.pool.getTotalStats();
        PoolStats stats = this.pool.getStats((Object)route);
        buf.append("[total kept alive: ").append(totals.getAvailable()).append("; ");
        buf.append("route allocated: ").append(stats.getLeased() + stats.getAvailable());
        buf.append(" of ").append(stats.getMax()).append("; ");
        buf.append("total allocated: ").append(totals.getLeased() + totals.getAvailable());
        buf.append(" of ").append(totals.getMax()).append("]");
        return buf.toString();
    }

    public void setMaxPerRoute(HttpRoute route, int max) {
        this.pool.setMaxPerRoute((Object)route, max);
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
}
