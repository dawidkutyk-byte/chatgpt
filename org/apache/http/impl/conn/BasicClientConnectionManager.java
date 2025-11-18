/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.logging.Log
 *  org.apache.commons.logging.LogFactory
 *  org.apache.http.HttpClientConnection
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.conn.ClientConnectionManager
 *  org.apache.http.conn.ClientConnectionOperator
 *  org.apache.http.conn.ClientConnectionRequest
 *  org.apache.http.conn.ManagedClientConnection
 *  org.apache.http.conn.OperatedClientConnection
 *  org.apache.http.conn.routing.HttpRoute
 *  org.apache.http.conn.scheme.SchemeRegistry
 *  org.apache.http.impl.conn.DefaultClientConnectionOperator
 *  org.apache.http.impl.conn.HttpPoolEntry
 *  org.apache.http.impl.conn.ManagedClientConnectionImpl
 *  org.apache.http.impl.conn.SchemeRegistryFactory
 *  org.apache.http.util.Args
 *  org.apache.http.util.Asserts
 */
package org.apache.http.impl.conn;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpClientConnection;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.conn.DefaultClientConnectionOperator;
import org.apache.http.impl.conn.HttpPoolEntry;
import org.apache.http.impl.conn.ManagedClientConnectionImpl;
import org.apache.http.impl.conn.SchemeRegistryFactory;
import org.apache.http.util.Args;
import org.apache.http.util.Asserts;

