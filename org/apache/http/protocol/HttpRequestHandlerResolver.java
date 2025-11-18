/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.protocol.HttpRequestHandler
 */
package org.apache.http.protocol;

import org.apache.http.protocol.HttpRequestHandler;

@Deprecated
public interface HttpRequestHandlerResolver {
    public HttpRequestHandler lookup(String var1);
}
