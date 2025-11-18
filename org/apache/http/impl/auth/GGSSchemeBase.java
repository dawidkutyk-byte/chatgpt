/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.logging.Log
 *  org.apache.commons.logging.LogFactory
 *  org.apache.http.Header
 *  org.apache.http.HttpHost
 *  org.apache.http.HttpRequest
 *  org.apache.http.auth.AuthenticationException
 *  org.apache.http.auth.Credentials
 *  org.apache.http.auth.InvalidCredentialsException
 *  org.apache.http.auth.KerberosCredentials
 *  org.apache.http.auth.MalformedChallengeException
 *  org.apache.http.conn.routing.HttpRoute
 *  org.apache.http.impl.auth.AuthSchemeBase
 *  org.apache.http.impl.auth.GGSSchemeBase$1
 *  org.apache.http.impl.auth.GGSSchemeBase$State
 *  org.apache.http.message.BufferedHeader
 *  org.apache.http.protocol.HttpContext
 *  org.apache.http.util.Args
 *  org.apache.http.util.CharArrayBuffer
 */
package org.apache.http.impl.auth;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.InvalidCredentialsException;
import org.apache.http.auth.KerberosCredentials;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.auth.AuthSchemeBase;
import org.apache.http.impl.auth.GGSSchemeBase;
import org.apache.http.message.BufferedHeader;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;
import org.ietf.jgss.GSSContext;
import org.ietf.jgss.GSSCredential;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.GSSManager;
import org.ietf.jgss.GSSName;
import org.ietf.jgss.Oid;

