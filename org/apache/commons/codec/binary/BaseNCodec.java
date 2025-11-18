/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.binary;

import java.util.Arrays;
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.StringUtils;

public abstract class BaseNCodec
implements BinaryDecoder,
BinaryEncoder {
    protected static final int MASK_8BITS = 255;
    static final int EOF = -1;
    protected final int lineLength;
    private static final int DEFAULT_BUFFER_SIZE = 8192;
    protected final byte pad;
    private final int encodedBlockSize;
    public static final int MIME_CHUNK_SIZE = 76;
    protected static final byte PAD_DEFAULT = 61;
    @Deprecated
    protected final byte PAD = (byte)61;
    private final int unencodedBlockSize;
    private static final int DEFAULT_BUFFER_RESIZE_FACTOR = 2;
    public static final int PEM_CHUNK_SIZE = 64;
    private final int chunkSeparatorLength;

    int readResults(byte[] b, int bPos, int bAvail, Context context) {
        if (context.buffer == null) return context.eof ? -1 : 0;
        int len = Math.min(this.available(context), bAvail);
        System.arraycopy(context.buffer, context.readPos, b, bPos, len);
        context.readPos += len;
        if (context.readPos < context.pos) return len;
        context.buffer = null;
        return len;
    }

    protected abstract boolean isInAlphabet(byte var1);

    protected BaseNCodec(int unencodedBlockSize, int encodedBlockSize, int lineLength, int chunkSeparatorLength, byte pad) {
        this.unencodedBlockSize = unencodedBlockSize;
        this.encodedBlockSize = encodedBlockSize;
        boolean useChunking = lineLength > 0 && chunkSeparatorLength > 0;
        this.lineLength = useChunking ? lineLength / encodedBlockSize * encodedBlockSize : 0;
        this.chunkSeparatorLength = chunkSeparatorLength;
        this.pad = pad;
    }

    protected boolean containsAlphabetOrPad(byte[] arrayOctet) {
        if (arrayOctet == null) {
            return false;
        }
        byte[] byArray = arrayOctet;
        int n = byArray.length;
        int n2 = 0;
        while (n2 < n) {
            byte element = byArray[n2];
            if (this.pad == element) return true;
            if (this.isInAlphabet(element)) {
                return true;
            }
            ++n2;
        }
        return false;
    }

    public String encodeToString(byte[] pArray) {
        return StringUtils.newStringUtf8(this.encode(pArray));
    }

    public boolean isInAlphabet(String basen) {
        return this.isInAlphabet(StringUtils.getBytesUtf8(basen), true);
    }

    protected byte[] ensureBufferSize(int size, Context context) {
        if (context.buffer == null) return this.resizeBuffer(context);
        if (context.buffer.length >= context.pos + size) return context.buffer;
        return this.resizeBuffer(context);
    }

    @Override
    public byte[] decode(byte[] pArray) {
        if (pArray == null) return pArray;
        if (pArray.length == 0) {
            return pArray;
        }
        Context context = new Context();
        this.decode(pArray, 0, pArray.length, context);
        this.decode(pArray, 0, -1, context);
        byte[] result = new byte[context.pos];
        this.readResults(result, 0, result.length, context);
        return result;
    }

    @Override
    public Object encode(Object obj) throws EncoderException {
        if (obj instanceof byte[]) return this.encode((byte[])obj);
        throw new EncoderException("Parameter supplied to Base-N encode is not a byte[]");
    }

    protected BaseNCodec(int unencodedBlockSize, int encodedBlockSize, int lineLength, int chunkSeparatorLength) {
        this(unencodedBlockSize, encodedBlockSize, lineLength, chunkSeparatorLength, 61);
    }

    int available(Context context) {
        return context.buffer != null ? context.pos - context.readPos : 0;
    }

    abstract void encode(byte[] var1, int var2, int var3, Context var4);

    @Override
    public Object decode(Object obj) throws DecoderException {
        if (obj instanceof byte[]) {
            return this.decode((byte[])obj);
        }
        if (!(obj instanceof String)) throw new DecoderException("Parameter supplied to Base-N decode is not a byte[] or a String");
        return this.decode((String)obj);
    }

    abstract void decode(byte[] var1, int var2, int var3, Context var4);

    public byte[] encode(byte[] pArray, int offset, int length) {
        if (pArray == null) return pArray;
        if (pArray.length == 0) {
            return pArray;
        }
        Context context = new Context();
        this.encode(pArray, offset, length, context);
        this.encode(pArray, offset, -1, context);
        byte[] buf = new byte[context.pos - context.readPos];
        this.readResults(buf, 0, buf.length, context);
        return buf;
    }

    public String encodeAsString(byte[] pArray) {
        return StringUtils.newStringUtf8(this.encode(pArray));
    }

    protected static boolean isWhiteSpace(byte byteToCheck) {
        switch (byteToCheck) {
            case 9: 
            case 10: 
            case 13: 
            case 32: {
                return true;
            }
        }
        return false;
    }

    protected int getDefaultBufferSize() {
        return 8192;
    }

    private byte[] resizeBuffer(Context context) {
        if (context.buffer == null) {
            context.buffer = new byte[this.getDefaultBufferSize()];
            context.pos = 0;
            context.readPos = 0;
        } else {
            byte[] b = new byte[context.buffer.length * 2];
            System.arraycopy(context.buffer, 0, b, 0, context.buffer.length);
            context.buffer = b;
        }
        return context.buffer;
    }

    @Override
    public byte[] encode(byte[] pArray) {
        if (pArray == null) return pArray;
        if (pArray.length != 0) return this.encode(pArray, 0, pArray.length);
        return pArray;
    }

    public long getEncodedLength(byte[] pArray) {
        long len = (long)((pArray.length + this.unencodedBlockSize - 1) / this.unencodedBlockSize) * (long)this.encodedBlockSize;
        if (this.lineLength <= 0) return len;
        len += (len + (long)this.lineLength - 1L) / (long)this.lineLength * (long)this.chunkSeparatorLength;
        return len;
    }

    public byte[] decode(String pArray) {
        return this.decode(StringUtils.getBytesUtf8(pArray));
    }

    public boolean isInAlphabet(byte[] arrayOctet, boolean allowWSPad) {
        byte[] byArray = arrayOctet;
        int n = byArray.length;
        int n2 = 0;
        while (n2 < n) {
            byte octet = byArray[n2];
            if (!this.isInAlphabet(octet)) {
                if (!allowWSPad) return false;
                if (octet != this.pad && !BaseNCodec.isWhiteSpace(octet)) {
                    return false;
                }
            }
            ++n2;
        }
        return true;
    }

    boolean hasData(Context context) {
        return context.buffer != null;
    }

    static class Context {
        int ibitWorkArea;
        long lbitWorkArea;
        byte[] buffer;
        int pos;
        int readPos;
        boolean eof;
        int currentLinePos;
        int modulus;

        Context() {
        }

        public String toString() {
            return String.format("%s[buffer=%s, currentLinePos=%s, eof=%s, ibitWorkArea=%s, lbitWorkArea=%s, modulus=%s, pos=%s, readPos=%s]", this.getClass().getSimpleName(), Arrays.toString(this.buffer), this.currentLinePos, this.eof, this.ibitWorkArea, this.lbitWorkArea, this.modulus, this.pos, this.readPos);
        }
    }
}
