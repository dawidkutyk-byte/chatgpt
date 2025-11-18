/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.auth.AuthOption
 *  org.apache.http.auth.AuthProtocolState
 *  org.apache.http.auth.AuthScheme
 *  org.apache.http.auth.AuthScope
 *  org.apache.http.auth.Credentials
 *  org.apache.http.util.Args
 */
package org.apache.http.auth;

import java.util.Queue;
import org.apache.http.auth.AuthOption;
import org.apache.http.auth.AuthProtocolState;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.util.Args;

public class AuthState {
    private AuthScheme authScheme;
    private AuthScope authScope;
    private Queue<AuthOption> authOptions;
    private AuthProtocolState state = AuthProtocolState.UNCHALLENGED;
    private Credentials credentials;

    public Credentials getCredentials() {
        return this.credentials;
    }

    public AuthScheme getAuthScheme() {
        return this.authScheme;
    }

    public void reset() {
        this.state = AuthProtocolState.UNCHALLENGED;
        this.authOptions = null;
        this.authScheme = null;
        this.authScope = null;
        this.credentials = null;
    }

    public boolean isConnectionBased() {
        return this.authScheme != null && this.authScheme.isConnectionBased();
    }

    @Deprecated
    public boolean isValid() {
        return this.authScheme != null;
    }

    public void setState(AuthProtocolState state) {
        this.state = state != null ? state : AuthProtocolState.UNCHALLENGED;
    }

    @Deprecated
    public void invalidate() {
        this.reset();
    }

    @Deprecated
    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    @Deprecated
    public AuthScope getAuthScope() {
        return this.authScope;
    }

    public AuthProtocolState getState() {
        return this.state;
    }

    public Queue<AuthOption> getAuthOptions() {
        return this.authOptions;
    }

    @Deprecated
    public void setAuthScope(AuthScope authScope) {
        this.authScope = authScope;
    }

    public boolean hasAuthOptions() {
        return this.authOptions != null && !this.authOptions.isEmpty();
    }

    @Deprecated
    public void setAuthScheme(AuthScheme authScheme) {
        if (authScheme == null) {
            this.reset();
            return;
        }
        this.authScheme = authScheme;
    }

    public void update(Queue<AuthOption> authOptions) {
        Args.notEmpty(authOptions, (String)"Queue of auth options");
        this.authOptions = authOptions;
        this.authScheme = null;
        this.credentials = null;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("state:").append(this.state).append(";");
        if (this.authScheme != null) {
            buffer.append("auth scheme:").append(this.authScheme.getSchemeName()).append(";");
        }
        if (this.credentials == null) return buffer.toString();
        buffer.append("credentials present");
        return buffer.toString();
    }

    public void update(AuthScheme authScheme, Credentials credentials) {
        Args.notNull((Object)authScheme, (String)"Auth scheme");
        Args.notNull((Object)credentials, (String)"Credentials");
        this.authScheme = authScheme;
        this.credentials = credentials;
        this.authOptions = null;
    }
}
