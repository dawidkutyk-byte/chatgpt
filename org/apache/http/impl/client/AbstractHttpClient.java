/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.logging.Log
 *  org.apache.commons.logging.LogFactory
 *  org.apache.http.ConnectionReuseStrategy
 *  org.apache.http.HttpException
 *  org.apache.http.HttpHost
 *  org.apache.http.HttpRequest
 *  org.apache.http.HttpRequestInterceptor
 *  org.apache.http.HttpResponse
 *  org.apache.http.HttpResponseInterceptor
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.auth.AuthSchemeFactory
 *  org.apache.http.auth.AuthSchemeRegistry
 *  org.apache.http.client.AuthenticationHandler
 *  org.apache.http.client.AuthenticationStrategy
 *  org.apache.http.client.BackoffManager
 *  org.apache.http.client.ClientProtocolException
 *  org.apache.http.client.ConnectionBackoffStrategy
 *  org.apache.http.client.CookieStore
 *  org.apache.http.client.CredentialsProvider
 *  org.apache.http.client.HttpRequestRetryHandler
 *  org.apache.http.client.RedirectHandler
 *  org.apache.http.client.RedirectStrategy
 *  org.apache.http.client.RequestDirector
 *  org.apache.http.client.UserTokenHandler
 *  org.apache.http.client.config.RequestConfig
 *  org.apache.http.client.methods.CloseableHttpResponse
 *  org.apache.http.client.params.HttpClientParamConfig
 *  org.apache.http.conn.ClientConnectionManager
 *  org.apache.http.conn.ClientConnectionManagerFactory
 *  org.apache.http.conn.ConnectionKeepAliveStrategy
 *  org.apache.http.conn.routing.HttpRoute
 *  org.apache.http.conn.routing.HttpRoutePlanner
 *  org.apache.http.conn.scheme.SchemeRegistry
 *  org.apache.http.cookie.CookieSpecFactory
 *  org.apache.http.cookie.CookieSpecRegistry
 *  org.apache.http.impl.DefaultConnectionReuseStrategy
 *  org.apache.http.impl.auth.BasicSchemeFactory
 *  org.apache.http.impl.auth.DigestSchemeFactory
 *  org.apache.http.impl.auth.KerberosSchemeFactory
 *  org.apache.http.impl.auth.NTLMSchemeFactory
 *  org.apache.http.impl.auth.SPNegoSchemeFactory
 *  org.apache.http.impl.client.AuthenticationStrategyAdaptor
 *  org.apache.http.impl.client.BasicCookieStore
 *  org.apache.http.impl.client.BasicCredentialsProvider
 *  org.apache.http.impl.client.ClientParamsStack
 *  org.apache.http.impl.client.CloseableHttpClient
 *  org.apache.http.impl.client.CloseableHttpResponseProxy
 *  org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy
 *  org.apache.http.impl.client.DefaultHttpRequestRetryHandler
 *  org.apache.http.impl.client.DefaultProxyAuthenticationHandler
 *  org.apache.http.impl.client.DefaultRedirectHandler
 *  org.apache.http.impl.client.DefaultRedirectStrategy
 *  org.apache.http.impl.client.DefaultRedirectStrategyAdaptor
 *  org.apache.http.impl.client.DefaultRequestDirector
 *  org.apache.http.impl.client.DefaultTargetAuthenticationHandler
 *  org.apache.http.impl.client.DefaultUserTokenHandler
 *  org.apache.http.impl.client.ProxyAuthenticationStrategy
 *  org.apache.http.impl.client.TargetAuthenticationStrategy
 *  org.apache.http.impl.conn.BasicClientConnectionManager
 *  org.apache.http.impl.conn.DefaultHttpRoutePlanner
 *  org.apache.http.impl.conn.SchemeRegistryFactory
 *  org.apache.http.impl.cookie.BestMatchSpecFactory
 *  org.apache.http.impl.cookie.BrowserCompatSpecFactory
 *  org.apache.http.impl.cookie.IgnoreSpecFactory
 *  org.apache.http.impl.cookie.NetscapeDraftSpecFactory
 *  org.apache.http.impl.cookie.RFC2109SpecFactory
 *  org.apache.http.impl.cookie.RFC2965SpecFactory
 *  org.apache.http.params.HttpParams
 *  org.apache.http.protocol.BasicHttpContext
 *  org.apache.http.protocol.BasicHttpProcessor
 *  org.apache.http.protocol.DefaultedHttpContext
 *  org.apache.http.protocol.HttpContext
 *  org.apache.http.protocol.HttpProcessor
 *  org.apache.http.protocol.HttpRequestExecutor
 *  org.apache.http.protocol.ImmutableHttpProcessor
 *  org.apache.http.util.Args
 */
