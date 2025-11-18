/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.client.utils.DateUtils
 *  org.apache.http.cookie.CommonCookieAttributeHandler
 *  org.apache.http.cookie.MalformedCookieException
 *  org.apache.http.cookie.SetCookie
 *  org.apache.http.impl.cookie.AbstractCookieAttributeHandler
 *  org.apache.http.util.Args
 */
package org.apache.http.impl.cookie;

import java.util.Date;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.client.utils.DateUtils;
import org.apache.http.cookie.CommonCookieAttributeHandler;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;
import org.apache.http.impl.cookie.AbstractCookieAttributeHandler;
import org.apache.http.util.Args;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class BasicExpiresHandler
extends AbstractCookieAttributeHandler
implements CommonCookieAttributeHandler {
    private final String[] datePatterns;

    public void parse(SetCookie cookie, String value) throws MalformedCookieException {
        Args.notNull((Object)cookie, (String)"Cookie");
        if (value == null) {
            throw new MalformedCookieException("Missing value for 'expires' attribute");
        }
        Date expiry = DateUtils.parseDate((String)value, (String[])this.datePatterns);
        if (expiry == null) {
            throw new MalformedCookieException("Invalid 'expires' attribute: " + value);
        }
        cookie.setExpiryDate(expiry);
    }

    public BasicExpiresHandler(String[] datePatterns) {
        Args.notNull((Object)datePatterns, (String)"Array of date patterns");
        this.datePatterns = (String[])datePatterns.clone();
    }

    public String getAttributeName() {
        return "expires";
    }
}
