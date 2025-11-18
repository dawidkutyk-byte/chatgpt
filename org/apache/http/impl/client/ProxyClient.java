/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.ConnectionReuseStrategy
 *  org.apache.http.HttpClientConnection
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpException
 *  org.apache.http.HttpHost
 *  org.apache.http.HttpRequest
 *  org.apache.http.HttpRequestInterceptor
 *  org.apache.http.HttpResponse
 *  org.apache.http.HttpVersion
 *  org.apache.http.ProtocolVersion
 *  org.apache.http.auth.AuthSchemeFactory
 *  org.apache.http.auth.AuthSchemeRegistry
 *  org.apache.http.auth.AuthScope
 *  org.apache.http.auth.AuthState
 *  org.apache.http.auth.Credentials
 *  org.apache.http.client.AuthenticationStrategy
 *  org.apache.http.client.config.RequestConfig
 *  org.apache.http.client.params.HttpClientParamConfig
 *  org.apache.http.client.protocol.RequestClientConnControl
 *  org.apache.http.config.ConnectionConfig
 *  org.apache.http.conn.HttpConnectionFactory
 *  org.apache.http.conn.ManagedHttpClientConnection
 *  org.apache.http.conn.routing.HttpRoute
 *  org.apache.http.conn.routing.RouteInfo$LayerType
 *  org.apache.http.conn.routing.RouteInfo$TunnelType
 *  org.apache.http.entity.BufferedHttpEntity
 *  org.apache.http.impl.DefaultConnectionReuseStrategy
 *  org.apache.http.impl.auth.BasicSchemeFactory
 *  org.apache.http.impl.auth.DigestSchemeFactory
 *  org.apache.http.impl.auth.HttpAuthenticator
 *  org.apache.http.impl.auth.KerberosSchemeFactory
 *  org.apache.http.impl.auth.NTLMSchemeFactory
 *  org.apache.http.impl.auth.SPNegoSchemeFactory
 *  org.apache.http.impl.client.BasicCredentialsProvider
 *  org.apache.http.impl.client.ProxyAuthenticationStrategy
 *  org.apache.http.impl.conn.ManagedHttpClientConnectionFactory
 *  org.apache.http.impl.execchain.TunnelRefusedException
 *  org.apache.http.message.BasicHttpRequest
 *  org.apache.http.params.BasicHttpParams
 *  org.apache.http.params.HttpParamConfig
 *  org.apache.http.params.HttpParams
 *  org.apache.http.protocol.BasicHttpContext
 *  org.apache.http.protocol.HttpContext
 *  org.apache.http.protocol.HttpProcessor
 *  org.apache.http.protocol.HttpRequestExecutor
 *  org.apache.http.protocol.ImmutableHttpProcessor
 *  org.apache.http.protocol.RequestTargetHost
 *  org.apache.http.protocol.RequestUserAgent
 *  org.apache.http.util.Args
 *  org.apache.http.util.EntityUtils
 */
package org.apache.http.impl.client;

import java.io.IOException;
import java.net.Socket;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.auth.AuthSchemeFactory;
import org.apache.http.auth.AuthSchemeRegistry;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.client.AuthenticationStrategy;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.params.HttpClientParamConfig;
import org.apache.http.client.protocol.RequestClientConnControl;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.RouteInfo;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.auth.BasicSchemeFactory;
import org.apache.http.impl.auth.DigestSchemeFactory;
import org.apache.http.impl.auth.HttpAuthenticator;
import org.apache.http.impl.auth.KerberosSchemeFactory;
import org.apache.http.impl.auth.NTLMSchemeFactory;
import org.apache.http.impl.auth.SPNegoSchemeFactory;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.ProxyAuthenticationStrategy;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.execchain.TunnelRefusedException;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParamConfig;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.protocol.ImmutableHttpProcessor;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.protocol.RequestUserAgent;
import org.apache.http.util.Args;
import org.apache.http.util.EntityUtils;

public class ProxyClient {
    private final AuthSchemeRegistry authSchemeRegistry;
    private final HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory;
    private final ConnectionReuseStrategy reuseStrategy;
    private final HttpProcessor httpProcessor;
    private final ConnectionConfig connectionConfig;
    private final RequestConfig requestConfig;
    private final ProxyAuthenticationStrategy proxyAuthStrategy;
    private final HttpRequestExecutor requestExec;
    private final HttpAuthenticator authenticator;
    private final AuthState proxyAuthState;

    @Deprecated
    public AuthSchemeRegistry getAuthSchemeRegistry() {
        return this.authSchemeRegistry;
    }

    public ProxyClient() {
        this(null, null, null);
    }

    @Deprecated
    public HttpParams getParams() {
        return new BasicHttpParams();
    }

    @Deprecated
    public ProxyClient(HttpParams params) {
        this(null, HttpParamConfig.getConnectionConfig((HttpParams)params), HttpClientParamConfig.getRequestConfig((HttpParams)params));
    }

