/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.conn.params.ConnPerRoute
 *  org.apache.http.conn.routing.HttpRoute
 */
package org.apache.http.impl.conn.tsccm;

import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.routing.HttpRoute;

class RouteSpecificPool.1
implements ConnPerRoute {
    RouteSpecificPool.1() {
    }

    public int getMaxForRoute(HttpRoute unused) {
        return RouteSpecificPool.this.maxEntries;
    }
}
