/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.conn.HttpClientConnectionManager
 */
package org.apache.http.impl.client;

import java.io.Closeable;
import java.io.IOException;
import org.apache.http.conn.HttpClientConnectionManager;

class HttpClientBuilder.2
implements Closeable {
    final /* synthetic */ HttpClientConnectionManager val$cm;

    HttpClientBuilder.2(HttpClientConnectionManager httpClientConnectionManager) {
        this.val$cm = httpClientConnectionManager;
    }

    @Override
    public void close() throws IOException {
        this.val$cm.shutdown();
    }
}
