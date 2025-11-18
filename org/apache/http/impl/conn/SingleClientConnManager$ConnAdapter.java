/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.conn.ClientConnectionManager
 *  org.apache.http.conn.routing.HttpRoute
 *  org.apache.http.impl.conn.AbstractPoolEntry
 *  org.apache.http.impl.conn.AbstractPooledConnAdapter
 *  org.apache.http.impl.conn.SingleClientConnManager$PoolEntry
 */
package org.apache.http.impl.conn;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.conn.AbstractPoolEntry;
import org.apache.http.impl.conn.AbstractPooledConnAdapter;
import org.apache.http.impl.conn.SingleClientConnManager;

protected class SingleClientConnManager.ConnAdapter
extends AbstractPooledConnAdapter {
    protected SingleClientConnManager.ConnAdapter(SingleClientConnManager.PoolEntry entry, HttpRoute route) {
        super((ClientConnectionManager)SingleClientConnManager.this, (AbstractPoolEntry)entry);
        this.markReusable();
        entry.route = route;
    }
}
