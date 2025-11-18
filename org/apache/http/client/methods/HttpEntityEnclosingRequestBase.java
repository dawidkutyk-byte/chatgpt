/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Header
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpEntityEnclosingRequest
 *  org.apache.http.client.methods.HttpRequestBase
 *  org.apache.http.client.utils.CloneUtils
 */
package org.apache.http.client.methods;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.CloneUtils;

public abstract class HttpEntityEnclosingRequestBase
extends HttpRequestBase
implements HttpEntityEnclosingRequest {
    private HttpEntity entity;

    public HttpEntity getEntity() {
        return this.entity;
    }

    public Object clone() throws CloneNotSupportedException {
        HttpEntityEnclosingRequestBase clone = (HttpEntityEnclosingRequestBase)((Object)super.clone());
        if (this.entity == null) return clone;
        clone.entity = (HttpEntity)CloneUtils.cloneObject((Object)this.entity);
        return clone;
    }

    public boolean expectContinue() {
        Header expect = this.getFirstHeader("Expect");
        return expect != null && "100-continue".equalsIgnoreCase(expect.getValue());
    }

    public void setEntity(HttpEntity entity) {
        this.entity = entity;
    }
}
