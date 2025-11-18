/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.binary;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.codec.binary.BaseNCodec;

public class BaseNCodecOutputStream
extends FilterOutputStream {
    private final BaseNCodec.Context context;
    private final byte[] singleByte = new byte[1];
    private final boolean doEncode;
    private final BaseNCodec baseNCodec;

    @Override
    public void write(int i) throws IOException {
        this.singleByte[0] = (byte)i;
        this.write(this.singleByte, 0, 1);
    }

    @Override
    public void close() throws IOException {
        this.eof();
        this.flush();
        this.out.close();
    }

    @Override
    public void flush() throws IOException {
        this.flush(true);
    }

    private void flush(boolean propagate) throws IOException {
        byte[] buf;
        int c;
        int avail = this.baseNCodec.available(this.context);
        if (avail > 0 && (c = this.baseNCodec.readResults(buf = new byte[avail], 0, avail, this.context)) > 0) {
            this.out.write(buf, 0, c);
        }
        if (!propagate) return;
        this.out.flush();
    }

    @Override
    public void write(byte[] b, int offset, int len) throws IOException {
        if (b == null) {
            throw new NullPointerException();
        }
        if (offset < 0) throw new IndexOutOfBoundsException();
        if (len < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (offset > b.length) throw new IndexOutOfBoundsException();
        if (offset + len > b.length) {
            throw new IndexOutOfBoundsException();
        }
        if (len <= 0) return;
        if (this.doEncode) {
            this.baseNCodec.encode(b, offset, len, this.context);
        } else {
            this.baseNCodec.decode(b, offset, len, this.context);
        }
        this.flush(false);
    }

    public BaseNCodecOutputStream(OutputStream out, BaseNCodec basedCodec, boolean doEncode) {
        super(out);
        this.context = new BaseNCodec.Context();
        this.baseNCodec = basedCodec;
        this.doEncode = doEncode;
    }

    public void eof() throws IOException {
        if (this.doEncode) {
            this.baseNCodec.encode(this.singleByte, 0, -1, this.context);
        } else {
            this.baseNCodec.decode(this.singleByte, 0, -1, this.context);
        }
    }
}
