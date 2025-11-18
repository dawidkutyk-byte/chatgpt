/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.entity.AbstractHttpEntity
 *  org.apache.http.entity.ContentType
 *  org.apache.http.util.Args
 */
package org.apache.http.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.util.Args;

public class InputStreamEntity
extends AbstractHttpEntity {
    private final long length;
    private final InputStream content;

    public InputStreamEntity(InputStream inStream, long length, ContentType contentType) {
        this.content = (InputStream)Args.notNull((Object)inStream, (String)"Source input stream");
        this.length = length;
        if (contentType == null) return;
        this.setContentType(contentType.toString());
    }

    public InputStreamEntity(InputStream inStream, ContentType contentType) {
        this(inStream, -1L, contentType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void writeTo(OutputStream outStream) throws IOException {
        Args.notNull((Object)outStream, (String)"Output stream");
        InputStream inStream = this.content;
        try {
            byte[] buffer = new byte[4096];
            if (this.length < 0L) {
                int readLen;
                while ((readLen = inStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, readLen);
                }
                return;
            }
            long remaining = this.length;
            while (remaining > 0L) {
                int readLen = inStream.read(buffer, 0, (int)Math.min(4096L, remaining));
                if (readLen == -1) return;
                outStream.write(buffer, 0, readLen);
                remaining -= (long)readLen;
            }
            return;
        }
        finally {
            inStream.close();
        }
    }

    public InputStream getContent() throws IOException {
        return this.content;
    }

    public long getContentLength() {
        return this.length;
    }

    public InputStreamEntity(InputStream inStream, long length) {
        this(inStream, length, null);
    }

    public InputStreamEntity(InputStream inStream) {
        this(inStream, -1L);
    }

    public boolean isStreaming() {
        return true;
    }

    public boolean isRepeatable() {
        return false;
    }
}
