/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.FormattedHeader
 *  org.apache.http.Header
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.cookie.CommonCookieAttributeHandler
 *  org.apache.http.cookie.Cookie
 *  org.apache.http.cookie.CookieAttributeHandler
 *  org.apache.http.cookie.CookieOrigin
 *  org.apache.http.cookie.CookiePriorityComparator
 *  org.apache.http.cookie.CookieSpec
 *  org.apache.http.cookie.MalformedCookieException
 *  org.apache.http.cookie.SetCookie
 *  org.apache.http.impl.cookie.BasicClientCookie
 *  org.apache.http.message.BufferedHeader
 *  org.apache.http.message.ParserCursor
 *  org.apache.http.message.TokenParser
 *  org.apache.http.util.Args
 *  org.apache.http.util.CharArrayBuffer
 */
package org.apache.http.impl.cookie;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.FormattedHeader;
import org.apache.http.Header;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.cookie.CommonCookieAttributeHandler;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookiePriorityComparator;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BufferedHeader;
import org.apache.http.message.ParserCursor;
import org.apache.http.message.TokenParser;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;

@Contract(threading=ThreadingBehavior.SAFE)
public class RFC6265CookieSpec
implements CookieSpec {
    private static final BitSet TOKEN_DELIMS = TokenParser.INIT_BITSET((int[])new int[]{61, 59});
    private final CookieAttributeHandler[] attribHandlers;
    private static final BitSet VALUE_DELIMS = TokenParser.INIT_BITSET((int[])new int[]{59});
    private static final char EQUAL_CHAR = '=';
    private static final char ESCAPE_CHAR = '\\';
    private final Map<String, CookieAttributeHandler> attribHandlerMap;
    private static final BitSet SPECIAL_CHARS = TokenParser.INIT_BITSET((int[])new int[]{32, 34, 44, 59, 92});
    private static final char COMMA_CHAR = ',';
    private final TokenParser tokenParser;
    private static final char PARAM_DELIMITER = ';';
    private static final char DQUOTE_CHAR = '\"';

    boolean containsChars(CharSequence s, BitSet chars) {
        int i = 0;
        while (i < s.length()) {
            char ch = s.charAt(i);
            if (chars.get(ch)) {
                return true;
            }
            ++i;
        }
        return false;
    }

    protected RFC6265CookieSpec(CommonCookieAttributeHandler ... handlers) {
        this.attribHandlers = (CookieAttributeHandler[])handlers.clone();
        this.attribHandlerMap = new ConcurrentHashMap<String, CookieAttributeHandler>(handlers.length);
        CommonCookieAttributeHandler[] arr$ = handlers;
        int len$ = arr$.length;
        int i$ = 0;
        while (true) {
            if (i$ >= len$) {
                this.tokenParser = TokenParser.INSTANCE;
                return;
            }
            CommonCookieAttributeHandler handler = arr$[i$];
            this.attribHandlerMap.put(handler.getAttributeName().toLowerCase(Locale.ROOT), (CookieAttributeHandler)handler);
            ++i$;
        }
    }

    public List<Header> formatCookies(List<Cookie> cookies) {
        List<Cookie> sortedCookies;
        Args.notEmpty(cookies, (String)"List of cookies");
        if (cookies.size() > 1) {
            sortedCookies = new ArrayList<Cookie>(cookies);
            Collections.sort(sortedCookies, CookiePriorityComparator.INSTANCE);
        } else {
            sortedCookies = cookies;
        }
        CharArrayBuffer buffer = new CharArrayBuffer(20 * sortedCookies.size());
        buffer.append("Cookie");
        buffer.append(": ");
        int n = 0;
        while (true) {
            if (n >= sortedCookies.size()) {
                ArrayList<Header> headers = new ArrayList<Header>(1);
                headers.add((Header)new BufferedHeader(buffer));
                return headers;
            }
            Cookie cookie = sortedCookies.get(n);
            if (n > 0) {
                buffer.append(';');
                buffer.append(' ');
            }
            buffer.append(cookie.getName());
            String s = cookie.getValue();
            if (s != null) {
                buffer.append('=');
                if (this.containsSpecialChar(s)) {
                    buffer.append('\"');
                    for (int i = 0; i < s.length(); ++i) {
                        char ch = s.charAt(i);
                        if (ch == '\"' || ch == '\\') {
                            buffer.append('\\');
                        }
                        buffer.append(ch);
                    }
                    buffer.append('\"');
                } else {
                    buffer.append(s);
                }
            }
            ++n;
        }
    }

    public final Header getVersionHeader() {
        return null;
    }

    public final List<Cookie> parse(Header header, CookieOrigin origin) throws MalformedCookieException {
        ParserCursor cursor;
        CharArrayBuffer buffer;
        Args.notNull((Object)header, (String)"Header");
        Args.notNull((Object)origin, (String)"Cookie origin");
        if (!header.getName().equalsIgnoreCase("Set-Cookie")) {
            throw new MalformedCookieException("Unrecognized cookie header: '" + header.toString() + "'");
        }
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
        String name = this.tokenParser.parseToken(buffer, cursor, TOKEN_DELIMS);
        if (name.isEmpty()) {
            return Collections.emptyList();
        }
        if (cursor.atEnd()) {
            return Collections.emptyList();
        }
        char valueDelim = buffer.charAt(cursor.getPos());
        cursor.updatePos(cursor.getPos() + 1);
        if (valueDelim != '=') {
            throw new MalformedCookieException("Cookie value is invalid: '" + header.toString() + "'");
        }
        String value = this.tokenParser.parseValue(buffer, cursor, VALUE_DELIMS);
        if (!cursor.atEnd()) {
            cursor.updatePos(cursor.getPos() + 1);
        }
        BasicClientCookie cookie = new BasicClientCookie(name, value);
        cookie.setPath(RFC6265CookieSpec.getDefaultPath(origin));
        cookie.setDomain(RFC6265CookieSpec.getDefaultDomain(origin));
        cookie.setCreationDate(new Date());
        LinkedHashMap<String, String> attribMap = new LinkedHashMap<String, String>();
        while (!cursor.atEnd()) {
            String paramName = this.tokenParser.parseToken(buffer, cursor, TOKEN_DELIMS).toLowerCase(Locale.ROOT);
            String paramValue = null;
            if (!cursor.atEnd()) {
                char paramDelim = buffer.charAt(cursor.getPos());
                cursor.updatePos(cursor.getPos() + 1);
                if (paramDelim == '=') {
                    paramValue = this.tokenParser.parseToken(buffer, cursor, VALUE_DELIMS);
                    if (!cursor.atEnd()) {
                        cursor.updatePos(cursor.getPos() + 1);
                    }
                }
            }
            cookie.setAttribute(paramName, paramValue);
            attribMap.put(paramName, paramValue);
        }
        if (attribMap.containsKey("max-age")) {
            attribMap.remove("expires");
        }
        Iterator i$ = attribMap.entrySet().iterator();
        while (i$.hasNext()) {
            Map.Entry entry = i$.next();
            String paramName = (String)entry.getKey();
            String paramValue = (String)entry.getValue();
            CookieAttributeHandler handler = this.attribHandlerMap.get(paramName);
            if (handler == null) continue;
            handler.parse((SetCookie)cookie, paramValue);
        }
        return Collections.singletonList(cookie);
    }

    public final int getVersion() {
        return 0;
    }

    public final void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
        Args.notNull((Object)cookie, (String)"Cookie");
        Args.notNull((Object)origin, (String)"Cookie origin");
        CookieAttributeHandler[] arr$ = this.attribHandlers;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            CookieAttributeHandler handler = arr$[i$];
            handler.validate(cookie, origin);
            ++i$;
        }
    }

    public final boolean match(Cookie cookie, CookieOrigin origin) {
        Args.notNull((Object)cookie, (String)"Cookie");
        Args.notNull((Object)origin, (String)"Cookie origin");
        CookieAttributeHandler[] arr$ = this.attribHandlers;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            CookieAttributeHandler handler = arr$[i$];
            if (!handler.match(cookie, origin)) {
                return false;
            }
            ++i$;
        }
        return true;
    }

    static String getDefaultDomain(CookieOrigin origin) {
        return origin.getHost();
    }

    static String getDefaultPath(CookieOrigin origin) {
        String defaultPath = origin.getPath();
        int lastSlashIndex = defaultPath.lastIndexOf(47);
        if (lastSlashIndex < 0) return defaultPath;
        if (lastSlashIndex == 0) {
            lastSlashIndex = 1;
        }
        defaultPath = defaultPath.substring(0, lastSlashIndex);
        return defaultPath;
    }

    boolean containsSpecialChar(CharSequence s) {
        return this.containsChars(s, SPECIAL_CHARS);
    }
}
