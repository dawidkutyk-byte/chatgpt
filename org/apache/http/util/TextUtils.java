/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http.util;

public final class TextUtils {
    public static boolean containsBlanks(CharSequence s) {
        if (s == null) {
            return false;
        }
        int i = 0;
        while (i < s.length()) {
            if (Character.isWhitespace(s.charAt(i))) {
                return true;
            }
            ++i;
        }
        return false;
    }

    public static boolean isEmpty(CharSequence s) {
        if (s != null) return s.length() == 0;
        return true;
    }

    public static boolean isBlank(CharSequence s) {
        if (s == null) {
            return true;
        }
        int i = 0;
        while (i < s.length()) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
            ++i;
        }
        return true;
    }
}