    public ProxyClient(RequestConfig requestConfig) {
        this(null, null, requestConfig);
    }

    public Socket tunnel(HttpHost proxy, HttpHost target, Credentials credentials) throws HttpException, IOException {
        HttpEntity entity;
        int status;
        HttpResponse response;
        Args.notNull((Object)proxy, (String)"Proxy host");
        Args.notNull((Object)target, (String)"Target host");
        Args.notNull((Object)credentials, (String)"Credentials");
        HttpHost host = target;
        if (host.getPort() <= 0) {
            host = new HttpHost(host.getHostName(), 80, host.getSchemeName());
        }
        HttpRoute route = new HttpRoute(host, this.requestConfig.getLocalAddress(), proxy, false, RouteInfo.TunnelType.TUNNELLED, RouteInfo.LayerType.PLAIN);
        ManagedHttpClientConnection conn = (ManagedHttpClientConnection)this.connFactory.create((Object)route, this.connectionConfig);
        BasicHttpContext context = new BasicHttpContext();
        BasicHttpRequest connect = new BasicHttpRequest("CONNECT", host.toHostString(), (ProtocolVersion)HttpVersion.HTTP_1_1);
        BasicCredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope(proxy), credentials);
        context.setAttribute("http.target_host", (Object)target);
        context.setAttribute("http.connection", (Object)conn);
        context.setAttribute("http.request", (Object)connect);
        context.setAttribute("http.route", (Object)route);
        context.setAttribute("http.auth.proxy-scope", (Object)this.proxyAuthState);
        context.setAttribute("http.auth.credentials-provider", (Object)credsProvider);
        context.setAttribute("http.authscheme-registry", (Object)this.authSchemeRegistry);
        context.setAttribute("http.request-config", (Object)this.requestConfig);
        this.requestExec.preProcess((HttpRequest)connect, this.httpProcessor, (HttpContext)context);
        while (true) {
            if (!conn.isOpen()) {
                Socket socket = new Socket(proxy.getHostName(), proxy.getPort());
                conn.bind(socket);
            }
            this.authenticator.generateAuthResponse((HttpRequest)connect, this.proxyAuthState, (HttpContext)context);
            response = this.requestExec.execute((HttpRequest)connect, (HttpClientConnection)conn, (HttpContext)context);
            status = response.getStatusLine().getStatusCode();
            if (status < 200) {
                throw new HttpException("Unexpected response to CONNECT request: " + response.getStatusLine());
            }
            if (!this.authenticator.isAuthenticationRequested(proxy, response, (AuthenticationStrategy)this.proxyAuthStrategy, this.proxyAuthState, (HttpContext)context) || !this.authenticator.handleAuthChallenge(proxy, response, (AuthenticationStrategy)this.proxyAuthStrategy, this.proxyAuthState, (HttpContext)context)) break;
            if (this.reuseStrategy.keepAlive(response, (HttpContext)context)) {
                entity = response.getEntity();
                EntityUtils.consume((HttpEntity)entity);
            } else {
                conn.close();
            }
            connect.removeHeaders("Proxy-Authorization");
        }
        status = response.getStatusLine().getStatusCode();
        if (status <= 299) return conn.getSocket();
        entity = response.getEntity();
        if (entity != null) {
            response.setEntity((HttpEntity)new BufferedHttpEntity(entity));
        }
        conn.close();
        throw new TunnelRefusedException("CONNECT refused by proxy: " + response.getStatusLine(), response);
    }

    public ProxyClient(HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory, ConnectionConfig connectionConfig, RequestConfig requestConfig) {
        this.connFactory = connFactory != null ? connFactory : ManagedHttpClientConnectionFactory.INSTANCE;
        this.connectionConfig = connectionConfig != null ? connectionConfig : ConnectionConfig.DEFAULT;
        this.requestConfig = requestConfig != null ? requestConfig : RequestConfig.DEFAULT;
        this.httpProcessor = new ImmutableHttpProcessor(new HttpRequestInterceptor[]{new RequestTargetHost(), new RequestClientConnControl(), new RequestUserAgent()});
        this.requestExec = new HttpRequestExecutor();
        this.proxyAuthStrategy = new ProxyAuthenticationStrategy();
        this.authenticator = new HttpAuthenticator();
        this.proxyAuthState = new AuthState();
        this.authSchemeRegistry = new AuthSchemeRegistry();
        this.authSchemeRegistry.register("Basic", (AuthSchemeFactory)new BasicSchemeFactory());
        this.authSchemeRegistry.register("Digest", (AuthSchemeFactory)new DigestSchemeFactory());
        this.authSchemeRegistry.register("NTLM", (AuthSchemeFactory)new NTLMSchemeFactory());
        this.authSchemeRegistry.register("Negotiate", (AuthSchemeFactory)new SPNegoSchemeFactory());
        this.authSchemeRegistry.register("Kerberos", (AuthSchemeFactory)new KerberosSchemeFactory());
        this.reuseStrategy = new DefaultConnectionReuseStrategy();
    }
}
