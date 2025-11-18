/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.net;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.BitSet;
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringDecoder;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.net.Utils;

public class URLCodec
implements StringDecoder,
StringEncoder,
BinaryDecoder,
BinaryEncoder {
    private static final BitSet WWW_FORM_URL_SAFE;
    @Deprecated
    protected static final BitSet WWW_FORM_URL;
    protected static final byte ESCAPE_CHAR = 37;
    @Deprecated
    protected volatile String charset;

    @Override
    public Object decode(Object obj) throws DecoderException {
        if (obj == null) {
            return null;
        }
        if (obj instanceof byte[]) {
            return this.decode((byte[])obj);
        }
        if (!(obj instanceof String)) throw new DecoderException("Objects of type " + obj.getClass().getName() + " cannot be URL decoded");
        return this.decode((String)obj);
    }

    public String encode(String str, String charset) throws UnsupportedEncodingException {
        if (str != null) return StringUtils.newStringUsAscii(this.encode(str.getBytes(charset)));
        return null;
    }

    public URLCodec(String charset) {
        this.charset = charset;
    }

    public String decode(String str, String charset) throws UnsupportedEncodingException, DecoderException {
        if (str != null) return new String(this.decode(StringUtils.getBytesUsAscii(str)), charset);
        return null;
    }

    @Deprecated
    public String getEncoding() {
        return this.charset;
    }

    public URLCodec() {
        this("UTF-8");
    }

    @Override
    public String encode(String str) throws EncoderException {
        if (str == null) {
            return null;
        }
        try {
            return this.encode(str, this.getDefaultCharset());
        }
        catch (UnsupportedEncodingException e) {
            throw new EncoderException(e.getMessage(), e);
        }
    }

    public String getDefaultCharset() {
        return this.charset;
    }

    public static final byte[] decodeUrl(byte[] bytes) throws DecoderException {
        if (bytes == null) {
            return null;
        }
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int i = 0;
        while (i < bytes.length) {
            byte b = bytes[i];
            if (b == 43) {
                buffer.write(32);
            } else if (b == 37) {
                try {
                    int u = Utils.digit16(bytes[++i]);
                    int l = Utils.digit16(bytes[++i]);
                    buffer.write((char)((u << 4) + l));
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    throw new DecoderException("Invalid URL encoding: ", e);
                }
            } else {
                buffer.write(b);
            }
            ++i;
        }
        return buffer.toByteArray();
    }

    static {
        int i;
        WWW_FORM_URL_SAFE = new BitSet(256);
        for (i = 97; i <= 122; ++i) {
            WWW_FORM_URL_SAFE.set(i);
        }
        for (i = 65; i <= 90; ++i) {
            WWW_FORM_URL_SAFE.set(i);
        }
        i = 48;
        while (true) {
            if (i > 57) {
                WWW_FORM_URL_SAFE.set(45);
                WWW_FORM_URL_SAFE.set(95);
                WWW_FORM_URL_SAFE.set(46);
                WWW_FORM_URL_SAFE.set(42);
                WWW_FORM_URL_SAFE.set(32);
                WWW_FORM_URL = (BitSet)WWW_FORM_URL_SAFE.clone();
                return;
            }
            WWW_FORM_URL_SAFE.set(i);
            ++i;
        }
    }

    @Override
    public String decode(String str) throws DecoderException {
        if (str == null) {
            return null;
        }
        try {
            return this.decode(str, this.getDefaultCharset());
        }
        catch (UnsupportedEncodingException e) {
            throw new DecoderException(e.getMessage(), e);
        }
    }

    @Override
    public Object encode(Object obj) throws EncoderException {
        if (obj == null) {
            return null;
        }
        if (obj instanceof byte[]) {
            return this.encode((byte[])obj);
        }
        if (!(obj instanceof String)) throw new EncoderException("Objects of type " + obj.getClass().getName() + " cannot be URL encoded");
        return this.encode((String)obj);
    }

    @Override
    public byte[] encode(byte[] bytes) {
        return URLCodec.encodeUrl(WWW_FORM_URL_SAFE, bytes);
    }

    public static final byte[] encodeUrl(BitSet urlsafe, byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        if (urlsafe == null) {
            urlsafe = WWW_FORM_URL_SAFE;
        }
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] byArray = bytes;
        int n = byArray.length;
        int n2 = 0;
        while (n2 < n) {
            int c = byArray[n2];
            int b = c;
            if (b < 0) {
                b = 256 + b;
            }
            if (urlsafe.get(b)) {
                if (b == 32) {
                    b = 43;
                }
                buffer.write(b);
            } else {
                buffer.write(37);
                char hex1 = Utils.hexDigit(b >> 4);
                char hex2 = Utils.hexDigit(b);
                buffer.write(hex1);
                buffer.write(hex2);
            }
            ++n2;
        }
        return buffer.toByteArray();
    }

    @Override
    public byte[] decode(byte[] bytes) throws DecoderException {
        return URLCodec.decodeUrl(bytes);
    }
}
