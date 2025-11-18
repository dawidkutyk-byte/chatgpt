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

class RequestAuthHandler
implements AuthenticationHandler.AuthShim {
    public void enable(RequestAuthenticator auth, HttpURLConnection con) {
        AuthenticationHandler authenticator = new AuthenticationHandler(auth);
        con.setAuthenticator((Authenticator)authenticator);
    }

    public void remove() {
    }

    public AuthenticationHandler get(AuthenticationHandler helper) {
        return helper;
    }
}
