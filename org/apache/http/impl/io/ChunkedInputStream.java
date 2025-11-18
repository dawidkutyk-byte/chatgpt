/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.ConnectionClosedException
 *  org.apache.http.Header
 *  org.apache.http.HttpException
 *  org.apache.http.MalformedChunkCodingException
 *  org.apache.http.TruncatedChunkException
 *  org.apache.http.config.MessageConstraints
 *  org.apache.http.impl.io.AbstractMessageParser
 *  org.apache.http.io.BufferInfo
 *  org.apache.http.io.SessionInputBuffer
 *  org.apache.http.util.Args
 *  org.apache.http.util.CharArrayBuffer
 */
package org.apache.http.impl.io;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.ConnectionClosedException;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.MalformedChunkCodingException;
import org.apache.http.TruncatedChunkException;
import org.apache.http.config.MessageConstraints;
import org.apache.http.impl.io.AbstractMessageParser;
import org.apache.http.io.BufferInfo;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;

public class ChunkedInputStream
extends InputStream {
    private boolean closed = false;
    private static final int CHUNK_INVALID = Integer.MAX_VALUE;
    private Header[] footers = new Header[0];
    private long chunkSize;
    private boolean eof = false;
    private static final int CHUNK_DATA = 2;
    private static final int BUFFER_SIZE = 2048;
    private static final int CHUNK_LEN = 1;
    private final CharArrayBuffer buffer;
    private int state;
    private final SessionInputBuffer in;
    private static final int CHUNK_CRLF = 3;
    private final MessageConstraints constraints;
    private long pos;

    private void parseTrailerHeaders() throws IOException {
        try {
            this.footers = AbstractMessageParser.parseHeaders((SessionInputBuffer)this.in, (int)this.constraints.getMaxHeaderCount(), (int)this.constraints.getMaxLineLength(), null);
        }
        catch (HttpException ex) {
            MalformedChunkCodingException ioe = new MalformedChunkCodingException("Invalid footer: " + ex.getMessage());
            ioe.initCause(ex);
            throw ioe;
        }
    }

    public ChunkedInputStream(SessionInputBuffer in, MessageConstraints constraints) {
        this.in = (SessionInputBuffer)Args.notNull((Object)in, (String)"Session input buffer");
        this.pos = 0L;
        this.buffer = new CharArrayBuffer(16);
        this.constraints = constraints != null ? constraints : MessageConstraints.DEFAULT;
        this.state = 1;
    }

    @Override
    public int read() throws IOException {
        int b;
        if (this.closed) {
            throw new IOException("Attempted read from closed stream.");
        }
        if (this.eof) {
            return -1;
        }
        if (this.state != 2) {
            this.nextChunk();
            if (this.eof) {
                return -1;
            }
        }
        if ((b = this.in.read()) == -1) return b;
        ++this.pos;
        if (this.pos < this.chunkSize) return b;
        this.state = 3;
        return b;
    }

    @Override
    public int read(byte[] b) throws IOException {
        return this.read(b, 0, b.length);
    }

    public Header[] getFooters() {
        return (Header[])this.footers.clone();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void close() throws IOException {
        if (this.closed) return;
        try {
            if (this.eof) return;
            if (this.state == Integer.MAX_VALUE) return;
            byte[] buff = new byte[2048];
            while (this.read(buff) >= 0) {
            }
            return;
        }
        finally {
            this.eof = true;
            this.closed = true;
        }
    }

    @Override
    public int available() throws IOException {
        if (!(this.in instanceof BufferInfo)) return 0;
        int len = ((BufferInfo)this.in).length();
        return (int)Math.min((long)len, this.chunkSize - this.pos);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int readLen;
        if (this.closed) {
            throw new IOException("Attempted read from closed stream.");
        }
        if (this.eof) {
            return -1;
        }
        if (this.state != 2) {
            this.nextChunk();
            if (this.eof) {
                return -1;
            }
        }
        if ((readLen = this.in.read(b, off, (int)Math.min((long)len, this.chunkSize - this.pos))) != -1) {
            this.pos += (long)readLen;
            if (this.pos < this.chunkSize) return readLen;
            this.state = 3;
            return readLen;
        }
        this.eof = true;
        throw new TruncatedChunkException("Truncated chunk (expected size: %,d; actual size: %,d)", new Object[]{this.chunkSize, this.pos});
    }

    private long getChunkSize() throws IOException {
        int st = this.state;
        switch (st) {
            case 3: {
                this.buffer.clear();
                int bytesRead1 = this.in.readLine(this.buffer);
                if (bytesRead1 == -1) {
                    throw new MalformedChunkCodingException("CRLF expected at end of chunk");
                }
                if (!this.buffer.isEmpty()) {
                    throw new MalformedChunkCodingException("Unexpected content at the end of chunk");
                }
                this.state = 1;
            }
            case 1: {
                this.buffer.clear();
                int bytesRead2 = this.in.readLine(this.buffer);
                if (bytesRead2 == -1) {
                    throw new ConnectionClosedException("Premature end of chunk coded message body: closing chunk expected");
                }
                int separator = this.buffer.indexOf(59);
                if (separator < 0) {
                    separator = this.buffer.length();
                }
                String s = this.buffer.substringTrimmed(0, separator);
                try {
                    return Long.parseLong(s, 16);
                }
                catch (NumberFormatException e) {
                    throw new MalformedChunkCodingException("Bad chunk header: " + s);
                }
            }
        }
        throw new IllegalStateException("Inconsistent codec state");
    }

    public ChunkedInputStream(SessionInputBuffer in) {
        this(in, null);
    }

    private void nextChunk() throws IOException {
        if (this.state == Integer.MAX_VALUE) {
            throw new MalformedChunkCodingException("Corrupt data stream");
        }
        try {
            this.chunkSize = this.getChunkSize();
            if (this.chunkSize < 0L) {
                throw new MalformedChunkCodingException("Negative chunk size");
            }
            this.state = 2;
            this.pos = 0L;
            if (this.chunkSize != 0L) return;
            this.eof = true;
            this.parseTrailerHeaders();
        }
        catch (MalformedChunkCodingException ex) {
            this.state = Integer.MAX_VALUE;
            throw ex;
        }
    }
}
