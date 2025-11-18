/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http.impl.client;

import java.util.concurrent.atomic.AtomicLong;

static class FutureRequestExecutionMetrics.DurationCounter {
    private final AtomicLong count = new AtomicLong(0L);
    private final AtomicLong cumulativeDuration = new AtomicLong(0L);

    FutureRequestExecutionMetrics.DurationCounter() {
    }

    public long averageDuration() {
        long counter = this.count.get();
        return counter > 0L ? this.cumulativeDuration.get() / counter : 0L;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[count=").append(this.count()).append(", averageDuration=").append(this.averageDuration()).append("]");
        return builder.toString();
    }

    public long count() {
        return this.count.get();
    }

    public void increment(long startTime) {
        this.count.incrementAndGet();
        this.cumulativeDuration.addAndGet(System.currentTimeMillis() - startTime);
    }
}
