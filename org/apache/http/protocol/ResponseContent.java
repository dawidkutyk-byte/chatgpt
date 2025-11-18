/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpException
 *  org.apache.http.HttpResponse
 *  org.apache.http.HttpResponseInterceptor
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
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolException;
import org.apache.http.ProtocolVersion;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class ResponseContent
implements HttpResponseInterceptor {
    private final boolean overwrite;

    public ResponseContent(boolean overwrite) {
        this.overwrite = overwrite;
    }

    public ResponseContent() {
        this(false);
    }

    public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
        Args.notNull((Object)response, (String)"HTTP response");
        if (this.overwrite) {
            response.removeHeaders("Transfer-Encoding");
            response.removeHeaders("Content-Length");
        } else {
            if (response.containsHeader("Transfer-Encoding")) {
                throw new ProtocolException("Transfer-encoding header already present");
            }
            if (response.containsHeader("Content-Length")) {
                throw new ProtocolException("Content-Length header already present");
            }
        }
        ProtocolVersion ver = response.getStatusLine().getProtocolVersion();
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            long len = entity.getContentLength();
            if (entity.isChunked() && !ver.lessEquals((ProtocolVersion)HttpVersion.HTTP_1_0)) {
                response.addHeader("Transfer-Encoding", "chunked");
            } else if (len >= 0L) {
                response.addHeader("Content-Length", Long.toString(entity.getContentLength()));
            }
            if (entity.getContentType() != null && !response.containsHeader("Content-Type")) {
                response.addHeader(entity.getContentType());
            }
            if (entity.getContentEncoding() == null) return;
            if (response.containsHeader("Content-Encoding")) return;
            response.addHeader(entity.getContentEncoding());
        } else {
            int status = response.getStatusLine().getStatusCode();
            if (status == 204) return;
            if (status == 304) return;
            if (status == 205) return;
            response.addHeader("Content-Length", "0");
        }
    }
}
