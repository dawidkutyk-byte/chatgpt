/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.cookie.MalformedCookieException
 */
package org.apache.http.cookie;

import org.apache.http.cookie.MalformedCookieException;

public class CookieRestrictionViolationException
extends MalformedCookieException {
    private static final long serialVersionUID = 7371235577078589013L;

    public CookieRestrictionViolationException(String message) {
        super(message);
    }

    public CookieRestrictionViolationException() {
    }
}
