/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpClientConnection
 *  org.apache.http.HttpHost
 *  org.apache.http.config.SocketConfig
 *  org.apache.http.conn.ConnectionPoolTimeoutException
 *  org.apache.http.conn.ConnectionRequest
 *  org.apache.http.conn.routing.HttpRoute
 *  org.apache.http.impl.conn.PoolingHttpClientConnectionManager
 */
package org.apache.http.impl.conn;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpHost;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ConnectionRequest;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/*
 * Exception performing whole class analysis ignored.
 */
class PoolingHttpClientConnectionManager.1
implements ConnectionRequest {
    final /* synthetic */ HttpRoute val$route;
    final /* synthetic */ Future val$future;

    public HttpClientConnection get(long timeout, TimeUnit timeUnit) throws ConnectionPoolTimeoutException, ExecutionException, InterruptedException {
        HttpClientConnection conn = PoolingHttpClientConnectionManager.this.leaseConnection(this.val$future, timeout, timeUnit);
        if (!conn.isOpen()) return conn;
        HttpHost host = this.val$route.getProxyHost() != null ? this.val$route.getProxyHost() : this.val$route.getTargetHost();
        SocketConfig socketConfig = PoolingHttpClientConnectionManager.access$000((PoolingHttpClientConnectionManager)PoolingHttpClientConnectionManager.this, (HttpHost)host);
        conn.setSocketTimeout(socketConfig.getSoTimeout());
        return conn;
    }

    PoolingHttpClientConnectionManager.1(Future future, HttpRoute httpRoute) {
        this.val$future = future;
        this.val$route = httpRoute;
    }

    public boolean cancel() {
        return this.val$future.cancel(true);
    }
}
