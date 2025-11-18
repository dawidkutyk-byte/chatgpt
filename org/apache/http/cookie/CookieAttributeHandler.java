/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.cookie.Cookie
 *  org.apache.http.cookie.CookieOrigin
 *  org.apache.http.cookie.MalformedCookieException
 *  org.apache.http.cookie.SetCookie
 */
package org.apache.http.cookie;

import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;

public interface CookieAttributeHandler {
    public void parse(SetCookie var1, String var2) throws MalformedCookieException;

    public boolean match(Cookie var1, CookieOrigin var2);

    public void validate(Cookie var1, CookieOrigin var2) throws MalformedCookieException;
}
