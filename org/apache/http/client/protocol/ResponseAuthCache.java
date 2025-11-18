/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.logging.Log
 *  org.apache.commons.logging.LogFactory
 *  org.apache.http.HttpException
 *  org.apache.http.HttpHost
 *  org.apache.http.HttpResponse
 *  org.apache.http.HttpResponseInterceptor
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.auth.AuthScheme
 *  org.apache.http.auth.AuthState
 *  org.apache.http.client.AuthCache
 *  org.apache.http.client.protocol.ResponseAuthCache$1
 *  org.apache.http.conn.scheme.Scheme
 *  org.apache.http.conn.scheme.SchemeRegistry
 *  org.apache.http.impl.client.BasicAuthCache
 *  org.apache.http.protocol.HttpContext
 *  org.apache.http.util.Args
 */
package org.apache.http.client.protocol;

import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthState;
import org.apache.http.client.AuthCache;
import org.apache.http.client.protocol.ResponseAuthCache;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;

@Deprecated
@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class ResponseAuthCache
implements HttpResponseInterceptor {
    private final Log log = LogFactory.getLog(this.getClass());

    private boolean isCachable(AuthState authState) {
        AuthScheme authScheme = authState.getAuthScheme();
        if (authScheme == null) return false;
        if (authScheme.isComplete()) String schemeName;
        return (schemeName = authScheme.getSchemeName()).equalsIgnoreCase("Basic") || schemeName.equalsIgnoreCase("Digest");
        return false;
    }

    public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
        Args.notNull((Object)response, (String)"HTTP request");
        Args.notNull((Object)context, (String)"HTTP context");
        AuthCache authCache = (AuthCache)context.getAttribute("http.auth.auth-cache");
        HttpHost target = (HttpHost)context.getAttribute("http.target_host");
        AuthState targetState = (AuthState)context.getAttribute("http.auth.target-scope");
        if (target != null && targetState != null) {
            if (this.log.isDebugEnabled()) {
                this.log.debug((Object)("Target auth state: " + targetState.getState()));
            }
            if (this.isCachable(targetState)) {
                SchemeRegistry schemeRegistry = (SchemeRegistry)context.getAttribute("http.scheme-registry");
                if (target.getPort() < 0) {
                    Scheme scheme = schemeRegistry.getScheme(target);
                    target = new HttpHost(target.getHostName(), scheme.resolvePort(target.getPort()), target.getSchemeName());
                }
                if (authCache == null) {
                    authCache = new BasicAuthCache();
                    context.setAttribute("http.auth.auth-cache", (Object)authCache);
                }
                switch (1.$SwitchMap$org$apache$http$auth$AuthProtocolState[targetState.getState().ordinal()]) {
                    case 1: {
                        this.cache(authCache, target, targetState.getAuthScheme());
                        break;
                    }
                    case 2: {
                        this.uncache(authCache, target, targetState.getAuthScheme());
                        break;
                    }
                }
            }
        }
        HttpHost proxy = (HttpHost)context.getAttribute("http.proxy_host");
        AuthState proxyState = (AuthState)context.getAttribute("http.auth.proxy-scope");
        if (proxy == null) return;
        if (proxyState == null) return;
        if (this.log.isDebugEnabled()) {
            this.log.debug((Object)("Proxy auth state: " + proxyState.getState()));
        }
        if (!this.isCachable(proxyState)) return;
        if (authCache == null) {
            authCache = new BasicAuthCache();
            context.setAttribute("http.auth.auth-cache", (Object)authCache);
        }
        switch (1.$SwitchMap$org$apache$http$auth$AuthProtocolState[proxyState.getState().ordinal()]) {
            case 1: {
                this.cache(authCache, proxy, proxyState.getAuthScheme());
                break;
            }
            case 2: {
                this.uncache(authCache, proxy, proxyState.getAuthScheme());
                break;
            }
        }
    }

    private void uncache(AuthCache authCache, HttpHost host, AuthScheme authScheme) {
        if (this.log.isDebugEnabled()) {
            this.log.debug((Object)("Removing from cache '" + authScheme.getSchemeName() + "' auth scheme for " + host));
        }
        authCache.remove(host);
    }

    private void cache(AuthCache authCache, HttpHost host, AuthScheme authScheme) {
        if (this.log.isDebugEnabled()) {
            this.log.debug((Object)("Caching '" + authScheme.getSchemeName() + "' auth scheme for " + host));
        }
        authCache.put(host, authScheme);
    }
}
