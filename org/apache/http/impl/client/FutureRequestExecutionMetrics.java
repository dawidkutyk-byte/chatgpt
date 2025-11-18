/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.impl.client.FutureRequestExecutionMetrics$DurationCounter
 */
package org.apache.http.impl.client;

import java.util.concurrent.atomic.AtomicLong;
import org.apache.http.impl.client.FutureRequestExecutionMetrics;

public final class FutureRequestExecutionMetrics {
    private final DurationCounter requests;
    private final DurationCounter successfulConnections;
    private final AtomicLong activeConnections = new AtomicLong();
    private final AtomicLong scheduledConnections = new AtomicLong();
    private final DurationCounter tasks;
    private final DurationCounter failedConnections;

    AtomicLong getActiveConnections() {
        return this.activeConnections;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[activeConnections=").append(this.activeConnections).append(", scheduledConnections=").append(this.scheduledConnections).append(", successfulConnections=").append(this.successfulConnections).append(", failedConnections=").append(this.failedConnections).append(", requests=").append(this.requests).append(", tasks=").append(this.tasks).append("]");
        return builder.toString();
    }

    public long getSuccessfulConnectionCount() {
        return this.successfulConnections.count();
    }

    AtomicLong getScheduledConnections() {
        return this.scheduledConnections;
    }

    public long getRequestCount() {
        return this.requests.count();
    }

    public long getTaskAverageDuration() {
        return this.tasks.averageDuration();
    }

    FutureRequestExecutionMetrics() {
        this.successfulConnections = new DurationCounter();
        this.failedConnections = new DurationCounter();
        this.requests = new DurationCounter();
        this.tasks = new DurationCounter();
    }

    DurationCounter getFailedConnections() {
        return this.failedConnections;
    }

    public long getActiveConnectionCount() {
        return this.activeConnections.get();
    }

    public long getRequestAverageDuration() {
        return this.requests.averageDuration();
    }

    DurationCounter getRequests() {
        return this.requests;
    }

    DurationCounter getTasks() {
        return this.tasks;
    }

    public long getFailedConnectionAverageDuration() {
        return this.failedConnections.averageDuration();
    }

    public long getScheduledConnectionCount() {
        return this.scheduledConnections.get();
    }

    public long getTaskCount() {
        return this.tasks.count();
    }

    public long getFailedConnectionCount() {
        return this.failedConnections.count();
    }

    DurationCounter getSuccessfulConnections() {
        return this.successfulConnections;
    }

    public long getSuccessfulConnectionAverageDuration() {
        return this.successfulConnections.averageDuration();
    }
}
