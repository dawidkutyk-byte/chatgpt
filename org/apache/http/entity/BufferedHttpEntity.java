/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpEntity
 *  org.apache.http.entity.HttpEntityWrapper
 *  org.apache.http.util.Args
 */
package org.apache.http.entity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.util.Args;

public class BufferedHttpEntity
extends HttpEntityWrapper {
    private final byte[] buffer;

    public boolean isStreaming() {
        return this.buffer == null && super.isStreaming();
    }

    public boolean isRepeatable() {
        return true;
    }

    public void writeTo(OutputStream outStream) throws IOException {
        Args.notNull((Object)outStream, (String)"Output stream");
        if (this.buffer != null) {
            outStream.write(this.buffer);
        } else {
            super.writeTo(outStream);
        }
    }

    public InputStream getContent() throws IOException {
        return this.buffer != null ? new ByteArrayInputStream(this.buffer) : super.getContent();
    }

    public boolean isChunked() {
        return this.buffer == null && super.isChunked();
    }

    public BufferedHttpEntity(HttpEntity entity) throws IOException {
        super(entity);
        if (!entity.isRepeatable() || entity.getContentLength() < 0L) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            entity.writeTo((OutputStream)out);
            out.flush();
            this.buffer = out.toByteArray();
        } else {
            this.buffer = null;
        }
    }

    public long getContentLength() {
        return this.buffer != null ? (long)this.buffer.length : super.getContentLength();
    }
}
