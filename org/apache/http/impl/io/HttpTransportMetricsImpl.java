/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.io.HttpTransportMetrics
 */
package org.apache.http.impl.io;

import org.apache.http.io.HttpTransportMetrics;

public class HttpTransportMetricsImpl
implements HttpTransportMetrics {
    private long bytesTransferred = 0L;

    public long getBytesTransferred() {
        return this.bytesTransferred;
    }

    public void reset() {
        this.bytesTransferred = 0L;
    }

    public void incrementBytesTransferred(long count) {
        this.bytesTransferred += count;
    }

    public void setBytesTransferred(long count) {
        this.bytesTransferred = count;
    }
}
