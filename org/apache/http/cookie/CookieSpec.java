/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Header
 *  org.apache.http.annotation.Obsolete
 *  org.apache.http.cookie.Cookie
 *  org.apache.http.cookie.CookieOrigin
 *  org.apache.http.cookie.MalformedCookieException
 */
package org.apache.http.cookie;

import java.util.List;
import org.apache.http.Header;
import org.apache.http.annotation.Obsolete;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.MalformedCookieException;

public interface CookieSpec {
    public void validate(Cookie var1, CookieOrigin var2) throws MalformedCookieException;

    public List<Header> formatCookies(List<Cookie> var1);

    public boolean match(Cookie var1, CookieOrigin var2);

    public List<Cookie> parse(Header var1, CookieOrigin var2) throws MalformedCookieException;

    @Obsolete
    public Header getVersionHeader();

    @Obsolete
    public int getVersion();
}
