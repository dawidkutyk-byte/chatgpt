/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpHost
 *  org.apache.http.client.config.RequestConfig
 */
package org.apache.http.client.config;

import java.net.InetAddress;
import java.util.Collection;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;

public static class RequestConfig.Builder {
    private InetAddress localAddress;
    private int socketTimeout = -1;
    private int maxRedirects = 50;
    private boolean contentCompressionEnabled = true;
    private boolean expectContinueEnabled;
    private boolean redirectsEnabled = true;
    private boolean normalizeUri = true;
    private Collection<String> proxyPreferredAuthSchemes;
    private int connectionRequestTimeout = -1;
    private HttpHost proxy;
    private boolean relativeRedirectsAllowed = true;
    private boolean circularRedirectsAllowed;
    private boolean authenticationEnabled = true;
    private int connectTimeout = -1;
    private boolean staleConnectionCheckEnabled = false;
    private Collection<String> targetPreferredAuthSchemes;
    private String cookieSpec;

    public RequestConfig.Builder setRedirectsEnabled(boolean redirectsEnabled) {
        this.redirectsEnabled = redirectsEnabled;
        return this;
    }

    public RequestConfig.Builder setCookieSpec(String cookieSpec) {
        this.cookieSpec = cookieSpec;
        return this;
    }

    public RequestConfig.Builder setContentCompressionEnabled(boolean contentCompressionEnabled) {
        this.contentCompressionEnabled = contentCompressionEnabled;
        return this;
    }

    public RequestConfig.Builder setLocalAddress(InetAddress localAddress) {
        this.localAddress = localAddress;
        return this;
    }

    public RequestConfig.Builder setProxyPreferredAuthSchemes(Collection<String> proxyPreferredAuthSchemes) {
        this.proxyPreferredAuthSchemes = proxyPreferredAuthSchemes;
        return this;
    }

    public RequestConfig.Builder setRelativeRedirectsAllowed(boolean relativeRedirectsAllowed) {
        this.relativeRedirectsAllowed = relativeRedirectsAllowed;
        return this;
    }

    public RequestConfig.Builder setExpectContinueEnabled(boolean expectContinueEnabled) {
        this.expectContinueEnabled = expectContinueEnabled;
        return this;
    }

    public RequestConfig.Builder setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
        return this;
    }

    public RequestConfig.Builder setAuthenticationEnabled(boolean authenticationEnabled) {
        this.authenticationEnabled = authenticationEnabled;
        return this;
    }

    RequestConfig.Builder() {
    }

    @Deprecated
    public RequestConfig.Builder setDecompressionEnabled(boolean decompressionEnabled) {
        this.contentCompressionEnabled = decompressionEnabled;
        return this;
    }

    public RequestConfig.Builder setTargetPreferredAuthSchemes(Collection<String> targetPreferredAuthSchemes) {
        this.targetPreferredAuthSchemes = targetPreferredAuthSchemes;
        return this;
    }

    public RequestConfig.Builder setCircularRedirectsAllowed(boolean circularRedirectsAllowed) {
        this.circularRedirectsAllowed = circularRedirectsAllowed;
        return this;
    }

    public RequestConfig.Builder setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    @Deprecated
    public RequestConfig.Builder setStaleConnectionCheckEnabled(boolean staleConnectionCheckEnabled) {
        this.staleConnectionCheckEnabled = staleConnectionCheckEnabled;
        return this;
    }

    public RequestConfig build() {
        return new RequestConfig(this.expectContinueEnabled, this.proxy, this.localAddress, this.staleConnectionCheckEnabled, this.cookieSpec, this.redirectsEnabled, this.relativeRedirectsAllowed, this.circularRedirectsAllowed, this.maxRedirects, this.authenticationEnabled, this.targetPreferredAuthSchemes, this.proxyPreferredAuthSchemes, this.connectionRequestTimeout, this.connectTimeout, this.socketTimeout, this.contentCompressionEnabled, this.normalizeUri);
    }

    public RequestConfig.Builder setProxy(HttpHost proxy) {
        this.proxy = proxy;
        return this;
    }

    public RequestConfig.Builder setNormalizeUri(boolean normalizeUri) {
        this.normalizeUri = normalizeUri;
        return this;
    }

    public RequestConfig.Builder setMaxRedirects(int maxRedirects) {
        this.maxRedirects = maxRedirects;
        return this;
    }

    public RequestConfig.Builder setConnectionRequestTimeout(int connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
        return this;
    }
}
