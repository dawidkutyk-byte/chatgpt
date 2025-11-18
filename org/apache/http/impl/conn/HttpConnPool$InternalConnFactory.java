/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.conn.ClientConnectionOperator
 *  org.apache.http.conn.OperatedClientConnection
 *  org.apache.http.conn.routing.HttpRoute
 *  org.apache.http.pool.ConnFactory
 */
package org.apache.http.impl.conn;

import java.io.IOException;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.pool.ConnFactory;

static class HttpConnPool.InternalConnFactory
implements ConnFactory<HttpRoute, OperatedClientConnection> {
    private final ClientConnectionOperator connOperator;

    public OperatedClientConnection create(HttpRoute route) throws IOException {
        return this.connOperator.createConnection();
    }

    HttpConnPool.InternalConnFactory(ClientConnectionOperator connOperator) {
        this.connOperator = connOperator;
    }
}
