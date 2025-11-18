/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpConnectionMetrics
 */
package org.apache.http;

import java.io.Closeable;
import java.io.IOException;
import org.apache.http.HttpConnectionMetrics;

public interface HttpConnection
extends Closeable {
    public void setSocketTimeout(int var1);

    public boolean isStale();

    public HttpConnectionMetrics getMetrics();

    public void shutdown() throws IOException;

    @Override
    public void close() throws IOException;

    public int getSocketTimeout();

    public boolean isOpen();
}
