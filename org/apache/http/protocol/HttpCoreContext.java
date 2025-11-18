/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpConnection
 *  org.apache.http.HttpHost
 *  org.apache.http.HttpRequest
 *  org.apache.http.HttpResponse
 *  org.apache.http.protocol.BasicHttpContext
 *  org.apache.http.protocol.HttpContext
 *  org.apache.http.util.Args
 */
package org.apache.http.protocol;

import org.apache.http.HttpConnection;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;

public class HttpCoreContext
implements HttpContext {
    public static final String HTTP_REQ_SENT = "http.request_sent";
    public static final String HTTP_TARGET_HOST = "http.target_host";
    public static final String HTTP_REQUEST = "http.request";
    public static final String HTTP_RESPONSE = "http.response";
    private final HttpContext context;
    public static final String HTTP_CONNECTION = "http.connection";

    public <T extends HttpConnection> T getConnection(Class<T> clazz) {
        return (T)((HttpConnection)this.getAttribute(HTTP_CONNECTION, clazz));
    }

    public HttpCoreContext() {
        this.context = new BasicHttpContext();
    }

    public static HttpCoreContext create() {
        return new HttpCoreContext((HttpContext)new BasicHttpContext());
    }

    public HttpHost getTargetHost() {
        return this.getAttribute(HTTP_TARGET_HOST, HttpHost.class);
    }

    public Object getAttribute(String id) {
        return this.context.getAttribute(id);
    }

    public boolean isRequestSent() {
        Boolean b = this.getAttribute(HTTP_REQ_SENT, Boolean.class);
        return b != null && b != false;
    }

    public Object removeAttribute(String id) {
        return this.context.removeAttribute(id);
    }

    public void setAttribute(String id, Object obj) {
        this.context.setAttribute(id, obj);
    }

    public void setTargetHost(HttpHost host) {
        this.setAttribute(HTTP_TARGET_HOST, host);
    }

    public HttpCoreContext(HttpContext context) {
        this.context = context;
    }

    public <T> T getAttribute(String attribname, Class<T> clazz) {
        Args.notNull(clazz, (String)"Attribute class");
        Object obj = this.getAttribute(attribname);
        if (obj != null) return clazz.cast(obj);
        return null;
    }

    public static HttpCoreContext adapt(HttpContext context) {
        Args.notNull((Object)context, (String)"HTTP context");
        return context instanceof HttpCoreContext ? (HttpCoreContext)context : new HttpCoreContext(context);
    }

    public HttpResponse getResponse() {
        return this.getAttribute(HTTP_RESPONSE, HttpResponse.class);
    }

    public HttpRequest getRequest() {
        return this.getAttribute(HTTP_REQUEST, HttpRequest.class);
    }

    public HttpConnection getConnection() {
        return this.getAttribute(HTTP_CONNECTION, HttpConnection.class);
    }
}
