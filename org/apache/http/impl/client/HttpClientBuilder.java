/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.ConnectionReuseStrategy
 *  org.apache.http.Header
 *  org.apache.http.HttpHost
 *  org.apache.http.HttpRequestInterceptor
 *  org.apache.http.HttpResponseInterceptor
 *  org.apache.http.auth.AuthSchemeProvider
 *  org.apache.http.client.AuthenticationStrategy
 *  org.apache.http.client.BackoffManager
 *  org.apache.http.client.ConnectionBackoffStrategy
 *  org.apache.http.client.CookieStore
 *  org.apache.http.client.CredentialsProvider
 *  org.apache.http.client.HttpRequestRetryHandler
 *  org.apache.http.client.RedirectStrategy
 *  org.apache.http.client.ServiceUnavailableRetryStrategy
 *  org.apache.http.client.UserTokenHandler
 *  org.apache.http.client.config.RequestConfig
 *  org.apache.http.client.entity.InputStreamFactory
 *  org.apache.http.client.protocol.RequestAcceptEncoding
 *  org.apache.http.client.protocol.RequestAddCookies
 *  org.apache.http.client.protocol.RequestAuthCache
 *  org.apache.http.client.protocol.RequestClientConnControl
 *  org.apache.http.client.protocol.RequestDefaultHeaders
 *  org.apache.http.client.protocol.RequestExpectContinue
 *  org.apache.http.client.protocol.ResponseContentEncoding
 *  org.apache.http.client.protocol.ResponseProcessCookies
 *  org.apache.http.config.ConnectionConfig
 *  org.apache.http.config.Lookup
 *  org.apache.http.config.Registry
 *  org.apache.http.config.RegistryBuilder
 *  org.apache.http.config.SocketConfig
 *  org.apache.http.conn.ConnectionKeepAliveStrategy
 *  org.apache.http.conn.DnsResolver
 *  org.apache.http.conn.HttpClientConnectionManager
 *  org.apache.http.conn.SchemePortResolver
 *  org.apache.http.conn.routing.HttpRoutePlanner
 *  org.apache.http.conn.socket.LayeredConnectionSocketFactory
 *  org.apache.http.conn.socket.PlainConnectionSocketFactory
 *  org.apache.http.conn.ssl.DefaultHostnameVerifier
 *  org.apache.http.conn.ssl.SSLConnectionSocketFactory
 *  org.apache.http.conn.ssl.X509HostnameVerifier
 *  org.apache.http.conn.util.PublicSuffixMatcher
 *  org.apache.http.conn.util.PublicSuffixMatcherLoader
 *  org.apache.http.cookie.CookieSpecProvider
 *  org.apache.http.impl.NoConnectionReuseStrategy
 *  org.apache.http.impl.auth.BasicSchemeFactory
 *  org.apache.http.impl.auth.DigestSchemeFactory
 *  org.apache.http.impl.auth.KerberosSchemeFactory
 *  org.apache.http.impl.auth.NTLMSchemeFactory
 *  org.apache.http.impl.auth.SPNegoSchemeFactory
 *  org.apache.http.impl.client.BasicCookieStore
 *  org.apache.http.impl.client.BasicCredentialsProvider
 *  org.apache.http.impl.client.CloseableHttpClient
 *  org.apache.http.impl.client.CookieSpecRegistries
 *  org.apache.http.impl.client.DefaultClientConnectionReuseStrategy
 *  org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy
 *  org.apache.http.impl.client.DefaultHttpRequestRetryHandler
 *  org.apache.http.impl.client.DefaultRedirectStrategy
 *  org.apache.http.impl.client.DefaultUserTokenHandler
 *  org.apache.http.impl.client.IdleConnectionEvictor
 *  org.apache.http.impl.client.InternalHttpClient
 *  org.apache.http.impl.client.NoopUserTokenHandler
 *  org.apache.http.impl.client.ProxyAuthenticationStrategy
 *  org.apache.http.impl.client.SystemDefaultCredentialsProvider
 *  org.apache.http.impl.client.TargetAuthenticationStrategy
 *  org.apache.http.impl.conn.DefaultProxyRoutePlanner
 *  org.apache.http.impl.conn.DefaultRoutePlanner
 *  org.apache.http.impl.conn.DefaultSchemePortResolver
 *  org.apache.http.impl.conn.PoolingHttpClientConnectionManager
 *  org.apache.http.impl.conn.SystemDefaultRoutePlanner
 *  org.apache.http.impl.execchain.BackoffStrategyExec
 *  org.apache.http.impl.execchain.ClientExecChain
 *  org.apache.http.impl.execchain.MainClientExec
 *  org.apache.http.impl.execchain.ProtocolExec
 *  org.apache.http.impl.execchain.RedirectExec
 *  org.apache.http.impl.execchain.RetryExec
 *  org.apache.http.impl.execchain.ServiceUnavailableRetryExec
 *  org.apache.http.protocol.HttpProcessor
 *  org.apache.http.protocol.HttpProcessorBuilder
 *  org.apache.http.protocol.HttpRequestExecutor
 *  org.apache.http.protocol.ImmutableHttpProcessor
 *  org.apache.http.protocol.RequestContent
 *  org.apache.http.protocol.RequestTargetHost
 *  org.apache.http.protocol.RequestUserAgent
 *  org.apache.http.ssl.SSLContexts
 *  org.apache.http.util.TextUtils
 *  org.apache.http.util.VersionInfo
 */
