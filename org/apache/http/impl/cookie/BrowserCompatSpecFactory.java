/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.cookie.CookieSpec
 *  org.apache.http.cookie.CookieSpecFactory
 *  org.apache.http.cookie.CookieSpecProvider
 *  org.apache.http.impl.cookie.BrowserCompatSpec
 *  org.apache.http.impl.cookie.BrowserCompatSpecFactory$SecurityLevel
 *  org.apache.http.params.HttpParams
 *  org.apache.http.protocol.HttpContext
 */
package org.apache.http.impl.cookie;

import java.util.Collection;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

@Deprecated
@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class BrowserCompatSpecFactory
implements CookieSpecFactory,
CookieSpecProvider {
    private final SecurityLevel securityLevel;
    private final CookieSpec cookieSpec;

    public CookieSpec newInstance(HttpParams params) {
        if (params == null) return new BrowserCompatSpec(null, this.securityLevel);
        String[] patterns = null;
        Collection param = (Collection)params.getParameter("http.protocol.cookie-datepatterns");
        if (param == null) return new BrowserCompatSpec(patterns, this.securityLevel);
        patterns = new String[param.size()];
        patterns = param.toArray(patterns);
        return new BrowserCompatSpec(patterns, this.securityLevel);
    }

    public BrowserCompatSpecFactory(String[] datepatterns, SecurityLevel securityLevel) {
        this.securityLevel = securityLevel;
        this.cookieSpec = new BrowserCompatSpec(datepatterns, securityLevel);
    }

    public BrowserCompatSpecFactory(String[] datepatterns) {
        this(null, SecurityLevel.SECURITYLEVEL_DEFAULT);
    }

    public CookieSpec create(HttpContext context) {
        return this.cookieSpec;
    }

    public BrowserCompatSpecFactory() {
        this(null, SecurityLevel.SECURITYLEVEL_DEFAULT);
    }
}
