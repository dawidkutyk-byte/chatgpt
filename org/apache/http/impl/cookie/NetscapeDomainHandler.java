/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.cookie.Cookie
 *  org.apache.http.cookie.CookieOrigin
 *  org.apache.http.cookie.CookieRestrictionViolationException
 *  org.apache.http.cookie.MalformedCookieException
 *  org.apache.http.cookie.SetCookie
 *  org.apache.http.impl.cookie.BasicDomainHandler
 *  org.apache.http.util.Args
 *  org.apache.http.util.TextUtils
 */
package org.apache.http.impl.cookie;

import java.util.Locale;
import java.util.StringTokenizer;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieRestrictionViolationException;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;
import org.apache.http.impl.cookie.BasicDomainHandler;
import org.apache.http.util.Args;
import org.apache.http.util.TextUtils;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class NetscapeDomainHandler
extends BasicDomainHandler {
    private static boolean isSpecialDomain(String domain) {
        String ucDomain = domain.toUpperCase(Locale.ROOT);
        return ucDomain.endsWith(".COM") || ucDomain.endsWith(".EDU") || ucDomain.endsWith(".NET") || ucDomain.endsWith(".GOV") || ucDomain.endsWith(".MIL") || ucDomain.endsWith(".ORG") || ucDomain.endsWith(".INT");
    }

    public void parse(SetCookie cookie, String value) throws MalformedCookieException {
        Args.notNull((Object)cookie, (String)"Cookie");
        if (TextUtils.isBlank((CharSequence)value)) {
            throw new MalformedCookieException("Blank or null value for domain attribute");
        }
        cookie.setDomain(value);
    }

    public boolean match(Cookie cookie, CookieOrigin origin) {
        Args.notNull((Object)cookie, (String)"Cookie");
        Args.notNull((Object)origin, (String)"Cookie origin");
        String host = origin.getHost();
        String domain = cookie.getDomain();
        if (domain != null) return host.endsWith(domain);
        return false;
    }

    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
        String domain;
        String host = origin.getHost();
        if (!host.equals(domain = cookie.getDomain()) && !BasicDomainHandler.domainMatch((String)domain, (String)host)) {
            throw new CookieRestrictionViolationException("Illegal domain attribute \"" + domain + "\". Domain of origin: \"" + host + "\"");
        }
        if (!host.contains(".")) return;
        int domainParts = new StringTokenizer(domain, ".").countTokens();
        if (NetscapeDomainHandler.isSpecialDomain(domain)) {
            if (domainParts >= 2) return;
            throw new CookieRestrictionViolationException("Domain attribute \"" + domain + "\" violates the Netscape cookie specification for " + "special domains");
        }
        if (domainParts >= 3) return;
        throw new CookieRestrictionViolationException("Domain attribute \"" + domain + "\" violates the Netscape cookie specification");
    }

    public String getAttributeName() {
        return "domain";
    }
}
