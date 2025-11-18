/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.cookie.Cookie
 *  org.apache.http.cookie.CookieOrigin
 *  org.apache.http.cookie.CookieRestrictionViolationException
 *  org.apache.http.cookie.MalformedCookieException
 *  org.apache.http.impl.cookie.BasicPathHandler
 */
package org.apache.http.impl.cookie;

import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieRestrictionViolationException;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.impl.cookie.BasicPathHandler;

class RFC2965Spec.1
extends BasicPathHandler {
    RFC2965Spec.1() {
    }

    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
        if (this.match(cookie, origin)) return;
        throw new CookieRestrictionViolationException("Illegal 'path' attribute \"" + cookie.getPath() + "\". Path of origin: \"" + origin.getPath() + "\"");
    }
}
