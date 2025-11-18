/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.client.HttpClient
 *  org.apache.http.client.ResponseHandler
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.concurrent.FutureCallback
 *  org.apache.http.impl.client.FutureRequestExecutionMetrics
 *  org.apache.http.impl.client.HttpRequestFutureTask
 *  org.apache.http.impl.client.HttpRequestTaskCallable
 *  org.apache.http.protocol.HttpContext
 */
package org.apache.http.impl.client;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.client.FutureRequestExecutionMetrics;
import org.apache.http.impl.client.HttpRequestFutureTask;
import org.apache.http.impl.client.HttpRequestTaskCallable;
import org.apache.http.protocol.HttpContext;

@Contract(threading=ThreadingBehavior.SAFE)
public class FutureRequestExecutionService
implements Closeable {
    private final FutureRequestExecutionMetrics metrics = new FutureRequestExecutionMetrics();
    private final ExecutorService executorService;
    private final HttpClient httpclient;
    private final AtomicBoolean closed = new AtomicBoolean(false);

    public <T> HttpRequestFutureTask<T> execute(HttpUriRequest request, HttpContext context, ResponseHandler<T> responseHandler) {
        return this.execute(request, context, responseHandler, null);
    }

    @Override
    public void close() throws IOException {
        this.closed.set(true);
        this.executorService.shutdownNow();
        if (!(this.httpclient instanceof Closeable)) return;
        ((Closeable)this.httpclient).close();
    }

    public FutureRequestExecutionService(HttpClient httpclient, ExecutorService executorService) {
        this.httpclient = httpclient;
        this.executorService = executorService;
    }

    public FutureRequestExecutionMetrics metrics() {
        return this.metrics;
    }

    public <T> HttpRequestFutureTask<T> execute(HttpUriRequest request, HttpContext context, ResponseHandler<T> responseHandler, FutureCallback<T> callback) {
        if (this.closed.get()) {
            throw new IllegalStateException("Close has been called on this httpclient instance.");
        }
        this.metrics.getScheduledConnections().incrementAndGet();
        HttpRequestTaskCallable callable = new HttpRequestTaskCallable(this.httpclient, request, context, responseHandler, callback, this.metrics);
        HttpRequestFutureTask httpRequestFutureTask = new HttpRequestFutureTask(request, callable);
        this.executorService.execute((Runnable)httpRequestFutureTask);
        return httpRequestFutureTask;
    }
}
