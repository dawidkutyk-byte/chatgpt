/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.logging.Log
 *  org.apache.http.HttpClientConnection
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.concurrent.Cancellable
 *  org.apache.http.conn.ConnectionReleaseTrigger
 *  org.apache.http.conn.HttpClientConnectionManager
 */
package org.apache.http.impl.execchain;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.logging.Log;
import org.apache.http.HttpClientConnection;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.concurrent.Cancellable;
import org.apache.http.conn.ConnectionReleaseTrigger;
import org.apache.http.conn.HttpClientConnectionManager;

@Contract(threading=ThreadingBehavior.SAFE)
class ConnectionHolder
implements Closeable,
Cancellable,
ConnectionReleaseTrigger {
    private final HttpClientConnection managedConn;
    private final Log log;
    private volatile TimeUnit timeUnit;
    private volatile boolean reusable;
    private volatile Object state;
    private final AtomicBoolean released;
    private volatile long validDuration;
    private final HttpClientConnectionManager manager;

    @Override
    public void close() throws IOException {
        this.releaseConnection(false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void releaseConnection(boolean reusable) {
        if (!this.released.compareAndSet(false, true)) return;
        HttpClientConnection httpClientConnection = this.managedConn;
        synchronized (httpClientConnection) {
            if (reusable) {
                this.manager.releaseConnection(this.managedConn, this.state, this.validDuration, this.timeUnit);
            } else {
                try {
                    this.managedConn.close();
                    this.log.debug((Object)"Connection discarded");
                }
                catch (IOException ex) {
                    if (!this.log.isDebugEnabled()) return;
                    this.log.debug((Object)ex.getMessage(), (Throwable)ex);
                }
                finally {
                    this.manager.releaseConnection(this.managedConn, null, 0L, TimeUnit.MILLISECONDS);
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setValidFor(long duration, TimeUnit timeUnit) {
        HttpClientConnection httpClientConnection = this.managedConn;
        synchronized (httpClientConnection) {
            this.validDuration = duration;
            this.timeUnit = timeUnit;
        }
    }

    public boolean isReusable() {
        return this.reusable;
    }

    public void markReusable() {
        this.reusable = true;
    }

    public void releaseConnection() {
        this.releaseConnection(this.reusable);
    }

    public boolean isReleased() {
        return this.released.get();
    }

    public void setState(Object state) {
        this.state = state;
    }

    public ConnectionHolder(Log log, HttpClientConnectionManager manager, HttpClientConnection managedConn) {
        this.log = log;
        this.manager = manager;
        this.managedConn = managedConn;
        this.released = new AtomicBoolean(false);
    }

    public void markNonReusable() {
        this.reusable = false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void abortConnection() {
        if (!this.released.compareAndSet(false, true)) return;
        HttpClientConnection httpClientConnection = this.managedConn;
        synchronized (httpClientConnection) {
            try {
                this.managedConn.shutdown();
                this.log.debug((Object)"Connection discarded");
            }
            catch (IOException ex) {
                if (!this.log.isDebugEnabled()) return;
                this.log.debug((Object)ex.getMessage(), (Throwable)ex);
            }
            finally {
                this.manager.releaseConnection(this.managedConn, null, 0L, TimeUnit.MILLISECONDS);
            }
        }
    }

    public boolean cancel() {
        boolean alreadyReleased = this.released.get();
        this.log.debug((Object)"Cancelling request execution");
        this.abortConnection();
        return !alreadyReleased;
    }
}
