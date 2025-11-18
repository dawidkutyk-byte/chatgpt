/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpResponse
 *  org.apache.http.client.HttpClient
 *  org.apache.http.client.methods.CloseableHttpResponse
 *  org.apache.http.util.EntityUtils
 */
package org.apache.http.client.utils;

import java.io.Closeable;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

public class HttpClientUtils {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void closeQuietly(CloseableHttpResponse response) {
        if (response == null) return;
        try {
            try {
                EntityUtils.consume((HttpEntity)response.getEntity());
            }
            finally {
                response.close();
            }
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    public static void closeQuietly(HttpClient httpClient) {
        if (httpClient == null) return;
        if (!(httpClient instanceof Closeable)) return;
        try {
            ((Closeable)httpClient).close();
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    private HttpClientUtils() {
    }

    public static void closeQuietly(HttpResponse response) {
        if (response == null) return;
        HttpEntity entity = response.getEntity();
        if (entity == null) return;
        try {
            EntityUtils.consume((HttpEntity)entity);
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }
}
