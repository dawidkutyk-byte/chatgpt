/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.auth.AuthenticationException
 */
package org.apache.http.auth;

import org.apache.http.auth.AuthenticationException;

public class InvalidCredentialsException
extends AuthenticationException {
    private static final long serialVersionUID = -4834003835215460648L;

    public InvalidCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }

    public InvalidCredentialsException() {
    }
}