@Deprecated
@Contract(threading=ThreadingBehavior.SAFE)
public class BasicClientConnectionManager
implements ClientConnectionManager {
    private static final AtomicLong COUNTER = new AtomicLong();
    private final Log log = LogFactory.getLog(this.getClass());
    private final ClientConnectionOperator connOperator;
    private HttpPoolEntry poolEntry;
    private volatile boolean shutdown;
    private final SchemeRegistry schemeRegistry;
    public static final String MISUSE_MESSAGE = "Invalid use of BasicClientConnManager: connection still allocated.\nMake sure to release the connection before allocating another one.";
    private ManagedClientConnectionImpl conn;

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

    private void assertNotShutdown() {
        Asserts.check((!this.shutdown ? 1 : 0) != 0, (String)"Connection manager has been shut down");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void closeExpiredConnections() {
        BasicClientConnectionManager basicClientConnectionManager = this;
        synchronized (basicClientConnectionManager) {
            this.assertNotShutdown();
            long now = System.currentTimeMillis();
            if (this.poolEntry == null) return;
            if (!this.poolEntry.isExpired(now)) return;
            this.poolEntry.close();
            this.poolEntry.getTracker().reset();
        }
    }

    public BasicClientConnectionManager() {
        this(SchemeRegistryFactory.createDefault());
    }

    private void shutdownConnection(HttpClientConnection conn) {
        try {
            conn.shutdown();
        }
        catch (IOException iox) {
            if (!this.log.isDebugEnabled()) return;
            this.log.debug((Object)"I/O exception shutting down connection", (Throwable)iox);
        }
    }

    protected ClientConnectionOperator createConnectionOperator(SchemeRegistry schreg) {
        return new DefaultClientConnectionOperator(schreg);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    ManagedClientConnection getConnection(HttpRoute route, Object state) {
        Args.notNull((Object)route, (String)"Route");
        BasicClientConnectionManager basicClientConnectionManager = this;
        synchronized (basicClientConnectionManager) {
            long now;
            this.assertNotShutdown();
            if (this.log.isDebugEnabled()) {
                this.log.debug((Object)("Get connection for route " + route));
            }
            Asserts.check((this.conn == null ? 1 : 0) != 0, (String)MISUSE_MESSAGE);
            if (this.poolEntry != null && !this.poolEntry.getPlannedRoute().equals((Object)route)) {
                this.poolEntry.close();
                this.poolEntry = null;
            }
            if (this.poolEntry == null) {
                String id = Long.toString(COUNTER.getAndIncrement());
                OperatedClientConnection opconn = this.connOperator.createConnection();
                this.poolEntry = new HttpPoolEntry(this.log, id, route, opconn, 0L, TimeUnit.MILLISECONDS);
            }
            if (this.poolEntry.isExpired(now = System.currentTimeMillis())) {
                this.poolEntry.close();
                this.poolEntry.getTracker().reset();
            }
            this.conn = new ManagedClientConnectionImpl((ClientConnectionManager)this, this.connOperator, this.poolEntry);
            return this.conn;
        }
    }

    public SchemeRegistry getSchemeRegistry() {
        return this.schemeRegistry;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void shutdown() {
        BasicClientConnectionManager basicClientConnectionManager = this;
        synchronized (basicClientConnectionManager) {
            this.shutdown = true;
            try {
                if (this.poolEntry == null) return;
                this.poolEntry.close();
            }
            finally {
                this.poolEntry = null;
                this.conn = null;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void closeIdleConnections(long idletime, TimeUnit timeUnit) {
        Args.notNull((Object)((Object)timeUnit), (String)"Time unit");
        BasicClientConnectionManager basicClientConnectionManager = this;
        synchronized (basicClientConnectionManager) {
            this.assertNotShutdown();
            long time = timeUnit.toMillis(idletime);
            if (time < 0L) {
                time = 0L;
            }
            long deadline = System.currentTimeMillis() - time;
            if (this.poolEntry == null) return;
            if (this.poolEntry.getUpdated() > deadline) return;
            this.poolEntry.close();
            this.poolEntry.getTracker().reset();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void releaseConnection(ManagedClientConnection conn, long keepalive, TimeUnit timeUnit) {
        ManagedClientConnectionImpl managedConn;
        Args.check((boolean)(conn instanceof ManagedClientConnectionImpl), (String)"Connection class mismatch, connection not obtained from this manager");
        ManagedClientConnectionImpl managedClientConnectionImpl = managedConn = (ManagedClientConnectionImpl)conn;
        synchronized (managedClientConnectionImpl) {
            if (this.log.isDebugEnabled()) {
                this.log.debug((Object)("Releasing connection " + conn));
            }
            if (managedConn.getPoolEntry() == null) {
                return;
            }
            ClientConnectionManager manager = managedConn.getManager();
            Asserts.check((manager == this ? 1 : 0) != 0, (String)"Connection not obtained from this manager");
            BasicClientConnectionManager basicClientConnectionManager = this;
            synchronized (basicClientConnectionManager) {
                if (this.shutdown) {
                    this.shutdownConnection((HttpClientConnection)managedConn);
                    return;
                }
                try {
                    if (managedConn.isOpen() && !managedConn.isMarkedReusable()) {
                        this.shutdownConnection((HttpClientConnection)managedConn);
                    }
                    if (!managedConn.isMarkedReusable()) return;
                    this.poolEntry.updateExpiry(keepalive, timeUnit != null ? timeUnit : TimeUnit.MILLISECONDS);
                    if (!this.log.isDebugEnabled()) return;
                    String s = keepalive > 0L ? "for " + keepalive + " " + (Object)((Object)timeUnit) : "indefinitely";
                    this.log.debug((Object)("Connection can be kept alive " + s));
                }
                finally {
                    managedConn.detach();
                    this.conn = null;
                    if (this.poolEntry.isClosed()) {
                        this.poolEntry = null;
                    }
                }
            }
        }
    }

    public BasicClientConnectionManager(SchemeRegistry schreg) {
        Args.notNull((Object)schreg, (String)"Scheme registry");
        this.schemeRegistry = schreg;
        this.connOperator = this.createConnectionOperator(schreg);
    }

    public final ClientConnectionRequest requestConnection(HttpRoute route, Object state) {
        return new /* Unavailable Anonymous Inner Class!! */;
    }
}
