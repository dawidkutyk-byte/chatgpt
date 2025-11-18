/*
 * Decompiled with CFR 0.152.
 */
package org.java_websocket.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactory
implements ThreadFactory {
    private final AtomicInteger threadNumber;
    private final ThreadFactory defaultThreadFactory = Executors.defaultThreadFactory();
    private final String threadPrefix;

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = this.defaultThreadFactory.newThread(runnable);
        thread.setName(this.threadPrefix + "-" + this.threadNumber);
        return thread;
    }

    public NamedThreadFactory(String threadPrefix) {
        this.threadNumber = new AtomicInteger(1);
        this.threadPrefix = threadPrefix;
    }
}
