/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.conn.util.PublicSuffixMatcher
 *  org.apache.http.cookie.Cookie
 *  org.apache.http.cookie.CookieAttributeHandler
 *  org.apache.http.cookie.CookieOrigin
 *  org.apache.http.cookie.MalformedCookieException
 *  org.apache.http.cookie.SetCookie
 */
package org.apache.http.impl.cookie;

import java.util.Collection;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;

@Deprecated
public class PublicSuffixFilter
implements CookieAttributeHandler {
    private Collection<String> suffixes;
    private PublicSuffixMatcher matcher;
    private final CookieAttributeHandler wrapped;
    private Collection<String> exceptions;

    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
        this.wrapped.validate(cookie, origin);
    }

    public PublicSuffixFilter(CookieAttributeHandler wrapped) {
        this.wrapped = wrapped;
    }

    public void parse(SetCookie cookie, String value) throws MalformedCookieException {
        this.wrapped.parse(cookie, value);
    }

    public void setExceptions(Collection<String> exceptions) {
        this.exceptions = exceptions;
        this.matcher = null;
    }

    public boolean match(Cookie cookie, CookieOrigin origin) {
        if (!this.isForPublicSuffix(cookie)) return this.wrapped.match(cookie, origin);
        return false;
    }

    public void setPublicSuffixes(Collection<String> suffixes) {
        this.suffixes = suffixes;
        this.matcher = null;
    }

    private boolean isForPublicSuffix(Cookie cookie) {
        if (this.matcher != null) return this.matcher.matches(cookie.getDomain());
        this.matcher = new PublicSuffixMatcher(this.suffixes, this.exceptions);
        return this.matcher.matches(cookie.getDomain());
    }
}
