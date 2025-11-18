/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.conn.ClientConnectionManager
 *  org.apache.http.conn.ClientConnectionRequest
 *  org.apache.http.conn.ManagedClientConnection
 *  org.apache.http.conn.routing.HttpRoute
 *  org.apache.http.conn.scheme.SchemeRegistry
 *  org.apache.http.impl.client.InternalHttpClient
 */
package org.apache.http.impl.client;

import java.util.concurrent.TimeUnit;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.InternalHttpClient;

/*
 * Exception performing whole class analysis ignored.
 */
class InternalHttpClient.1
implements ClientConnectionManager {
    public SchemeRegistry getSchemeRegistry() {
        throw new UnsupportedOperationException();
    }

    public void closeIdleConnections(long idletime, TimeUnit timeUnit) {
        InternalHttpClient.access$000((InternalHttpClient)InternalHttpClient.this).closeIdleConnections(idletime, timeUnit);
    }

    public void releaseConnection(ManagedClientConnection conn, long validDuration, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public ClientConnectionRequest requestConnection(HttpRoute route, Object state) {
        throw new UnsupportedOperationException();
    }

    public void closeExpiredConnections() {
        InternalHttpClient.access$000((InternalHttpClient)InternalHttpClient.this).closeExpiredConnections();
    }

    InternalHttpClient.1() {
    }

    public void shutdown() {
        InternalHttpClient.access$000((InternalHttpClient)InternalHttpClient.this).shutdown();
    }
}
