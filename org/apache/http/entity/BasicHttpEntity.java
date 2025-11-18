/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.entity.AbstractHttpEntity
 *  org.apache.http.impl.io.EmptyInputStream
 *  org.apache.http.util.Args
 *  org.apache.http.util.Asserts
 */
package org.apache.http.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.impl.io.EmptyInputStream;
import org.apache.http.util.Args;
import org.apache.http.util.Asserts;

public class BasicHttpEntity
extends AbstractHttpEntity {
    private long length = -1L;
    private InputStream content;

    public InputStream getContent() throws IllegalStateException {
        Asserts.check((this.content != null ? 1 : 0) != 0, (String)"Content has not been provided");
        return this.content;
    }

    public long getContentLength() {
        return this.length;
    }

    public void setContent(InputStream inStream) {
        this.content = inStream;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void writeTo(OutputStream outStream) throws IOException {
        Args.notNull((Object)outStream, (String)"Output stream");
        InputStream inStream = this.getContent();
        try {
            int l;
            byte[] tmp = new byte[4096];
            while ((l = inStream.read(tmp)) != -1) {
                outStream.write(tmp, 0, l);
            }
            return;
        }
        finally {
            inStream.close();
        }
    }

    public boolean isStreaming() {
        return this.content != null && this.content != EmptyInputStream.INSTANCE;
    }

    public boolean isRepeatable() {
        return false;
    }

    public void setContentLength(long len) {
        this.length = len;
    }
}
