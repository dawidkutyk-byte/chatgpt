/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.client.methods.HttpEntityEnclosingRequestBase
 */
package org.apache.http.client.methods;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

static class RequestBuilder.InternalEntityEclosingRequest
extends HttpEntityEnclosingRequestBase {
    private final String method;

    public String getMethod() {
        return this.method;
    }

    RequestBuilder.InternalEntityEclosingRequest(String method) {
        this.method = method;
    }
}
