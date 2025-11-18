/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.concurrent.Cancellable
 *  org.apache.http.conn.ConnectionReleaseTrigger
 */
package org.apache.http.client.methods;

import java.io.IOException;
import org.apache.http.concurrent.Cancellable;
import org.apache.http.conn.ConnectionReleaseTrigger;

class AbstractExecutionAwareRequest.2
implements Cancellable {
    final /* synthetic */ ConnectionReleaseTrigger val$releaseTrigger;

    public boolean cancel() {
        try {
            this.val$releaseTrigger.abortConnection();
            return true;
        }
        catch (IOException ex) {
            return false;
        }
    }

    AbstractExecutionAwareRequest.2(ConnectionReleaseTrigger connectionReleaseTrigger) {
        this.val$releaseTrigger = connectionReleaseTrigger;
    }
}
