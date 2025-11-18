/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Header
 *  org.apache.http.HeaderIterator
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpResponse
 *  org.apache.http.ProtocolVersion
 *  org.apache.http.StatusLine
 *  org.apache.http.client.methods.CloseableHttpResponse
 *  org.apache.http.impl.execchain.ConnectionHolder
 *  org.apache.http.impl.execchain.ResponseEntityProxy
 *  org.apache.http.params.HttpParams
 */
package org.apache.http.impl.execchain;

import java.io.IOException;
import java.util.Locale;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.execchain.ConnectionHolder;
import org.apache.http.impl.execchain.ResponseEntityProxy;
import org.apache.http.params.HttpParams;

class HttpResponseProxy
implements CloseableHttpResponse {
    private final ConnectionHolder connHolder;
    private final HttpResponse original;

    public void removeHeaders(String name) {
        this.original.removeHeaders(name);
    }

    public void setHeader(String name, String value) {
        this.original.setHeader(name, value);
    }

    public void setParams(HttpParams params) {
        this.original.setParams(params);
    }

    public Header[] getAllHeaders() {
        return this.original.getAllHeaders();
    }

    public void setStatusLine(StatusLine statusline) {
        this.original.setStatusLine(statusline);
    }

    public boolean containsHeader(String name) {
        return this.original.containsHeader(name);
    }

    public void addHeader(Header header) {
        this.original.addHeader(header);
    }

    public StatusLine getStatusLine() {
        return this.original.getStatusLine();
    }

    public ProtocolVersion getProtocolVersion() {
        return this.original.getProtocolVersion();
    }

    public void setStatusLine(ProtocolVersion ver, int code, String reason) {
        this.original.setStatusLine(ver, code, reason);
    }

    public void setLocale(Locale loc) {
        this.original.setLocale(loc);
    }

    public void setStatusLine(ProtocolVersion ver, int code) {
        this.original.setStatusLine(ver, code);
    }

    public void removeHeader(Header header) {
        this.original.removeHeader(header);
    }

    public void setStatusCode(int code) throws IllegalStateException {
        this.original.setStatusCode(code);
    }

    public HttpResponseProxy(HttpResponse original, ConnectionHolder connHolder) {
        this.original = original;
        this.connHolder = connHolder;
        ResponseEntityProxy.enchance((HttpResponse)original, (ConnectionHolder)connHolder);
    }

    public HeaderIterator headerIterator() {
        return this.original.headerIterator();
    }

    public void addHeader(String name, String value) {
        this.original.addHeader(name, value);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("HttpResponseProxy{");
        sb.append(this.original);
        sb.append('}');
        return sb.toString();
    }

    public void setHeaders(Header[] headers) {
        this.original.setHeaders(headers);
    }

    public HttpEntity getEntity() {
        return this.original.getEntity();
    }

    public void setEntity(HttpEntity entity) {
        this.original.setEntity(entity);
    }

    public Header getFirstHeader(String name) {
        return this.original.getFirstHeader(name);
    }

    public void close() throws IOException {
        if (this.connHolder == null) return;
        this.connHolder.close();
    }

    public HttpParams getParams() {
        return this.original.getParams();
    }

    public Header[] getHeaders(String name) {
        return this.original.getHeaders(name);
    }

    public void setHeader(Header header) {
        this.original.setHeader(header);
    }

    public Locale getLocale() {
        return this.original.getLocale();
    }

    public void setReasonPhrase(String reason) throws IllegalStateException {
        this.original.setReasonPhrase(reason);
    }

    public Header getLastHeader(String name) {
        return this.original.getLastHeader(name);
    }

    public HeaderIterator headerIterator(String name) {
        return this.original.headerIterator(name);
    }
}
