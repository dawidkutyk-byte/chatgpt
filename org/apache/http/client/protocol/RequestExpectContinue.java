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
 *  org.apache.http.ProtocolVersion
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.client.config.RequestConfig
 *  org.apache.http.client.protocol.HttpClientContext
 *  org.apache.http.protocol.HttpContext
 *  org.apache.http.util.Args
 */
package org.apache.http.client.protocol;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class RequestExpectContinue
implements HttpRequestInterceptor {
    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        Args.notNull((Object)request, (String)"HTTP request");
        if (request.containsHeader("Expect")) return;
        if (!(request instanceof HttpEntityEnclosingRequest)) return;
        ProtocolVersion ver = request.getRequestLine().getProtocolVersion();
        HttpEntity entity = ((HttpEntityEnclosingRequest)request).getEntity();
        if (entity == null) return;
        if (entity.getContentLength() == 0L) return;
        if (ver.lessEquals((ProtocolVersion)HttpVersion.HTTP_1_0)) return;
        HttpClientContext clientContext = HttpClientContext.adapt((HttpContext)context);
        RequestConfig config = clientContext.getRequestConfig();
        if (!config.isExpectContinueEnabled()) return;
        request.addHeader("Expect", "100-continue");
    }
}
