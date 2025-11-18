/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.conn.HttpClientConnectionManager
 *  org.apache.http.impl.client.IdleConnectionEvictor
 */
package org.apache.http.impl.client;

import java.util.concurrent.TimeUnit;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.IdleConnectionEvictor;

/*
 * Exception performing whole class analysis ignored.
 */
class IdleConnectionEvictor.1
implements Runnable {
    final /* synthetic */ HttpClientConnectionManager val$connectionManager;

    IdleConnectionEvictor.1(HttpClientConnectionManager httpClientConnectionManager) {
        this.val$connectionManager = httpClientConnectionManager;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(IdleConnectionEvictor.access$000((IdleConnectionEvictor)IdleConnectionEvictor.this));
                this.val$connectionManager.closeExpiredConnections();
                if (IdleConnectionEvictor.access$100((IdleConnectionEvictor)IdleConnectionEvictor.this) <= 0L) continue;
                this.val$connectionManager.closeIdleConnections(IdleConnectionEvictor.access$100((IdleConnectionEvictor)IdleConnectionEvictor.this), TimeUnit.MILLISECONDS);
            }
            return;
        }
        catch (Exception ex) {
            IdleConnectionEvictor.access$202((IdleConnectionEvictor)IdleConnectionEvictor.this, (Exception)ex);
        }
    }
}
