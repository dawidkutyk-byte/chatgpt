/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.ConnectionReuseStrategy
 *  org.apache.http.HttpException
 *  org.apache.http.HttpHost
 *  org.apache.http.HttpRequest
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.client.ClientProtocolException
 *  org.apache.http.client.config.RequestConfig
 *  org.apache.http.client.methods.CloseableHttpResponse
 *  org.apache.http.client.methods.Configurable
 *  org.apache.http.client.methods.HttpExecutionAware
 *  org.apache.http.client.methods.HttpRequestWrapper
 *  org.apache.http.client.protocol.HttpClientContext
 *  org.apache.http.conn.ClientConnectionManager
 *  org.apache.http.conn.ConnectionKeepAliveStrategy
 *  org.apache.http.conn.HttpClientConnectionManager
 *  org.apache.http.conn.routing.HttpRoute
 *  org.apache.http.impl.DefaultConnectionReuseStrategy
 *  org.apache.http.impl.client.CloseableHttpClient
 *  org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy
 *  org.apache.http.impl.execchain.MinimalClientExec
 *  org.apache.http.params.BasicHttpParams
 *  org.apache.http.params.HttpParams
 *  org.apache.http.protocol.BasicHttpContext
 *  org.apache.http.protocol.HttpContext
 *  org.apache.http.protocol.HttpRequestExecutor
 *  org.apache.http.util.Args
 */
package org.apache.http.impl.client;

import java.io.IOException;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.Configurable;
import org.apache.http.client.methods.HttpExecutionAware;
import org.apache.http.client.methods.HttpRequestWrapper;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.execchain.MinimalClientExec;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.util.Args;

@Contract(threading=ThreadingBehavior.SAFE_CONDITIONAL)
class MinimalHttpClient
extends CloseableHttpClient {
    private final MinimalClientExec requestExecutor;
    private final HttpParams params;
    private final HttpClientConnectionManager connManager;

    public HttpParams getParams() {
        return this.params;
    }

    public MinimalHttpClient(HttpClientConnectionManager connManager) {
        this.connManager = (HttpClientConnectionManager)Args.notNull((Object)connManager, (String)"HTTP connection manager");
        this.requestExecutor = new MinimalClientExec(new HttpRequestExecutor(), connManager, (ConnectionReuseStrategy)DefaultConnectionReuseStrategy.INSTANCE, (ConnectionKeepAliveStrategy)DefaultConnectionKeepAliveStrategy.INSTANCE);
        this.params = new BasicHttpParams();
    }

    public ClientConnectionManager getConnectionManager() {
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    static /* synthetic */ HttpClientConnectionManager access$000(MinimalHttpClient x0) {
        return x0.connManager;
    }

    public void close() {
        this.connManager.shutdown();
    }

    protected CloseableHttpResponse doExecute(HttpHost target, HttpRequest request, HttpContext context) throws ClientProtocolException, IOException {
        Args.notNull((Object)target, (String)"Target host");
        Args.notNull((Object)request, (String)"HTTP request");
        HttpExecutionAware execAware = null;
        if (request instanceof HttpExecutionAware) {
            execAware = (HttpExecutionAware)request;
        }
        try {
            HttpRequestWrapper wrapper = HttpRequestWrapper.wrap((HttpRequest)request);
            HttpClientContext localcontext = HttpClientContext.adapt((HttpContext)(context != null ? context : new BasicHttpContext()));
            HttpRoute route = new HttpRoute(target);
            RequestConfig config = null;
            if (request instanceof Configurable) {
                config = ((Configurable)request).getConfig();
            }
            if (config == null) return this.requestExecutor.execute(route, wrapper, localcontext, execAware);
            localcontext.setRequestConfig(config);
            return this.requestExecutor.execute(route, wrapper, localcontext, execAware);
        }
        catch (HttpException httpException) {
            throw new ClientProtocolException((Throwable)httpException);
        }
    }
}
