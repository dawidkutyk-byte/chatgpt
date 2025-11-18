/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.conn.HttpClientConnectionManager
 *  org.apache.http.impl.client.IdleConnectionEvictor$DefaultThreadFactory
 *  org.apache.http.util.Args
 */
package org.apache.http.impl.client;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.IdleConnectionEvictor;
import org.apache.http.util.Args;

public final class IdleConnectionEvictor {
    private final ThreadFactory threadFactory;
    private final HttpClientConnectionManager connectionManager;
    private final Thread thread;
    private volatile Exception exception;
    private final long sleepTimeMs;
    private final long maxIdleTimeMs;

    public void awaitTermination(long time, TimeUnit timeUnit) throws InterruptedException {
        this.thread.join((timeUnit != null ? timeUnit : TimeUnit.MILLISECONDS).toMillis(time));
    }

    static /* synthetic */ long access$000(IdleConnectionEvictor x0) {
        return x0.sleepTimeMs;
    }

    public boolean isRunning() {
        return this.thread.isAlive();
    }

    public void start() {
        this.thread.start();
    }

    static /* synthetic */ long access$100(IdleConnectionEvictor x0) {
        return x0.maxIdleTimeMs;
    }

    static /* synthetic */ Exception access$202(IdleConnectionEvictor x0, Exception x1) {
        x0.exception = x1;
        return x0.exception;
    }

    public IdleConnectionEvictor(HttpClientConnectionManager connectionManager, long sleepTime, TimeUnit sleepTimeUnit, long maxIdleTime, TimeUnit maxIdleTimeUnit) {
        this(connectionManager, null, sleepTime, sleepTimeUnit, maxIdleTime, maxIdleTimeUnit);
    }

    public void shutdown() {
        this.thread.interrupt();
    }

    public IdleConnectionEvictor(HttpClientConnectionManager connectionManager, long maxIdleTime, TimeUnit maxIdleTimeUnit) {
        this(connectionManager, null, maxIdleTime > 0L ? maxIdleTime : 5L, maxIdleTimeUnit != null ? maxIdleTimeUnit : TimeUnit.SECONDS, maxIdleTime, maxIdleTimeUnit);
    }

    public IdleConnectionEvictor(HttpClientConnectionManager connectionManager, ThreadFactory threadFactory, long sleepTime, TimeUnit sleepTimeUnit, long maxIdleTime, TimeUnit maxIdleTimeUnit) {
        this.connectionManager = (HttpClientConnectionManager)Args.notNull((Object)connectionManager, (String)"Connection manager");
        this.threadFactory = threadFactory != null ? threadFactory : new DefaultThreadFactory();
        this.sleepTimeMs = sleepTimeUnit != null ? sleepTimeUnit.toMillis(sleepTime) : sleepTime;
        this.maxIdleTimeMs = maxIdleTimeUnit != null ? maxIdleTimeUnit.toMillis(maxIdleTime) : maxIdleTime;
        this.thread = this.threadFactory.newThread((Runnable)new /* Unavailable Anonymous Inner Class!! */);
    }
}
