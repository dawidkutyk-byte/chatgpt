/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language;

import java.util.Locale;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

final class SoundexUtils {
    static String clean(String str) {
        if (str == null) return str;
        if (str.length() == 0) {
            return str;
        }
        int len = str.length();
        char[] chars = new char[len];
        int count = 0;
        int i = 0;
        while (true) {
            if (i >= len) {
                if (count != len) return new String(chars, 0, count).toUpperCase(Locale.ENGLISH);
                return str.toUpperCase(Locale.ENGLISH);
            }
            if (Character.isLetter(str.charAt(i))) {
                chars[count++] = str.charAt(i);
            }
            ++i;
        }
    }

    static int differenceEncoded(String es1, String es2) {
        if (es1 == null) return 0;
        if (es2 == null) {
            return 0;
        }
        int lengthToMatch = Math.min(es1.length(), es2.length());
        int diff = 0;
        int i = 0;
        while (i < lengthToMatch) {
            if (es1.charAt(i) == es2.charAt(i)) {
                ++diff;
            }
            ++i;
        }
        return diff;
    }

    SoundexUtils() {
    }

    static int difference(StringEncoder encoder, String s1, String s2) throws EncoderException {
        return SoundexUtils.differenceEncoded(encoder.encode(s1), encoder.encode(s2));
    }
}
