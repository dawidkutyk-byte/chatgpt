/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpClientConnection
 *  org.apache.http.HttpHost
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.config.ConnectionConfig
 *  org.apache.http.config.SocketConfig
 *  org.apache.http.impl.pool.BasicConnFactory
 *  org.apache.http.impl.pool.BasicPoolEntry
 *  org.apache.http.params.HttpParams
 *  org.apache.http.pool.AbstractConnPool
 *  org.apache.http.pool.ConnFactory
 */
package org.apache.http.impl.pool;

import java.util.concurrent.atomic.AtomicLong;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpHost;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.pool.BasicConnFactory;
import org.apache.http.impl.pool.BasicPoolEntry;
import org.apache.http.params.HttpParams;
import org.apache.http.pool.AbstractConnPool;
import org.apache.http.pool.ConnFactory;

@Contract(threading=ThreadingBehavior.SAFE_CONDITIONAL)
public class BasicConnPool
extends AbstractConnPool<HttpHost, HttpClientConnection, BasicPoolEntry> {
    private static final AtomicLong COUNTER = new AtomicLong();

    protected BasicPoolEntry createEntry(HttpHost host, HttpClientConnection conn) {
        return new BasicPoolEntry(Long.toString(COUNTER.getAndIncrement()), host, conn);
    }

    @Deprecated
    public BasicConnPool(HttpParams params) {
        super((ConnFactory)new BasicConnFactory(params), 2, 20);
    }

    public BasicConnPool(SocketConfig sconfig, ConnectionConfig cconfig) {
        super((ConnFactory)new BasicConnFactory(sconfig, cconfig), 2, 20);
    }

    public BasicConnPool(ConnFactory<HttpHost, HttpClientConnection> connFactory) {
        super(connFactory, 2, 20);
    }

    protected boolean validate(BasicPoolEntry entry) {
        return !((HttpClientConnection)entry.getConnection()).isStale();
    }

    public BasicConnPool() {
        super((ConnFactory)new BasicConnFactory(SocketConfig.DEFAULT, ConnectionConfig.DEFAULT), 2, 20);
    }
}
