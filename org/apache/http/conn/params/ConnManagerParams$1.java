/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.conn.params.ConnPerRoute
 *  org.apache.http.conn.routing.HttpRoute
 */
package org.apache.http.conn.params;

import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.routing.HttpRoute;

static final class ConnManagerParams.1
implements ConnPerRoute {
    ConnManagerParams.1() {
    }

    public int getMaxForRoute(HttpRoute route) {
        return 2;
    }
}
