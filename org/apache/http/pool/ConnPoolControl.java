/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.pool.PoolStats
 */
package org.apache.http.pool;

import org.apache.http.pool.PoolStats;

public interface ConnPoolControl<T> {
    public PoolStats getTotalStats();

    public void setMaxTotal(int var1);

    public int getMaxTotal();

    public int getDefaultMaxPerRoute();

    public int getMaxPerRoute(T var1);

    public void setMaxPerRoute(T var1, int var2);

    public void setDefaultMaxPerRoute(int var1);

    public PoolStats getStats(T var1);
}
