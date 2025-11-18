/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.FormattedHeader
 *  org.apache.http.Header
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
 *  org.apache.http.impl.cookie.BasicClientCookie
 *  org.apache.http.impl.cookie.BasicCommentHandler
 *  org.apache.http.impl.cookie.BasicDomainHandler
 *  org.apache.http.impl.cookie.BasicExpiresHandler
 *  org.apache.http.impl.cookie.BasicMaxAgeHandler
 *  org.apache.http.impl.cookie.BasicPathHandler
 *  org.apache.http.impl.cookie.BasicSecureHandler
 *  org.apache.http.impl.cookie.BrowserCompatSpecFactory$SecurityLevel
 *  org.apache.http.impl.cookie.BrowserCompatVersionAttributeHandler
 *  org.apache.http.impl.cookie.CookieSpecBase
 *  org.apache.http.impl.cookie.NetscapeDraftHeaderParser
 *  org.apache.http.message.BasicHeaderElement
 *  org.apache.http.message.BasicHeaderValueFormatter
 *  org.apache.http.message.BufferedHeader
 *  org.apache.http.message.ParserCursor
 *  org.apache.http.util.Args
 *  org.apache.http.util.CharArrayBuffer
 */
package org.apache.http.impl.cookie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.apache.http.FormattedHeader;
import org.apache.http.Header;
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
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BasicCommentHandler;
import org.apache.http.impl.cookie.BasicDomainHandler;
import org.apache.http.impl.cookie.BasicExpiresHandler;
import org.apache.http.impl.cookie.BasicMaxAgeHandler;
import org.apache.http.impl.cookie.BasicPathHandler;
import org.apache.http.impl.cookie.BasicSecureHandler;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatVersionAttributeHandler;
import org.apache.http.impl.cookie.CookieSpecBase;
import org.apache.http.impl.cookie.NetscapeDraftHeaderParser;
import org.apache.http.message.BasicHeaderElement;
import org.apache.http.message.BasicHeaderValueFormatter;
import org.apache.http.message.BufferedHeader;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;

@Deprecated
@Contract(threading=ThreadingBehavior.SAFE)
public class BrowserCompatSpec
extends CookieSpecBase {
    private static final String[] DEFAULT_DATE_PATTERNS = new String[]{"EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z"};

    public List<Cookie> parse(Header header, CookieOrigin origin) throws MalformedCookieException {
        ParserCursor cursor;
        CharArrayBuffer buffer;
        Args.notNull((Object)header, (String)"Header");
        Args.notNull((Object)origin, (String)"Cookie origin");
        String headername = header.getName();
        if (!headername.equalsIgnoreCase("Set-Cookie")) {
            throw new MalformedCookieException("Unrecognized cookie header '" + header.toString() + "'");
        }
        HeaderElement[] helems = header.getElements();
        boolean versioned = false;
        boolean netscape = false;
        for (HeaderElement helem : helems) {
            if (helem.getParameterByName("version") != null) {
                versioned = true;
            }
            if (helem.getParameterByName("expires") == null) continue;
            netscape = true;
        }
        if (!netscape) {
            if (versioned) return this.parse(helems, origin);
        }
        NetscapeDraftHeaderParser parser = NetscapeDraftHeaderParser.DEFAULT;
        if (header instanceof FormattedHeader) {
            buffer = ((FormattedHeader)header).getBuffer();
            cursor = new ParserCursor(((FormattedHeader)header).getValuePos(), buffer.length());
        } else {
            String s = header.getValue();
            if (s == null) {
                throw new MalformedCookieException("Header value is null");
            }
            buffer = new CharArrayBuffer(s.length());
            buffer.append(s);
            cursor = new ParserCursor(0, buffer.length());
        }
        HeaderElement elem = parser.parseHeader(buffer, cursor);
        String name = elem.getName();
        String value = elem.getValue();
        if (name == null) throw new MalformedCookieException("Cookie name may not be empty");
        if (name.isEmpty()) {
            throw new MalformedCookieException("Cookie name may not be empty");
        }
        BasicClientCookie cookie = new BasicClientCookie(name, value);
        cookie.setPath(BrowserCompatSpec.getDefaultPath((CookieOrigin)origin));
        cookie.setDomain(BrowserCompatSpec.getDefaultDomain((CookieOrigin)origin));
        NameValuePair[] attribs = elem.getParameters();
        int j = attribs.length - 1;
        while (true) {
            if (j < 0) {
                if (!netscape) return Collections.singletonList(cookie);
                cookie.setVersion(0);
                return Collections.singletonList(cookie);
            }
            NameValuePair attrib = attribs[j];
            String s = attrib.getName().toLowerCase(Locale.ROOT);
            cookie.setAttribute(s, attrib.getValue());
            CookieAttributeHandler handler = this.findAttribHandler(s);
            if (handler != null) {
                handler.parse((SetCookie)cookie, attrib.getValue());
            }
            --j;
        }
    }

    public BrowserCompatSpec(String[] datepatterns, BrowserCompatSpecFactory.SecurityLevel securityLevel) {
        super(new CommonCookieAttributeHandler[]{new BrowserCompatVersionAttributeHandler(), new BasicDomainHandler(), securityLevel == BrowserCompatSpecFactory.SecurityLevel.SECURITYLEVEL_IE_MEDIUM ? new /* Unavailable Anonymous Inner Class!! */ : new BasicPathHandler(), new BasicMaxAgeHandler(), new BasicSecureHandler(), new BasicCommentHandler(), new BasicExpiresHandler(datepatterns != null ? (String[])datepatterns.clone() : DEFAULT_DATE_PATTERNS)});
    }

    private static boolean isQuoteEnclosed(String s) {
        return s != null && s.startsWith("\"") && s.endsWith("\"");
    }

    public List<Header> formatCookies(List<Cookie> cookies) {
        Args.notEmpty(cookies, (String)"List of cookies");
        CharArrayBuffer buffer = new CharArrayBuffer(20 * cookies.size());
        buffer.append("Cookie");
        buffer.append(": ");
        int i = 0;
        while (true) {
            if (i >= cookies.size()) {
                ArrayList<Header> headers = new ArrayList<Header>(1);
                headers.add((Header)new BufferedHeader(buffer));
                return headers;
            }
            Cookie cookie = cookies.get(i);
            if (i > 0) {
                buffer.append("; ");
            }
            String cookieName = cookie.getName();
            String cookieValue = cookie.getValue();
            if (cookie.getVersion() > 0 && !BrowserCompatSpec.isQuoteEnclosed(cookieValue)) {
                BasicHeaderValueFormatter.INSTANCE.formatHeaderElement(buffer, (HeaderElement)new BasicHeaderElement(cookieName, cookieValue), false);
            } else {
                buffer.append(cookieName);
                buffer.append("=");
                if (cookieValue != null) {
                    buffer.append(cookieValue);
                }
            }
            ++i;
        }
    }

    public Header getVersionHeader() {
        return null;
    }

    public String toString() {
        return "compatibility";
    }

    public BrowserCompatSpec(String[] datepatterns) {
        this(datepatterns, BrowserCompatSpecFactory.SecurityLevel.SECURITYLEVEL_DEFAULT);
    }

    public int getVersion() {
        return 0;
    }

    public BrowserCompatSpec() {
        this(null, BrowserCompatSpecFactory.SecurityLevel.SECURITYLEVEL_DEFAULT);
    }
}
