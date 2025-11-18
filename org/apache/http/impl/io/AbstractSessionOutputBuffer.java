/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Consts
 *  org.apache.http.impl.io.HttpTransportMetricsImpl
 *  org.apache.http.io.BufferInfo
 *  org.apache.http.io.HttpTransportMetrics
 *  org.apache.http.io.SessionOutputBuffer
 *  org.apache.http.params.HttpParams
 *  org.apache.http.util.Args
 *  org.apache.http.util.ByteArrayBuffer
 *  org.apache.http.util.CharArrayBuffer
 */
package org.apache.http.impl.io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import org.apache.http.Consts;
import org.apache.http.impl.io.HttpTransportMetricsImpl;
import org.apache.http.io.BufferInfo;
import org.apache.http.io.HttpTransportMetrics;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.params.HttpParams;
import org.apache.http.util.Args;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.CharArrayBuffer;

@Deprecated
public abstract class AbstractSessionOutputBuffer
implements BufferInfo,
SessionOutputBuffer {
    private CodingErrorAction onMalformedCharAction;
    private boolean ascii;
    private ByteBuffer bbuf;
    private OutputStream outStream;
    private Charset charset;
    private HttpTransportMetricsImpl metrics;
    private ByteArrayBuffer buffer;
    private int minChunkLimit;
    private CodingErrorAction onUnmappableCharAction;
    private CharsetEncoder encoder;
    private static final byte[] CRLF = new byte[]{13, 10};

    public void writeLine(CharArrayBuffer charbuffer) throws IOException {
        if (charbuffer == null) {
            return;
        }
        if (this.ascii) {
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

    public void write(int b) throws IOException {
        if (this.buffer.isFull()) {
            this.flushBuffer();
        }
        this.buffer.append(b);
    }

    public void write(byte[] b, int off, int len) throws IOException {
        if (b == null) {
            return;
        }
        if (len > this.minChunkLimit || len > this.buffer.capacity()) {
            this.flushBuffer();
            this.outStream.write(b, off, len);
            this.metrics.incrementBytesTransferred((long)len);
        } else {
            int freecapacity = this.buffer.capacity() - this.buffer.length();
            if (len > freecapacity) {
                this.flushBuffer();
            }
            this.buffer.append(b, off, len);
        }
    }

    public AbstractSessionOutputBuffer() {
    }

    public void flush() throws IOException {
        this.flushBuffer();
        this.outStream.flush();
    }

    protected AbstractSessionOutputBuffer(OutputStream outStream, int bufferSize, Charset charset, int minChunkLimit, CodingErrorAction malformedCharAction, CodingErrorAction unmappableCharAction) {
        Args.notNull((Object)outStream, (String)"Input stream");
        Args.notNegative((int)bufferSize, (String)"Buffer size");
        this.outStream = outStream;
        this.buffer = new ByteArrayBuffer(bufferSize);
        this.charset = charset != null ? charset : Consts.ASCII;
        this.ascii = this.charset.equals(Consts.ASCII);
        this.encoder = null;
        this.minChunkLimit = minChunkLimit >= 0 ? minChunkLimit : 512;
        this.metrics = this.createTransportMetrics();
        this.onMalformedCharAction = malformedCharAction != null ? malformedCharAction : CodingErrorAction.REPORT;
        this.onUnmappableCharAction = unmappableCharAction != null ? unmappableCharAction : CodingErrorAction.REPORT;
    }

    private void writeEncoded(CharBuffer cbuf) throws IOException {
        if (!cbuf.hasRemaining()) {
            return;
        }
        if (this.encoder == null) {
            this.encoder = this.charset.newEncoder();
            this.encoder.onMalformedInput(this.onMalformedCharAction);
            this.encoder.onUnmappableCharacter(this.onUnmappableCharAction);
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

    protected void init(OutputStream outStream, int bufferSize, HttpParams params) {
        Args.notNull((Object)outStream, (String)"Input stream");
        Args.notNegative((int)bufferSize, (String)"Buffer size");
        Args.notNull((Object)params, (String)"HTTP parameters");
        this.outStream = outStream;
        this.buffer = new ByteArrayBuffer(bufferSize);
        String charset = (String)params.getParameter("http.protocol.element-charset");
        this.charset = charset != null ? Charset.forName(charset) : Consts.ASCII;
        this.ascii = this.charset.equals(Consts.ASCII);
        this.encoder = null;
        this.minChunkLimit = params.getIntParameter("http.connection.min-chunk-limit", 512);
        this.metrics = this.createTransportMetrics();
        CodingErrorAction a1 = (CodingErrorAction)params.getParameter("http.malformed.input.action");
        this.onMalformedCharAction = a1 != null ? a1 : CodingErrorAction.REPORT;
        CodingErrorAction a2 = (CodingErrorAction)params.getParameter("http.unmappable.input.action");
        this.onUnmappableCharAction = a2 != null ? a2 : CodingErrorAction.REPORT;
    }

    protected HttpTransportMetricsImpl createTransportMetrics() {
        return new HttpTransportMetricsImpl();
    }

    public void writeLine(String s) throws IOException {
        if (s == null) {
            return;
        }
        if (s.length() > 0) {
            if (this.ascii) {
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

    protected void flushBuffer() throws IOException {
        int len = this.buffer.length();
        if (len <= 0) return;
        this.outStream.write(this.buffer.buffer(), 0, len);
        this.buffer.clear();
        this.metrics.incrementBytesTransferred((long)len);
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

    public int capacity() {
        return this.buffer.capacity();
    }

    public void write(byte[] b) throws IOException {
        if (b == null) {
            return;
        }
        this.write(b, 0, b.length);
    }

    public int length() {
        return this.buffer.length();
    }

    public HttpTransportMetrics getMetrics() {
        return this.metrics;
    }
}
