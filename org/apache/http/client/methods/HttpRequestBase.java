/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.ProtocolVersion
 *  org.apache.http.RequestLine
 *  org.apache.http.client.config.RequestConfig
 *  org.apache.http.client.methods.AbstractExecutionAwareRequest
 *  org.apache.http.client.methods.Configurable
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.message.BasicRequestLine
 *  org.apache.http.params.HttpParams
 *  org.apache.http.params.HttpProtocolParams
 */
package org.apache.http.client.methods;

import java.net.URI;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.AbstractExecutionAwareRequest;
import org.apache.http.client.methods.Configurable;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicRequestLine;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public abstract class HttpRequestBase
extends AbstractExecutionAwareRequest
implements Configurable,
HttpUriRequest {
    private URI uri;
    private RequestConfig config;
    private ProtocolVersion version;

    public ProtocolVersion getProtocolVersion() {
        return this.version != null ? this.version : HttpProtocolParams.getVersion((HttpParams)this.getParams());
    }

    public void started() {
    }

    public void releaseConnection() {
        this.reset();
    }

    public RequestConfig getConfig() {
        return this.config;
    }

    public String toString() {
        return this.getMethod() + " " + this.getURI() + " " + this.getProtocolVersion();
    }

    public void setConfig(RequestConfig config) {
        this.config = config;
    }

    public void setProtocolVersion(ProtocolVersion version) {
        this.version = version;
    }

    public void setURI(URI uri) {
        this.uri = uri;
    }

    public abstract String getMethod();

    public RequestLine getRequestLine() {
        String method = this.getMethod();
        ProtocolVersion ver = this.getProtocolVersion();
        URI uriCopy = this.getURI();
        String uritext = null;
        if (uriCopy != null) {
            uritext = uriCopy.toASCIIString();
        }
        if (uritext != null) {
            if (!uritext.isEmpty()) return new BasicRequestLine(method, uritext, ver);
        }
        uritext = "/";
        return new BasicRequestLine(method, uritext, ver);
    }

    public URI getURI() {
        return this.uri;
    }
}
