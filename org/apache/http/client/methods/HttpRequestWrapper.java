/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpEntityEnclosingRequest
 *  org.apache.http.HttpHost
 *  org.apache.http.HttpRequest
 *  org.apache.http.ProtocolVersion
 *  org.apache.http.RequestLine
 *  org.apache.http.client.methods.HttpRequestWrapper$HttpEntityEnclosingRequestWrapper
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.message.AbstractHttpMessage
 *  org.apache.http.message.BasicRequestLine
 *  org.apache.http.params.HttpParams
 *  org.apache.http.util.Args
 */
package org.apache.http.client.methods;

import java.net.URI;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.client.methods.HttpRequestWrapper;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.message.BasicRequestLine;
import org.apache.http.params.HttpParams;
import org.apache.http.util.Args;

public class HttpRequestWrapper
extends AbstractHttpMessage
implements HttpUriRequest {
    private RequestLine requestLine;
    private final HttpRequest original;
    private ProtocolVersion version;
    private final HttpHost target;
    private final String method;
    private URI uri;

    public static HttpRequestWrapper wrap(HttpRequest request, HttpHost target) {
        Args.notNull((Object)request, (String)"HTTP request");
        return request instanceof HttpEntityEnclosingRequest ? new HttpEntityEnclosingRequestWrapper((HttpEntityEnclosingRequest)request, target) : new HttpRequestWrapper(request, target);
    }

    public HttpRequest getOriginal() {
        return this.original;
    }

    public ProtocolVersion getProtocolVersion() {
        return this.version != null ? this.version : this.original.getProtocolVersion();
    }

    public String toString() {
        return this.getRequestLine() + " " + this.headergroup;
    }

    public HttpHost getTarget() {
        return this.target;
    }

    public RequestLine getRequestLine() {
        if (this.requestLine != null) return this.requestLine;
        String requestUri = this.uri != null ? this.uri.toASCIIString() : this.original.getRequestLine().getUri();
        if (requestUri == null || requestUri.isEmpty()) {
            requestUri = "/";
        }
        this.requestLine = new BasicRequestLine(this.method, requestUri, this.getProtocolVersion());
        return this.requestLine;
    }

    private HttpRequestWrapper(HttpRequest request, HttpHost target) {
        this.original = (HttpRequest)Args.notNull((Object)request, (String)"HTTP request");
        this.target = target;
        this.version = this.original.getRequestLine().getProtocolVersion();
        this.method = this.original.getRequestLine().getMethod();
        this.uri = request instanceof HttpUriRequest ? ((HttpUriRequest)request).getURI() : null;
        this.setHeaders(request.getAllHeaders());
    }

    public URI getURI() {
        return this.uri;
    }

    public void abort() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    public String getMethod() {
        return this.method;
    }

    @Deprecated
    public HttpParams getParams() {
        if (this.params != null) return this.params;
        this.params = this.original.getParams().copy();
        return this.params;
    }

    public boolean isAborted() {
        return false;
    }

    public static HttpRequestWrapper wrap(HttpRequest request) {
        return HttpRequestWrapper.wrap(request, null);
    }

    public void setProtocolVersion(ProtocolVersion version) {
        this.version = version;
        this.requestLine = null;
    }

    public void setURI(URI uri) {
        this.uri = uri;
        this.requestLine = null;
    }
}
