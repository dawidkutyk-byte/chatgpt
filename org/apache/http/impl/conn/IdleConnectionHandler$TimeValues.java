/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http.impl.conn;

import java.util.concurrent.TimeUnit;

private static class IdleConnectionHandler.TimeValues {
    private final long timeExpires;
    private final long timeAdded;

    static /* synthetic */ long access$100(IdleConnectionHandler.TimeValues x0) {
        return x0.timeAdded;
    }

    static /* synthetic */ long access$000(IdleConnectionHandler.TimeValues x0) {
        return x0.timeExpires;
    }

    IdleConnectionHandler.TimeValues(long now, long validDuration, TimeUnit validUnit) {
        this.timeAdded = now;
        this.timeExpires = validDuration > 0L ? now + validUnit.toMillis(validDuration) : Long.MAX_VALUE;
    }
}
