/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.concurrent.Cancellable
 *  org.apache.http.conn.ClientConnectionRequest
 */
package org.apache.http.client.methods;

import org.apache.http.concurrent.Cancellable;
import org.apache.http.conn.ClientConnectionRequest;

class AbstractExecutionAwareRequest.1
implements Cancellable {
    final /* synthetic */ ClientConnectionRequest val$connRequest;

    AbstractExecutionAwareRequest.1(ClientConnectionRequest clientConnectionRequest) {
        this.val$connRequest = clientConnectionRequest;
    }

    public boolean cancel() {
        this.val$connRequest.abortRequest();
        return true;
    }
}
