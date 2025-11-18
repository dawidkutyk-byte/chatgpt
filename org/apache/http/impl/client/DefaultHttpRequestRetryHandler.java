/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpEntityEnclosingRequest
 *  org.apache.http.HttpRequest
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.client.HttpRequestRetryHandler
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.client.protocol.HttpClientContext
 *  org.apache.http.impl.client.RequestWrapper
 *  org.apache.http.protocol.HttpContext
 *  org.apache.http.util.Args
 */
package org.apache.http.impl.client;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.net.ssl.SSLException;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.RequestWrapper;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class DefaultHttpRequestRetryHandler
implements HttpRequestRetryHandler {
    private final Set<Class<? extends IOException>> nonRetriableClasses;
    public static final DefaultHttpRequestRetryHandler INSTANCE = new DefaultHttpRequestRetryHandler();
    private final int retryCount;
    private final boolean requestSentRetryEnabled;

    protected boolean handleAsIdempotent(HttpRequest request) {
        return !(request instanceof HttpEntityEnclosingRequest);
    }

    public int getRetryCount() {
        return this.retryCount;
    }

    public DefaultHttpRequestRetryHandler(int retryCount, boolean requestSentRetryEnabled) {
        this(retryCount, requestSentRetryEnabled, Arrays.asList(InterruptedIOException.class, UnknownHostException.class, ConnectException.class, SSLException.class));
    }

    public boolean isRequestSentRetryEnabled() {
        return this.requestSentRetryEnabled;
    }

    public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
        Args.notNull((Object)exception, (String)"Exception parameter");
        Args.notNull((Object)context, (String)"HTTP context");
        if (executionCount > this.retryCount) {
            return false;
        }
        if (this.nonRetriableClasses.contains(exception.getClass())) {
            return false;
        }
        for (Class<? extends IOException> rejectException : this.nonRetriableClasses) {
            if (!rejectException.isInstance(exception)) continue;
            return false;
        }
        HttpClientContext clientContext = HttpClientContext.adapt((HttpContext)context);
        HttpRequest request = clientContext.getRequest();
        if (this.requestIsAborted(request)) {
            return false;
        }
        if (this.handleAsIdempotent(request)) {
            return true;
        }
        if (!clientContext.isRequestSent()) return true;
        if (!this.requestSentRetryEnabled) return false;
        return true;
    }

    @Deprecated
    protected boolean requestIsAborted(HttpRequest request) {
        HttpRequest req = request;
        if (!(request instanceof RequestWrapper)) return req instanceof HttpUriRequest && ((HttpUriRequest)req).isAborted();
        req = ((RequestWrapper)request).getOriginal();
        return req instanceof HttpUriRequest && ((HttpUriRequest)req).isAborted();
    }

    protected DefaultHttpRequestRetryHandler(int retryCount, boolean requestSentRetryEnabled, Collection<Class<? extends IOException>> clazzes) {
        this.retryCount = retryCount;
        this.requestSentRetryEnabled = requestSentRetryEnabled;
        this.nonRetriableClasses = new HashSet<Class<? extends IOException>>();
        Iterator<Class<? extends IOException>> i$ = clazzes.iterator();
        while (i$.hasNext()) {
            Class<? extends IOException> clazz = i$.next();
            this.nonRetriableClasses.add(clazz);
        }
    }

    public DefaultHttpRequestRetryHandler() {
        this(3, false);
    }
}
