/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Header
 *  org.apache.http.HeaderIterator
 *  org.apache.http.HttpRequest
 *  org.apache.http.HttpResponse
 *  org.apache.http.impl.DefaultConnectionReuseStrategy
 *  org.apache.http.message.BasicHeaderIterator
 *  org.apache.http.message.BasicTokenIterator
 *  org.apache.http.protocol.HttpContext
 */
package org.apache.http.impl.client;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.message.BasicHeaderIterator;
import org.apache.http.message.BasicTokenIterator;
import org.apache.http.protocol.HttpContext;

public class DefaultClientConnectionReuseStrategy
extends DefaultConnectionReuseStrategy {
    public static final DefaultClientConnectionReuseStrategy INSTANCE = new DefaultClientConnectionReuseStrategy();

    public boolean keepAlive(HttpResponse response, HttpContext context) {
        String token;
        HttpRequest request = (HttpRequest)context.getAttribute("http.request");
        if (request == null) return super.keepAlive(response, context);
        Header[] connHeaders = request.getHeaders("Connection");
        if (connHeaders.length == 0) return super.keepAlive(response, context);
        BasicTokenIterator ti = new BasicTokenIterator((HeaderIterator)new BasicHeaderIterator(connHeaders, null));
        do {
            if (!ti.hasNext()) return super.keepAlive(response, context);
        } while (!"Close".equalsIgnoreCase(token = ti.nextToken()));
        return false;
    }
}
