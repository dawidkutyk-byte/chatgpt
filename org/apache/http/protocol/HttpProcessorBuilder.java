/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpRequestInterceptor
 *  org.apache.http.HttpResponseInterceptor
 *  org.apache.http.protocol.ChainBuilder
 *  org.apache.http.protocol.HttpProcessor
 *  org.apache.http.protocol.ImmutableHttpProcessor
 */
package org.apache.http.protocol;

import java.util.List;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.ChainBuilder;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.ImmutableHttpProcessor;

public class HttpProcessorBuilder {
    private ChainBuilder<HttpResponseInterceptor> responseChainBuilder;
    private ChainBuilder<HttpRequestInterceptor> requestChainBuilder;

    public HttpProcessorBuilder addAllLast(HttpRequestInterceptor ... e) {
        if (e == null) {
            return this;
        }
        this.getRequestChainBuilder().addAllLast((Object[])e);
        return this;
    }

    private ChainBuilder<HttpResponseInterceptor> getResponseChainBuilder() {
        if (this.responseChainBuilder != null) return this.responseChainBuilder;
        this.responseChainBuilder = new ChainBuilder();
        return this.responseChainBuilder;
    }

    public HttpProcessorBuilder addLast(HttpResponseInterceptor e) {
        if (e == null) {
            return this;
        }
        this.getResponseChainBuilder().addLast((Object)e);
        return this;
    }

    HttpProcessorBuilder() {
    }

    public HttpProcessorBuilder add(HttpRequestInterceptor e) {
        return this.addLast(e);
    }

    public HttpProcessorBuilder add(HttpResponseInterceptor e) {
        return this.addLast(e);
    }

    public HttpProcessorBuilder addAllLast(HttpResponseInterceptor ... e) {
        if (e == null) {
            return this;
        }
        this.getResponseChainBuilder().addAllLast((Object[])e);
        return this;
    }

    public HttpProcessorBuilder addFirst(HttpResponseInterceptor e) {
        if (e == null) {
            return this;
        }
        this.getResponseChainBuilder().addFirst((Object)e);
        return this;
    }

    public static HttpProcessorBuilder create() {
        return new HttpProcessorBuilder();
    }

    public HttpProcessorBuilder addAllFirst(HttpResponseInterceptor ... e) {
        if (e == null) {
            return this;
        }
        this.getResponseChainBuilder().addAllFirst((Object[])e);
        return this;
    }

    public HttpProcessorBuilder addAllFirst(HttpRequestInterceptor ... e) {
        if (e == null) {
            return this;
        }
        this.getRequestChainBuilder().addAllFirst((Object[])e);
        return this;
    }

    public HttpProcessorBuilder addAll(HttpRequestInterceptor ... e) {
        return this.addAllLast(e);
    }

    public HttpProcessorBuilder addAll(HttpResponseInterceptor ... e) {
        return this.addAllLast(e);
    }

    private ChainBuilder<HttpRequestInterceptor> getRequestChainBuilder() {
        if (this.requestChainBuilder != null) return this.requestChainBuilder;
        this.requestChainBuilder = new ChainBuilder();
        return this.requestChainBuilder;
    }

    public HttpProcessorBuilder addLast(HttpRequestInterceptor e) {
        if (e == null) {
            return this;
        }
        this.getRequestChainBuilder().addLast((Object)e);
        return this;
    }

    public HttpProcessor build() {
        return new ImmutableHttpProcessor((List)(this.requestChainBuilder != null ? this.requestChainBuilder.build() : null), this.responseChainBuilder != null ? this.responseChainBuilder.build() : null);
    }

    public HttpProcessorBuilder addFirst(HttpRequestInterceptor e) {
        if (e == null) {
            return this;
        }
        this.getRequestChainBuilder().addFirst((Object)e);
        return this;
    }
}
