/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.ConnectionReuseStrategy
 *  org.apache.http.Header
 *  org.apache.http.HeaderIterator
 *  org.apache.http.HttpRequest
 *  org.apache.http.HttpResponse
 *  org.apache.http.HttpVersion
 *  org.apache.http.ParseException
 *  org.apache.http.ProtocolVersion
 *  org.apache.http.TokenIterator
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.message.BasicTokenIterator
 *  org.apache.http.protocol.HttpContext
 *  org.apache.http.util.Args
 */
package org.apache.http.impl;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.ProtocolVersion;
import org.apache.http.TokenIterator;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.message.BasicTokenIterator;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class DefaultConnectionReuseStrategy
implements ConnectionReuseStrategy {
    public static final DefaultConnectionReuseStrategy INSTANCE = new DefaultConnectionReuseStrategy();

    protected TokenIterator createTokenIterator(HeaderIterator hit) {
        return new BasicTokenIterator(hit);
    }

    public boolean keepAlive(HttpResponse response, HttpContext context) {
        HeaderIterator headerIterator;
        HttpRequest request;
        Args.notNull((Object)response, (String)"HTTP response");
        Args.notNull((Object)context, (String)"HTTP context");
        if (response.getStatusLine().getStatusCode() == 204) {
            Header teh;
            Header clh = response.getFirstHeader("Content-Length");
            if (clh != null) {
                try {
                    int contentLen = Integer.parseInt(clh.getValue());
                    if (contentLen > 0) {
                        return false;
                    }
                }
                catch (NumberFormatException ex) {
                    // empty catch block
                }
            }
            if ((teh = response.getFirstHeader("Transfer-Encoding")) != null) {
                return false;
            }
        }
        if ((request = (HttpRequest)context.getAttribute("http.request")) != null) {
            try {
                BasicTokenIterator ti = new BasicTokenIterator(request.headerIterator("Connection"));
                while (ti.hasNext()) {
                    String token = ti.nextToken();
                    if (!"Close".equalsIgnoreCase(token)) continue;
                    return false;
                }
            }
            catch (ParseException px) {
                return false;
            }
        }
        ProtocolVersion ver = response.getStatusLine().getProtocolVersion();
        Header teh = response.getFirstHeader("Transfer-Encoding");
        if (teh != null) {
            if (!"chunked".equalsIgnoreCase(teh.getValue())) {
                return false;
            }
        } else if (this.canResponseHaveBody(request, response)) {
            Header[] clhs = response.getHeaders("Content-Length");
            if (clhs.length != 1) return false;
            Header clh = clhs[0];
            try {
                long contentLen = Long.parseLong(clh.getValue());
                if (contentLen < 0L) {
                    return false;
                }
            }
            catch (NumberFormatException ex) {
                return false;
            }
        }
        if (!(headerIterator = response.headerIterator("Connection")).hasNext()) {
            headerIterator = response.headerIterator("Proxy-Connection");
        }
        if (!headerIterator.hasNext()) return !ver.lessEquals((ProtocolVersion)HttpVersion.HTTP_1_0);
        try {
            BasicTokenIterator ti = new BasicTokenIterator(headerIterator);
            boolean keepalive = false;
            while (ti.hasNext()) {
                String token = ti.nextToken();
                if ("Close".equalsIgnoreCase(token)) {
                    return false;
                }
                if (!"Keep-Alive".equalsIgnoreCase(token)) continue;
                keepalive = true;
            }
            if (!keepalive) return !ver.lessEquals((ProtocolVersion)HttpVersion.HTTP_1_0);
            return true;
        }
        catch (ParseException px) {
            return false;
        }
    }

    private boolean canResponseHaveBody(HttpRequest request, HttpResponse response) {
        if (request == null || !request.getRequestLine().getMethod().equalsIgnoreCase("HEAD")) int status;
        return (status = response.getStatusLine().getStatusCode()) >= 200 && status != 204 && status != 304 && status != 205;
        return false;
    }
}
