/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpHost
 *  org.apache.http.config.ConnectionConfig
 *  org.apache.http.config.SocketConfig
 */
package org.apache.http.impl.conn;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.HttpHost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.SocketConfig;

static class PoolingHttpClientConnectionManager.ConfigData {
    private final Map<HttpHost, SocketConfig> socketConfigMap = new ConcurrentHashMap<HttpHost, SocketConfig>();
    private volatile SocketConfig defaultSocketConfig;
    private final Map<HttpHost, ConnectionConfig> connectionConfigMap = new ConcurrentHashMap<HttpHost, ConnectionConfig>();
    private volatile ConnectionConfig defaultConnectionConfig;

    public void setSocketConfig(HttpHost host, SocketConfig socketConfig) {
        this.socketConfigMap.put(host, socketConfig);
    }

    public SocketConfig getDefaultSocketConfig() {
        return this.defaultSocketConfig;
    }

    PoolingHttpClientConnectionManager.ConfigData() {
    }

    public ConnectionConfig getDefaultConnectionConfig() {
        return this.defaultConnectionConfig;
    }

    public SocketConfig getSocketConfig(HttpHost host) {
        return this.socketConfigMap.get(host);
    }

    public void setDefaultConnectionConfig(ConnectionConfig defaultConnectionConfig) {
        this.defaultConnectionConfig = defaultConnectionConfig;
    }

    public ConnectionConfig getConnectionConfig(HttpHost host) {
        return this.connectionConfigMap.get(host);
    }

    public void setDefaultSocketConfig(SocketConfig defaultSocketConfig) {
        this.defaultSocketConfig = defaultSocketConfig;
    }

    public void setConnectionConfig(HttpHost host, ConnectionConfig connectionConfig) {
        this.connectionConfigMap.put(host, connectionConfig);
    }
}
