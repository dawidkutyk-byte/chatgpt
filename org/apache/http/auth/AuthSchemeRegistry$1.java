/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpRequest
 *  org.apache.http.auth.AuthScheme
 *  org.apache.http.auth.AuthSchemeProvider
 *  org.apache.http.protocol.HttpContext
 */
package org.apache.http.auth;

import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthSchemeProvider;
import org.apache.http.protocol.HttpContext;

class AuthSchemeRegistry.1
implements AuthSchemeProvider {
    final /* synthetic */ String val$name;

    AuthSchemeRegistry.1(String string) {
        this.val$name = string;
    }

    public AuthScheme create(HttpContext context) {
        HttpRequest request = (HttpRequest)context.getAttribute("http.request");
        return AuthSchemeRegistry.this.getAuthScheme(this.val$name, request.getParams());
    }
}
