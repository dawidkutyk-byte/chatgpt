/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpConnection
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.auth.AuthScheme
 *  org.apache.http.auth.AuthState
 *  org.apache.http.auth.Credentials
 *  org.apache.http.client.UserTokenHandler
 *  org.apache.http.client.protocol.HttpClientContext
 *  org.apache.http.conn.ManagedHttpClientConnection
 *  org.apache.http.protocol.HttpContext
 */
package org.apache.http.impl.client;

import java.security.Principal;
import javax.net.ssl.SSLSession;
import org.apache.http.HttpConnection;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.protocol.HttpContext;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class DefaultUserTokenHandler
implements UserTokenHandler {
    public static final DefaultUserTokenHandler INSTANCE = new DefaultUserTokenHandler();

    private static Principal getAuthPrincipal(AuthState authState) {
        AuthScheme scheme = authState.getAuthScheme();
        if (scheme == null) return null;
        if (!scheme.isComplete()) return null;
        if (!scheme.isConnectionBased()) return null;
        Credentials creds = authState.getCredentials();
        if (creds == null) return null;
        return creds.getUserPrincipal();
    }

    public Object getUserToken(HttpContext context) {
        HttpClientContext clientContext = HttpClientContext.adapt((HttpContext)context);
        Principal userPrincipal = null;
        AuthState targetAuthState = clientContext.getTargetAuthState();
        if (targetAuthState != null && (userPrincipal = DefaultUserTokenHandler.getAuthPrincipal(targetAuthState)) == null) {
            AuthState proxyAuthState = clientContext.getProxyAuthState();
            userPrincipal = DefaultUserTokenHandler.getAuthPrincipal(proxyAuthState);
        }
        if (userPrincipal != null) return userPrincipal;
        HttpConnection conn = clientContext.getConnection();
        if (!conn.isOpen()) return userPrincipal;
        if (!(conn instanceof ManagedHttpClientConnection)) return userPrincipal;
        SSLSession sslsession = ((ManagedHttpClientConnection)conn).getSSLSession();
        if (sslsession == null) return userPrincipal;
        userPrincipal = sslsession.getLocalPrincipal();
        return userPrincipal;
    }
}
