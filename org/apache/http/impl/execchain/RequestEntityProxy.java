/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Header
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpEntityEnclosingRequest
 *  org.apache.http.HttpRequest
 */
package org.apache.http.impl.execchain;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;

class RequestEntityProxy
implements HttpEntity {
    private boolean consumed = false;
    private final HttpEntity original;

    static boolean isRepeatable(HttpRequest request) {
        if (!(request instanceof HttpEntityEnclosingRequest)) return true;
        HttpEntity entity = ((HttpEntityEnclosingRequest)request).getEntity();
        if (entity == null) return true;
        if (!RequestEntityProxy.isEnhanced(entity)) return entity.isRepeatable();
        RequestEntityProxy proxy = (RequestEntityProxy)entity;
        if (proxy.isConsumed()) return entity.isRepeatable();
        return true;
    }

    public InputStream getContent() throws IOException, IllegalStateException {
        return this.original.getContent();
    }

    public long getContentLength() {
        return this.original.getContentLength();
    }

    public Header getContentType() {
        return this.original.getContentType();
    }

    public void writeTo(OutputStream outStream) throws IOException {
        this.consumed = true;
        this.original.writeTo(outStream);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("RequestEntityProxy{");
        sb.append(this.original);
        sb.append('}');
        return sb.toString();
    }

    public boolean isStreaming() {
        return this.original.isStreaming();
    }

    public boolean isRepeatable() {
        return this.original.isRepeatable();
    }

    public void consumeContent() throws IOException {
        this.consumed = true;
        this.original.consumeContent();
    }

    RequestEntityProxy(HttpEntity original) {
        this.original = original;
    }

    static void enhance(HttpEntityEnclosingRequest request) {
        HttpEntity entity = request.getEntity();
        if (entity == null) return;
        if (entity.isRepeatable()) return;
        if (RequestEntityProxy.isEnhanced(entity)) return;
        request.setEntity((HttpEntity)new RequestEntityProxy(entity));
    }

    public HttpEntity getOriginal() {
        return this.original;
    }

    static boolean isEnhanced(HttpEntity entity) {
        return entity instanceof RequestEntityProxy;
    }

    public boolean isChunked() {
        return this.original.isChunked();
    }

    public boolean isConsumed() {
        return this.consumed;
    }

    public Header getContentEncoding() {
        return this.original.getContentEncoding();
    }
}
