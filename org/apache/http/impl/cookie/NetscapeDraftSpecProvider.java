/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.Obsolete
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.cookie.CookieSpec
 *  org.apache.http.cookie.CookieSpecProvider
 *  org.apache.http.impl.cookie.NetscapeDraftSpec
 *  org.apache.http.protocol.HttpContext
 */
package org.apache.http.impl.cookie;

import org.apache.http.annotation.Contract;
import org.apache.http.annotation.Obsolete;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.cookie.NetscapeDraftSpec;
import org.apache.http.protocol.HttpContext;

@Obsolete
@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class NetscapeDraftSpecProvider
implements CookieSpecProvider {
    private volatile CookieSpec cookieSpec;
    private final String[] datepatterns;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public CookieSpec create(HttpContext context) {
        if (this.cookieSpec != null) return this.cookieSpec;
        NetscapeDraftSpecProvider netscapeDraftSpecProvider = this;
        synchronized (netscapeDraftSpecProvider) {
            if (this.cookieSpec != null) return this.cookieSpec;
            this.cookieSpec = new NetscapeDraftSpec(this.datepatterns);
        }
        return this.cookieSpec;
    }

    public NetscapeDraftSpecProvider() {
        this(null);
    }

    public NetscapeDraftSpecProvider(String[] datepatterns) {
        this.datepatterns = datepatterns;
    }
}
