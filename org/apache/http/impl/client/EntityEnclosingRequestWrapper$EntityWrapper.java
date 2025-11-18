/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpEntity
 *  org.apache.http.entity.HttpEntityWrapper
 *  org.apache.http.impl.client.EntityEnclosingRequestWrapper
 */
package org.apache.http.impl.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.client.EntityEnclosingRequestWrapper;

/*
 * Exception performing whole class analysis ignored.
 */
class EntityEnclosingRequestWrapper.EntityWrapper
extends HttpEntityWrapper {
    public InputStream getContent() throws IOException {
        EntityEnclosingRequestWrapper.access$002((EntityEnclosingRequestWrapper)EntityEnclosingRequestWrapper.this, (boolean)true);
        return super.getContent();
    }

    EntityEnclosingRequestWrapper.EntityWrapper(HttpEntity entity) {
        super(entity);
    }

    public void consumeContent() throws IOException {
        EntityEnclosingRequestWrapper.access$002((EntityEnclosingRequestWrapper)EntityEnclosingRequestWrapper.this, (boolean)true);
        super.consumeContent();
    }

    public void writeTo(OutputStream outStream) throws IOException {
        EntityEnclosingRequestWrapper.access$002((EntityEnclosingRequestWrapper)EntityEnclosingRequestWrapper.this, (boolean)true);
        super.writeTo(outStream);
    }
}