package org.apache.http.impl.client;

import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.auth.AuthSchemeFactory;
import org.apache.http.auth.AuthSchemeRegistry;
import org.apache.http.client.AuthenticationHandler;
import org.apache.http.client.AuthenticationStrategy;
import org.apache.http.client.BackoffManager;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ConnectionBackoffStrategy;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.RequestDirector;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.params.HttpClientParamConfig;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionManagerFactory;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.cookie.CookieSpecRegistry;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.auth.BasicSchemeFactory;
import org.apache.http.impl.auth.DigestSchemeFactory;
import org.apache.http.impl.auth.KerberosSchemeFactory;
import org.apache.http.impl.auth.NTLMSchemeFactory;
import org.apache.http.impl.auth.SPNegoSchemeFactory;
import org.apache.http.impl.client.AuthenticationStrategyAdaptor;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.ClientParamsStack;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.CloseableHttpResponseProxy;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.DefaultProxyAuthenticationHandler;
import org.apache.http.impl.client.DefaultRedirectHandler;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.DefaultRedirectStrategyAdaptor;
import org.apache.http.impl.client.DefaultRequestDirector;
import org.apache.http.impl.client.DefaultTargetAuthenticationHandler;
import org.apache.http.impl.client.DefaultUserTokenHandler;
import org.apache.http.impl.client.ProxyAuthenticationStrategy;
import org.apache.http.impl.client.TargetAuthenticationStrategy;
import org.apache.http.impl.conn.BasicClientConnectionManager;
import org.apache.http.impl.conn.DefaultHttpRoutePlanner;
import org.apache.http.impl.conn.SchemeRegistryFactory;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.impl.cookie.IgnoreSpecFactory;
import org.apache.http.impl.cookie.NetscapeDraftSpecFactory;
import org.apache.http.impl.cookie.RFC2109SpecFactory;
import org.apache.http.impl.cookie.RFC2965SpecFactory;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.DefaultedHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.protocol.ImmutableHttpProcessor;
import org.apache.http.util.Args;

