/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Consts
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.impl.conn.Wire
 *  org.apache.http.io.EofSensor
 *  org.apache.http.io.HttpTransportMetrics
 *  org.apache.http.io.SessionInputBuffer
 *  org.apache.http.util.CharArrayBuffer
 */
package org.apache.http.impl.conn;

import java.io.IOException;
import org.apache.http.Consts;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.impl.conn.Wire;
import org.apache.http.io.EofSensor;
import org.apache.http.io.HttpTransportMetrics;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.util.CharArrayBuffer;

@Deprecated
@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class LoggingSessionInputBuffer
implements SessionInputBuffer,
EofSensor {
    private final String charset;
    private final EofSensor eofSensor;
    private final SessionInputBuffer in;
    private final Wire wire;

    public int read() throws IOException {
        int b = this.in.read();
        if (!this.wire.enabled()) return b;
        if (b == -1) return b;
        this.wire.input(b);
        return b;
    }

    public boolean isDataAvailable(int timeout) throws IOException {
        return this.in.isDataAvailable(timeout);
    }

    public int read(byte[] b, int off, int len) throws IOException {
        int readLen = this.in.read(b, off, len);
        if (!this.wire.enabled()) return readLen;
        if (readLen <= 0) return readLen;
        this.wire.input(b, off, readLen);
        return readLen;
    }

    public int read(byte[] b) throws IOException {
        int readLen = this.in.read(b);
        if (!this.wire.enabled()) return readLen;
        if (readLen <= 0) return readLen;
        this.wire.input(b, 0, readLen);
        return readLen;
    }

    public String readLine() throws IOException {
        String s = this.in.readLine();
        if (!this.wire.enabled()) return s;
        if (s == null) return s;
        String tmp = s + "\r\n";
        this.wire.input(tmp.getBytes(this.charset));
        return s;
    }

    public LoggingSessionInputBuffer(SessionInputBuffer in, Wire wire, String charset) {
        this.in = in;
        this.eofSensor = in instanceof EofSensor ? (EofSensor)in : null;
        this.wire = wire;
        this.charset = charset != null ? charset : Consts.ASCII.name();
    }

    public HttpTransportMetrics getMetrics() {
        return this.in.getMetrics();
    }

    public LoggingSessionInputBuffer(SessionInputBuffer in, Wire wire) {
        this(in, wire, null);
    }

    public int readLine(CharArrayBuffer buffer) throws IOException {
        int readLen = this.in.readLine(buffer);
        if (!this.wire.enabled()) return readLen;
        if (readLen < 0) return readLen;
        int pos = buffer.length() - readLen;
        String s = new String(buffer.buffer(), pos, readLen);
        String tmp = s + "\r\n";
        this.wire.input(tmp.getBytes(this.charset));
        return readLen;
    }

    public boolean isEof() {
        if (this.eofSensor == null) return false;
        return this.eofSensor.isEof();
    }
}
