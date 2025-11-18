/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.client.methods.HttpRequestBase
 */
package org.apache.http.client.methods;

import org.apache.http.client.methods.HttpRequestBase;

static class RequestBuilder.InternalRequest
extends HttpRequestBase {
    private final String method;

    RequestBuilder.InternalRequest(String method) {
        this.method = method;
    }

    public String getMethod() {
        return this.method;
    }
}
