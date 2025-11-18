/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.cookie.CookieSpec
 *  org.apache.http.cookie.CookieSpecProvider
 *  org.apache.http.impl.cookie.IgnoreSpec
 *  org.apache.http.protocol.HttpContext
 */
package org.apache.http.impl.cookie;

import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.cookie.IgnoreSpec;
import org.apache.http.protocol.HttpContext;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class IgnoreSpecProvider
implements CookieSpecProvider {
    private volatile CookieSpec cookieSpec;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public CookieSpec create(HttpContext context) {
        if (this.cookieSpec != null) return this.cookieSpec;
        IgnoreSpecProvider ignoreSpecProvider = this;
        synchronized (ignoreSpecProvider) {
            if (this.cookieSpec != null) return this.cookieSpec;
            this.cookieSpec = new IgnoreSpec();
        }
        return this.cookieSpec;
    }
}
