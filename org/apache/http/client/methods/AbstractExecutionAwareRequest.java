/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpRequest
 *  org.apache.http.client.methods.AbortableHttpRequest
 *  org.apache.http.client.methods.HttpExecutionAware
 *  org.apache.http.client.utils.CloneUtils
 *  org.apache.http.concurrent.Cancellable
 *  org.apache.http.conn.ClientConnectionRequest
 *  org.apache.http.conn.ConnectionReleaseTrigger
 *  org.apache.http.message.AbstractHttpMessage
 *  org.apache.http.message.HeaderGroup
 *  org.apache.http.params.HttpParams
 */
package org.apache.http.client.methods;

import java.util.concurrent.atomic.AtomicMarkableReference;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.AbortableHttpRequest;
import org.apache.http.client.methods.HttpExecutionAware;
import org.apache.http.client.utils.CloneUtils;
import org.apache.http.concurrent.Cancellable;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectionReleaseTrigger;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.message.HeaderGroup;
import org.apache.http.params.HttpParams;

public abstract class AbstractExecutionAwareRequest
extends AbstractHttpMessage
implements AbortableHttpRequest,
HttpRequest,
Cloneable,
HttpExecutionAware {
    private final AtomicMarkableReference<Cancellable> cancellableRef = new AtomicMarkableReference<Object>(null, false);

    public boolean isAborted() {
        return this.cancellableRef.isMarked();
    }

    public void setCancellable(Cancellable cancellable) {
        Cancellable actualCancellable = this.cancellableRef.getReference();
        if (this.cancellableRef.compareAndSet(actualCancellable, cancellable, false, false)) return;
        cancellable.cancel();
    }

    @Deprecated
    public void completed() {
        this.cancellableRef.set(null, false);
    }

    public void reset() {
        boolean marked;
        Cancellable actualCancellable;
        do {
            marked = this.cancellableRef.isMarked();
            actualCancellable = this.cancellableRef.getReference();
            if (actualCancellable == null) continue;
            actualCancellable.cancel();
        } while (!this.cancellableRef.compareAndSet(actualCancellable, null, marked, false));
    }

    @Deprecated
    public void setConnectionRequest(ClientConnectionRequest connRequest) {
        this.setCancellable((Cancellable)new /* Unavailable Anonymous Inner Class!! */);
    }

    public Object clone() throws CloneNotSupportedException {
        AbstractExecutionAwareRequest clone = (AbstractExecutionAwareRequest)super.clone();
        clone.headergroup = (HeaderGroup)CloneUtils.cloneObject((Object)this.headergroup);
        clone.params = (HttpParams)CloneUtils.cloneObject((Object)this.params);
        return clone;
    }

    public void abort() {
        while (!this.cancellableRef.isMarked()) {
            Cancellable actualCancellable = this.cancellableRef.getReference();
            if (!this.cancellableRef.compareAndSet(actualCancellable, actualCancellable, false, true) || actualCancellable == null) continue;
            actualCancellable.cancel();
        }
    }

    protected AbstractExecutionAwareRequest() {
    }

    @Deprecated
    public void setReleaseTrigger(ConnectionReleaseTrigger releaseTrigger) {
        this.setCancellable((Cancellable)new /* Unavailable Anonymous Inner Class!! */);
    }
}
