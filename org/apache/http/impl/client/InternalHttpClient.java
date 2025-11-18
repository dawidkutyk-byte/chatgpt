/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.logging.Log
 *  org.apache.commons.logging.LogFactory
 *  org.apache.http.HttpException
 *  org.apache.http.HttpHost
 *  org.apache.http.HttpRequest
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.auth.AuthSchemeProvider
 *  org.apache.http.auth.AuthState
 *  org.apache.http.client.ClientProtocolException
 *  org.apache.http.client.CookieStore
 *  org.apache.http.client.CredentialsProvider
 *  org.apache.http.client.config.RequestConfig
 *  org.apache.http.client.methods.CloseableHttpResponse
 *  org.apache.http.client.methods.Configurable
 *  org.apache.http.client.methods.HttpExecutionAware
 *  org.apache.http.client.methods.HttpRequestWrapper
 *  org.apache.http.client.params.HttpClientParamConfig
 *  org.apache.http.client.protocol.HttpClientContext
 *  org.apache.http.config.Lookup
 *  org.apache.http.conn.ClientConnectionManager
 *  org.apache.http.conn.HttpClientConnectionManager
 *  org.apache.http.conn.routing.HttpRoute
 *  org.apache.http.conn.routing.HttpRoutePlanner
 *  org.apache.http.cookie.CookieSpecProvider
 *  org.apache.http.impl.client.CloseableHttpClient
 *  org.apache.http.impl.execchain.ClientExecChain
 *  org.apache.http.params.HttpParams
 *  org.apache.http.params.HttpParamsNames
 *  org.apache.http.protocol.BasicHttpContext
 *  org.apache.http.protocol.HttpContext
 *  org.apache.http.util.Args
 */
package org.apache.http.impl.client;

import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.auth.AuthSchemeProvider;
import org.apache.http.auth.AuthState;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.Configurable;
import org.apache.http.client.methods.HttpExecutionAware;
import org.apache.http.client.methods.HttpRequestWrapper;
import org.apache.http.client.params.HttpClientParamConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Lookup;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.execchain.ClientExecChain;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpParamsNames;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;

@Contract(threading=ThreadingBehavior.SAFE_CONDITIONAL)
class InternalHttpClient
extends CloseableHttpClient
implements Configurable {
    private final CookieStore cookieStore;
    private final Lookup<CookieSpecProvider> cookieSpecRegistry;
    private final CredentialsProvider credentialsProvider;
    private final RequestConfig defaultConfig;
    private final ClientExecChain execChain;
    private final HttpClientConnectionManager connManager;
    private final List<Closeable> closeables;
    private final Lookup<AuthSchemeProvider> authSchemeRegistry;
    private final Log log = LogFactory.getLog(((Object)((Object)this)).getClass());
    private final HttpRoutePlanner routePlanner;

    public RequestConfig getConfig() {
        return this.defaultConfig;
    }

    private void setupContext(HttpClientContext context) {
        if (context.getAttribute("http.auth.target-scope") == null) {
            context.setAttribute("http.auth.target-scope", (Object)new AuthState());
        }
        if (context.getAttribute("http.auth.proxy-scope") == null) {
            context.setAttribute("http.auth.proxy-scope", (Object)new AuthState());
        }
        if (context.getAttribute("http.authscheme-registry") == null) {
            context.setAttribute("http.authscheme-registry", this.authSchemeRegistry);
        }
        if (context.getAttribute("http.cookiespec-registry") == null) {
            context.setAttribute("http.cookiespec-registry", this.cookieSpecRegistry);
        }
        if (context.getAttribute("http.cookie-store") == null) {
            context.setAttribute("http.cookie-store", (Object)this.cookieStore);
        }
        if (context.getAttribute("http.auth.credentials-provider") == null) {
            context.setAttribute("http.auth.credentials-provider", (Object)this.credentialsProvider);
        }
        if (context.getAttribute("http.request-config") != null) return;
        context.setAttribute("http.request-config", (Object)this.defaultConfig);
    }

    private HttpRoute determineRoute(HttpHost target, HttpRequest request, HttpContext context) throws HttpException {
        HttpHost host = target;
        if (host != null) return this.routePlanner.determineRoute(host, request, context);
        host = (HttpHost)request.getParams().getParameter("http.default-host");
        return this.routePlanner.determineRoute(host, request, context);
    }

    public HttpParams getParams() {
        throw new UnsupportedOperationException();
    }

    static /* synthetic */ HttpClientConnectionManager access$000(InternalHttpClient x0) {
        return x0.connManager;
    }

    public InternalHttpClient(ClientExecChain execChain, HttpClientConnectionManager connManager, HttpRoutePlanner routePlanner, Lookup<CookieSpecProvider> cookieSpecRegistry, Lookup<AuthSchemeProvider> authSchemeRegistry, CookieStore cookieStore, CredentialsProvider credentialsProvider, RequestConfig defaultConfig, List<Closeable> closeables) {
        Args.notNull((Object)execChain, (String)"HTTP client exec chain");
        Args.notNull((Object)connManager, (String)"HTTP connection manager");
        Args.notNull((Object)routePlanner, (String)"HTTP route planner");
        this.execChain = execChain;
        this.connManager = connManager;
        this.routePlanner = routePlanner;
        this.cookieSpecRegistry = cookieSpecRegistry;
        this.authSchemeRegistry = authSchemeRegistry;
        this.cookieStore = cookieStore;
        this.credentialsProvider = credentialsProvider;
        this.defaultConfig = defaultConfig;
        this.closeables = closeables;
    }

    public ClientConnectionManager getConnectionManager() {
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    protected CloseableHttpResponse doExecute(HttpHost target, HttpRequest request, HttpContext context) throws IOException, ClientProtocolException {
        Args.notNull((Object)request, (String)"HTTP request");
        HttpExecutionAware execAware = null;
        if (request instanceof HttpExecutionAware) {
            execAware = (HttpExecutionAware)request;
        }
        try {
            HttpRequestWrapper wrapper = HttpRequestWrapper.wrap((HttpRequest)request, (HttpHost)target);
            HttpClientContext localcontext = HttpClientContext.adapt((HttpContext)(context != null ? context : new BasicHttpContext()));
            RequestConfig config = null;
            if (request instanceof Configurable) {
                config = ((Configurable)request).getConfig();
            }
            if (config == null) {
                HttpParams params = request.getParams();
                if (params instanceof HttpParamsNames) {
                    if (!((HttpParamsNames)params).getNames().isEmpty()) {
                        config = HttpClientParamConfig.getRequestConfig((HttpParams)params, (RequestConfig)this.defaultConfig);
                    }
                } else {
                    config = HttpClientParamConfig.getRequestConfig((HttpParams)params, (RequestConfig)this.defaultConfig);
                }
            }
            if (config != null) {
                localcontext.setRequestConfig(config);
            }
            this.setupContext(localcontext);
            HttpRoute route = this.determineRoute(target, (HttpRequest)wrapper, (HttpContext)localcontext);
            return this.execChain.execute(route, wrapper, localcontext, execAware);
        }
        catch (HttpException httpException) {
            throw new ClientProtocolException((Throwable)httpException);
        }
    }

    public void close() {
        if (this.closeables == null) return;
        Iterator<Closeable> i$ = this.closeables.iterator();
        while (i$.hasNext()) {
            Closeable closeable = i$.next();
            try {
                closeable.close();
            }
            catch (IOException ex) {
                this.log.error((Object)ex.getMessage(), (Throwable)ex);
            }
        }
    }
}
