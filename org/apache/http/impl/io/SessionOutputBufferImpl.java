/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.impl.io.HttpTransportMetricsImpl
 *  org.apache.http.io.BufferInfo
 *  org.apache.http.io.HttpTransportMetrics
 *  org.apache.http.io.SessionOutputBuffer
 *  org.apache.http.util.Args
 *  org.apache.http.util.Asserts
 *  org.apache.http.util.ByteArrayBuffer
 *  org.apache.http.util.CharArrayBuffer
 */
package org.apache.http.impl.io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import org.apache.http.impl.io.HttpTransportMetricsImpl;
import org.apache.http.io.BufferInfo;
import org.apache.http.io.HttpTransportMetrics;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.util.Args;
import org.apache.http.util.Asserts;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.CharArrayBuffer;

public class SessionOutputBufferImpl
implements SessionOutputBuffer,
BufferInfo {
    private final ByteArrayBuffer buffer;
    private final CharsetEncoder encoder;
    private final int fragementSizeHint;
    private final HttpTransportMetricsImpl metrics;
    private OutputStream outStream;
    private ByteBuffer bbuf;
    private static final byte[] CRLF = new byte[]{13, 10};

    private void writeEncoded(CharBuffer cbuf) throws IOException {
        if (!cbuf.hasRemaining()) {
            return;
        }
        if (this.bbuf == null) {
            this.bbuf = ByteBuffer.allocate(1024);
        }
        this.encoder.reset();
        while (true) {
            CoderResult result;
            if (!cbuf.hasRemaining()) {
                result = this.encoder.flush(this.bbuf);
                this.handleEncodingResult(result);
                this.bbuf.clear();
                return;
            }
            result = this.encoder.encode(cbuf, this.bbuf, true);
            this.handleEncodingResult(result);
        }
    }

    public int available() {
        return this.capacity() - this.length();
    }

    public void write(byte[] b, int off, int len) throws IOException {
        if (b == null) {
            return;
        }
        if (len > this.fragementSizeHint || len > this.buffer.capacity()) {
            this.flushBuffer();
            this.streamWrite(b, off, len);
            this.metrics.incrementBytesTransferred((long)len);
        } else {
            int freecapacity = this.buffer.capacity() - this.buffer.length();
            if (len > freecapacity) {
                this.flushBuffer();
            }
            this.buffer.append(b, off, len);
        }
    }

    public HttpTransportMetrics getMetrics() {
        return this.metrics;
    }

    public SessionOutputBufferImpl(HttpTransportMetricsImpl metrics, int bufferSize, int fragementSizeHint, CharsetEncoder charEncoder) {
        Args.positive((int)bufferSize, (String)"Buffer size");
        Args.notNull((Object)metrics, (String)"HTTP transport metrcis");
        this.metrics = metrics;
        this.buffer = new ByteArrayBuffer(bufferSize);
        this.fragementSizeHint = fragementSizeHint >= 0 ? fragementSizeHint : 0;
        this.encoder = charEncoder;
    }

    public int length() {
        return this.buffer.length();
    }

    private void streamWrite(byte[] b, int off, int len) throws IOException {
        Asserts.notNull((Object)this.outStream, (String)"Output stream");
        this.outStream.write(b, off, len);
    }

    public void write(int b) throws IOException {
        if (this.fragementSizeHint > 0) {
            if (this.buffer.isFull()) {
                this.flushBuffer();
            }
            this.buffer.append(b);
        } else {
            this.flushBuffer();
            this.outStream.write(b);
        }
    }

    public int capacity() {
        return this.buffer.capacity();
    }

    private void handleEncodingResult(CoderResult result) throws IOException {
        if (result.isError()) {
            result.throwException();
        }
        this.bbuf.flip();
        while (true) {
            if (!this.bbuf.hasRemaining()) {
                this.bbuf.compact();
                return;
            }
            this.write(this.bbuf.get());
        }
    }

    private void flushStream() throws IOException {
        if (this.outStream == null) return;
        this.outStream.flush();
    }

    public void writeLine(String s) throws IOException {
        if (s == null) {
            return;
        }
        if (s.length() > 0) {
            if (this.encoder == null) {
                for (int i = 0; i < s.length(); ++i) {
                    this.write(s.charAt(i));
                }
            } else {
                CharBuffer cbuf = CharBuffer.wrap(s);
                this.writeEncoded(cbuf);
            }
        }
        this.write(CRLF);
    }

    private void flushBuffer() throws IOException {
        int len = this.buffer.length();
        if (len <= 0) return;
        this.streamWrite(this.buffer.buffer(), 0, len);
        this.buffer.clear();
        this.metrics.incrementBytesTransferred((long)len);
    }

    public void bind(OutputStream outStream) {
        this.outStream = outStream;
    }

    public void flush() throws IOException {
        this.flushBuffer();
        this.flushStream();
    }

    public boolean isBound() {
        return this.outStream != null;
    }

    public void writeLine(CharArrayBuffer charbuffer) throws IOException {
        if (charbuffer == null) {
            return;
        }
        if (this.encoder == null) {
            int chunk;
            int off = 0;
            for (int remaining = charbuffer.length(); remaining > 0; off += chunk, remaining -= chunk) {
                chunk = this.buffer.capacity() - this.buffer.length();
                if ((chunk = Math.min(chunk, remaining)) > 0) {
                    this.buffer.append(charbuffer, off, chunk);
                }
                if (!this.buffer.isFull()) continue;
                this.flushBuffer();
            }
        } else {
            CharBuffer cbuf = CharBuffer.wrap(charbuffer.buffer(), 0, charbuffer.length());
            this.writeEncoded(cbuf);
        }
        this.write(CRLF);
    }

    public void write(byte[] b) throws IOException {
        if (b == null) {
            return;
        }
        this.write(b, 0, b.length);
    }

    public SessionOutputBufferImpl(HttpTransportMetricsImpl metrics, int bufferSize) {
        this(metrics, bufferSize, bufferSize, null);
    }
}
