/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.conn.ConnectionPoolTimeoutException
 *  org.apache.http.conn.routing.HttpRoute
 *  org.apache.http.impl.conn.tsccm.BasicPoolEntry
 *  org.apache.http.impl.conn.tsccm.ConnPoolByRoute
 *  org.apache.http.impl.conn.tsccm.PoolEntryRequest
 *  org.apache.http.impl.conn.tsccm.WaitingThreadAborter
 */
package org.apache.http.impl.conn.tsccm;

import java.util.concurrent.TimeUnit;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.conn.tsccm.BasicPoolEntry;
import org.apache.http.impl.conn.tsccm.ConnPoolByRoute;
import org.apache.http.impl.conn.tsccm.PoolEntryRequest;
import org.apache.http.impl.conn.tsccm.WaitingThreadAborter;

/*
 * Exception performing whole class analysis ignored.
 */
class ConnPoolByRoute.1
implements PoolEntryRequest {
    final /* synthetic */ Object val$state;
    final /* synthetic */ HttpRoute val$route;
    final /* synthetic */ WaitingThreadAborter val$aborter;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void abortRequest() {
        ConnPoolByRoute.access$000((ConnPoolByRoute)ConnPoolByRoute.this).lock();
        try {
            this.val$aborter.abort();
        }
        finally {
            ConnPoolByRoute.access$000((ConnPoolByRoute)ConnPoolByRoute.this).unlock();
        }
    }

    ConnPoolByRoute.1(WaitingThreadAborter waitingThreadAborter, HttpRoute httpRoute, Object object) {
        this.val$aborter = waitingThreadAborter;
        this.val$route = httpRoute;
        this.val$state = object;
    }

    public BasicPoolEntry getPoolEntry(long timeout, TimeUnit timeUnit) throws ConnectionPoolTimeoutException, InterruptedException {
        return ConnPoolByRoute.this.getEntryBlocking(this.val$route, this.val$state, timeout, timeUnit, this.val$aborter);
    }
}
