/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.conn.HttpClientConnectionManager
 *  org.apache.http.impl.client.CloseableHttpClient
 *  org.apache.http.impl.client.HttpClientBuilder
 *  org.apache.http.impl.client.MinimalHttpClient
 *  org.apache.http.impl.conn.PoolingHttpClientConnectionManager
 */
package org.apache.http.impl.client;

import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.MinimalHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class HttpClients {
    public static CloseableHttpClient createMinimal(HttpClientConnectionManager connManager) {
        return new MinimalHttpClient(connManager);
    }

    private HttpClients() {
    }

    public static CloseableHttpClient createDefault() {
        return HttpClientBuilder.create().build();
    }

    public static HttpClientBuilder custom() {
        return HttpClientBuilder.create();
    }

    public static CloseableHttpClient createMinimal() {
        return new MinimalHttpClient((HttpClientConnectionManager)new PoolingHttpClientConnectionManager());
    }

    public static CloseableHttpClient createSystem() {
        return HttpClientBuilder.create().useSystemProperties().build();
    }
}
