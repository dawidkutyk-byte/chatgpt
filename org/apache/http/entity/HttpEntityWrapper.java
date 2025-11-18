/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Header
 *  org.apache.http.HttpEntity
 *  org.apache.http.util.Args
 */
package org.apache.http.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.util.Args;

public class HttpEntityWrapper
implements HttpEntity {
    protected HttpEntity wrappedEntity;

    public boolean isChunked() {
        return this.wrappedEntity.isChunked();
    }

    public Header getContentEncoding() {
        return this.wrappedEntity.getContentEncoding();
    }

    public boolean isRepeatable() {
        return this.wrappedEntity.isRepeatable();
    }

    public HttpEntityWrapper(HttpEntity wrappedEntity) {
        this.wrappedEntity = (HttpEntity)Args.notNull((Object)wrappedEntity, (String)"Wrapped entity");
    }

    @Deprecated
    public void consumeContent() throws IOException {
        this.wrappedEntity.consumeContent();
    }

    public Header getContentType() {
        return this.wrappedEntity.getContentType();
    }

    public long getContentLength() {
        return this.wrappedEntity.getContentLength();
    }

    public boolean isStreaming() {
        return this.wrappedEntity.isStreaming();
    }

    public InputStream getContent() throws IOException {
        return this.wrappedEntity.getContent();
    }

    public void writeTo(OutputStream outStream) throws IOException {
        this.wrappedEntity.writeTo(outStream);
    }
}
