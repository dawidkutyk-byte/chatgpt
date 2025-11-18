/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.impl.conn.tsccm.WaitingThread
 */
package org.apache.http.impl.conn.tsccm;

import org.apache.http.impl.conn.tsccm.WaitingThread;

@Deprecated
public class WaitingThreadAborter {
    private WaitingThread waitingThread;
    private boolean aborted;

    public void setWaitingThread(WaitingThread waitingThread) {
        this.waitingThread = waitingThread;
        if (!this.aborted) return;
        waitingThread.interrupt();
    }

    public void abort() {
        this.aborted = true;
        if (this.waitingThread == null) return;
        this.waitingThread.interrupt();
    }
}
