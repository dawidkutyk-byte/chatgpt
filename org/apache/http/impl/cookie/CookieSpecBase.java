/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HeaderElement
 *  org.apache.http.NameValuePair
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.cookie.CommonCookieAttributeHandler
 *  org.apache.http.cookie.Cookie
 *  org.apache.http.cookie.CookieAttributeHandler
 *  org.apache.http.cookie.CookieOrigin
 *  org.apache.http.cookie.MalformedCookieException
 *  org.apache.http.cookie.SetCookie
 *  org.apache.http.impl.cookie.AbstractCookieSpec
 *  org.apache.http.impl.cookie.BasicClientCookie
 *  org.apache.http.util.Args
 */
package org.apache.http.impl.cookie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.cookie.CommonCookieAttributeHandler;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;
import org.apache.http.impl.cookie.AbstractCookieSpec;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.Args;

@Contract(threading=ThreadingBehavior.SAFE)
public abstract class CookieSpecBase
extends AbstractCookieSpec {
    protected CookieSpecBase(CommonCookieAttributeHandler ... handlers) {
        super(handlers);
    }

    protected List<Cookie> parse(HeaderElement[] elems, CookieOrigin origin) throws MalformedCookieException {
        ArrayList<Cookie> cookies = new ArrayList<Cookie>(elems.length);
        HeaderElement[] arr$ = elems;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            HeaderElement headerelement = arr$[i$];
            String name = headerelement.getName();
            String value = headerelement.getValue();
            if (name != null && !name.isEmpty()) {
                BasicClientCookie cookie = new BasicClientCookie(name, value);
                cookie.setPath(CookieSpecBase.getDefaultPath(origin));
                cookie.setDomain(CookieSpecBase.getDefaultDomain(origin));
                NameValuePair[] attribs = headerelement.getParameters();
                for (int j = attribs.length - 1; j >= 0; --j) {
                    NameValuePair attrib = attribs[j];
                    String s = attrib.getName().toLowerCase(Locale.ROOT);
                    cookie.setAttribute(s, attrib.getValue());
                    CookieAttributeHandler handler = this.findAttribHandler(s);
                    if (handler == null) continue;
                    handler.parse((SetCookie)cookie, attrib.getValue());
                }
                cookies.add((Cookie)cookie);
            }
            ++i$;
        }
        return cookies;
    }

    public boolean match(Cookie cookie, CookieOrigin origin) {
        CookieAttributeHandler handler;
        Args.notNull((Object)cookie, (String)"Cookie");
        Args.notNull((Object)origin, (String)"Cookie origin");
        Iterator i$ = this.getAttribHandlers().iterator();
        do {
            if (!i$.hasNext()) return true;
        } while ((handler = (CookieAttributeHandler)i$.next()).match(cookie, origin));
        return false;
    }

    protected static String getDefaultPath(CookieOrigin origin) {
        String defaultPath = origin.getPath();
        int lastSlashIndex = defaultPath.lastIndexOf(47);
        if (lastSlashIndex < 0) return defaultPath;
        if (lastSlashIndex == 0) {
            lastSlashIndex = 1;
        }
        defaultPath = defaultPath.substring(0, lastSlashIndex);
        return defaultPath;
    }

    protected CookieSpecBase(HashMap<String, CookieAttributeHandler> map) {
        super(map);
    }

    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
        Args.notNull((Object)cookie, (String)"Cookie");
        Args.notNull((Object)origin, (String)"Cookie origin");
        Iterator i$ = this.getAttribHandlers().iterator();
        while (i$.hasNext()) {
            CookieAttributeHandler handler = (CookieAttributeHandler)i$.next();
            handler.validate(cookie, origin);
        }
    }

    public CookieSpecBase() {
    }

    protected static String getDefaultDomain(CookieOrigin origin) {
        return origin.getHost();
    }
}
