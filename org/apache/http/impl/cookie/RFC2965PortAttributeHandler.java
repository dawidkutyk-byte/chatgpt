/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.cookie.ClientCookie
 *  org.apache.http.cookie.CommonCookieAttributeHandler
 *  org.apache.http.cookie.Cookie
 *  org.apache.http.cookie.CookieOrigin
 *  org.apache.http.cookie.CookieRestrictionViolationException
 *  org.apache.http.cookie.MalformedCookieException
 *  org.apache.http.cookie.SetCookie
 *  org.apache.http.cookie.SetCookie2
 *  org.apache.http.util.Args
 */
package org.apache.http.impl.cookie;

import java.util.StringTokenizer;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.CommonCookieAttributeHandler;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieRestrictionViolationException;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;
import org.apache.http.cookie.SetCookie2;
import org.apache.http.util.Args;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class RFC2965PortAttributeHandler
implements CommonCookieAttributeHandler {
    public void parse(SetCookie cookie, String portValue) throws MalformedCookieException {
        Args.notNull((Object)cookie, (String)"Cookie");
        if (!(cookie instanceof SetCookie2)) return;
        SetCookie2 cookie2 = (SetCookie2)cookie;
        if (portValue == null) return;
        if (portValue.trim().isEmpty()) return;
        int[] ports = RFC2965PortAttributeHandler.parsePortAttribute(portValue);
        cookie2.setPorts(ports);
    }

    private static boolean portMatch(int port, int[] ports) {
        boolean portInList = false;
        int[] arr$ = ports;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            int port2 = arr$[i$];
            if (port == port2) {
                portInList = true;
                return portInList;
            }
            ++i$;
        }
        return portInList;
    }

    public String getAttributeName() {
        return "port";
    }

    private static int[] parsePortAttribute(String portValue) throws MalformedCookieException {
        StringTokenizer st = new StringTokenizer(portValue, ",");
        int[] ports = new int[st.countTokens()];
        try {
            int i = 0;
            while (st.hasMoreTokens()) {
                ports[i] = Integer.parseInt(st.nextToken().trim());
                if (ports[i] < 0) {
                    throw new MalformedCookieException("Invalid Port attribute.");
                }
                ++i;
            }
            return ports;
        }
        catch (NumberFormatException e) {
            throw new MalformedCookieException("Invalid Port attribute: " + e.getMessage());
        }
    }

    public boolean match(Cookie cookie, CookieOrigin origin) {
        Args.notNull((Object)cookie, (String)"Cookie");
        Args.notNull((Object)origin, (String)"Cookie origin");
        int port = origin.getPort();
        if (!(cookie instanceof ClientCookie)) return true;
        if (!((ClientCookie)cookie).containsAttribute("port")) return true;
        if (cookie.getPorts() == null) {
            return false;
        }
        if (RFC2965PortAttributeHandler.portMatch(port, cookie.getPorts())) return true;
        return false;
    }

    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
        Args.notNull((Object)cookie, (String)"Cookie");
        Args.notNull((Object)origin, (String)"Cookie origin");
        int port = origin.getPort();
        if (!(cookie instanceof ClientCookie)) return;
        if (!((ClientCookie)cookie).containsAttribute("port")) return;
        if (RFC2965PortAttributeHandler.portMatch(port, cookie.getPorts())) return;
        throw new CookieRestrictionViolationException("Port attribute violates RFC 2965: Request port not found in cookie's port list.");
    }
}
