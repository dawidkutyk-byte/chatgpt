/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpClientConnection
 *  org.apache.http.HttpHost
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.pool.PoolEntry
 */
package org.apache.http.impl.pool;

import java.io.IOException;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpHost;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.pool.PoolEntry;

@Contract(threading=ThreadingBehavior.SAFE_CONDITIONAL)
public class BasicPoolEntry
extends PoolEntry<HttpHost, HttpClientConnection> {
    public void close() {
        try {
            HttpClientConnection connection = (HttpClientConnection)this.getConnection();
            try {
                int socketTimeout = connection.getSocketTimeout();
                if (socketTimeout <= 0 || socketTimeout > 1000) {
                    connection.setSocketTimeout(1000);
                }
                connection.close();
            }
            catch (IOException ex) {
                connection.shutdown();
            }
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    public BasicPoolEntry(String id, HttpHost route, HttpClientConnection conn) {
        super(id, (Object)route, (Object)conn);
    }

    public boolean isClosed() {
        return !((HttpClientConnection)this.getConnection()).isOpen();
    }
}
