/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpRequest
 */
package org.apache.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;

public interface HttpEntityEnclosingRequest
extends HttpRequest {
    public HttpEntity getEntity();

    public void setEntity(HttpEntity var1);

    public boolean expectContinue();
}