package org.apache.http.impl.client;

import java.io.Closeable;
import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.auth.AuthSchemeProvider;
import org.apache.http.client.AuthenticationStrategy;
import org.apache.http.client.BackoffManager;
import org.apache.http.client.ConnectionBackoffStrategy;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.ServiceUnavailableRetryStrategy;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.InputStreamFactory;
import org.apache.http.client.protocol.RequestAcceptEncoding;
import org.apache.http.client.protocol.RequestAddCookies;
import org.apache.http.client.protocol.RequestAuthCache;
import org.apache.http.client.protocol.RequestClientConnControl;
import org.apache.http.client.protocol.RequestDefaultHeaders;
import org.apache.http.client.protocol.RequestExpectContinue;
import org.apache.http.client.protocol.ResponseContentEncoding;
import org.apache.http.client.protocol.ResponseProcessCookies;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Lookup;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.SchemePortResolver;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.conn.util.PublicSuffixMatcherLoader;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.NoConnectionReuseStrategy;
import org.apache.http.impl.auth.BasicSchemeFactory;
import org.apache.http.impl.auth.DigestSchemeFactory;
import org.apache.http.impl.auth.KerberosSchemeFactory;
import org.apache.http.impl.auth.NTLMSchemeFactory;
import org.apache.http.impl.auth.SPNegoSchemeFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.CookieSpecRegistries;
import org.apache.http.impl.client.DefaultClientConnectionReuseStrategy;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.DefaultUserTokenHandler;
import org.apache.http.impl.client.IdleConnectionEvictor;
import org.apache.http.impl.client.InternalHttpClient;
import org.apache.http.impl.client.NoopUserTokenHandler;
import org.apache.http.impl.client.ProxyAuthenticationStrategy;
import org.apache.http.impl.client.SystemDefaultCredentialsProvider;
import org.apache.http.impl.client.TargetAuthenticationStrategy;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.DefaultRoutePlanner;
import org.apache.http.impl.conn.DefaultSchemePortResolver;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultRoutePlanner;
import org.apache.http.impl.execchain.BackoffStrategyExec;
import org.apache.http.impl.execchain.ClientExecChain;
import org.apache.http.impl.execchain.MainClientExec;
import org.apache.http.impl.execchain.ProtocolExec;
import org.apache.http.impl.execchain.RedirectExec;
import org.apache.http.impl.execchain.RetryExec;
import org.apache.http.impl.execchain.ServiceUnavailableRetryExec;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpProcessorBuilder;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.protocol.ImmutableHttpProcessor;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.protocol.RequestUserAgent;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.TextUtils;
import org.apache.http.util.VersionInfo;

