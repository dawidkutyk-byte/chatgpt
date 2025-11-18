/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Consts
 *  org.apache.http.impl.io.HttpTransportMetricsImpl
 *  org.apache.http.io.BufferInfo
 *  org.apache.http.io.HttpTransportMetrics
 *  org.apache.http.io.SessionInputBuffer
 *  org.apache.http.params.HttpParams
 *  org.apache.http.util.Args
 *  org.apache.http.util.ByteArrayBuffer
 *  org.apache.http.util.CharArrayBuffer
 */
package org.apache.http.impl.io;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import org.apache.http.Consts;
import org.apache.http.impl.io.HttpTransportMetricsImpl;
import org.apache.http.io.BufferInfo;
import org.apache.http.io.HttpTransportMetrics;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.params.HttpParams;
import org.apache.http.util.Args;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.CharArrayBuffer;

@Deprecated
public abstract class AbstractSessionInputBuffer
implements BufferInfo,
SessionInputBuffer {
    private Charset charset;
    private int bufferLen;
    private HttpTransportMetricsImpl metrics;
    private boolean ascii;
    private ByteArrayBuffer lineBuffer;
    private int bufferPos;
    private CodingErrorAction onMalformedCharAction;
    private CodingErrorAction onUnmappableCharAction;
    private CharBuffer cbuf;
    private int minChunkLimit;
    private CharsetDecoder decoder;
    private byte[] buffer;
    private int maxLineLen;
    private InputStream inStream;

    public HttpTransportMetrics getMetrics() {
        return this.metrics;
    }

    protected void init(InputStream inputStream, int bufferSize, HttpParams params) {
        Args.notNull((Object)inputStream, (String)"Input stream");
        Args.notNegative((int)bufferSize, (String)"Buffer size");
        Args.notNull((Object)params, (String)"HTTP parameters");
        this.inStream = inputStream;
        this.buffer = new byte[bufferSize];
        this.bufferPos = 0;
        this.bufferLen = 0;
        this.lineBuffer = new ByteArrayBuffer(bufferSize);
        String charset = (String)params.getParameter("http.protocol.element-charset");
        this.charset = charset != null ? Charset.forName(charset) : Consts.ASCII;
        this.ascii = this.charset.equals(Consts.ASCII);
        this.decoder = null;
        this.maxLineLen = params.getIntParameter("http.connection.max-line-length", -1);
        this.minChunkLimit = params.getIntParameter("http.connection.min-chunk-limit", 512);
        this.metrics = this.createTransportMetrics();
        CodingErrorAction a1 = (CodingErrorAction)params.getParameter("http.malformed.input.action");
        this.onMalformedCharAction = a1 != null ? a1 : CodingErrorAction.REPORT;
        CodingErrorAction a2 = (CodingErrorAction)params.getParameter("http.unmappable.input.action");
        this.onUnmappableCharAction = a2 != null ? a2 : CodingErrorAction.REPORT;
    }

    private int appendDecoded(CharArrayBuffer charbuffer, ByteBuffer bbuf) throws IOException {
        if (!bbuf.hasRemaining()) {
            return 0;
        }
        if (this.decoder == null) {
            this.decoder = this.charset.newDecoder();
            this.decoder.onMalformedInput(this.onMalformedCharAction);
            this.decoder.onUnmappableCharacter(this.onUnmappableCharAction);
        }
        if (this.cbuf == null) {
            this.cbuf = CharBuffer.allocate(1024);
        }
        this.decoder.reset();
        int len = 0;
        while (true) {
            CoderResult result;
            if (!bbuf.hasRemaining()) {
                result = this.decoder.flush(this.cbuf);
                this.cbuf.clear();
                return len += this.handleDecodingResult(result, charbuffer, bbuf);
            }
            result = this.decoder.decode(bbuf, this.cbuf, true);
            len += this.handleDecodingResult(result, charbuffer, bbuf);
        }
    }

    public String readLine() throws IOException {
        CharArrayBuffer charbuffer = new CharArrayBuffer(64);
        int readLen = this.readLine(charbuffer);
        if (readLen == -1) return null;
        return charbuffer.toString();
    }

    public int read() throws IOException {
        int noRead;
        do {
            if (this.hasBufferedData()) return this.buffer[this.bufferPos++] & 0xFF;
        } while ((noRead = this.fillBuffer()) != -1);
        return -1;
    }

    public int readLine(CharArrayBuffer charbuffer) throws IOException {
        Args.notNull((Object)charbuffer, (String)"Char array buffer");
        int noRead = 0;
        boolean retry = true;
        do {
            int len;
            if (!retry) {
                if (noRead != -1) return this.lineFromLineBuffer(charbuffer);
                if (!this.lineBuffer.isEmpty()) return this.lineFromLineBuffer(charbuffer);
                return -1;
            }
            int i = this.locateLF();
            if (i != -1) {
                if (this.lineBuffer.isEmpty()) {
                    return this.lineFromReadBuffer(charbuffer, i);
                }
                retry = false;
                len = i + 1 - this.bufferPos;
                this.lineBuffer.append(this.buffer, this.bufferPos, len);
                this.bufferPos = i + 1;
                continue;
            }
            if (this.hasBufferedData()) {
                len = this.bufferLen - this.bufferPos;
                this.lineBuffer.append(this.buffer, this.bufferPos, len);
                this.bufferPos = this.bufferLen;
            }
            if ((noRead = this.fillBuffer()) != -1) continue;
            retry = false;
        } while (this.maxLineLen <= 0 || this.lineBuffer.length() < this.maxLineLen);
        throw new IOException("Maximum line length limit exceeded");
    }

    private int lineFromReadBuffer(CharArrayBuffer charbuffer, int position) throws IOException {
        int off = this.bufferPos;
        int i = position;
        this.bufferPos = i + 1;
        if (i > off && this.buffer[i - 1] == 13) {
            --i;
        }
        int len = i - off;
        if (this.ascii) {
            charbuffer.append(this.buffer, off, len);
        } else {
            ByteBuffer bbuf = ByteBuffer.wrap(this.buffer, off, len);
            len = this.appendDecoded(charbuffer, bbuf);
        }
        return len;
    }

    public int read(byte[] b, int off, int len) throws IOException {
        int noRead;
        if (b == null) {
            return 0;
        }
        if (this.hasBufferedData()) {
            int chunk = Math.min(len, this.bufferLen - this.bufferPos);
            System.arraycopy(this.buffer, this.bufferPos, b, off, chunk);
            this.bufferPos += chunk;
            return chunk;
        }
        if (len > this.minChunkLimit) {
            int read = this.inStream.read(b, off, len);
            if (read <= 0) return read;
            this.metrics.incrementBytesTransferred((long)read);
            return read;
        }
        do {
            if (!this.hasBufferedData()) continue;
            int chunk = Math.min(len, this.bufferLen - this.bufferPos);
            System.arraycopy(this.buffer, this.bufferPos, b, off, chunk);
            this.bufferPos += chunk;
            return chunk;
        } while ((noRead = this.fillBuffer()) != -1);
        return -1;
    }

    private int locateLF() {
        int i = this.bufferPos;
        while (i < this.bufferLen) {
            if (this.buffer[i] == 10) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    public int available() {
        return this.capacity() - this.length();
    }

    private int handleDecodingResult(CoderResult result, CharArrayBuffer charbuffer, ByteBuffer bbuf) throws IOException {
        if (result.isError()) {
            result.throwException();
        }
        this.cbuf.flip();
        int len = this.cbuf.remaining();
        while (true) {
            if (!this.cbuf.hasRemaining()) {
                this.cbuf.compact();
                return len;
            }
            charbuffer.append(this.cbuf.get());
        }
    }

    protected int fillBuffer() throws IOException {
        int len;
        int off;
        int readLen;
        if (this.bufferPos > 0) {
            int len2 = this.bufferLen - this.bufferPos;
            if (len2 > 0) {
                System.arraycopy(this.buffer, this.bufferPos, this.buffer, 0, len2);
            }
            this.bufferPos = 0;
            this.bufferLen = len2;
        }
        if ((readLen = this.inStream.read(this.buffer, off = this.bufferLen, len = this.buffer.length - off)) == -1) {
            return -1;
        }
        this.bufferLen = off + readLen;
        this.metrics.incrementBytesTransferred((long)readLen);
        return readLen;
    }

    public int length() {
        return this.bufferLen - this.bufferPos;
    }

    protected HttpTransportMetricsImpl createTransportMetrics() {
        return new HttpTransportMetricsImpl();
    }

    protected boolean hasBufferedData() {
        return this.bufferPos < this.bufferLen;
    }

    public int read(byte[] b) throws IOException {
        if (b != null) return this.read(b, 0, b.length);
        return 0;
    }

    private int lineFromLineBuffer(CharArrayBuffer charbuffer) throws IOException {
        int len = this.lineBuffer.length();
        if (len > 0) {
            if (this.lineBuffer.byteAt(len - 1) == 10) {
                --len;
            }
            if (len > 0 && this.lineBuffer.byteAt(len - 1) == 13) {
                --len;
            }
        }
        if (this.ascii) {
            charbuffer.append(this.lineBuffer, 0, len);
        } else {
            ByteBuffer bbuf = ByteBuffer.wrap(this.lineBuffer.buffer(), 0, len);
            len = this.appendDecoded(charbuffer, bbuf);
        }
        this.lineBuffer.clear();
        return len;
    }

    public int capacity() {
        return this.buffer.length;
    }
}
