/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.helper.AuthenticationHandler
 *  org.jsoup.helper.AuthenticationHandler$AuthShim
 *  org.jsoup.helper.RequestAuthenticator
 */
package org.jsoup.helper;

import java.net.Authenticator;
import java.net.HttpURLConnection;
import org.jsoup.helper.AuthenticationHandler;
import org.jsoup.helper.RequestAuthenticator;

static class AuthenticationHandler.GlobalHandler
implements AuthenticationHandler.AuthShim {
    static ThreadLocal<AuthenticationHandler> authenticators = new ThreadLocal();

    public void remove() {
        authenticators.remove();
    }

    public AuthenticationHandler get(AuthenticationHandler helper) {
        return authenticators.get();
    }

    AuthenticationHandler.GlobalHandler() {
    }

    public void enable(RequestAuthenticator auth, HttpURLConnection con) {
        authenticators.set(new AuthenticationHandler(auth));
    }

    static {
        Authenticator.setDefault((Authenticator)new AuthenticationHandler());
    }
}
