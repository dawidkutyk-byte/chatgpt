/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.logging.Log
 *  org.apache.commons.logging.LogFactory
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpHost
 *  org.apache.http.HttpRequest
 *  org.apache.http.HttpResponse
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.client.HttpClient
 *  org.apache.http.client.ResponseHandler
 *  org.apache.http.client.ServiceUnavailableRetryStrategy
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.conn.ClientConnectionManager
 *  org.apache.http.impl.client.DefaultHttpClient
 *  org.apache.http.impl.client.DefaultServiceUnavailableRetryStrategy
 *  org.apache.http.params.HttpParams
 *  org.apache.http.protocol.HttpContext
 *  org.apache.http.util.Args
 *  org.apache.http.util.EntityUtils
 */
package org.apache.http.impl.client;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URI;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.ServiceUnavailableRetryStrategy;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultServiceUnavailableRetryStrategy;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;
import org.apache.http.util.EntityUtils;

@Deprecated
@Contract(threading=ThreadingBehavior.SAFE_CONDITIONAL)
public class AutoRetryHttpClient
implements HttpClient {
    private final HttpClient backend;
    private final ServiceUnavailableRetryStrategy retryStrategy;
    private final Log log = LogFactory.getLog(this.getClass());

    public HttpParams getParams() {
        return this.backend.getParams();
    }

    public HttpResponse execute(HttpUriRequest request, HttpContext context) throws IOException {
        URI uri = request.getURI();
        HttpHost httpHost = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
        return this.execute(httpHost, (HttpRequest)request, context);
    }

    public HttpResponse execute(HttpUriRequest request) throws IOException {
        HttpContext context = null;
        return this.execute(request, context);
    }

    public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler) throws IOException {
        return this.execute(request, responseHandler, null);
    }

    public AutoRetryHttpClient(HttpClient client) {
        this(client, (ServiceUnavailableRetryStrategy)new DefaultServiceUnavailableRetryStrategy());
    }

    public <T> T execute(HttpHost target, HttpRequest request, ResponseHandler<? extends T> responseHandler, HttpContext context) throws IOException {
        HttpResponse resp = this.execute(target, request, context);
        return (T)responseHandler.handleResponse(resp);
    }

    public <T> T execute(HttpHost target, HttpRequest request, ResponseHandler<? extends T> responseHandler) throws IOException {
        return this.execute(target, request, responseHandler, null);
    }

    public AutoRetryHttpClient() {
        this((HttpClient)new DefaultHttpClient(), (ServiceUnavailableRetryStrategy)new DefaultServiceUnavailableRetryStrategy());
    }

    public AutoRetryHttpClient(ServiceUnavailableRetryStrategy config) {
        this((HttpClient)new DefaultHttpClient(), config);
    }

    public HttpResponse execute(HttpHost target, HttpRequest request) throws IOException {
        HttpContext defaultContext = null;
        return this.execute(target, request, defaultContext);
    }

    public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler, HttpContext context) throws IOException {
        HttpResponse resp = this.execute(request, context);
        return (T)responseHandler.handleResponse(resp);
    }

    public ClientConnectionManager getConnectionManager() {
        return this.backend.getConnectionManager();
    }

    public HttpResponse execute(HttpHost target, HttpRequest request, HttpContext context) throws IOException {
        int c = 1;
        while (true) {
            HttpResponse response = this.backend.execute(target, request, context);
            try {
                if (!this.retryStrategy.retryRequest(response, c, context)) return response;
                EntityUtils.consume((HttpEntity)response.getEntity());
                long nextInterval = this.retryStrategy.getRetryInterval();
                try {
                    this.log.trace((Object)("Wait for " + nextInterval));
                    Thread.sleep(nextInterval);
                }
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new InterruptedIOException();
                }
            }
            catch (RuntimeException ex) {
                try {
                    EntityUtils.consume((HttpEntity)response.getEntity());
                }
                catch (IOException ioex) {
                    this.log.warn((Object)"I/O error consuming response content", (Throwable)ioex);
                }
                throw ex;
            }
            ++c;
        }
    }

    public AutoRetryHttpClient(HttpClient client, ServiceUnavailableRetryStrategy retryStrategy) {
        Args.notNull((Object)client, (String)"HttpClient");
        Args.notNull((Object)retryStrategy, (String)"ServiceUnavailableRetryStrategy");
        this.backend = client;
        this.retryStrategy = retryStrategy;
    }
}
