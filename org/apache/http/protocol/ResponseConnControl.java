/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Header
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpException
 *  org.apache.http.HttpRequest
 *  org.apache.http.HttpResponse
 *  org.apache.http.HttpResponseInterceptor
 *  org.apache.http.HttpVersion
 *  org.apache.http.ProtocolVersion
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.protocol.HttpContext
 *  org.apache.http.protocol.HttpCoreContext
 *  org.apache.http.util.Args
 */
package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpCoreContext;
import org.apache.http.util.Args;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class ResponseConnControl
implements HttpResponseInterceptor {
    public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
        HttpRequest request;
        Args.notNull((Object)response, (String)"HTTP response");
        HttpCoreContext corecontext = HttpCoreContext.adapt((HttpContext)context);
        int status = response.getStatusLine().getStatusCode();
        if (status == 400 || status == 408 || status == 411 || status == 413 || status == 414 || status == 503 || status == 501) {
            response.setHeader("Connection", "Close");
            return;
        }
        Header explicit = response.getFirstHeader("Connection");
        if (explicit != null && "Close".equalsIgnoreCase(explicit.getValue())) {
            return;
        }
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            ProtocolVersion ver = response.getStatusLine().getProtocolVersion();
            if (entity.getContentLength() < 0L && (!entity.isChunked() || ver.lessEquals((ProtocolVersion)HttpVersion.HTTP_1_0))) {
                response.setHeader("Connection", "Close");
                return;
            }
        }
        if ((request = corecontext.getRequest()) == null) return;
        Header header = request.getFirstHeader("Connection");
        if (header != null) {
            response.setHeader("Connection", header.getValue());
        } else {
            if (!request.getProtocolVersion().lessEquals((ProtocolVersion)HttpVersion.HTTP_1_0)) return;
            response.setHeader("Connection", "Close");
        }
    }
}
