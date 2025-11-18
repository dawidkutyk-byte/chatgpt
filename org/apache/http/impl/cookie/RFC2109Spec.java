/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Header
 *  org.apache.http.HeaderElement
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.Obsolete
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.cookie.ClientCookie
 *  org.apache.http.cookie.CommonCookieAttributeHandler
 *  org.apache.http.cookie.Cookie
 *  org.apache.http.cookie.CookieOrigin
 *  org.apache.http.cookie.CookiePathComparator
 *  org.apache.http.cookie.CookieRestrictionViolationException
 *  org.apache.http.cookie.MalformedCookieException
 *  org.apache.http.impl.cookie.BasicCommentHandler
 *  org.apache.http.impl.cookie.BasicExpiresHandler
 *  org.apache.http.impl.cookie.BasicMaxAgeHandler
 *  org.apache.http.impl.cookie.BasicSecureHandler
 *  org.apache.http.impl.cookie.CookieSpecBase
 *  org.apache.http.impl.cookie.RFC2109DomainHandler
 *  org.apache.http.impl.cookie.RFC2109VersionHandler
 *  org.apache.http.message.BufferedHeader
 *  org.apache.http.util.Args
 *  org.apache.http.util.CharArrayBuffer
 */
package org.apache.http.impl.cookie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.Obsolete;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.CommonCookieAttributeHandler;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookiePathComparator;
import org.apache.http.cookie.CookieRestrictionViolationException;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.impl.cookie.BasicCommentHandler;
import org.apache.http.impl.cookie.BasicExpiresHandler;
import org.apache.http.impl.cookie.BasicMaxAgeHandler;
import org.apache.http.impl.cookie.BasicSecureHandler;
import org.apache.http.impl.cookie.CookieSpecBase;
import org.apache.http.impl.cookie.RFC2109DomainHandler;
import org.apache.http.impl.cookie.RFC2109VersionHandler;
import org.apache.http.message.BufferedHeader;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;

@Contract(threading=ThreadingBehavior.SAFE)
@Obsolete
public class RFC2109Spec
extends CookieSpecBase {
    static final String[] DATE_PATTERNS = new String[]{"EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy"};
    private final boolean oneHeader;

    public String toString() {
        return "rfc2109";
    }

    private List<Header> doFormatOneHeader(List<Cookie> cookies) {
        int version = Integer.MAX_VALUE;
        for (Cookie cookie : cookies) {
            if (cookie.getVersion() >= version) continue;
            version = cookie.getVersion();
        }
        CharArrayBuffer buffer = new CharArrayBuffer(40 * cookies.size());
        buffer.append("Cookie");
        buffer.append(": ");
        buffer.append("$Version=");
        buffer.append(Integer.toString(version));
        Iterator<Cookie> i$ = cookies.iterator();
        while (true) {
            if (!i$.hasNext()) {
                ArrayList<Header> headers = new ArrayList<Header>(1);
                headers.add((Header)new BufferedHeader(buffer));
                return headers;
            }
            Cookie cooky = i$.next();
            buffer.append("; ");
            Cookie cookie = cooky;
            this.formatCookieAsVer(buffer, cookie, version);
        }
    }

    public List<Header> formatCookies(List<Cookie> cookies) {
        List<Cookie> cookieList;
        Args.notEmpty(cookies, (String)"List of cookies");
        if (cookies.size() > 1) {
            cookieList = new ArrayList<Cookie>(cookies);
            Collections.sort(cookieList, CookiePathComparator.INSTANCE);
        } else {
            cookieList = cookies;
        }
        return this.oneHeader ? this.doFormatOneHeader(cookieList) : this.doFormatManyHeaders(cookieList);
    }

    protected void formatParamAsVer(CharArrayBuffer buffer, String name, String value, int version) {
        buffer.append(name);
        buffer.append("=");
        if (value == null) return;
        if (version > 0) {
            buffer.append('\"');
            buffer.append(value);
            buffer.append('\"');
        } else {
            buffer.append(value);
        }
    }

    protected RFC2109Spec(boolean oneHeader, CommonCookieAttributeHandler ... handlers) {
        super(handlers);
        this.oneHeader = oneHeader;
    }

    protected void formatCookieAsVer(CharArrayBuffer buffer, Cookie cookie, int version) {
        this.formatParamAsVer(buffer, cookie.getName(), cookie.getValue(), version);
        if (cookie.getPath() != null && cookie instanceof ClientCookie && ((ClientCookie)cookie).containsAttribute("path")) {
            buffer.append("; ");
            this.formatParamAsVer(buffer, "$Path", cookie.getPath(), version);
        }
        if (cookie.getDomain() == null) return;
        if (!(cookie instanceof ClientCookie)) return;
        if (!((ClientCookie)cookie).containsAttribute("domain")) return;
        buffer.append("; ");
        this.formatParamAsVer(buffer, "$Domain", cookie.getDomain(), version);
    }

    private List<Header> doFormatManyHeaders(List<Cookie> cookies) {
        ArrayList<Header> headers = new ArrayList<Header>(cookies.size());
        Iterator<Cookie> i$ = cookies.iterator();
        while (i$.hasNext()) {
            Cookie cookie = i$.next();
            int version = cookie.getVersion();
            CharArrayBuffer buffer = new CharArrayBuffer(40);
            buffer.append("Cookie: ");
            buffer.append("$Version=");
            buffer.append(Integer.toString(version));
            buffer.append("; ");
            this.formatCookieAsVer(buffer, cookie, version);
            headers.add((Header)new BufferedHeader(buffer));
        }
        return headers;
    }

    public List<Cookie> parse(Header header, CookieOrigin origin) throws MalformedCookieException {
        Args.notNull((Object)header, (String)"Header");
        Args.notNull((Object)origin, (String)"Cookie origin");
        if (!header.getName().equalsIgnoreCase("Set-Cookie")) {
            throw new MalformedCookieException("Unrecognized cookie header '" + header.toString() + "'");
        }
        HeaderElement[] elems = header.getElements();
        return this.parse(elems, origin);
    }

    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
        Args.notNull((Object)cookie, (String)"Cookie");
        String name = cookie.getName();
        if (name.indexOf(32) != -1) {
            throw new CookieRestrictionViolationException("Cookie name may not contain blanks");
        }
        if (name.startsWith("$")) {
            throw new CookieRestrictionViolationException("Cookie name may not start with $");
        }
        super.validate(cookie, origin);
    }

    public Header getVersionHeader() {
        return null;
    }

    public RFC2109Spec(String[] datepatterns, boolean oneHeader) {
        super(new CommonCookieAttributeHandler[]{new RFC2109VersionHandler(), new /* Unavailable Anonymous Inner Class!! */, new RFC2109DomainHandler(), new BasicMaxAgeHandler(), new BasicSecureHandler(), new BasicCommentHandler(), new BasicExpiresHandler(datepatterns != null ? (String[])datepatterns.clone() : DATE_PATTERNS)});
        this.oneHeader = oneHeader;
    }

    public int getVersion() {
        return 1;
    }

    public RFC2109Spec() {
        this(null, false);
    }
}
