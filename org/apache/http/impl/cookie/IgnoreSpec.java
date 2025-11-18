/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Header
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.cookie.Cookie
 *  org.apache.http.cookie.CookieOrigin
 *  org.apache.http.cookie.MalformedCookieException
 *  org.apache.http.impl.cookie.CookieSpecBase
 */
package org.apache.http.impl.cookie;

import java.util.Collections;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.impl.cookie.CookieSpecBase;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class IgnoreSpec
extends CookieSpecBase {
    public List<Header> formatCookies(List<Cookie> cookies) {
        return Collections.emptyList();
    }

    public boolean match(Cookie cookie, CookieOrigin origin) {
        return false;
    }

    public int getVersion() {
        return 0;
    }

    public List<Cookie> parse(Header header, CookieOrigin origin) throws MalformedCookieException {
        return Collections.emptyList();
    }

    public Header getVersionHeader() {
        return null;
    }
}