public class HttpClientBuilder {
    private Map<String, InputStreamFactory> contentDecoderMap;
    private long connTimeToLive = -1L;
    private boolean authCachingDisabled;
    private Lookup<AuthSchemeProvider> authSchemeRegistry;
    private ConnectionReuseStrategy reuseStrategy;
    private AuthenticationStrategy targetAuthStrategy;
    private Lookup<CookieSpecProvider> cookieSpecRegistry;
    private LinkedList<HttpRequestInterceptor> requestLast;
    private String userAgent;
    private SSLContext sslContext;
    private LinkedList<HttpResponseInterceptor> responseFirst;
    private ConnectionBackoffStrategy connectionBackoffStrategy;
    private ServiceUnavailableRetryStrategy serviceUnavailStrategy;
    private RedirectStrategy redirectStrategy;
    private ConnectionKeepAliveStrategy keepAliveStrategy;
    private Collection<? extends Header> defaultHeaders;
    private SocketConfig defaultSocketConfig;
    private DnsResolver dnsResolver;
    private boolean cookieManagementDisabled;
    private AuthenticationStrategy proxyAuthStrategy;
    private boolean connectionStateDisabled;
    private List<Closeable> closeables;
    private HttpHost proxy;
    private HttpClientConnectionManager connManager;
    private int maxConnTotal = 0;
    private TimeUnit connTimeToLiveTimeUnit = TimeUnit.MILLISECONDS;
    private HttpProcessor httpprocessor;
    private SchemePortResolver schemePortResolver;
    private boolean systemProperties;
    private RequestConfig defaultRequestConfig;
    private CookieStore cookieStore;
    private boolean evictIdleConnections;
    private CredentialsProvider credentialsProvider;
    private TimeUnit maxIdleTimeUnit;
    private HttpRoutePlanner routePlanner;
    private int maxConnPerRoute = 0;
    private HttpRequestRetryHandler retryHandler;
    private long maxIdleTime;
    private boolean automaticRetriesDisabled;
    private BackoffManager backoffManager;
    private LinkedList<HttpRequestInterceptor> requestFirst;
    private boolean evictExpiredConnections;
    private LinkedList<HttpResponseInterceptor> responseLast;
    private PublicSuffixMatcher publicSuffixMatcher;
    private boolean defaultUserAgentDisabled;
    private LayeredConnectionSocketFactory sslSocketFactory;
    private boolean contentCompressionDisabled;
    private HostnameVerifier hostnameVerifier;
    private ConnectionConfig defaultConnectionConfig;
    private boolean connManagerShared;
    private HttpRequestExecutor requestExec;
    private boolean redirectHandlingDisabled;
    private UserTokenHandler userTokenHandler;

    public final HttpClientBuilder setConnectionTimeToLive(long connTimeToLive, TimeUnit connTimeToLiveTimeUnit) {
        this.connTimeToLive = connTimeToLive;
        this.connTimeToLiveTimeUnit = connTimeToLiveTimeUnit;
        return this;
    }

    public final HttpClientBuilder disableCookieManagement() {
        this.cookieManagementDisabled = true;
        return this;
    }

    protected void addCloseable(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        if (this.closeables == null) {
            this.closeables = new ArrayList<Closeable>();
        }
        this.closeables.add(closeable);
    }

    public final HttpClientBuilder addInterceptorFirst(HttpResponseInterceptor itcp) {
        if (itcp == null) {
            return this;
        }
        if (this.responseFirst == null) {
            this.responseFirst = new LinkedList();
        }
        this.responseFirst.addFirst(itcp);
        return this;
    }

    public static HttpClientBuilder create() {
        return new HttpClientBuilder();
    }

    public final HttpClientBuilder addInterceptorLast(HttpRequestInterceptor itcp) {
        if (itcp == null) {
            return this;
        }
        if (this.requestLast == null) {
            this.requestLast = new LinkedList();
        }
        this.requestLast.addLast(itcp);
        return this;
    }

    private static String[] split(String s) {
        if (!TextUtils.isBlank((CharSequence)s)) return s.split(" *, *");
        return null;
    }

    public final HttpClientBuilder setKeepAliveStrategy(ConnectionKeepAliveStrategy keepAliveStrategy) {
        this.keepAliveStrategy = keepAliveStrategy;
        return this;
    }

    public final HttpClientBuilder setConnectionBackoffStrategy(ConnectionBackoffStrategy connectionBackoffStrategy) {
        this.connectionBackoffStrategy = connectionBackoffStrategy;
        return this;
    }

    public final HttpClientBuilder setContentDecoderRegistry(Map<String, InputStreamFactory> contentDecoderMap) {
        this.contentDecoderMap = contentDecoderMap;
        return this;
    }

