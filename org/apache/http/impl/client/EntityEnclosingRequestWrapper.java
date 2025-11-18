/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Header
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpEntityEnclosingRequest
 *  org.apache.http.HttpRequest
 *  org.apache.http.ProtocolException
 *  org.apache.http.impl.client.EntityEnclosingRequestWrapper$EntityWrapper
 *  org.apache.http.impl.client.RequestWrapper
 */
package org.apache.http.impl.client;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.ProtocolException;
import org.apache.http.impl.client.EntityEnclosingRequestWrapper;
import org.apache.http.impl.client.RequestWrapper;

@Deprecated
public class EntityEnclosingRequestWrapper
extends RequestWrapper
implements HttpEntityEnclosingRequest {
    private boolean consumed;
    private HttpEntity entity;

    public boolean isRepeatable() {
        return this.entity == null || this.entity.isRepeatable() || !this.consumed;
    }

    public boolean expectContinue() {
        Header expect = this.getFirstHeader("Expect");
        return expect != null && "100-continue".equalsIgnoreCase(expect.getValue());
    }

    public void setEntity(HttpEntity entity) {
        this.entity = entity != null ? new EntityWrapper(this, entity) : null;
        this.consumed = false;
    }

    public EntityEnclosingRequestWrapper(HttpEntityEnclosingRequest request) throws ProtocolException {
        super((HttpRequest)request);
        this.setEntity(request.getEntity());
    }

    public HttpEntity getEntity() {
        return this.entity;
    }

    static /* synthetic */ boolean access$002(EntityEnclosingRequestWrapper x0, boolean x1) {
        x0.consumed = x1;
        return x0.consumed;
    }
}
