/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.util.Base64
 */
package org.java_websocket.util;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.java_websocket.util.Base64;

/*
 * Exception performing whole class analysis ignored.
 */
public static class Base64.OutputStream
extends FilterOutputStream {
    private boolean suspendEncoding;
    private int lineLength;
    private byte[] b4;
    private boolean breakLines;
    private boolean encode;
    private byte[] decodabet;
    private int bufferLength;
    private int options;
    private int position;
    private byte[] buffer;

    public Base64.OutputStream(OutputStream out, int options) {
        super(out);
        this.breakLines = (options & 8) != 0;
        this.encode = (options & 1) != 0;
        this.bufferLength = this.encode ? 3 : 4;
        this.buffer = new byte[this.bufferLength];
        this.position = 0;
        this.lineLength = 0;
        this.suspendEncoding = false;
        this.b4 = new byte[4];
        this.options = options;
        this.decodabet = Base64.access$000((int)options);
    }

    public Base64.OutputStream(OutputStream out) {
        this(out, 1);
    }

    public void flushBase64() throws IOException {
        if (this.position <= 0) return;
        if (!this.encode) throw new IOException("Base64 input not properly padded.");
        this.out.write(Base64.access$100((byte[])this.b4, (byte[])this.buffer, (int)this.position, (int)this.options));
        this.position = 0;
    }

    @Override
    public void close() throws IOException {
        this.flushBase64();
        super.close();
        this.buffer = null;
        this.out = null;
    }

    @Override
    public void write(int theByte) throws IOException {
        if (this.suspendEncoding) {
            this.out.write(theByte);
            return;
        }
        if (this.encode) {
            this.buffer[this.position++] = (byte)theByte;
            if (this.position < this.bufferLength) return;
            this.out.write(Base64.access$100((byte[])this.b4, (byte[])this.buffer, (int)this.bufferLength, (int)this.options));
            this.lineLength += 4;
            if (this.breakLines && this.lineLength >= 76) {
                this.out.write(10);
                this.lineLength = 0;
            }
            this.position = 0;
        } else {
            if (this.decodabet[theByte & 0x7F] <= -5) {
                if (this.decodabet[theByte & 0x7F] == -5) return;
                throw new IOException("Invalid character in Base64 data.");
            }
            this.buffer[this.position++] = (byte)theByte;
            if (this.position < this.bufferLength) return;
            int len = Base64.access$200((byte[])this.buffer, (int)0, (byte[])this.b4, (int)0, (int)this.options);
            this.out.write(this.b4, 0, len);
            this.position = 0;
        }
    }

    @Override
    public void write(byte[] theBytes, int off, int len) throws IOException {
        if (this.suspendEncoding) {
            this.out.write(theBytes, off, len);
            return;
        }
        int i = 0;
        while (i < len) {
            this.write(theBytes[off + i]);
            ++i;
        }
    }
}
