/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.impl.client.Clock
 */
package org.apache.http.impl.client;

import org.apache.http.impl.client.Clock;

class SystemClock
implements Clock {
    SystemClock() {
    }

    public long getCurrentTime() {
        return System.currentTimeMillis();
    }
}
