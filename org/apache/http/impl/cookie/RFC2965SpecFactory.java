/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.cookie.CookieSpec
 *  org.apache.http.cookie.CookieSpecFactory
 *  org.apache.http.cookie.CookieSpecProvider
 *  org.apache.http.impl.cookie.RFC2965Spec
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
import org.apache.http.impl.cookie.RFC2965Spec;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

@Deprecated
@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class RFC2965SpecFactory
implements CookieSpecProvider,
CookieSpecFactory {
    private final CookieSpec cookieSpec;

    public RFC2965SpecFactory(String[] datepatterns, boolean oneHeader) {
        this.cookieSpec = new RFC2965Spec(datepatterns, oneHeader);
    }

    public CookieSpec create(HttpContext context) {
        return this.cookieSpec;
    }

    public RFC2965SpecFactory() {
        this(null, false);
    }

    public CookieSpec newInstance(HttpParams params) {
        if (params == null) return new RFC2965Spec();
        String[] patterns = null;
        Collection param = (Collection)params.getParameter("http.protocol.cookie-datepatterns");
        if (param != null) {
            patterns = new String[param.size()];
            patterns = param.toArray(patterns);
        }
        boolean singleHeader = params.getBooleanParameter("http.protocol.single-cookie-header", false);
        return new RFC2965Spec(patterns, singleHeader);
    }
}
