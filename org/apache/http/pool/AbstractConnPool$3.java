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

class AbstractConnPool.3
implements PoolEntryCallback<T, C> {
    final /* synthetic */ long val$deadline;

    public void process(PoolEntry<T, C> entry) {
        if (entry.getUpdated() > this.val$deadline) return;
        entry.close();
    }

    AbstractConnPool.3(long l) {
        this.val$deadline = l;
    }
}
