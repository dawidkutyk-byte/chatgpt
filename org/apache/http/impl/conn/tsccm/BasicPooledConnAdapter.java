/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.conn.ClientConnectionManager
 *  org.apache.http.impl.conn.AbstractPoolEntry
 *  org.apache.http.impl.conn.AbstractPooledConnAdapter
 *  org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager
 */
package org.apache.http.impl.conn.tsccm;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.conn.AbstractPoolEntry;
import org.apache.http.impl.conn.AbstractPooledConnAdapter;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;

@Deprecated
public class BasicPooledConnAdapter
extends AbstractPooledConnAdapter {
    protected BasicPooledConnAdapter(ThreadSafeClientConnManager tsccm, AbstractPoolEntry entry) {
        super((ClientConnectionManager)tsccm, entry);
        this.markReusable();
    }

    protected void detach() {
        super.detach();
    }

    protected ClientConnectionManager getManager() {
        return super.getManager();
    }

    protected AbstractPoolEntry getPoolEntry() {
        return super.getPoolEntry();
    }
}
