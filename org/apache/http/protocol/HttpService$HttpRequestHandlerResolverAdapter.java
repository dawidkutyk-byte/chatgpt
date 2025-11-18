/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpRequest
 *  org.apache.http.protocol.HttpRequestHandler
 *  org.apache.http.protocol.HttpRequestHandlerMapper
 *  org.apache.http.protocol.HttpRequestHandlerResolver
 */
package org.apache.http.protocol;

import org.apache.http.HttpRequest;
import org.apache.http.protocol.HttpRequestHandler;
import org.apache.http.protocol.HttpRequestHandlerMapper;
import org.apache.http.protocol.HttpRequestHandlerResolver;

@Deprecated
private static class HttpService.HttpRequestHandlerResolverAdapter
implements HttpRequestHandlerMapper {
    private final HttpRequestHandlerResolver resolver;

    public HttpService.HttpRequestHandlerResolverAdapter(HttpRequestHandlerResolver resolver) {
        this.resolver = resolver;
    }

    public HttpRequestHandler lookup(HttpRequest request) {
        return this.resolver.lookup(request.getRequestLine().getUri());
    }
}
