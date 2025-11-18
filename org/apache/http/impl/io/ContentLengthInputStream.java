/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.ConnectionClosedException
 *  org.apache.http.io.BufferInfo
 *  org.apache.http.io.SessionInputBuffer
 *  org.apache.http.util.Args
 */
package org.apache.http.impl.io;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.ConnectionClosedException;
import org.apache.http.io.BufferInfo;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.util.Args;

public class ContentLengthInputStream
extends InputStream {
    private static final int BUFFER_SIZE = 2048;
    private SessionInputBuffer in = null;
    private long pos = 0L;
    private final long contentLength;
    private boolean closed = false;

    @Override
    public int read() throws IOException {
        if (this.closed) {
            throw new IOException("Attempted read from closed stream.");
        }
        if (this.pos >= this.contentLength) {
            return -1;
        }
        int b = this.in.read();
        if (b == -1) {
            if (this.pos >= this.contentLength) return b;
            throw new ConnectionClosedException("Premature end of Content-Length delimited message body (expected: %,d; received: %,d)", new Object[]{this.contentLength, this.pos});
        }
        ++this.pos;
        return b;
    }

    @Override
    public int read(byte[] b) throws IOException {
        return this.read(b, 0, b.length);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void close() throws IOException {
        if (this.closed) return;
        try {
            if (this.pos >= this.contentLength) return;
            byte[] buffer = new byte[2048];
            while (this.read(buffer) >= 0) {
            }
            return;
        }
        finally {
            this.closed = true;
        }
    }

    public ContentLengthInputStream(SessionInputBuffer in, long contentLength) {
        this.in = (SessionInputBuffer)Args.notNull((Object)in, (String)"Session input buffer");
        this.contentLength = Args.notNegative((long)contentLength, (String)"Content length");
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int readLen;
        if (this.closed) {
            throw new IOException("Attempted read from closed stream.");
        }
        if (this.pos >= this.contentLength) {
            return -1;
        }
        int chunk = len;
        if (this.pos + (long)len > this.contentLength) {
            chunk = (int)(this.contentLength - this.pos);
        }
        if ((readLen = this.in.read(b, off, chunk)) == -1 && this.pos < this.contentLength) {
            throw new ConnectionClosedException("Premature end of Content-Length delimited message body (expected: %,d; received: %,d)", new Object[]{this.contentLength, this.pos});
        }
        if (readLen <= 0) return readLen;
        this.pos += (long)readLen;
        return readLen;
    }

    @Override
    public long skip(long n) throws IOException {
        if (n <= 0L) {
            return 0L;
        }
        byte[] buffer = new byte[2048];
        long remaining = Math.min(n, this.contentLength - this.pos);
        long count = 0L;
        while (remaining > 0L) {
            int readLen = this.read(buffer, 0, (int)Math.min(2048L, remaining));
            if (readLen == -1) {
                return count;
            }
            count += (long)readLen;
            remaining -= (long)readLen;
        }
        return count;
    }

    @Override
    public int available() throws IOException {
        if (!(this.in instanceof BufferInfo)) return 0;
        int len = ((BufferInfo)this.in).length();
        return Math.min(len, (int)(this.contentLength - this.pos));
    }
}
