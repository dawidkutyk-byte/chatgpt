/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.cookie.Cookie
 */
package org.apache.http.client;

import java.util.Date;
import java.util.List;
import org.apache.http.cookie.Cookie;

public interface CookieStore {
    public void addCookie(Cookie var1);

    public boolean clearExpired(Date var1);

    public List<Cookie> getCookies();

    public void clear();
}
