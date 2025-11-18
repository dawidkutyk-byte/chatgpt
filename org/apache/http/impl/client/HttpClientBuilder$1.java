/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.impl.client.IdleConnectionEvictor
 */
package org.apache.http.impl.client;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.http.impl.client.IdleConnectionEvictor;

class HttpClientBuilder.1
implements Closeable {
    final /* synthetic */ IdleConnectionEvictor val$connectionEvictor;

    @Override
    public void close() throws IOException {
        this.val$connectionEvictor.shutdown();
        try {
            this.val$connectionEvictor.awaitTermination(1L, TimeUnit.SECONDS);
        }
        catch (InterruptedException interrupted) {
            Thread.currentThread().interrupt();
        }
    }

    HttpClientBuilder.1(IdleConnectionEvictor idleConnectionEvictor) {
        this.val$connectionEvictor = idleConnectionEvictor;
    }
}
