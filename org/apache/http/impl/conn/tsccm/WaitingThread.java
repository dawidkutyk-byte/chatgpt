/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.impl.conn.tsccm.RouteSpecificPool
 *  org.apache.http.util.Args
 */
package org.apache.http.impl.conn.tsccm;

import java.util.Date;
import java.util.concurrent.locks.Condition;
import org.apache.http.impl.conn.tsccm.RouteSpecificPool;
import org.apache.http.util.Args;

@Deprecated
public class WaitingThread {
    private final Condition cond;
    private boolean aborted;
    private final RouteSpecificPool pool;
    private Thread waiter;

    public final Thread getThread() {
        return this.waiter;
    }

    public void interrupt() {
        this.aborted = true;
        this.cond.signalAll();
    }

    public final Condition getCondition() {
        return this.cond;
    }

    public void wakeup() {
        if (this.waiter == null) {
            throw new IllegalStateException("Nobody waiting on this object.");
        }
        this.cond.signalAll();
    }

    public final RouteSpecificPool getPool() {
        return this.pool;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean await(Date deadline) throws InterruptedException {
        if (this.waiter != null) {
            throw new IllegalStateException("A thread is already waiting on this object.\ncaller: " + Thread.currentThread() + "\nwaiter: " + this.waiter);
        }
        if (this.aborted) {
            throw new InterruptedException("Operation interrupted");
        }
        this.waiter = Thread.currentThread();
        boolean success = false;
        try {
            if (deadline != null) {
                success = this.cond.awaitUntil(deadline);
            } else {
                this.cond.await();
                success = true;
            }
            if (!this.aborted) return success;
            throw new InterruptedException("Operation interrupted");
        }
        finally {
            this.waiter = null;
        }
    }

    public WaitingThread(Condition cond, RouteSpecificPool pool) {
        Args.notNull((Object)cond, (String)"Condition");
        this.cond = cond;
        this.pool = pool;
    }
}
