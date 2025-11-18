/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.impl.conn.AbstractPoolEntry
 */
package org.apache.http.impl.conn;

import java.io.IOException;
import org.apache.http.impl.conn.AbstractPoolEntry;

protected class SingleClientConnManager.PoolEntry
extends AbstractPoolEntry {
    protected void close() throws IOException {
        this.shutdownEntry();
        if (!this.connection.isOpen()) return;
        this.connection.close();
    }

    protected void shutdown() throws IOException {
        this.shutdownEntry();
        if (!this.connection.isOpen()) return;
        this.connection.shutdown();
    }

    protected SingleClientConnManager.PoolEntry() {
        super(SingleClientConnManager.this.connOperator, null);
    }
}
