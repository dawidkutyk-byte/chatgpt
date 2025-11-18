/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Consts
 *  org.apache.http.util.Args
 */
package org.apache.http.util;

import java.io.UnsupportedEncodingException;
import org.apache.http.Consts;
import org.apache.http.util.Args;

public final class EncodingUtils {
    public static String getString(byte[] data, int offset, int length, String charset) {
        Args.notNull((Object)data, (String)"Input");
        Args.notEmpty((CharSequence)charset, (String)"Charset");
        try {
            return new String(data, offset, length, charset);
        }
        catch (UnsupportedEncodingException e) {
            return new String(data, offset, length);
        }
    }

    public static String getAsciiString(byte[] data) {
        Args.notNull((Object)data, (String)"Input");
        return EncodingUtils.getAsciiString(data, 0, data.length);
    }

    public static byte[] getAsciiBytes(String data) {
        Args.notNull((Object)data, (String)"Input");
        return data.getBytes(Consts.ASCII);
    }

    private EncodingUtils() {
    }

    public static byte[] getBytes(String data, String charset) {
        Args.notNull((Object)data, (String)"Input");
        Args.notEmpty((CharSequence)charset, (String)"Charset");
        try {
            return data.getBytes(charset);
        }
        catch (UnsupportedEncodingException e) {
            return data.getBytes();
        }
    }

    public static String getAsciiString(byte[] data, int offset, int length) {
        Args.notNull((Object)data, (String)"Input");
        return new String(data, offset, length, Consts.ASCII);
    }

    public static String getString(byte[] data, String charset) {
        Args.notNull((Object)data, (String)"Input");
        return EncodingUtils.getString(data, 0, data.length, charset);
    }
}
