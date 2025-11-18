/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpEntityEnclosingRequest
 *  org.apache.http.HttpException
 *  org.apache.http.HttpRequest
 *  org.apache.http.HttpRequestInterceptor
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.protocol.HttpContext
 *  org.apache.http.protocol.HttpDateGenerator
 *  org.apache.http.util.Args
 */
package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpDateGenerator;
import org.apache.http.util.Args;

@Contract(threading=ThreadingBehavior.SAFE)
public class RequestDate
implements HttpRequestInterceptor {
    private static final HttpDateGenerator DATE_GENERATOR = new HttpDateGenerator();

    public void process(HttpRequest request, HttpContext context) throws IOException, HttpException {
        Args.notNull((Object)request, (String)"HTTP request");
        if (!(request instanceof HttpEntityEnclosingRequest)) return;
        if (request.containsHeader("Date")) return;
        String httpdate = DATE_GENERATOR.getCurrentDate();
        request.setHeader("Date", httpdate);
    }
}
