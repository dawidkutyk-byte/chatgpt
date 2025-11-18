/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.conn.util.InetAddressUtils
 *  org.apache.http.cookie.ClientCookie
 *  org.apache.http.cookie.CommonCookieAttributeHandler
 *  org.apache.http.cookie.Cookie
 *  org.apache.http.cookie.CookieOrigin
 *  org.apache.http.cookie.CookieRestrictionViolationException
 *  org.apache.http.cookie.MalformedCookieException
 *  org.apache.http.cookie.SetCookie
 *  org.apache.http.util.Args
 *  org.apache.http.util.TextUtils
 */
package org.apache.http.impl.cookie;

import java.util.Locale;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.conn.util.InetAddressUtils;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.CommonCookieAttributeHandler;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieRestrictionViolationException;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;
import org.apache.http.util.Args;
import org.apache.http.util.TextUtils;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class BasicDomainHandler
implements CommonCookieAttributeHandler {
    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
        Args.notNull((Object)cookie, (String)"Cookie");
        Args.notNull((Object)origin, (String)"Cookie origin");
        String host = origin.getHost();
        String domain = cookie.getDomain();
        if (domain == null) {
            throw new CookieRestrictionViolationException("Cookie 'domain' may not be null");
        }
        if (host.equals(domain)) return;
        if (BasicDomainHandler.domainMatch(domain, host)) return;
        throw new CookieRestrictionViolationException("Illegal 'domain' attribute \"" + domain + "\". Domain of origin: \"" + host + "\"");
    }

    public String getAttributeName() {
        return "domain";
    }

    public boolean match(Cookie cookie, CookieOrigin origin) {
        Args.notNull((Object)cookie, (String)"Cookie");
        Args.notNull((Object)origin, (String)"Cookie origin");
        String host = origin.getHost();
        String domain = cookie.getDomain();
        if (domain == null) {
            return false;
        }
        if (domain.startsWith(".")) {
            domain = domain.substring(1);
        }
        if (host.equals(domain = domain.toLowerCase(Locale.ROOT))) {
            return true;
        }
        if (!(cookie instanceof ClientCookie)) return false;
        if (!((ClientCookie)cookie).containsAttribute("domain")) return false;
        return BasicDomainHandler.domainMatch(domain, host);
    }

    static boolean domainMatch(String domain, String host) {
        if (InetAddressUtils.isIPv4Address((String)host)) return false;
        if (InetAddressUtils.isIPv6Address((String)host)) {
            return false;
        }
        String normalizedDomain = domain.startsWith(".") ? domain.substring(1) : domain;
        if (!host.endsWith(normalizedDomain)) return false;
        int prefix = host.length() - normalizedDomain.length();
        if (prefix == 0) {
            return true;
        }
        if (prefix <= 1) return false;
        if (host.charAt(prefix - 1) != '.') return false;
        return true;
    }

    public void parse(SetCookie cookie, String value) throws MalformedCookieException {
        Args.notNull((Object)cookie, (String)"Cookie");
        if (TextUtils.isBlank((CharSequence)value)) {
            throw new MalformedCookieException("Blank or null value for domain attribute");
        }
        if (value.endsWith(".")) {
            return;
        }
        String domain = value;
        if (domain.startsWith(".")) {
            domain = domain.substring(1);
        }
        domain = domain.toLowerCase(Locale.ROOT);
        cookie.setDomain(domain);
    }
}
