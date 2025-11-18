/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpResponse
 *  org.apache.http.client.ConnectionBackoffStrategy
 */
package org.apache.http.impl.client;

import org.apache.http.HttpResponse;
import org.apache.http.client.ConnectionBackoffStrategy;

public class NullBackoffStrategy
implements ConnectionBackoffStrategy {
    public boolean shouldBackoff(HttpResponse resp) {
        return false;
    }

    public boolean shouldBackoff(Throwable t) {
        return false;
    }
}
