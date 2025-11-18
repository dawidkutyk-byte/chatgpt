/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.binary;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;

public class Hex
implements BinaryDecoder,
BinaryEncoder {
    private final Charset charset;
    public static final Charset DEFAULT_CHARSET = Charsets.UTF_8;
    private static final char[] DIGITS_LOWER = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] DIGITS_UPPER = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public static final String DEFAULT_CHARSET_NAME = "UTF-8";

    @Override
    public Object decode(Object object) throws DecoderException {
        if (object instanceof String) {
            return this.decode(((String)object).toCharArray());
        }
        if (object instanceof byte[]) {
            return this.decode((byte[])object);
        }
        if (object instanceof ByteBuffer) {
            return this.decode((ByteBuffer)object);
        }
        try {
            return Hex.decodeHex((char[])object);
        }
        catch (ClassCastException e) {
            throw new DecoderException(e.getMessage(), e);
        }
    }

    public static char[] encodeHex(ByteBuffer data, boolean toLowerCase) {
        return Hex.encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    public static byte[] decodeHex(char[] data) throws DecoderException {
        int len = data.length;
        if ((len & 1) != 0) {
            throw new DecoderException("Odd number of characters.");
        }
        byte[] out = new byte[len >> 1];
        int i = 0;
        int j = 0;
        while (j < len) {
            int f = Hex.toDigit(data[j], j) << 4;
            f |= Hex.toDigit(data[++j], j);
            ++j;
            out[i] = (byte)(f & 0xFF);
            ++i;
        }
        return out;
    }

    public byte[] encode(ByteBuffer array) {
        return Hex.encodeHexString(array).getBytes(this.getCharset());
    }

    protected static char[] encodeHex(ByteBuffer data, char[] toDigits) {
        return Hex.encodeHex(data.array(), toDigits);
    }

    protected static char[] encodeHex(byte[] data, char[] toDigits) {
        int l = data.length;
        char[] out = new char[l << 1];
        int i = 0;
        int j = 0;
        while (i < l) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0xF & data[i]];
            ++i;
        }
        return out;
    }

    public static String encodeHexString(ByteBuffer data) {
        return new String(Hex.encodeHex(data));
    }

    public byte[] decode(ByteBuffer buffer) throws DecoderException {
        return Hex.decodeHex(new String(buffer.array(), this.getCharset()).toCharArray());
    }

    @Override
    public Object encode(Object object) throws EncoderException {
        byte[] byteArray;
        if (object instanceof String) {
            byteArray = ((String)object).getBytes(this.getCharset());
        } else if (object instanceof ByteBuffer) {
            byteArray = ((ByteBuffer)object).array();
        } else {
            try {
                byteArray = (byte[])object;
            }
            catch (ClassCastException e) {
                throw new EncoderException(e.getMessage(), e);
            }
        }
        return Hex.encodeHex(byteArray);
    }

    public Hex(String charsetName) {
        this(Charset.forName(charsetName));
    }

    public static char[] encodeHex(ByteBuffer data) {
        return Hex.encodeHex(data, true);
    }

    @Override
    public byte[] encode(byte[] array) {
        return Hex.encodeHexString(array).getBytes(this.getCharset());
    }

    @Override
    public byte[] decode(byte[] array) throws DecoderException {
        return Hex.decodeHex(new String(array, this.getCharset()).toCharArray());
    }

    public static String encodeHexString(ByteBuffer data, boolean toLowerCase) {
        return new String(Hex.encodeHex(data, toLowerCase));
    }

    public static String encodeHexString(byte[] data) {
        return new String(Hex.encodeHex(data));
    }

    public String toString() {
        return super.toString() + "[charsetName=" + this.charset + "]";
    }

    protected static int toDigit(char ch, int index) throws DecoderException {
        int digit = Character.digit(ch, 16);
        if (digit != -1) return digit;
        throw new DecoderException("Illegal hexadecimal character " + ch + " at index " + index);
    }

    public Hex(Charset charset) {
        this.charset = charset;
    }

    public Charset getCharset() {
        return this.charset;
    }

    public static char[] encodeHex(byte[] data, boolean toLowerCase) {
        return Hex.encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    public Hex() {
        this.charset = DEFAULT_CHARSET;
    }

    public static String encodeHexString(byte[] data, boolean toLowerCase) {
        return new String(Hex.encodeHex(data, toLowerCase));
    }

    public static byte[] decodeHex(String data) throws DecoderException {
        return Hex.decodeHex(data.toCharArray());
    }

    public static char[] encodeHex(byte[] data) {
        return Hex.encodeHex(data, true);
    }

    public String getCharsetName() {
        return this.charset.name();
    }
}
