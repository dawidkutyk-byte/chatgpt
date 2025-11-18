/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.pool.RouteSpecificPool
 */
package org.apache.http.pool;

import org.apache.http.pool.RouteSpecificPool;

class AbstractConnPool.1
extends RouteSpecificPool<T, C, E> {
    final /* synthetic */ Object val$route;

    protected E createEntry(C conn) {
        return AbstractConnPool.this.createEntry(this.val$route, conn);
    }

    AbstractConnPool.1(Object x0, Object object) {
        this.val$route = object;
        super(x0);
    }
}
