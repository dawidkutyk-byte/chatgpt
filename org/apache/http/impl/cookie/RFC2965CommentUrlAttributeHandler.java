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
 *  org.apache.http.cookie.SetCookie2
 */
package org.apache.http.impl.cookie;

import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.cookie.CommonCookieAttributeHandler;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;
import org.apache.http.cookie.SetCookie2;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class RFC2965CommentUrlAttributeHandler
implements CommonCookieAttributeHandler {
    public boolean match(Cookie cookie, CookieOrigin origin) {
        return true;
    }

    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
    }

    public void parse(SetCookie cookie, String commenturl) throws MalformedCookieException {
        if (!(cookie instanceof SetCookie2)) return;
        SetCookie2 cookie2 = (SetCookie2)cookie;
        cookie2.setCommentURL(commenturl);
    }

    public String getAttributeName() {
        return "commenturl";
    }
}
