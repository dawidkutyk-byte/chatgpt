/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpHost
 *  org.apache.http.conn.ClientConnectionManager
 *  org.apache.http.conn.OperatedClientConnection
 *  org.apache.http.conn.routing.HttpRoute
 *  org.apache.http.impl.conn.AbstractClientConnAdapter
 *  org.apache.http.impl.conn.AbstractPoolEntry
 *  org.apache.http.impl.conn.ConnectionShutdownException
 *  org.apache.http.params.HttpParams
 *  org.apache.http.protocol.HttpContext
 */
package org.apache.http.impl.conn;

import java.io.IOException;
import org.apache.http.HttpHost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.conn.AbstractClientConnAdapter;
import org.apache.http.impl.conn.AbstractPoolEntry;
import org.apache.http.impl.conn.ConnectionShutdownException;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

@Deprecated
public abstract class AbstractPooledConnAdapter
extends AbstractClientConnAdapter {
    protected volatile AbstractPoolEntry poolEntry;

    public void setState(Object state) {
        AbstractPoolEntry entry = this.getPoolEntry();
        this.assertValid(entry);
        entry.setState(state);
    }

    public String getId() {
        return null;
    }

    public void tunnelProxy(HttpHost next, boolean secure, HttpParams params) throws IOException {
        AbstractPoolEntry entry = this.getPoolEntry();
        this.assertValid(entry);
        entry.tunnelProxy(next, secure, params);
    }

    @Deprecated
    protected final void assertAttached() {
        if (this.poolEntry != null) return;
        throw new ConnectionShutdownException();
    }

    public void open(HttpRoute route, HttpContext context, HttpParams params) throws IOException {
        AbstractPoolEntry entry = this.getPoolEntry();
        this.assertValid(entry);
        entry.open(route, context, params);
    }

    public void shutdown() throws IOException {
        OperatedClientConnection conn;
        AbstractPoolEntry entry = this.getPoolEntry();
        if (entry != null) {
            entry.shutdownEntry();
        }
        if ((conn = this.getWrappedConnection()) == null) return;
        conn.shutdown();
    }

    protected void assertValid(AbstractPoolEntry entry) {
        if (this.isReleased()) throw new ConnectionShutdownException();
        if (entry != null) return;
        throw new ConnectionShutdownException();
    }

    protected AbstractPooledConnAdapter(ClientConnectionManager manager, AbstractPoolEntry entry) {
        super(manager, entry.connection);
        this.poolEntry = entry;
    }

    public Object getState() {
        AbstractPoolEntry entry = this.getPoolEntry();
        this.assertValid(entry);
        return entry.getState();
    }

    @Deprecated
    protected AbstractPoolEntry getPoolEntry() {
        return this.poolEntry;
    }

    protected synchronized void detach() {
        this.poolEntry = null;
        super.detach();
    }

    public HttpRoute getRoute() {
        AbstractPoolEntry entry = this.getPoolEntry();
        this.assertValid(entry);
        return entry.tracker == null ? null : entry.tracker.toRoute();
    }

    public void close() throws IOException {
        OperatedClientConnection conn;
        AbstractPoolEntry entry = this.getPoolEntry();
        if (entry != null) {
            entry.shutdownEntry();
        }
        if ((conn = this.getWrappedConnection()) == null) return;
        conn.close();
    }

    public void layerProtocol(HttpContext context, HttpParams params) throws IOException {
        AbstractPoolEntry entry = this.getPoolEntry();
        this.assertValid(entry);
        entry.layerProtocol(context, params);
    }

    public void tunnelTarget(boolean secure, HttpParams params) throws IOException {
        AbstractPoolEntry entry = this.getPoolEntry();
        this.assertValid(entry);
        entry.tunnelTarget(secure, params);
    }
}
