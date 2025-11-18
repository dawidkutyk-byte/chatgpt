/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.conn.routing.HttpRoute
 *  org.apache.http.impl.client.RequestWrapper
 */
package org.apache.http.impl.client;

import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.RequestWrapper;

@Deprecated
public class RoutedRequest {
    protected final HttpRoute route;
    protected final RequestWrapper request;

    public final RequestWrapper getRequest() {
        return this.request;
    }

    public final HttpRoute getRoute() {
        return this.route;
    }

    public RoutedRequest(RequestWrapper req, HttpRoute route) {
        this.request = req;
        this.route = route;
    }
}
