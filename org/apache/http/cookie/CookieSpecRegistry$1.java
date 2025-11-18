/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpRequest
 *  org.apache.http.cookie.CookieSpec
 *  org.apache.http.cookie.CookieSpecProvider
 *  org.apache.http.protocol.HttpContext
 */
package org.apache.http.cookie;

import org.apache.http.HttpRequest;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.protocol.HttpContext;

class CookieSpecRegistry.1
implements CookieSpecProvider {
    final /* synthetic */ String val$name;

    public CookieSpec create(HttpContext context) {
        HttpRequest request = (HttpRequest)context.getAttribute("http.request");
        return CookieSpecRegistry.this.getCookieSpec(this.val$name, request.getParams());
    }

    CookieSpecRegistry.1(String string) {
        this.val$name = string;
    }
}
