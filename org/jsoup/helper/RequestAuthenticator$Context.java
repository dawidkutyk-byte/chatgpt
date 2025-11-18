/*
 * Decompiled with CFR 0.152.
 */
package org.jsoup.helper;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;

public static class RequestAuthenticator.Context {
    private final Authenticator.RequestorType type;
    private final URL url;
    private final String realm;

    public boolean isProxy() {
        return this.type == Authenticator.RequestorType.PROXY;
    }

    RequestAuthenticator.Context(URL url, Authenticator.RequestorType type, String realm) {
        this.url = url;
        this.type = type;
        this.realm = realm;
    }

    public String realm() {
        return this.realm;
    }

    public URL url() {
        return this.url;
    }

    public Authenticator.RequestorType type() {
        return this.type;
    }

    public boolean isServer() {
        return this.type == Authenticator.RequestorType.SERVER;
    }

    public PasswordAuthentication credentials(String username, String password) {
        return new PasswordAuthentication(username, password.toCharArray());
    }
}
