/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.cookie.CommonCookieAttributeHandler
 *  org.apache.http.impl.cookie.BasicDomainHandler
 *  org.apache.http.impl.cookie.BasicExpiresHandler
 *  org.apache.http.impl.cookie.BasicMaxAgeHandler
 *  org.apache.http.impl.cookie.BasicPathHandler
 *  org.apache.http.impl.cookie.BasicSecureHandler
 *  org.apache.http.impl.cookie.RFC6265CookieSpecBase
 */
package org.apache.http.impl.cookie;

import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.cookie.CommonCookieAttributeHandler;
import org.apache.http.impl.cookie.BasicDomainHandler;
import org.apache.http.impl.cookie.BasicExpiresHandler;
import org.apache.http.impl.cookie.BasicMaxAgeHandler;
import org.apache.http.impl.cookie.BasicPathHandler;
import org.apache.http.impl.cookie.BasicSecureHandler;
import org.apache.http.impl.cookie.RFC6265CookieSpecBase;

@Contract(threading=ThreadingBehavior.SAFE)
public class RFC6265StrictSpec
extends RFC6265CookieSpecBase {
    static final String[] DATE_PATTERNS = new String[]{"EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy"};

    public String toString() {
        return "rfc6265-strict";
    }

    public RFC6265StrictSpec() {
        super(new CommonCookieAttributeHandler[]{new BasicPathHandler(), new BasicDomainHandler(), new BasicMaxAgeHandler(), new BasicSecureHandler(), new BasicExpiresHandler(DATE_PATTERNS)});
    }

    RFC6265StrictSpec(CommonCookieAttributeHandler ... handlers) {
        super(handlers);
    }
}
