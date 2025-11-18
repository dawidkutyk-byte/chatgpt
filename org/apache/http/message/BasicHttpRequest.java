/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpRequest
 *  org.apache.http.HttpVersion
 *  org.apache.http.ProtocolVersion
 *  org.apache.http.RequestLine
 *  org.apache.http.message.AbstractHttpMessage
 *  org.apache.http.message.BasicRequestLine
 *  org.apache.http.util.Args
 */
package org.apache.http.message;

import org.apache.http.HttpRequest;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.message.BasicRequestLine;
import org.apache.http.util.Args;

public class BasicHttpRequest
extends AbstractHttpMessage
implements HttpRequest {
    private RequestLine requestline;
    private final String uri;
    private final String method;

    public BasicHttpRequest(RequestLine requestline) {
        this.requestline = (RequestLine)Args.notNull((Object)requestline, (String)"Request line");
        this.method = requestline.getMethod();
        this.uri = requestline.getUri();
    }

    public String toString() {
        return this.method + ' ' + this.uri + ' ' + this.headergroup;
    }

    public BasicHttpRequest(String method, String uri, ProtocolVersion ver) {
        this((RequestLine)new BasicRequestLine(method, uri, ver));
    }

    public RequestLine getRequestLine() {
        if (this.requestline != null) return this.requestline;
        this.requestline = new BasicRequestLine(this.method, this.uri, (ProtocolVersion)HttpVersion.HTTP_1_1);
        return this.requestline;
    }

    public BasicHttpRequest(String method, String uri) {
        this.method = (String)Args.notNull((Object)method, (String)"Method name");
        this.uri = (String)Args.notNull((Object)uri, (String)"Request URI");
        this.requestline = null;
    }

    public ProtocolVersion getProtocolVersion() {
        return this.getRequestLine().getProtocolVersion();
    }
}
