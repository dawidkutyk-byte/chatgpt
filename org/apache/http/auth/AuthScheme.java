/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Header
 *  org.apache.http.HttpRequest
 *  org.apache.http.auth.AuthenticationException
 *  org.apache.http.auth.Credentials
 *  org.apache.http.auth.MalformedChallengeException
 */
package org.apache.http.auth;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.MalformedChallengeException;

public interface AuthScheme {
    public String getSchemeName();

    public String getRealm();

    public void processChallenge(Header var1) throws MalformedChallengeException;

    public boolean isComplete();

    public boolean isConnectionBased();

    @Deprecated
    public Header authenticate(Credentials var1, HttpRequest var2) throws AuthenticationException;

    public String getParameter(String var1);
}
