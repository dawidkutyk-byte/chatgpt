/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http.impl.client;

import java.util.concurrent.ThreadFactory;

static class IdleConnectionEvictor.DefaultThreadFactory
implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "Connection evictor");
        t.setDaemon(true);
        return t;
    }

    IdleConnectionEvictor.DefaultThreadFactory() {
    }
}
