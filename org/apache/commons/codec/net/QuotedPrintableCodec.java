/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.net;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.BitSet;
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringDecoder;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.net.Utils;

public class QuotedPrintableCodec
implements BinaryDecoder,
StringEncoder,
BinaryEncoder,
StringDecoder {
    private static final byte CR = 13;
    private static final byte ESCAPE_CHAR = 61;
    private static final BitSet PRINTABLE_CHARS;
    private static final byte SPACE = 32;
    private final Charset charset;
    private static final int SAFE_LENGTH = 73;
    private final boolean strict;
    private static final byte TAB = 9;
    private static final byte LF = 10;

    public static final byte[] encodeQuotedPrintable(BitSet printable, byte[] bytes) {
        return QuotedPrintableCodec.encodeQuotedPrintable(printable, bytes, false);
    }

    public Charset getCharset() {
        return this.charset;
    }

    @Override
    public byte[] encode(byte[] bytes) {
        return QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, bytes, this.strict);
    }

    /*
     * Unable to fully structure code
     */
    public static final byte[] decodeQuotedPrintable(byte[] bytes) throws DecoderException {
        if (bytes == null) {
            return null;
        }
        buffer = new ByteArrayOutputStream();
        i = 0;
        while (i < bytes.length) {
            b = bytes[i];
            if (b == 61) {
                try {
                    if (bytes[++i] == 13) ** GOTO lbl18
                    u = Utils.digit16(bytes[i]);
                    l = Utils.digit16(bytes[++i]);
                    buffer.write((char)((u << 4) + l));
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    throw new DecoderException("Invalid quoted-printable encoding", e);
                }
            } else if (b != 13 && b != 10) {
                buffer.write(b);
            }
lbl18:
            // 5 sources

            ++i;
        }
        return buffer.toByteArray();
    }

    public QuotedPrintableCodec(boolean strict) {
        this(Charsets.UTF_8, strict);
    }

    static {
        int i;
        PRINTABLE_CHARS = new BitSet(256);
        for (i = 33; i <= 60; ++i) {
            PRINTABLE_CHARS.set(i);
        }
        i = 62;
        while (true) {
            if (i > 126) {
                PRINTABLE_CHARS.set(9);
                PRINTABLE_CHARS.set(32);
                return;
            }
            PRINTABLE_CHARS.set(i);
            ++i;
        }
    }

    public QuotedPrintableCodec() {
        this(Charsets.UTF_8, false);
    }

    @Override
    public Object encode(Object obj) throws EncoderException {
        if (obj == null) {
            return null;
        }
        if (obj instanceof byte[]) {
            return this.encode((byte[])obj);
        }
        if (!(obj instanceof String)) throw new EncoderException("Objects of type " + obj.getClass().getName() + " cannot be quoted-printable encoded");
        return this.encode((String)obj);
    }

    private static int getUnsignedOctet(int index, byte[] bytes) {
        int b = bytes[index];
        if (b >= 0) return b;
        b = 256 + b;
        return b;
    }

    @Override
    public String decode(String str) throws DecoderException {
        return this.decode(str, this.getCharset());
    }

    public QuotedPrintableCodec(Charset charset, boolean strict) {
        this.charset = charset;
        this.strict = strict;
    }

    @Override
    public Object decode(Object obj) throws DecoderException {
        if (obj == null) {
            return null;
        }
        if (obj instanceof byte[]) {
            return this.decode((byte[])obj);
        }
        if (!(obj instanceof String)) throw new DecoderException("Objects of type " + obj.getClass().getName() + " cannot be quoted-printable decoded");
        return this.decode((String)obj);
    }

    public String encode(String str, Charset charset) {
        if (str != null) return StringUtils.newStringUsAscii(this.encode(str.getBytes(charset)));
        return null;
    }

    public String decode(String str, Charset charset) throws DecoderException {
        if (str != null) return new String(this.decode(StringUtils.getBytesUsAscii(str)), charset);
        return null;
    }

    public String encode(String str, String charset) throws UnsupportedEncodingException {
        if (str != null) return StringUtils.newStringUsAscii(this.encode(str.getBytes(charset)));
        return null;
    }

    public String getDefaultCharset() {
        return this.charset.name();
    }

    private static boolean isWhitespace(int b) {
        return b == 32 || b == 9;
    }

    private static final int encodeQuotedPrintable(int b, ByteArrayOutputStream buffer) {
        buffer.write(61);
        char hex1 = Utils.hexDigit(b >> 4);
        char hex2 = Utils.hexDigit(b);
        buffer.write(hex1);
        buffer.write(hex2);
        return 3;
    }

    @Override
    public String encode(String str) throws EncoderException {
        return this.encode(str, this.getCharset());
    }

    @Override
    public byte[] decode(byte[] bytes) throws DecoderException {
        return QuotedPrintableCodec.decodeQuotedPrintable(bytes);
    }

    public String decode(String str, String charset) throws UnsupportedEncodingException, DecoderException {
        if (str != null) return new String(this.decode(StringUtils.getBytesUsAscii(str)), charset);
        return null;
    }

    private static int encodeByte(int b, boolean encode, ByteArrayOutputStream buffer) {
        if (encode) {
            return QuotedPrintableCodec.encodeQuotedPrintable(b, buffer);
        }
        buffer.write(b);
        return 1;
    }

    public QuotedPrintableCodec(Charset charset) {
        this(charset, false);
    }

    public QuotedPrintableCodec(String charsetName) throws IllegalCharsetNameException, UnsupportedCharsetException, IllegalArgumentException {
        this(Charset.forName(charsetName), false);
    }

    public static final byte[] encodeQuotedPrintable(BitSet printable, byte[] bytes, boolean strict) {
        if (bytes == null) {
            return null;
        }
        if (printable == null) {
            printable = PRINTABLE_CHARS;
        }
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        if (strict) {
            boolean encode;
            int pos = 1;
            for (int i = 0; i < bytes.length - 3; ++i) {
                int b = QuotedPrintableCodec.getUnsignedOctet(i, bytes);
                if (pos < 73) {
                    pos += QuotedPrintableCodec.encodeByte(b, !printable.get(b), buffer);
                    continue;
                }
                QuotedPrintableCodec.encodeByte(b, !printable.get(b) || QuotedPrintableCodec.isWhitespace(b), buffer);
                buffer.write(61);
                buffer.write(13);
                buffer.write(10);
                pos = 1;
            }
            int b = QuotedPrintableCodec.getUnsignedOctet(bytes.length - 3, bytes);
            boolean bl = encode = !printable.get(b) || QuotedPrintableCodec.isWhitespace(b) && pos > 68;
            if ((pos += QuotedPrintableCodec.encodeByte(b, encode, buffer)) > 71) {
                buffer.write(61);
                buffer.write(13);
                buffer.write(10);
            }
            int i = bytes.length - 2;
            while (i < bytes.length) {
                b = QuotedPrintableCodec.getUnsignedOctet(i, bytes);
                encode = !printable.get(b) || i > bytes.length - 2 && QuotedPrintableCodec.isWhitespace(b);
                QuotedPrintableCodec.encodeByte(b, encode, buffer);
                ++i;
            }
            return buffer.toByteArray();
        }
        byte[] byArray = bytes;
        int n = byArray.length;
        int n2 = 0;
        while (n2 < n) {
            int c = byArray[n2];
            int b = c;
            if (b < 0) {
                b = 256 + b;
            }
            if (printable.get(b)) {
                buffer.write(b);
            } else {
                QuotedPrintableCodec.encodeQuotedPrintable(b, buffer);
            }
            ++n2;
        }
        return buffer.toByteArray();
    }
}
