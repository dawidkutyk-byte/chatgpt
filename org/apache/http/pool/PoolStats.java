/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 */
package org.apache.http.pool;

import java.io.Serializable;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class PoolStats
implements Serializable {
    private final int leased;
    private final int max;
    private final int pending;
    private final int available;
    private static final long serialVersionUID = -2807686144795228544L;

    public int getLeased() {
        return this.leased;
    }

    public int getMax() {
        return this.max;
    }

    public int getAvailable() {
        return this.available;
    }

    public int getPending() {
        return this.pending;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[leased: ");
        buffer.append(this.leased);
        buffer.append("; pending: ");
        buffer.append(this.pending);
        buffer.append("; available: ");
        buffer.append(this.available);
        buffer.append("; max: ");
        buffer.append(this.max);
        buffer.append("]");
        return buffer.toString();
    }

    public PoolStats(int leased, int pending, int free, int max) {
        this.leased = leased;
        this.pending = pending;
        this.available = free;
        this.max = max;
    }
}
