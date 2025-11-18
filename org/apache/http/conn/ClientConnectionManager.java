/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.conn.ClientConnectionRequest
 *  org.apache.http.conn.ManagedClientConnection
 *  org.apache.http.conn.routing.HttpRoute
 *  org.apache.http.conn.scheme.SchemeRegistry
 */
package org.apache.http.conn;

import java.util.concurrent.TimeUnit;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.SchemeRegistry;

@Deprecated
public interface ClientConnectionManager {
    public void shutdown();

    public ClientConnectionRequest requestConnection(HttpRoute var1, Object var2);

    public SchemeRegistry getSchemeRegistry();

    public void closeIdleConnections(long var1, TimeUnit var3);

    public void releaseConnection(ManagedClientConnection var1, long var2, TimeUnit var4);

    public void closeExpiredConnections();
}