@Deprecated
@Contract(threading=ThreadingBehavior.SAFE_CONDITIONAL)
public abstract class AbstractHttpClient
extends CloseableHttpClient {
    private CookieSpecRegistry supportedCookieSpecs;
    private final Log log = LogFactory.getLog(((Object)((Object)this)).getClass());
    private AuthenticationStrategy targetAuthStrategy;
    private AuthSchemeRegistry supportedAuthSchemes;
    private UserTokenHandler userTokenHandler;
    private AuthenticationStrategy proxyAuthStrategy;
    private BackoffManager backoffManager;
    private ImmutableHttpProcessor protocolProcessor;
    private HttpRequestRetryHandler retryHandler;
    private ConnectionBackoffStrategy connectionBackoffStrategy;
    private CookieStore cookieStore;
    private BasicHttpProcessor mutableProcessor;
    private ConnectionKeepAliveStrategy keepAliveStrategy;
    private ConnectionReuseStrategy reuseStrategy;
    private HttpRequestExecutor requestExec;
    private HttpRoutePlanner routePlanner;
    private HttpParams defaultParams;
    private RedirectStrategy redirectStrategy;
    private CredentialsProvider credsProvider;
    private ClientConnectionManager connManager;

    @Deprecated
    public final synchronized AuthenticationHandler getProxyAuthenticationHandler() {
        return this.createProxyAuthenticationHandler();
    }

    public synchronized void setCredentialsProvider(CredentialsProvider credsProvider) {
        this.credsProvider = credsProvider;
    }

    protected HttpRequestRetryHandler createHttpRequestRetryHandler() {
        return new DefaultHttpRequestRetryHandler();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected final CloseableHttpResponse doExecute(HttpHost target, HttpRequest request, HttpContext context) throws IOException, ClientProtocolException {
        Args.notNull((Object)request, (String)"HTTP request");
        Object execContext = null;
        RequestDirector director = null;
        HttpRoutePlanner routePlanner = null;
        ConnectionBackoffStrategy connectionBackoffStrategy = null;
        BackoffManager backoffManager = null;
        AbstractHttpClient abstractHttpClient = this;
        synchronized (abstractHttpClient) {
            HttpContext defaultContext = this.createHttpContext();
            execContext = context == null ? defaultContext : new DefaultedHttpContext(context, defaultContext);
            HttpParams params = this.determineParams(request);
            RequestConfig config = HttpClientParamConfig.getRequestConfig((HttpParams)params);
            execContext.setAttribute("http.request-config", (Object)config);
            director = this.createClientRequestDirector(this.getRequestExecutor(), this.getConnectionManager(), this.getConnectionReuseStrategy(), this.getConnectionKeepAliveStrategy(), this.getRoutePlanner(), this.getProtocolProcessor(), this.getHttpRequestRetryHandler(), this.getRedirectStrategy(), this.getTargetAuthenticationStrategy(), this.getProxyAuthenticationStrategy(), this.getUserTokenHandler(), params);
            routePlanner = this.getRoutePlanner();
            connectionBackoffStrategy = this.getConnectionBackoffStrategy();
            backoffManager = this.getBackoffManager();
        }
        try {
            CloseableHttpResponse out;
            if (connectionBackoffStrategy == null) return CloseableHttpResponseProxy.newProxy((HttpResponse)director.execute(target, request, execContext));
            if (backoffManager == null) return CloseableHttpResponseProxy.newProxy((HttpResponse)director.execute(target, request, execContext));
            HttpHost targetForRoute = target != null ? target : (HttpHost)this.determineParams(request).getParameter("http.default-host");
            HttpRoute route = routePlanner.determineRoute(targetForRoute, request, execContext);
            try {
                out = CloseableHttpResponseProxy.newProxy((HttpResponse)director.execute(target, request, execContext));
            }
            catch (RuntimeException re) {
                if (!connectionBackoffStrategy.shouldBackoff((Throwable)re)) throw re;
                backoffManager.backOff(route);
                throw re;
            }
            catch (Exception e) {
                if (connectionBackoffStrategy.shouldBackoff((Throwable)e)) {
                    backoffManager.backOff(route);
                }
                if (e instanceof HttpException) {
                    throw (HttpException)((Object)e);
                }
                if (!(e instanceof IOException)) throw new UndeclaredThrowableException(e);
                throw (IOException)e;
            }
            if (connectionBackoffStrategy.shouldBackoff((HttpResponse)out)) {
                backoffManager.backOff(route);
            } else {
                backoffManager.probe(route);
            }
            return out;
        }
        catch (HttpException httpException) {
            throw new ClientProtocolException((Throwable)httpException);
        }
    }

    @Deprecated
    public synchronized void setProxyAuthenticationHandler(AuthenticationHandler handler) {
        this.proxyAuthStrategy = new AuthenticationStrategyAdaptor(handler);
    }

    public final synchronized UserTokenHandler getUserTokenHandler() {
        if (this.userTokenHandler != null) return this.userTokenHandler;
        this.userTokenHandler = this.createUserTokenHandler();
        return this.userTokenHandler;
    }

    public synchronized void addResponseInterceptor(HttpResponseInterceptor itcp) {
        this.getHttpProcessor().addInterceptor(itcp);
        this.protocolProcessor = null;
    }

    protected ClientConnectionManager createClientConnectionManager() {
        SchemeRegistry registry = SchemeRegistryFactory.createDefault();
        Object connManager = null;
        HttpParams params = this.getParams();
        ClientConnectionManagerFactory factory = null;
        String className = (String)params.getParameter("http.connection-manager.factory-class-name");
        ClassLoader contextLoader = Thread.currentThread().getContextClassLoader();
        if (className != null) {
            try {
                Class<?> clazz = contextLoader != null ? Class.forName(className, true, contextLoader) : Class.forName(className);
                factory = (ClientConnectionManagerFactory)clazz.newInstance();
            }
            catch (ClassNotFoundException ex) {
                throw new IllegalStateException("Invalid class name: " + className);
            }
            catch (IllegalAccessException ex) {
                throw new IllegalAccessError(ex.getMessage());
            }
            catch (InstantiationException ex) {
                throw new InstantiationError(ex.getMessage());
            }
        }
        connManager = factory != null ? factory.newInstance(params, registry) : new BasicClientConnectionManager(registry);
        return connManager;
    }

    public final synchronized AuthenticationStrategy getProxyAuthenticationStrategy() {
        if (this.proxyAuthStrategy != null) return this.proxyAuthStrategy;
        this.proxyAuthStrategy = this.createProxyAuthenticationStrategy();
        return this.proxyAuthStrategy;
    }

    public synchronized void setRedirectStrategy(RedirectStrategy strategy) {
        this.redirectStrategy = strategy;
    }

    protected CookieStore createCookieStore() {
        return new BasicCookieStore();
    }

    public synchronized void setConnectionBackoffStrategy(ConnectionBackoffStrategy strategy) {
        this.connectionBackoffStrategy = strategy;
    }

    @Deprecated
    public synchronized void setTargetAuthenticationHandler(AuthenticationHandler handler) {
        this.targetAuthStrategy = new AuthenticationStrategyAdaptor(handler);
    }

    public final synchronized RedirectStrategy getRedirectStrategy() {
        if (this.redirectStrategy != null) return this.redirectStrategy;
        this.redirectStrategy = new DefaultRedirectStrategy();
        return this.redirectStrategy;
    }

    public synchronized HttpRequestInterceptor getRequestInterceptor(int index) {
        return this.getHttpProcessor().getRequestInterceptor(index);
    }

    public synchronized void clearResponseInterceptors() {
        this.getHttpProcessor().clearResponseInterceptors();
        this.protocolProcessor = null;
    }

    @Deprecated
    protected RedirectHandler createRedirectHandler() {
        return new DefaultRedirectHandler();
    }

    public final synchronized AuthSchemeRegistry getAuthSchemes() {
        if (this.supportedAuthSchemes != null) return this.supportedAuthSchemes;
        this.supportedAuthSchemes = this.createAuthSchemeRegistry();
        return this.supportedAuthSchemes;
    }

    protected CookieSpecRegistry createCookieSpecRegistry() {
        CookieSpecRegistry registry = new CookieSpecRegistry();
        registry.register("default", (CookieSpecFactory)new BestMatchSpecFactory());
        registry.register("best-match", (CookieSpecFactory)new BestMatchSpecFactory());
        registry.register("compatibility", (CookieSpecFactory)new BrowserCompatSpecFactory());
        registry.register("netscape", (CookieSpecFactory)new NetscapeDraftSpecFactory());
        registry.register("rfc2109", (CookieSpecFactory)new RFC2109SpecFactory());
        registry.register("rfc2965", (CookieSpecFactory)new RFC2965SpecFactory());
        registry.register("ignoreCookies", (CookieSpecFactory)new IgnoreSpecFactory());
        return registry;
    }

    public final synchronized ConnectionKeepAliveStrategy getConnectionKeepAliveStrategy() {
        if (this.keepAliveStrategy != null) return this.keepAliveStrategy;
        this.keepAliveStrategy = this.createConnectionKeepAliveStrategy();
        return this.keepAliveStrategy;
    }

    protected HttpContext createHttpContext() {
        BasicHttpContext context = new BasicHttpContext();
        context.setAttribute("http.scheme-registry", (Object)this.getConnectionManager().getSchemeRegistry());
        context.setAttribute("http.authscheme-registry", (Object)this.getAuthSchemes());
        context.setAttribute("http.cookiespec-registry", (Object)this.getCookieSpecs());
        context.setAttribute("http.cookie-store", (Object)this.getCookieStore());
        context.setAttribute("http.auth.credentials-provider", (Object)this.getCredentialsProvider());
        return context;
    }

    @Deprecated
    protected AuthenticationHandler createTargetAuthenticationHandler() {
        return new DefaultTargetAuthenticationHandler();
    }

    public synchronized void setParams(HttpParams params) {
        this.defaultParams = params;
    }

    protected final synchronized BasicHttpProcessor getHttpProcessor() {
        if (this.mutableProcessor != null) return this.mutableProcessor;
        this.mutableProcessor = this.createHttpProcessor();
        return this.mutableProcessor;
    }

    public synchronized void addRequestInterceptor(HttpRequestInterceptor itcp, int index) {
        this.getHttpProcessor().addInterceptor(itcp, index);
        this.protocolProcessor = null;
    }

    protected AuthSchemeRegistry createAuthSchemeRegistry() {
        AuthSchemeRegistry registry = new AuthSchemeRegistry();
        registry.register("Basic", (AuthSchemeFactory)new BasicSchemeFactory());
        registry.register("Digest", (AuthSchemeFactory)new DigestSchemeFactory());
        registry.register("NTLM", (AuthSchemeFactory)new NTLMSchemeFactory());
        registry.register("Negotiate", (AuthSchemeFactory)new SPNegoSchemeFactory());
        registry.register("Kerberos", (AuthSchemeFactory)new KerberosSchemeFactory());
        return registry;
    }

    protected RequestDirector createClientRequestDirector(HttpRequestExecutor requestExec, ClientConnectionManager conman, ConnectionReuseStrategy reustrat, ConnectionKeepAliveStrategy kastrat, HttpRoutePlanner rouplan, HttpProcessor httpProcessor, HttpRequestRetryHandler retryHandler, RedirectStrategy redirectStrategy, AuthenticationStrategy targetAuthStrategy, AuthenticationStrategy proxyAuthStrategy, UserTokenHandler userTokenHandler, HttpParams params) {
        return new DefaultRequestDirector(this.log, requestExec, conman, reustrat, kastrat, rouplan, httpProcessor, retryHandler, redirectStrategy, targetAuthStrategy, proxyAuthStrategy, userTokenHandler, params);
    }

    public synchronized void setProxyAuthenticationStrategy(AuthenticationStrategy strategy) {
        this.proxyAuthStrategy = strategy;
    }

    protected AuthenticationStrategy createProxyAuthenticationStrategy() {
        return new ProxyAuthenticationStrategy();
    }

    public synchronized void setTargetAuthenticationStrategy(AuthenticationStrategy strategy) {
        this.targetAuthStrategy = strategy;
    }

    @Deprecated
    protected RequestDirector createClientRequestDirector(HttpRequestExecutor requestExec, ClientConnectionManager conman, ConnectionReuseStrategy reustrat, ConnectionKeepAliveStrategy kastrat, HttpRoutePlanner rouplan, HttpProcessor httpProcessor, HttpRequestRetryHandler retryHandler, RedirectStrategy redirectStrategy, AuthenticationHandler targetAuthHandler, AuthenticationHandler proxyAuthHandler, UserTokenHandler userTokenHandler, HttpParams params) {
        return new DefaultRequestDirector(this.log, requestExec, conman, reustrat, kastrat, rouplan, httpProcessor, retryHandler, redirectStrategy, targetAuthHandler, proxyAuthHandler, userTokenHandler, params);
    }

    @Deprecated
    public synchronized void setRedirectHandler(RedirectHandler handler) {
        this.redirectStrategy = new DefaultRedirectStrategyAdaptor(handler);
    }

    public synchronized void setUserTokenHandler(UserTokenHandler handler) {
        this.userTokenHandler = handler;
    }

    public final synchronized BackoffManager getBackoffManager() {
        return this.backoffManager;
    }

    public final synchronized AuthenticationStrategy getTargetAuthenticationStrategy() {
        if (this.targetAuthStrategy != null) return this.targetAuthStrategy;
        this.targetAuthStrategy = this.createTargetAuthenticationStrategy();
        return this.targetAuthStrategy;
    }

    @Deprecated
    public final synchronized RedirectHandler getRedirectHandler() {
        return this.createRedirectHandler();
    }

    public final synchronized HttpParams getParams() {
        if (this.defaultParams != null) return this.defaultParams;
        this.defaultParams = this.createHttpParams();
        return this.defaultParams;
    }

    public synchronized void setAuthSchemes(AuthSchemeRegistry registry) {
        this.supportedAuthSchemes = registry;
    }

    public synchronized HttpResponseInterceptor getResponseInterceptor(int index) {
        return this.getHttpProcessor().getResponseInterceptor(index);
    }

    public final synchronized CookieSpecRegistry getCookieSpecs() {
        if (this.supportedCookieSpecs != null) return this.supportedCookieSpecs;
        this.supportedCookieSpecs = this.createCookieSpecRegistry();
        return this.supportedCookieSpecs;
    }

    public synchronized void setRoutePlanner(HttpRoutePlanner routePlanner) {
        this.routePlanner = routePlanner;
    }

    public synchronized void setKeepAliveStrategy(ConnectionKeepAliveStrategy strategy) {
        this.keepAliveStrategy = strategy;
    }

    public final synchronized CookieStore getCookieStore() {
        if (this.cookieStore != null) return this.cookieStore;
        this.cookieStore = this.createCookieStore();
        return this.cookieStore;
    }

    protected UserTokenHandler createUserTokenHandler() {
        return new DefaultUserTokenHandler();
    }

    public synchronized void removeResponseInterceptorByClass(Class<? extends HttpResponseInterceptor> clazz) {
        this.getHttpProcessor().removeResponseInterceptorByClass(clazz);
        this.protocolProcessor = null;
    }

    public synchronized int getRequestInterceptorCount() {
        return this.getHttpProcessor().getRequestInterceptorCount();
    }

    @Deprecated
    public final synchronized AuthenticationHandler getTargetAuthenticationHandler() {
        return this.createTargetAuthenticationHandler();
    }

    public void close() {
        this.getConnectionManager().shutdown();
    }

    protected HttpParams determineParams(HttpRequest req) {
        return new ClientParamsStack(null, this.getParams(), req.getParams(), null);
    }

    protected AuthenticationStrategy createTargetAuthenticationStrategy() {
        return new TargetAuthenticationStrategy();
    }

    protected abstract BasicHttpProcessor createHttpProcessor();

    public synchronized void addResponseInterceptor(HttpResponseInterceptor itcp, int index) {
        this.getHttpProcessor().addInterceptor(itcp, index);
        this.protocolProcessor = null;
    }

    protected HttpRequestExecutor createRequestExecutor() {
        return new HttpRequestExecutor();
    }

    public synchronized void setCookieStore(CookieStore cookieStore) {
        this.cookieStore = cookieStore;
    }

    public synchronized void setReuseStrategy(ConnectionReuseStrategy strategy) {
        this.reuseStrategy = strategy;
    }

    public synchronized void setCookieSpecs(CookieSpecRegistry registry) {
        this.supportedCookieSpecs = registry;
    }

    @Deprecated
    protected RequestDirector createClientRequestDirector(HttpRequestExecutor requestExec, ClientConnectionManager conman, ConnectionReuseStrategy reustrat, ConnectionKeepAliveStrategy kastrat, HttpRoutePlanner rouplan, HttpProcessor httpProcessor, HttpRequestRetryHandler retryHandler, RedirectHandler redirectHandler, AuthenticationHandler targetAuthHandler, AuthenticationHandler proxyAuthHandler, UserTokenHandler userTokenHandler, HttpParams params) {
        return new DefaultRequestDirector(requestExec, conman, reustrat, kastrat, rouplan, httpProcessor, retryHandler, redirectHandler, targetAuthHandler, proxyAuthHandler, userTokenHandler, params);
    }

    public synchronized void addRequestInterceptor(HttpRequestInterceptor itcp) {
        this.getHttpProcessor().addInterceptor(itcp);
        this.protocolProcessor = null;
    }

    public final synchronized HttpRequestRetryHandler getHttpRequestRetryHandler() {
        if (this.retryHandler != null) return this.retryHandler;
        this.retryHandler = this.createHttpRequestRetryHandler();
        return this.retryHandler;
    }

    public final synchronized HttpRoutePlanner getRoutePlanner() {
        if (this.routePlanner != null) return this.routePlanner;
        this.routePlanner = this.createHttpRoutePlanner();
        return this.routePlanner;
    }

    public synchronized void setBackoffManager(BackoffManager manager) {
        this.backoffManager = manager;
    }

    public synchronized void removeRequestInterceptorByClass(Class<? extends HttpRequestInterceptor> clazz) {
        this.getHttpProcessor().removeRequestInterceptorByClass(clazz);
        this.protocolProcessor = null;
    }

    public final synchronized ClientConnectionManager getConnectionManager() {
        if (this.connManager != null) return this.connManager;
        this.connManager = this.createClientConnectionManager();
        return this.connManager;
    }

    protected AbstractHttpClient(ClientConnectionManager conman, HttpParams params) {
        this.defaultParams = params;
        this.connManager = conman;
    }

    protected ConnectionReuseStrategy createConnectionReuseStrategy() {
        return new DefaultConnectionReuseStrategy();
    }

    protected ConnectionKeepAliveStrategy createConnectionKeepAliveStrategy() {
        return new DefaultConnectionKeepAliveStrategy();
    }

    protected abstract HttpParams createHttpParams();

    protected HttpRoutePlanner createHttpRoutePlanner() {
        return new DefaultHttpRoutePlanner(this.getConnectionManager().getSchemeRegistry());
    }

    public synchronized int getResponseInterceptorCount() {
        return this.getHttpProcessor().getResponseInterceptorCount();
    }

    public final synchronized ConnectionBackoffStrategy getConnectionBackoffStrategy() {
        return this.connectionBackoffStrategy;
    }

    protected CredentialsProvider createCredentialsProvider() {
        return new BasicCredentialsProvider();
    }

    @Deprecated
    protected AuthenticationHandler createProxyAuthenticationHandler() {
        return new DefaultProxyAuthenticationHandler();
    }

    public final synchronized ConnectionReuseStrategy getConnectionReuseStrategy() {
        if (this.reuseStrategy != null) return this.reuseStrategy;
        this.reuseStrategy = this.createConnectionReuseStrategy();
        return this.reuseStrategy;
    }

    private synchronized HttpProcessor getProtocolProcessor() {
        if (this.protocolProcessor != null) return this.protocolProcessor;
        BasicHttpProcessor proc = this.getHttpProcessor();
        int reqc = proc.getRequestInterceptorCount();
        HttpRequestInterceptor[] reqinterceptors = new HttpRequestInterceptor[reqc];
        for (int i = 0; i < reqc; ++i) {
            reqinterceptors[i] = proc.getRequestInterceptor(i);
        }
        int resc = proc.getResponseInterceptorCount();
        HttpResponseInterceptor[] resinterceptors = new HttpResponseInterceptor[resc];
        int i = 0;
        while (true) {
            if (i >= resc) {
                this.protocolProcessor = new ImmutableHttpProcessor(reqinterceptors, resinterceptors);
                return this.protocolProcessor;
            }
            resinterceptors[i] = proc.getResponseInterceptor(i);
            ++i;
        }
    }

    public final synchronized HttpRequestExecutor getRequestExecutor() {
        if (this.requestExec != null) return this.requestExec;
        this.requestExec = this.createRequestExecutor();
        return this.requestExec;
    }

    public synchronized void clearRequestInterceptors() {
        this.getHttpProcessor().clearRequestInterceptors();
        this.protocolProcessor = null;
    }

    public final synchronized CredentialsProvider getCredentialsProvider() {
        if (this.credsProvider != null) return this.credsProvider;
        this.credsProvider = this.createCredentialsProvider();
        return this.credsProvider;
    }

    public synchronized void setHttpRequestRetryHandler(HttpRequestRetryHandler handler) {
        this.retryHandler = handler;
    }
}
