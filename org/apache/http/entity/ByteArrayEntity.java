/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.entity.AbstractHttpEntity
 *  org.apache.http.entity.ContentType
 *  org.apache.http.util.Args
 */
package org.apache.http.entity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.util.Args;

public class ByteArrayEntity
extends AbstractHttpEntity
implements Cloneable {
    private final int len;
    private final byte[] b;
    private final int off;
    @Deprecated
    protected final byte[] content;

    public boolean isStreaming() {
        return false;
    }

    public InputStream getContent() {
        return new ByteArrayInputStream(this.b, this.off, this.len);
    }

    public ByteArrayEntity(byte[] b, ContentType contentType) {
        Args.notNull((Object)b, (String)"Source byte array");
        this.content = b;
        this.b = b;
        this.off = 0;
        this.len = this.b.length;
        if (contentType == null) return;
        this.setContentType(contentType.toString());
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public ByteArrayEntity(byte[] b) {
        this(b, null);
    }

    public void writeTo(OutputStream outStream) throws IOException {
        Args.notNull((Object)outStream, (String)"Output stream");
        outStream.write(this.b, this.off, this.len);
        outStream.flush();
    }

    public boolean isRepeatable() {
        return true;
    }

    public long getContentLength() {
        return this.len;
    }

    public ByteArrayEntity(byte[] b, int off, int len, ContentType contentType) {
        Args.notNull((Object)b, (String)"Source byte array");
        if (off < 0) throw new IndexOutOfBoundsException("off: " + off + " len: " + len + " b.length: " + b.length);
        if (off > b.length) throw new IndexOutOfBoundsException("off: " + off + " len: " + len + " b.length: " + b.length);
        if (len < 0) throw new IndexOutOfBoundsException("off: " + off + " len: " + len + " b.length: " + b.length);
        if (off + len < 0) throw new IndexOutOfBoundsException("off: " + off + " len: " + len + " b.length: " + b.length);
        if (off + len > b.length) {
            throw new IndexOutOfBoundsException("off: " + off + " len: " + len + " b.length: " + b.length);
        }
        this.content = b;
        this.b = b;
        this.off = off;
        this.len = len;
        if (contentType == null) return;
        this.setContentType(contentType.toString());
    }

    public ByteArrayEntity(byte[] b, int off, int len) {
        this(b, off, len, null);
    }
}