    public final HttpClientBuilder setSSLSocketFactory(LayeredConnectionSocketFactory sslSocketFactory) {
        this.sslSocketFactory = sslSocketFactory;
        return this;
    }

    public final HttpClientBuilder setBackoffManager(BackoffManager backoffManager) {
        this.backoffManager = backoffManager;
        return this;
    }

    public final HttpClientBuilder setRequestExecutor(HttpRequestExecutor requestExec) {
        this.requestExec = requestExec;
        return this;
    }

    public final HttpClientBuilder disableAuthCaching() {
        this.authCachingDisabled = true;
        return this;
    }

    protected ClientExecChain createMainExec(HttpRequestExecutor requestExec, HttpClientConnectionManager connManager, ConnectionReuseStrategy reuseStrategy, ConnectionKeepAliveStrategy keepAliveStrategy, HttpProcessor proxyHttpProcessor, AuthenticationStrategy targetAuthStrategy, AuthenticationStrategy proxyAuthStrategy, UserTokenHandler userTokenHandler) {
        return new MainClientExec(requestExec, connManager, reuseStrategy, keepAliveStrategy, proxyHttpProcessor, targetAuthStrategy, proxyAuthStrategy, userTokenHandler);
    }

    protected HttpClientBuilder() {
    }

    public final HttpClientBuilder setDefaultCookieStore(CookieStore cookieStore) {
        this.cookieStore = cookieStore;
        return this;
    }

    public final HttpClientBuilder setDefaultSocketConfig(SocketConfig config) {
        this.defaultSocketConfig = config;
        return this;
    }

    public final HttpClientBuilder disableConnectionState() {
        this.connectionStateDisabled = true;
        return this;
    }

    public final HttpClientBuilder useSystemProperties() {
        this.systemProperties = true;
        return this;
    }

    public final HttpClientBuilder disableAutomaticRetries() {
        this.automaticRetriesDisabled = true;
        return this;
    }

    public final HttpClientBuilder setRoutePlanner(HttpRoutePlanner routePlanner) {
        this.routePlanner = routePlanner;
        return this;
    }

    public final HttpClientBuilder setProxyAuthenticationStrategy(AuthenticationStrategy proxyAuthStrategy) {
        this.proxyAuthStrategy = proxyAuthStrategy;
        return this;
    }

    public final HttpClientBuilder setServiceUnavailableRetryStrategy(ServiceUnavailableRetryStrategy serviceUnavailStrategy) {
        this.serviceUnavailStrategy = serviceUnavailStrategy;
        return this;
    }

    public final HttpClientBuilder setMaxConnTotal(int maxConnTotal) {
        this.maxConnTotal = maxConnTotal;
        return this;
    }

    public final HttpClientBuilder evictIdleConnections(long maxIdleTime, TimeUnit maxIdleTimeUnit) {
        this.evictIdleConnections = true;
        this.maxIdleTime = maxIdleTime;
        this.maxIdleTimeUnit = maxIdleTimeUnit;
        return this;
    }

    public final HttpClientBuilder setDefaultHeaders(Collection<? extends Header> defaultHeaders) {
        this.defaultHeaders = defaultHeaders;
        return this;
    }

    public final HttpClientBuilder setDefaultRequestConfig(RequestConfig config) {
        this.defaultRequestConfig = config;
        return this;
    }

    public final HttpClientBuilder setConnectionManager(HttpClientConnectionManager connManager) {
        this.connManager = connManager;
        return this;
    }

    public final HttpClientBuilder setDefaultCredentialsProvider(CredentialsProvider credentialsProvider) {
        this.credentialsProvider = credentialsProvider;
        return this;
    }

    public final HttpClientBuilder setDefaultAuthSchemeRegistry(Lookup<AuthSchemeProvider> authSchemeRegistry) {
        this.authSchemeRegistry = authSchemeRegistry;
        return this;
    }

    public final HttpClientBuilder disableContentCompression() {
        this.contentCompressionDisabled = true;
        return this;
    }

    public final HttpClientBuilder setHttpProcessor(HttpProcessor httpprocessor) {
        this.httpprocessor = httpprocessor;
        return this;
    }

    public final HttpClientBuilder disableRedirectHandling() {
        this.redirectHandlingDisabled = true;
        return this;
    }

