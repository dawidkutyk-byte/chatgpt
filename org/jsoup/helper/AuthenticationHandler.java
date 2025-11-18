/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.helper.AuthenticationHandler$AuthShim
 *  org.jsoup.helper.AuthenticationHandler$GlobalHandler
 *  org.jsoup.helper.RequestAuthenticator
 *  org.jsoup.helper.RequestAuthenticator$Context
 */
package org.jsoup.helper;

import java.lang.reflect.Constructor;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import org.jsoup.helper.AuthenticationHandler;
import org.jsoup.helper.RequestAuthenticator;

class AuthenticationHandler
extends Authenticator {
    int attemptCount = 0;
    static final int MaxAttempts = 5;
    static AuthShim handler;
    @7\u015aCz RequestAuthenticator auth;

    AuthenticationHandler() {
    }

    @Override
    public final @7\u015aCz PasswordAuthentication getPasswordAuthentication() {
        AuthenticationHandler delegate = handler.get(this);
        if (delegate == null) {
            return null;
        }
        ++delegate.attemptCount;
        if (delegate.attemptCount > 5) {
            return null;
        }
        if (delegate.auth == null) {
            return null;
        }
        RequestAuthenticator.Context ctx = new RequestAuthenticator.Context(this.getRequestingURL(), this.getRequestorType(), this.getRequestingPrompt());
        return delegate.auth.authenticate(ctx);
    }

    AuthenticationHandler(RequestAuthenticator auth) {
        this.auth = auth;
    }

    static {
        try {
            Class<?> perRequestClass = Class.forName("org.jsoup.helper.RequestAuthHandler");
            Constructor<?> constructor = perRequestClass.getConstructor(new Class[0]);
            handler = (AuthShim)constructor.newInstance(new Object[0]);
        }
        catch (ClassNotFoundException e) {
            handler = new GlobalHandler();
        }
        catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
