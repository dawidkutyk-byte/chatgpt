/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.io.SessionOutputBuffer
 */
package org.apache.http.impl.io;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.http.io.SessionOutputBuffer;

public class ChunkedOutputStream
extends OutputStream {
    private boolean wroteLastChunk = false;
    private final byte[] cache;
    private final SessionOutputBuffer out;
    private boolean closed = false;
    private int cachePosition = 0;

    @Override
    public void flush() throws IOException {
        this.flushCache();
        this.out.flush();
    }

    public void finish() throws IOException {
        if (this.wroteLastChunk) return;
        this.flushCache();
        this.writeClosingChunk();
        this.wroteLastChunk = true;
    }

    public ChunkedOutputStream(int bufferSize, SessionOutputBuffer out) {
        this.cache = new byte[bufferSize];
        this.out = out;
    }

    protected void writeClosingChunk() throws IOException {
        this.out.writeLine("0");
        this.out.writeLine("");
    }

    protected void flushCacheWithAppend(byte[] bufferToAppend, int off, int len) throws IOException {
        this.out.writeLine(Integer.toHexString(this.cachePosition + len));
        this.out.write(this.cache, 0, this.cachePosition);
        this.out.write(bufferToAppend, off, len);
        this.out.writeLine("");
        this.cachePosition = 0;
    }

    @Override
    public void write(int b) throws IOException {
        if (this.closed) {
            throw new IOException("Attempted write to closed stream.");
        }
        this.cache[this.cachePosition] = (byte)b;
        ++this.cachePosition;
        if (this.cachePosition != this.cache.length) return;
        this.flushCache();
    }

    @Deprecated
    public ChunkedOutputStream(SessionOutputBuffer out, int bufferSize) throws IOException {
        this(bufferSize, out);
    }

    @Override
    public void write(byte[] src, int off, int len) throws IOException {
        if (this.closed) {
            throw new IOException("Attempted write to closed stream.");
        }
        if (len >= this.cache.length - this.cachePosition) {
            this.flushCacheWithAppend(src, off, len);
        } else {
            System.arraycopy(src, off, this.cache, this.cachePosition, len);
            this.cachePosition += len;
        }
    }

    @Override
    public void close() throws IOException {
        if (this.closed) return;
        this.closed = true;
        this.finish();
        this.out.flush();
    }

    protected void flushCache() throws IOException {
        if (this.cachePosition <= 0) return;
        this.out.writeLine(Integer.toHexString(this.cachePosition));
        this.out.write(this.cache, 0, this.cachePosition);
        this.out.writeLine("");
        this.cachePosition = 0;
    }

    @Deprecated
    public ChunkedOutputStream(SessionOutputBuffer out) throws IOException {
        this(2048, out);
    }

    @Override
    public void write(byte[] b) throws IOException {
        this.write(b, 0, b.length);
    }
}
