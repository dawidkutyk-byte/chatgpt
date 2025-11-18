/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.conn.ClientConnectionRequest
 *  org.apache.http.conn.ConnectionPoolTimeoutException
 *  org.apache.http.conn.ManagedClientConnection
 *  org.apache.http.conn.routing.HttpRoute
 *  org.apache.http.impl.conn.AbstractPoolEntry
 *  org.apache.http.impl.conn.tsccm.BasicPoolEntry
 *  org.apache.http.impl.conn.tsccm.BasicPooledConnAdapter
 *  org.apache.http.impl.conn.tsccm.PoolEntryRequest
 *  org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager
 *  org.apache.http.util.Args
 */
package org.apache.http.impl.conn.tsccm;

import java.util.concurrent.TimeUnit;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.conn.AbstractPoolEntry;
import org.apache.http.impl.conn.tsccm.BasicPoolEntry;
import org.apache.http.impl.conn.tsccm.BasicPooledConnAdapter;
import org.apache.http.impl.conn.tsccm.PoolEntryRequest;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.util.Args;

/*
 * Exception performing whole class analysis ignored.
 */
class ThreadSafeClientConnManager.1
implements ClientConnectionRequest {
    final /* synthetic */ PoolEntryRequest val$poolRequest;
    final /* synthetic */ HttpRoute val$route;

    public ManagedClientConnection getConnection(long timeout, TimeUnit timeUnit) throws ConnectionPoolTimeoutException, InterruptedException {
        Args.notNull((Object)this.val$route, (String)"Route");
        if (ThreadSafeClientConnManager.access$000((ThreadSafeClientConnManager)ThreadSafeClientConnManager.this).isDebugEnabled()) {
            ThreadSafeClientConnManager.access$000((ThreadSafeClientConnManager)ThreadSafeClientConnManager.this).debug((Object)("Get connection: " + this.val$route + ", timeout = " + timeout));
        }
        BasicPoolEntry entry = this.val$poolRequest.getPoolEntry(timeout, timeUnit);
        return new BasicPooledConnAdapter(ThreadSafeClientConnManager.this, (AbstractPoolEntry)entry);
    }

    public void abortRequest() {
        this.val$poolRequest.abortRequest();
    }

    ThreadSafeClientConnManager.1(PoolEntryRequest poolEntryRequest, HttpRoute httpRoute) {
        this.val$poolRequest = poolEntryRequest;
        this.val$route = httpRoute;
    }
}
