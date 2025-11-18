/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpEntityEnclosingRequest
 *  org.apache.http.HttpException
 *  org.apache.http.HttpRequest
 *  org.apache.http.HttpRequestInterceptor
 *  org.apache.http.HttpVersion
 *  org.apache.http.ProtocolException
 *  org.apache.http.ProtocolVersion
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.protocol.HttpContext
 *  org.apache.http.util.Args
 */
package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolException;
import org.apache.http.ProtocolVersion;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class RequestContent
implements HttpRequestInterceptor {
    private final boolean overwrite;

    public RequestContent() {
        this(false);
    }

    public RequestContent(boolean overwrite) {
        this.overwrite = overwrite;
    }

    public void process(HttpRequest request, HttpContext context) throws IOException, HttpException {
        Args.notNull((Object)request, (String)"HTTP request");
        if (!(request instanceof HttpEntityEnclosingRequest)) return;
        if (this.overwrite) {
            request.removeHeaders("Transfer-Encoding");
            request.removeHeaders("Content-Length");
        } else {
            if (request.containsHeader("Transfer-Encoding")) {
                throw new ProtocolException("Transfer-encoding header already present");
            }
            if (request.containsHeader("Content-Length")) {
                throw new ProtocolException("Content-Length header already present");
            }
        }
        ProtocolVersion ver = request.getRequestLine().getProtocolVersion();
        HttpEntity entity = ((HttpEntityEnclosingRequest)request).getEntity();
        if (entity == null) {
            request.addHeader("Content-Length", "0");
            return;
        }
        if (entity.isChunked() || entity.getContentLength() < 0L) {
            if (ver.lessEquals((ProtocolVersion)HttpVersion.HTTP_1_0)) {
                throw new ProtocolException("Chunked transfer encoding not allowed for " + ver);
            }
            request.addHeader("Transfer-Encoding", "chunked");
        } else {
            request.addHeader("Content-Length", Long.toString(entity.getContentLength()));
        }
        if (entity.getContentType() != null && !request.containsHeader("Content-Type")) {
            request.addHeader(entity.getContentType());
        }
        if (entity.getContentEncoding() == null) return;
        if (request.containsHeader("Content-Encoding")) return;
        request.addHeader(entity.getContentEncoding());
    }
}