public abstract class GGSSchemeBase
extends AuthSchemeBase {
    private final boolean stripPort;
    private byte[] token;
    private final Log log = LogFactory.getLog(((Object)((Object)this)).getClass());
    private final boolean useCanonicalHostname;
    private State state;
    private final Base64 base64codec = new Base64(0);

    GGSSchemeBase(boolean stripPort, boolean useCanonicalHostname) {
        this.stripPort = stripPort;
        this.useCanonicalHostname = useCanonicalHostname;
        this.state = State.UNINITIATED;
    }

    protected byte[] generateGSSToken(byte[] input, Oid oid, String authServer, Credentials credentials) throws GSSException {
        GSSManager manager = this.getManager();
        GSSName serverName = manager.createName("HTTP@" + authServer, GSSName.NT_HOSTBASED_SERVICE);
        GSSCredential gssCredential = credentials instanceof KerberosCredentials ? ((KerberosCredentials)credentials).getGSSCredential() : null;
        GSSContext gssContext = this.createGSSContext(manager, oid, serverName, gssCredential);
        return input != null ? gssContext.initSecContext(input, 0, input.length) : gssContext.initSecContext(new byte[0], 0, 0);
    }

    GGSSchemeBase(boolean stripPort) {
        this(stripPort, true);
    }

    public Header authenticate(Credentials credentials, HttpRequest request, HttpContext context) throws AuthenticationException {
        Args.notNull((Object)request, (String)"HTTP request");
        switch (1.$SwitchMap$org$apache$http$impl$auth$GGSSchemeBase$State[this.state.ordinal()]) {
            case 1: {
                throw new AuthenticationException(this.getSchemeName() + " authentication has not been initiated");
            }
            case 2: {
                throw new AuthenticationException(this.getSchemeName() + " authentication has failed");
            }
            case 3: {
                try {
                    HttpHost host;
                    HttpRoute route = (HttpRoute)context.getAttribute("http.route");
                    if (route == null) {
                        throw new AuthenticationException("Connection route is not available");
                    }
                    if (this.isProxy()) {
                        host = route.getProxyHost();
                        if (host == null) {
                            host = route.getTargetHost();
                        }
                    } else {
                        host = route.getTargetHost();
                    }
                    String hostname = host.getHostName();
                    if (this.useCanonicalHostname) {
                        try {
                            hostname = this.resolveCanonicalHostname(hostname);
                        }
                        catch (UnknownHostException ignore) {
                            // empty catch block
                        }
                    }
                    String authServer = this.stripPort ? hostname : hostname + ":" + host.getPort();
                    if (this.log.isDebugEnabled()) {
                        this.log.debug((Object)("init " + authServer));
                    }
                    this.token = this.generateToken(this.token, authServer, credentials);
                    this.state = State.TOKEN_GENERATED;
                }
                catch (GSSException gsse) {
                    this.state = State.FAILED;
                    if (gsse.getMajor() == 9) throw new InvalidCredentialsException(gsse.getMessage(), (Throwable)gsse);
                    if (gsse.getMajor() == 8) {
                        throw new InvalidCredentialsException(gsse.getMessage(), (Throwable)gsse);
                    }
                    if (gsse.getMajor() == 13) {
                        throw new InvalidCredentialsException(gsse.getMessage(), (Throwable)gsse);
                    }
                    if (gsse.getMajor() == 10) throw new AuthenticationException(gsse.getMessage(), (Throwable)gsse);
                    if (gsse.getMajor() == 19) throw new AuthenticationException(gsse.getMessage(), (Throwable)gsse);
                    if (gsse.getMajor() != 20) throw new AuthenticationException(gsse.getMessage());
                    throw new AuthenticationException(gsse.getMessage(), (Throwable)gsse);
                }
            }
            case 4: {
                String tokenstr = new String(this.base64codec.encode(this.token));
                if (this.log.isDebugEnabled()) {
                    this.log.debug((Object)("Sending response '" + tokenstr + "' back to the auth server"));
                }
                CharArrayBuffer buffer = new CharArrayBuffer(32);
                if (this.isProxy()) {
                    buffer.append("Proxy-Authorization");
                } else {
                    buffer.append("Authorization");
                }
                buffer.append(": Negotiate ");
                buffer.append(tokenstr);
                return new BufferedHeader(buffer);
            }
        }
        throw new IllegalStateException("Illegal state: " + this.state);
    }

    protected void parseChallenge(CharArrayBuffer buffer, int beginIndex, int endIndex) throws MalformedChallengeException {
        String challenge = buffer.substringTrimmed(beginIndex, endIndex);
        if (this.log.isDebugEnabled()) {
            this.log.debug((Object)("Received challenge '" + challenge + "' from the auth server"));
        }
        if (this.state == State.UNINITIATED) {
            this.token = Base64.decodeBase64(challenge.getBytes());
            this.state = State.CHALLENGE_RECEIVED;
        } else {
            this.log.debug((Object)"Authentication already attempted");
            this.state = State.FAILED;
        }
    }

    @Deprecated
    protected byte[] generateToken(byte[] input, String authServer) throws GSSException {
        return null;
    }

    @Deprecated
    public Header authenticate(Credentials credentials, HttpRequest request) throws AuthenticationException {
        return this.authenticate(credentials, request, null);
    }

    protected byte[] generateToken(byte[] input, String authServer, Credentials credentials) throws GSSException {
        return this.generateToken(input, authServer);
    }

    protected GSSManager getManager() {
        return GSSManager.getInstance();
    }

    GGSSchemeBase() {
        this(true, true);
    }

    GSSContext createGSSContext(GSSManager manager, Oid oid, GSSName serverName, GSSCredential gssCredential) throws GSSException {
        GSSContext gssContext = manager.createContext(serverName.canonicalize(oid), oid, gssCredential, 0);
        gssContext.requestMutualAuth(true);
        return gssContext;
    }

    public boolean isComplete() {
        return this.state == State.TOKEN_GENERATED || this.state == State.FAILED;
    }

    protected byte[] generateGSSToken(byte[] input, Oid oid, String authServer) throws GSSException {
        return this.generateGSSToken(input, oid, authServer, null);
    }

    private String resolveCanonicalHostname(String host) throws UnknownHostException {
        InetAddress in = InetAddress.getByName(host);
        String canonicalServer = in.getCanonicalHostName();
        if (!in.getHostAddress().contentEquals(canonicalServer)) return canonicalServer;
        return host;
    }
}
