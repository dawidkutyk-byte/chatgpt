/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpClientConnection
 *  org.apache.http.conn.ConnectionRequest
 *  org.apache.http.conn.routing.HttpRoute
 */
package org.apache.http.impl.conn;

import java.util.concurrent.TimeUnit;
import org.apache.http.HttpClientConnection;
import org.apache.http.conn.ConnectionRequest;
import org.apache.http.conn.routing.HttpRoute;

class BasicHttpClientConnectionManager.1
implements ConnectionRequest {
    final /* synthetic */ HttpRoute val$route;
    final /* synthetic */ Object val$state;

    public HttpClientConnection get(long timeout, TimeUnit timeUnit) {
        return BasicHttpClientConnectionManager.this.getConnection(this.val$route, this.val$state);
    }

    BasicHttpClientConnectionManager.1(HttpRoute httpRoute, Object object) {
        this.val$route = httpRoute;
        this.val$state = object;
    }

    public boolean cancel() {
        return false;
    }
}
