/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.Connection$Request
 *  org.jsoup.helper.HttpConnection$Request
 *  org.jsoup.internal.StringUtil
 */
package org.jsoup.helper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;
import org.jsoup.internal.StringUtil;

class CookieUtil {
    private static final Map<String, List<String>> EmptyRequestHeaders = Collections.unmodifiableMap(new HashMap());
    private static final String CookieName = "Cookie";
    private static final String Sep = "; ";
    private static final String Cookie2Name = "Cookie2";

    static void applyCookiesToRequest(HttpConnection.Request req, HttpURLConnection con) throws IOException {
        LinkedHashSet<String> cookieSet = CookieUtil.requestCookieSet((Connection.Request)req);
        HashSet cookies2 = null;
        Map<String, List<String>> storedCookies = req.cookieManager().get(CookieUtil.asUri(req.url), EmptyRequestHeaders);
        for (Map.Entry<String, List<String>> entry : storedCookies.entrySet()) {
            HashSet set;
            List<String> cookies = entry.getValue();
            if (cookies == null || cookies.size() == 0) continue;
            String key = entry.getKey();
            if (CookieName.equals(key)) {
                set = cookieSet;
            } else {
                if (!Cookie2Name.equals(key)) continue;
                cookies2 = set = new HashSet();
            }
            set.addAll(cookies);
        }
        if (cookieSet.size() > 0) {
            con.addRequestProperty(CookieName, StringUtil.join(cookieSet, (String)Sep));
        }
        if (cookies2 == null) return;
        if (cookies2.size() <= 0) return;
        con.addRequestProperty(Cookie2Name, StringUtil.join((Collection)cookies2, (String)Sep));
    }

    CookieUtil() {
    }

    static void storeCookies(HttpConnection.Request req, URL url, Map<String, List<String>> resHeaders) throws IOException {
        req.cookieManager().put(CookieUtil.asUri(url), resHeaders);
    }

    private static LinkedHashSet<String> requestCookieSet(Connection.Request req) {
        LinkedHashSet<String> set = new LinkedHashSet<String>();
        Iterator iterator = req.cookies().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry cookie = iterator.next();
            set.add((String)cookie.getKey() + "=" + (String)cookie.getValue());
        }
        return set;
    }

    static URI asUri(URL url) throws IOException {
        try {
            return url.toURI();
        }
        catch (URISyntaxException e) {
            MalformedURLException ue = new MalformedURLException(e.getMessage());
            ue.initCause(e);
            throw ue;
        }
    }
}
