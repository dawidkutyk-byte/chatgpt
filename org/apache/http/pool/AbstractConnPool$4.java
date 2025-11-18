/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.pool.PoolEntry
 *  org.apache.http.pool.PoolEntryCallback
 */
package org.apache.http.pool;

import org.apache.http.pool.PoolEntry;
import org.apache.http.pool.PoolEntryCallback;

class AbstractConnPool.4
implements PoolEntryCallback<T, C> {
    final /* synthetic */ long val$now;

    AbstractConnPool.4(long l) {
        this.val$now = l;
    }

    public void process(PoolEntry<T, C> entry) {
        if (!entry.isExpired(this.val$now)) return;
        entry.close();
    }
}
