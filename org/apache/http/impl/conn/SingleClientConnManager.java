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
 *  org.apache.http.conn.ManagedClientConnection
 *  org.apache.http.conn.routing.HttpRoute
 *  org.apache.http.conn.routing.RouteTracker
 *  org.apache.http.conn.scheme.SchemeRegistry
 *  org.apache.http.impl.conn.DefaultClientConnectionOperator
 *  org.apache.http.impl.conn.SchemeRegistryFactory
 *  org.apache.http.impl.conn.SingleClientConnManager$ConnAdapter
 *  org.apache.http.impl.conn.SingleClientConnManager$PoolEntry
 *  org.apache.http.params.HttpParams
 *  org.apache.http.util.Args
 *  org.apache.http.util.Asserts
 */
package org.apache.http.impl.conn;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.RouteTracker;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.conn.DefaultClientConnectionOperator;
import org.apache.http.impl.conn.SchemeRegistryFactory;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.params.HttpParams;
import org.apache.http.util.Args;
import org.apache.http.util.Asserts;

@Deprecated
@Contract(threading=ThreadingBehavior.SAFE)
public class SingleClientConnManager
implements ClientConnectionManager {
    protected volatile long lastReleaseTime;
    protected volatile PoolEntry uniquePoolEntry;
    protected final boolean alwaysShutDown;
    protected final ClientConnectionOperator connOperator;
    private final Log log = LogFactory.getLog(this.getClass());
    protected volatile boolean isShutDown;
    protected volatile ConnAdapter managedConn;
    protected volatile long connectionExpiresTime;
    protected final SchemeRegistry schemeRegistry;
    public static final String MISUSE_MESSAGE = "Invalid use of SingleClientConnManager: connection still allocated.\nMake sure to release the connection before allocating another one.";

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void releaseConnection(ManagedClientConnection conn, long validDuration, TimeUnit timeUnit) {
        ConnAdapter sca;
        Args.check((boolean)(conn instanceof ConnAdapter), (String)"Connection class mismatch, connection not obtained from this manager");
        this.assertStillUp();
        if (this.log.isDebugEnabled()) {
            this.log.debug((Object)("Releasing connection " + conn));
        }
        ConnAdapter connAdapter = sca = (ConnAdapter)conn;
        synchronized (connAdapter) {
            if (sca.poolEntry == null) {
                return;
            }
            ClientConnectionManager manager = sca.getManager();
            Asserts.check((manager == this ? 1 : 0) != 0, (String)"Connection not obtained from this manager");
            try {
                if (!sca.isOpen()) return;
                if (!this.alwaysShutDown) {
                    if (sca.isMarkedReusable()) return;
                }
                if (this.log.isDebugEnabled()) {
                    this.log.debug((Object)"Released connection open but not reusable.");
                }
                sca.shutdown();
            }
            catch (IOException iox) {
                if (!this.log.isDebugEnabled()) return;
                this.log.debug((Object)"Exception shutting down released connection.", (Throwable)iox);
            }
            finally {
                sca.detach();
                SingleClientConnManager singleClientConnManager = this;
                synchronized (singleClientConnManager) {
                    this.managedConn = null;
                    this.lastReleaseTime = System.currentTimeMillis();
                    this.connectionExpiresTime = validDuration > 0L ? timeUnit.toMillis(validDuration) + this.lastReleaseTime : Long.MAX_VALUE;
                }
            }
        }
    }

    public void closeExpiredConnections() {
        long time = this.connectionExpiresTime;
        if (System.currentTimeMillis() < time) return;
        this.closeIdleConnections(0L, TimeUnit.MILLISECONDS);
    }

    protected ClientConnectionOperator createConnectionOperator(SchemeRegistry schreg) {
        return new DefaultClientConnectionOperator(schreg);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void closeIdleConnections(long idletime, TimeUnit timeUnit) {
        this.assertStillUp();
        Args.notNull((Object)((Object)timeUnit), (String)"Time unit");
        SingleClientConnManager singleClientConnManager = this;
        synchronized (singleClientConnManager) {
            if (this.managedConn != null) return;
            if (!this.uniquePoolEntry.connection.isOpen()) return;
            long cutoff = System.currentTimeMillis() - timeUnit.toMillis(idletime);
            if (this.lastReleaseTime > cutoff) return;
            try {
                this.uniquePoolEntry.close();
            }
            catch (IOException iox) {
                this.log.debug((Object)"Problem closing idle connection.", (Throwable)iox);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void revokeConnection() {
        ConnAdapter conn = this.managedConn;
        if (conn == null) {
            return;
        }
        conn.detach();
        SingleClientConnManager singleClientConnManager = this;
        synchronized (singleClientConnManager) {
            try {
                this.uniquePoolEntry.shutdown();
            }
            catch (IOException iox) {
                this.log.debug((Object)"Problem while shutting down connection.", (Throwable)iox);
            }
        }
    }

    protected final void assertStillUp() throws IllegalStateException {
        Asserts.check((!this.isShutDown ? 1 : 0) != 0, (String)"Manager is shut down");
    }

    public SingleClientConnManager() {
        this(SchemeRegistryFactory.createDefault());
    }

    public SchemeRegistry getSchemeRegistry() {
        return this.schemeRegistry;
    }

    public SingleClientConnManager(SchemeRegistry schreg) {
        Args.notNull((Object)schreg, (String)"Scheme registry");
        this.schemeRegistry = schreg;
        this.connOperator = this.createConnectionOperator(schreg);
        this.uniquePoolEntry = new PoolEntry(this);
        this.managedConn = null;
        this.lastReleaseTime = -1L;
        this.alwaysShutDown = false;
        this.isShutDown = false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ManagedClientConnection getConnection(HttpRoute route, Object state) {
        Args.notNull((Object)route, (String)"Route");
        this.assertStillUp();
        if (this.log.isDebugEnabled()) {
            this.log.debug((Object)("Get connection for route " + route));
        }
        SingleClientConnManager singleClientConnManager = this;
        synchronized (singleClientConnManager) {
            Asserts.check((this.managedConn == null ? 1 : 0) != 0, (String)MISUSE_MESSAGE);
            boolean recreate = false;
            boolean shutdown = false;
            this.closeExpiredConnections();
            if (this.uniquePoolEntry.connection.isOpen()) {
                RouteTracker tracker = this.uniquePoolEntry.tracker;
                shutdown = tracker == null || !tracker.toRoute().equals((Object)route);
            } else {
                recreate = true;
            }
            if (shutdown) {
                recreate = true;
                try {
                    this.uniquePoolEntry.shutdown();
                }
                catch (IOException iox) {
                    this.log.debug((Object)"Problem shutting down connection.", (Throwable)iox);
                }
            }
            if (recreate) {
                this.uniquePoolEntry = new PoolEntry(this);
            }
            this.managedConn = new ConnAdapter(this, this.uniquePoolEntry, route);
            return this.managedConn;
        }
    }

    public final ClientConnectionRequest requestConnection(HttpRoute route, Object state) {
        return new /* Unavailable Anonymous Inner Class!! */;
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

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void shutdown() {
        this.isShutDown = true;
        SingleClientConnManager singleClientConnManager = this;
        synchronized (singleClientConnManager) {
            try {
                if (this.uniquePoolEntry == null) return;
                this.uniquePoolEntry.shutdown();
            }
            catch (IOException iox) {
                this.log.debug((Object)"Problem while shutting down manager.", (Throwable)iox);
            }
            finally {
                this.uniquePoolEntry = null;
                this.managedConn = null;
            }
        }
    }

    @Deprecated
    public SingleClientConnManager(HttpParams params, SchemeRegistry schreg) {
        this(schreg);
    }
}