    public final HttpClientBuilder setSSLHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.hostnameVerifier = hostnameVerifier;
        return this;
    }

    @Deprecated
    public final HttpClientBuilder evictIdleConnections(Long maxIdleTime, TimeUnit maxIdleTimeUnit) {
        return this.evictIdleConnections((long)maxIdleTime, maxIdleTimeUnit);
    }

    public final HttpClientBuilder setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public final HttpClientBuilder addInterceptorFirst(HttpRequestInterceptor itcp) {
        if (itcp == null) {
            return this;
        }
        if (this.requestFirst == null) {
            this.requestFirst = new LinkedList();
        }
        this.requestFirst.addFirst(itcp);
        return this;
    }

    public final HttpClientBuilder setConnectionReuseStrategy(ConnectionReuseStrategy reuseStrategy) {
        this.reuseStrategy = reuseStrategy;
        return this;
    }

    public final HttpClientBuilder setTargetAuthenticationStrategy(AuthenticationStrategy targetAuthStrategy) {
        this.targetAuthStrategy = targetAuthStrategy;
        return this;
    }

    public final HttpClientBuilder setMaxConnPerRoute(int maxConnPerRoute) {
        this.maxConnPerRoute = maxConnPerRoute;
        return this;
    }

    public final HttpClientBuilder addInterceptorLast(HttpResponseInterceptor itcp) {
        if (itcp == null) {
            return this;
        }
        if (this.responseLast == null) {
            this.responseLast = new LinkedList();
        }
        this.responseLast.addLast(itcp);
        return this;
    }

    /*
     * WARNING - void declaration
     */
    public CloseableHttpClient build() {
        void var14_31;
        ArrayList<Closeable> closeablesCopy;
        Object defaultCredentialsProvider;
        CookieStore defaultCookieStore;
        Lookup cookieSpecRegistryCopy;
        Lookup<AuthSchemeProvider> lookup;
        ServiceUnavailableRetryStrategy serviceUnavailStrategyCopy;
        Object routePlannerCopy;
        String userAgentCopy;
        Object userTokenHandlerCopy;
        AuthenticationStrategy proxyAuthStrategyCopy;
        AuthenticationStrategy targetAuthStrategyCopy;
        ConnectionKeepAliveStrategy keepAliveStrategyCopy;
        Object reuseStrategyCopy;
        HttpClientConnectionManager connManagerCopy;
        HttpRequestExecutor requestExecCopy;
        PublicSuffixMatcher publicSuffixMatcherCopy = this.publicSuffixMatcher;
        if (publicSuffixMatcherCopy == null) {
            publicSuffixMatcherCopy = PublicSuffixMatcherLoader.getDefault();
        }
        if ((requestExecCopy = this.requestExec) == null) {
            requestExecCopy = new HttpRequestExecutor();
        }
        if ((connManagerCopy = this.connManager) == null) {
            String s;
            LayeredConnectionSocketFactory sslSocketFactoryCopy = this.sslSocketFactory;
            if (sslSocketFactoryCopy == null) {
                String[] supportedProtocols = this.systemProperties ? HttpClientBuilder.split(System.getProperty("https.protocols")) : null;
                String[] supportedCipherSuites = this.systemProperties ? HttpClientBuilder.split(System.getProperty("https.cipherSuites")) : null;
                HostnameVerifier hostnameVerifierCopy = this.hostnameVerifier;
                if (hostnameVerifierCopy == null) {
                    hostnameVerifierCopy = new DefaultHostnameVerifier(publicSuffixMatcherCopy);
                }
                sslSocketFactoryCopy = this.sslContext != null ? new SSLConnectionSocketFactory(this.sslContext, supportedProtocols, supportedCipherSuites, hostnameVerifierCopy) : (this.systemProperties ? new SSLConnectionSocketFactory((SSLSocketFactory)SSLSocketFactory.getDefault(), supportedProtocols, supportedCipherSuites, hostnameVerifierCopy) : new SSLConnectionSocketFactory(SSLContexts.createDefault(), hostnameVerifierCopy));
            }
            PoolingHttpClientConnectionManager poolingmgr = new PoolingHttpClientConnectionManager(RegistryBuilder.create().register("http", (Object)PlainConnectionSocketFactory.getSocketFactory()).register("https", (Object)sslSocketFactoryCopy).build(), null, null, this.dnsResolver, this.connTimeToLive, this.connTimeToLiveTimeUnit != null ? this.connTimeToLiveTimeUnit : TimeUnit.MILLISECONDS);
            if (this.defaultSocketConfig != null) {
                poolingmgr.setDefaultSocketConfig(this.defaultSocketConfig);
            }
            if (this.defaultConnectionConfig != null) {
                poolingmgr.setDefaultConnectionConfig(this.defaultConnectionConfig);
            }
            if (this.systemProperties && "true".equalsIgnoreCase(s = System.getProperty("http.keepAlive", "true"))) {
                s = System.getProperty("http.maxConnections", "5");
                int max = Integer.parseInt(s);
                poolingmgr.setDefaultMaxPerRoute(max);
                poolingmgr.setMaxTotal(2 * max);
            }
            if (this.maxConnTotal > 0) {
                poolingmgr.setMaxTotal(this.maxConnTotal);
            }
            if (this.maxConnPerRoute > 0) {
                poolingmgr.setDefaultMaxPerRoute(this.maxConnPerRoute);
            }
            connManagerCopy = poolingmgr;
        }
        if ((reuseStrategyCopy = this.reuseStrategy) == null) {
            String s;
            reuseStrategyCopy = this.systemProperties ? ("true".equalsIgnoreCase(s = System.getProperty("http.keepAlive", "true")) ? DefaultClientConnectionReuseStrategy.INSTANCE : NoConnectionReuseStrategy.INSTANCE) : DefaultClientConnectionReuseStrategy.INSTANCE;
        }
        if ((keepAliveStrategyCopy = this.keepAliveStrategy) == null) {
            keepAliveStrategyCopy = DefaultConnectionKeepAliveStrategy.INSTANCE;
        }
        if ((targetAuthStrategyCopy = this.targetAuthStrategy) == null) {
            targetAuthStrategyCopy = TargetAuthenticationStrategy.INSTANCE;
        }
        if ((proxyAuthStrategyCopy = this.proxyAuthStrategy) == null) {
            proxyAuthStrategyCopy = ProxyAuthenticationStrategy.INSTANCE;
        }
        if ((userTokenHandlerCopy = this.userTokenHandler) == null) {
            userTokenHandlerCopy = !this.connectionStateDisabled ? DefaultUserTokenHandler.INSTANCE : NoopUserTokenHandler.INSTANCE;
        }
        if ((userAgentCopy = this.userAgent) == null) {
            if (this.systemProperties) {
                userAgentCopy = System.getProperty("http.agent");
            }
            if (userAgentCopy == null && !this.defaultUserAgentDisabled) {
                userAgentCopy = VersionInfo.getUserAgent((String)"Apache-HttpClient", (String)"org.apache.http.client", this.getClass());
            }
        }
        ClientExecChain execChain = this.createMainExec(requestExecCopy, connManagerCopy, (ConnectionReuseStrategy)reuseStrategyCopy, keepAliveStrategyCopy, (HttpProcessor)new ImmutableHttpProcessor(new HttpRequestInterceptor[]{new RequestTargetHost(), new RequestUserAgent(userAgentCopy)}), targetAuthStrategyCopy, proxyAuthStrategyCopy, (UserTokenHandler)userTokenHandlerCopy);
        execChain = this.decorateMainExec(execChain);
        HttpProcessor httpprocessorCopy = this.httpprocessor;
        if (httpprocessorCopy == null) {
            HttpProcessorBuilder b = HttpProcessorBuilder.create();
            if (this.requestFirst != null) {
                for (HttpRequestInterceptor httpRequestInterceptor : this.requestFirst) {
                    b.addFirst(httpRequestInterceptor);
                }
            }
            if (this.responseFirst != null) {
                for (HttpResponseInterceptor httpResponseInterceptor : this.responseFirst) {
                    b.addFirst(httpResponseInterceptor);
                }
            }
            b.addAll(new HttpRequestInterceptor[]{new RequestDefaultHeaders(this.defaultHeaders), new RequestContent(), new RequestTargetHost(), new RequestClientConnControl(), new RequestUserAgent(userAgentCopy), new RequestExpectContinue()});
            if (!this.cookieManagementDisabled) {
                b.add((HttpRequestInterceptor)new RequestAddCookies());
            }
            if (!this.contentCompressionDisabled) {
                if (this.contentDecoderMap != null) {
                    ArrayList<String> encodings = new ArrayList<String>(this.contentDecoderMap.keySet());
                    Collections.sort(encodings);
                    b.add((HttpRequestInterceptor)new RequestAcceptEncoding(encodings));
                } else {
                    b.add((HttpRequestInterceptor)new RequestAcceptEncoding());
                }
            }
            if (!this.authCachingDisabled) {
                b.add((HttpRequestInterceptor)new RequestAuthCache());
            }
            if (!this.cookieManagementDisabled) {
                b.add((HttpResponseInterceptor)new ResponseProcessCookies());
            }
            if (!this.contentCompressionDisabled) {
                if (this.contentDecoderMap != null) {
                    RegistryBuilder b2 = RegistryBuilder.create();
                    for (Map.Entry<String, InputStreamFactory> entry : this.contentDecoderMap.entrySet()) {
                        b2.register(entry.getKey(), (Object)entry.getValue());
                    }
                    b.add((HttpResponseInterceptor)new ResponseContentEncoding((Lookup)b2.build()));
                } else {
                    b.add((HttpResponseInterceptor)new ResponseContentEncoding());
                }
            }
            if (this.requestLast != null) {
                for (HttpRequestInterceptor httpRequestInterceptor : this.requestLast) {
                    b.addLast(httpRequestInterceptor);
                }
            }
            if (this.responseLast != null) {
                for (HttpResponseInterceptor httpResponseInterceptor : this.responseLast) {
                    b.addLast(httpResponseInterceptor);
                }
            }
            httpprocessorCopy = b.build();
        }
        execChain = new ProtocolExec(execChain, httpprocessorCopy);
        execChain = this.decorateProtocolExec(execChain);
        if (!this.automaticRetriesDisabled) {
            HttpRequestRetryHandler retryHandlerCopy = this.retryHandler;
            if (retryHandlerCopy == null) {
                retryHandlerCopy = DefaultHttpRequestRetryHandler.INSTANCE;
            }
            execChain = new RetryExec(execChain, retryHandlerCopy);
        }
        if ((routePlannerCopy = this.routePlanner) == null) {
            SchemePortResolver schemePortResolverCopy = this.schemePortResolver;
            if (schemePortResolverCopy == null) {
                schemePortResolverCopy = DefaultSchemePortResolver.INSTANCE;
            }
            routePlannerCopy = this.proxy != null ? new DefaultProxyRoutePlanner(this.proxy, schemePortResolverCopy) : (this.systemProperties ? new SystemDefaultRoutePlanner(schemePortResolverCopy, ProxySelector.getDefault()) : new DefaultRoutePlanner(schemePortResolverCopy));
        }
        if ((serviceUnavailStrategyCopy = this.serviceUnavailStrategy) != null) {
            execChain = new ServiceUnavailableRetryExec(execChain, serviceUnavailStrategyCopy);
        }
        if (!this.redirectHandlingDisabled) {
            void var14_27;
            RedirectStrategy redirectStrategy = this.redirectStrategy;
            if (redirectStrategy == null) {
                DefaultRedirectStrategy defaultRedirectStrategy = DefaultRedirectStrategy.INSTANCE;
            }
            execChain = new RedirectExec(execChain, routePlannerCopy, (RedirectStrategy)var14_27);
        }
        if (this.backoffManager != null && this.connectionBackoffStrategy != null) {
            execChain = new BackoffStrategyExec(execChain, this.connectionBackoffStrategy, this.backoffManager);
        }
        if ((lookup = this.authSchemeRegistry) == null) {
            Registry registry = RegistryBuilder.create().register("Basic", (Object)new BasicSchemeFactory()).register("Digest", (Object)new DigestSchemeFactory()).register("NTLM", (Object)new NTLMSchemeFactory()).register("Negotiate", (Object)new SPNegoSchemeFactory()).register("Kerberos", (Object)new KerberosSchemeFactory()).build();
        }
        if ((cookieSpecRegistryCopy = this.cookieSpecRegistry) == null) {
            cookieSpecRegistryCopy = CookieSpecRegistries.createDefault((PublicSuffixMatcher)publicSuffixMatcherCopy);
        }
        if ((defaultCookieStore = this.cookieStore) == null) {
            defaultCookieStore = new BasicCookieStore();
        }
        if ((defaultCredentialsProvider = this.credentialsProvider) == null) {
            defaultCredentialsProvider = this.systemProperties ? new SystemDefaultCredentialsProvider() : new BasicCredentialsProvider();
        }
        ArrayList<Closeable> arrayList = closeablesCopy = this.closeables != null ? new ArrayList<Closeable>(this.closeables) : null;
        if (this.connManagerShared) return new InternalHttpClient(execChain, connManagerCopy, routePlannerCopy, cookieSpecRegistryCopy, (Lookup)var14_31, defaultCookieStore, defaultCredentialsProvider, this.defaultRequestConfig != null ? this.defaultRequestConfig : RequestConfig.DEFAULT, closeablesCopy);
        if (closeablesCopy == null) {
            closeablesCopy = new ArrayList(1);
        }
        HttpClientConnectionManager cm = connManagerCopy;
        if (this.evictExpiredConnections || this.evictIdleConnections) {
            IdleConnectionEvictor connectionEvictor = new IdleConnectionEvictor(cm, this.maxIdleTime > 0L ? this.maxIdleTime : 10L, this.maxIdleTimeUnit != null ? this.maxIdleTimeUnit : TimeUnit.SECONDS, this.maxIdleTime, this.maxIdleTimeUnit);
            closeablesCopy.add((Closeable)new /* Unavailable Anonymous Inner Class!! */);
            connectionEvictor.start();
        }
        closeablesCopy.add((Closeable)new /* Unavailable Anonymous Inner Class!! */);
        return new InternalHttpClient(execChain, connManagerCopy, routePlannerCopy, cookieSpecRegistryCopy, (Lookup)var14_31, defaultCookieStore, defaultCredentialsProvider, this.defaultRequestConfig != null ? this.defaultRequestConfig : RequestConfig.DEFAULT, closeablesCopy);
    }

    public final HttpClientBuilder setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
        return this;
    }

    public final HttpClientBuilder setUserTokenHandler(UserTokenHandler userTokenHandler) {
        this.userTokenHandler = userTokenHandler;
        return this;
    }

    public final HttpClientBuilder evictExpiredConnections() {
        this.evictExpiredConnections = true;
        return this;
    }

    public final HttpClientBuilder setRetryHandler(HttpRequestRetryHandler retryHandler) {
        this.retryHandler = retryHandler;
        return this;
    }

    public final HttpClientBuilder setDefaultCookieSpecRegistry(Lookup<CookieSpecProvider> cookieSpecRegistry) {
        this.cookieSpecRegistry = cookieSpecRegistry;
        return this;
    }

    public final HttpClientBuilder setSchemePortResolver(SchemePortResolver schemePortResolver) {
        this.schemePortResolver = schemePortResolver;
        return this;
    }

    protected ClientExecChain decorateMainExec(ClientExecChain mainExec) {
        return mainExec;
    }

    public final HttpClientBuilder setDefaultConnectionConfig(ConnectionConfig config) {
        this.defaultConnectionConfig = config;
        return this;
    }

    public final HttpClientBuilder setProxy(HttpHost proxy) {
        this.proxy = proxy;
        return this;
    }

    @Deprecated
    public final HttpClientBuilder setHostnameVerifier(X509HostnameVerifier hostnameVerifier) {
        this.hostnameVerifier = hostnameVerifier;
        return this;
    }

    public final HttpClientBuilder setDnsResolver(DnsResolver dnsResolver) {
        this.dnsResolver = dnsResolver;
        return this;
    }

    public final HttpClientBuilder setConnectionManagerShared(boolean shared) {
        this.connManagerShared = shared;
        return this;
    }

    public final HttpClientBuilder setPublicSuffixMatcher(PublicSuffixMatcher publicSuffixMatcher) {
        this.publicSuffixMatcher = publicSuffixMatcher;
        return this;
    }

    public final HttpClientBuilder disableDefaultUserAgent() {
        this.defaultUserAgentDisabled = true;
        return this;
    }

    protected ClientExecChain decorateProtocolExec(ClientExecChain protocolExec) {
        return protocolExec;
    }

    @Deprecated
    public final HttpClientBuilder setSslcontext(SSLContext sslcontext) {
        return this.setSSLContext(sslcontext);
    }

    public final HttpClientBuilder setSSLContext(SSLContext sslContext) {
        this.sslContext = sslContext;
        return this;
    }
}
