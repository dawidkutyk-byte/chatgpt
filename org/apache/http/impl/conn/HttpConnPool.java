/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.logging.Log
 *  org.apache.http.conn.ClientConnectionOperator
 *  org.apache.http.conn.OperatedClientConnection
 *  org.apache.http.conn.routing.HttpRoute
 *  org.apache.http.impl.conn.HttpConnPool$InternalConnFactory
 *  org.apache.http.impl.conn.HttpPoolEntry
 *  org.apache.http.pool.AbstractConnPool
 *  org.apache.http.pool.ConnFactory
 */
package org.apache.http.impl.conn;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.commons.logging.Log;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.conn.HttpConnPool;
import org.apache.http.impl.conn.HttpPoolEntry;
import org.apache.http.pool.AbstractConnPool;
import org.apache.http.pool.ConnFactory;

@Deprecated
class HttpConnPool
extends AbstractConnPool<HttpRoute, OperatedClientConnection, HttpPoolEntry> {
    private final Log log;
    private final long timeToLive;
    private final TimeUnit timeUnit;
    private static final AtomicLong COUNTER = new AtomicLong();

    public HttpConnPool(Log log, ClientConnectionOperator connOperator, int defaultMaxPerRoute, int maxTotal, long timeToLive, TimeUnit timeUnit) {
        super((ConnFactory)new InternalConnFactory(connOperator), defaultMaxPerRoute, maxTotal);
        this.log = log;
        this.timeToLive = timeToLive;
        this.timeUnit = timeUnit;
    }

    protected HttpPoolEntry createEntry(HttpRoute route, OperatedClientConnection conn) {
        String id = Long.toString(COUNTER.getAndIncrement());
        return new HttpPoolEntry(this.log, id, route, conn, this.timeToLive, this.timeUnit);
    }
}
