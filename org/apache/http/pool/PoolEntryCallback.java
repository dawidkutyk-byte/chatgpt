/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.pool.PoolEntry
 */
package org.apache.http.pool;

import org.apache.http.pool.PoolEntry;

public interface PoolEntryCallback<T, C> {
    public void process(PoolEntry<T, C> var1);
}
