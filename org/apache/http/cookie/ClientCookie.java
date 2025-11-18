/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Obsolete
 *  org.apache.http.cookie.Cookie
 */
package org.apache.http.cookie;

import org.apache.http.annotation.Obsolete;
import org.apache.http.cookie.Cookie;

public interface ClientCookie
extends Cookie {
    public static final String MAX_AGE_ATTR = "max-age";
    public static final String SECURE_ATTR = "secure";
    public static final String DOMAIN_ATTR = "domain";
    @Obsolete
    public static final String DISCARD_ATTR = "discard";
    @Obsolete
    public static final String VERSION_ATTR = "version";
    @Obsolete
    public static final String PORT_ATTR = "port";
    @Obsolete
    public static final String COMMENTURL_ATTR = "commenturl";
    public static final String EXPIRES_ATTR = "expires";
    public static final String PATH_ATTR = "path";
    @Obsolete
    public static final String COMMENT_ATTR = "comment";

    public boolean containsAttribute(String var1);

    public String getAttribute(String var1);
}
