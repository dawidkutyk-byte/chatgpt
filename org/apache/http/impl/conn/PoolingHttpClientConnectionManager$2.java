/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.conn.ManagedHttpClientConnection
 *  org.apache.http.conn.routing.HttpRoute
 *  org.apache.http.impl.conn.PoolingHttpClientConnectionManager
 *  org.apache.http.pool.PoolEntry
 *  org.apache.http.pool.PoolEntryCallback
 */
package org.apache.http.impl.conn;

import java.io.IOException;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.pool.PoolEntry;
import org.apache.http.pool.PoolEntryCallback;

/*
 * Exception performing whole class analysis ignored.
 */
class PoolingHttpClientConnectionManager.2
implements PoolEntryCallback<HttpRoute, ManagedHttpClientConnection> {
    public void process(PoolEntry<HttpRoute, ManagedHttpClientConnection> entry) {
        ManagedHttpClientConnection connection = (ManagedHttpClientConnection)entry.getConnection();
        if (connection == null) return;
        try {
            connection.shutdown();
        }
        catch (IOException iox) {
            if (!PoolingHttpClientConnectionManager.access$100((PoolingHttpClientConnectionManager)PoolingHttpClientConnectionManager.this).isDebugEnabled()) return;
            PoolingHttpClientConnectionManager.access$100((PoolingHttpClientConnectionManager)PoolingHttpClientConnectionManager.this).debug((Object)"I/O exception shutting down connection", (Throwable)iox);
        }
    }

    PoolingHttpClientConnectionManager.2() {
    }
}
