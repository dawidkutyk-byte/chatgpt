/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.impl.client.HttpRequestTaskCallable
 */
package org.apache.http.impl.client;

import java.util.concurrent.FutureTask;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpRequestTaskCallable;

public class HttpRequestFutureTask<V>
extends FutureTask<V> {
    private final HttpUriRequest request;
    private final HttpRequestTaskCallable<V> callable;

    public HttpRequestFutureTask(HttpUriRequest request, HttpRequestTaskCallable<V> httpCallable) {
        super(httpCallable);
        this.request = request;
        this.callable = httpCallable;
    }

    public long scheduledTime() {
        return this.callable.getScheduled();
    }

    public long requestDuration() {
        if (!this.isDone()) throw new IllegalStateException("Task is not done yet");
        return this.endedTime() - this.startedTime();
    }

    public long endedTime() {
        if (!this.isDone()) throw new IllegalStateException("Task is not done yet");
        return this.callable.getEnded();
    }

    @Override
    public String toString() {
        return this.request.getRequestLine().getUri();
    }

    public long startedTime() {
        return this.callable.getStarted();
    }

    public long taskDuration() {
        if (!this.isDone()) throw new IllegalStateException("Task is not done yet");
        return this.endedTime() - this.scheduledTime();
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        this.callable.cancel();
        if (!mayInterruptIfRunning) return super.cancel(mayInterruptIfRunning);
        this.request.abort();
        return super.cancel(mayInterruptIfRunning);
    }
}
