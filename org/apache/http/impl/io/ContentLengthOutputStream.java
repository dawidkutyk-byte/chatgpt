/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.io.SessionOutputBuffer
 *  org.apache.http.util.Args
 */
package org.apache.http.impl.io;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.util.Args;

public class ContentLengthOutputStream
extends OutputStream {
    private final long contentLength;
    private final SessionOutputBuffer out;
    private boolean closed;
    private long total;

    @Override
    public void write(byte[] b) throws IOException {
        this.write(b, 0, b.length);
    }

    @Override
    public void close() throws IOException {
        if (this.closed) return;
        this.closed = true;
        this.out.flush();
    }

    public ContentLengthOutputStream(SessionOutputBuffer out, long contentLength) {
        this.out = (SessionOutputBuffer)Args.notNull((Object)out, (String)"Session output buffer");
        this.contentLength = Args.notNegative((long)contentLength, (String)"Content length");
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        if (this.closed) {
            throw new IOException("Attempted write to closed stream.");
        }
        if (this.total >= this.contentLength) return;
        int chunk = len;
        long max = this.contentLength - this.total;
        if ((long)chunk > max) {
            chunk = (int)max;
        }
        this.out.write(b, off, chunk);
        this.total += (long)chunk;
    }

    @Override
    public void write(int b) throws IOException {
        if (this.closed) {
            throw new IOException("Attempted write to closed stream.");
        }
        if (this.total >= this.contentLength) return;
        this.out.write(b);
        ++this.total;
    }

    @Override
    public void flush() throws IOException {
        this.out.flush();
    }
}
