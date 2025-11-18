/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpRequest
 *  org.apache.http.protocol.HttpRequestHandler
 */
package org.apache.http.protocol;

import org.apache.http.HttpRequest;
import org.apache.http.protocol.HttpRequestHandler;

public interface HttpRequestHandlerMapper {
    public HttpRequestHandler lookup(HttpRequest var1);
}
