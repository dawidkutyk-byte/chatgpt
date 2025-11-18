/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http;

public interface HttpConnectionMetrics {
    public long getReceivedBytesCount();

    public Object getMetric(String var1);

    public long getResponseCount();

    public void reset();

    public long getSentBytesCount();

    public long getRequestCount();
}
