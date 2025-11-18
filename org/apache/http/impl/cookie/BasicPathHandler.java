/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.cookie.CommonCookieAttributeHandler
 *  org.apache.http.cookie.Cookie
 *  org.apache.http.cookie.CookieOrigin
 *  org.apache.http.cookie.MalformedCookieException
 *  org.apache.http.cookie.SetCookie
 *  org.apache.http.util.Args
 *  org.apache.http.util.TextUtils
 */
package org.apache.http.impl.cookie;

import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.cookie.CommonCookieAttributeHandler;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;
import org.apache.http.util.Args;
import org.apache.http.util.TextUtils;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class BasicPathHandler
implements CommonCookieAttributeHandler {
    public void parse(SetCookie cookie, String value) throws MalformedCookieException {
        Args.notNull((Object)cookie, (String)"Cookie");
        cookie.setPath(!TextUtils.isBlank((CharSequence)value) ? value : "/");
    }

    public String getAttributeName() {
        return "path";
    }

    static boolean pathMatch(String uriPath, String cookiePath) {
        String normalizedCookiePath = cookiePath;
        if (normalizedCookiePath == null) {
            normalizedCookiePath = "/";
        }
        if (normalizedCookiePath.length() > 1 && normalizedCookiePath.endsWith("/")) {
            normalizedCookiePath = normalizedCookiePath.substring(0, normalizedCookiePath.length() - 1);
        }
        if (!uriPath.startsWith(normalizedCookiePath)) return false;
        if (normalizedCookiePath.equals("/")) {
            return true;
        }
        if (uriPath.length() == normalizedCookiePath.length()) {
            return true;
        }
        if (uriPath.charAt(normalizedCookiePath.length()) != '/') return false;
        return true;
    }

    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
    }

    public boolean match(Cookie cookie, CookieOrigin origin) {
        Args.notNull((Object)cookie, (String)"Cookie");
        Args.notNull((Object)origin, (String)"Cookie origin");
        return BasicPathHandler.pathMatch(origin.getPath(), cookie.getPath());
    }
}
