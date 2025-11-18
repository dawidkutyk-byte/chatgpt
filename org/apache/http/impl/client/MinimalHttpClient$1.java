/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.conn.ClientConnectionManager
 *  org.apache.http.conn.ClientConnectionRequest
 *  org.apache.http.conn.ManagedClientConnection
 *  org.apache.http.conn.routing.HttpRoute
 *  org.apache.http.conn.scheme.SchemeRegistry
 *  org.apache.http.impl.client.MinimalHttpClient
 */
package org.apache.http.impl.client;

import java.util.concurrent.TimeUnit;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.MinimalHttpClient;

/*
 * Exception performing whole class analysis ignored.
 */
class MinimalHttpClient.1
implements ClientConnectionManager {
    public void shutdown() {
        MinimalHttpClient.access$000((MinimalHttpClient)MinimalHttpClient.this).shutdown();
    }

    public void releaseConnection(ManagedClientConnection conn, long validDuration, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public void closeExpiredConnections() {
        MinimalHttpClient.access$000((MinimalHttpClient)MinimalHttpClient.this).closeExpiredConnections();
    }

    public SchemeRegistry getSchemeRegistry() {
        throw new UnsupportedOperationException();
    }

    MinimalHttpClient.1() {
    }

    public void closeIdleConnections(long idletime, TimeUnit timeUnit) {
        MinimalHttpClient.access$000((MinimalHttpClient)MinimalHttpClient.this).closeIdleConnections(idletime, timeUnit);
    }

    public ClientConnectionRequest requestConnection(HttpRoute route, Object state) {
        throw new UnsupportedOperationException();
    }
}
