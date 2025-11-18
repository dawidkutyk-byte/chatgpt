/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.conn.ClientConnectionRequest
 *  org.apache.http.conn.ManagedClientConnection
 *  org.apache.http.conn.routing.HttpRoute
 */
package org.apache.http.impl.conn;

import java.util.concurrent.TimeUnit;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.routing.HttpRoute;

class BasicClientConnectionManager.1
implements ClientConnectionRequest {
    final /* synthetic */ Object val$state;
    final /* synthetic */ HttpRoute val$route;

    public ManagedClientConnection getConnection(long timeout, TimeUnit timeUnit) {
        return BasicClientConnectionManager.this.getConnection(this.val$route, this.val$state);
    }

    public void abortRequest() {
    }

    BasicClientConnectionManager.1(HttpRoute httpRoute, Object object) {
        this.val$route = httpRoute;
        this.val$state = object;
    }
}
