/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.config.ConnectionConfig
 *  org.apache.http.conn.HttpConnectionFactory
 *  org.apache.http.conn.ManagedHttpClientConnection
 *  org.apache.http.conn.routing.HttpRoute
 *  org.apache.http.impl.conn.ManagedHttpClientConnectionFactory
 *  org.apache.http.impl.conn.PoolingHttpClientConnectionManager$ConfigData
 *  org.apache.http.pool.ConnFactory
 */
package org.apache.http.impl.conn;

import java.io.IOException;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.pool.ConnFactory;

static class PoolingHttpClientConnectionManager.InternalConnectionFactory
implements ConnFactory<HttpRoute, ManagedHttpClientConnection> {
    private final PoolingHttpClientConnectionManager.ConfigData configData;
    private final HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory;

    PoolingHttpClientConnectionManager.InternalConnectionFactory(PoolingHttpClientConnectionManager.ConfigData configData, HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory) {
        this.configData = configData != null ? configData : new PoolingHttpClientConnectionManager.ConfigData();
        this.connFactory = connFactory != null ? connFactory : ManagedHttpClientConnectionFactory.INSTANCE;
    }

    public ManagedHttpClientConnection create(HttpRoute route) throws IOException {
        ConnectionConfig config = null;
        if (route.getProxyHost() != null) {
            config = this.configData.getConnectionConfig(route.getProxyHost());
        }
        if (config == null) {
            config = this.configData.getConnectionConfig(route.getTargetHost());
        }
        if (config == null) {
            config = this.configData.getDefaultConnectionConfig();
        }
        if (config != null) return (ManagedHttpClientConnection)this.connFactory.create((Object)route, config);
        config = ConnectionConfig.DEFAULT;
        return (ManagedHttpClientConnection)this.connFactory.create((Object)route, config);
    }
}
