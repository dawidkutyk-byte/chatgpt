/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.concurrent.Cancellable
 *  org.apache.http.concurrent.FutureCallback
 *  org.apache.http.util.Args
 */
package org.apache.http.concurrent;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.http.concurrent.Cancellable;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.util.Args;

public class BasicFuture<T>
implements Future<T>,
Cancellable {
    private volatile boolean completed;
    private volatile T result;
    private volatile boolean cancelled;
    private volatile Exception ex;
    private final FutureCallback<T> callback;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean completed(T result) {
        BasicFuture basicFuture = this;
        synchronized (basicFuture) {
            if (this.completed) {
                return false;
            }
            this.completed = true;
            this.result = result;
            this.notifyAll();
        }
        if (this.callback == null) return true;
        this.callback.completed(result);
        return true;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public synchronized T get() throws InterruptedException, ExecutionException {
        while (!this.completed) {
            this.wait();
        }
        return this.getResult();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean failed(Exception exception) {
        BasicFuture basicFuture = this;
        synchronized (basicFuture) {
            if (this.completed) {
                return false;
            }
            this.completed = true;
            this.ex = exception;
            this.notifyAll();
        }
        if (this.callback == null) return true;
        this.callback.failed(exception);
        return true;
    }

    @Override
    public synchronized T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        Args.notNull((Object)((Object)unit), (String)"Time unit");
        long msecs = unit.toMillis(timeout);
        long startTime = msecs <= 0L ? 0L : System.currentTimeMillis();
        long waitTime = msecs;
        if (this.completed) {
            return this.getResult();
        }
        if (waitTime <= 0L) {
            throw new TimeoutException();
        }
        do {
            this.wait(waitTime);
            if (!this.completed) continue;
            return this.getResult();
        } while ((waitTime = msecs - (System.currentTimeMillis() - startTime)) > 0L);
        throw new TimeoutException();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        BasicFuture basicFuture = this;
        synchronized (basicFuture) {
            if (this.completed) {
                return false;
            }
            this.completed = true;
            this.cancelled = true;
            this.notifyAll();
        }
        if (this.callback == null) return true;
        this.callback.cancelled();
        return true;
    }

    public BasicFuture(FutureCallback<T> callback) {
        this.callback = callback;
    }

    public boolean cancel() {
        return this.cancel(true);
    }

    @Override
    public boolean isDone() {
        return this.completed;
    }

    private T getResult() throws ExecutionException {
        if (this.ex != null) {
            throw new ExecutionException(this.ex);
        }
        if (!this.cancelled) return this.result;
        throw new CancellationException();
    }
}
