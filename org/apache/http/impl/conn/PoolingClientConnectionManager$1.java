/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.conn.ClientConnectionRequest
 *  org.apache.http.conn.ConnectionPoolTimeoutException
 *  org.apache.http.conn.ManagedClientConnection
 */
package org.apache.http.impl.conn;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ManagedClientConnection;

class PoolingClientConnectionManager.1
implements ClientConnectionRequest {
    final /* synthetic */ Future val$future;

    public void abortRequest() {
        this.val$future.cancel(true);
    }

    public ManagedClientConnection getConnection(long timeout, TimeUnit timeUnit) throws ConnectionPoolTimeoutException, InterruptedException {
        return PoolingClientConnectionManager.this.leaseConnection(this.val$future, timeout, timeUnit);
    }

    PoolingClientConnectionManager.1(Future future) {
        this.val$future = future;
    }
}
