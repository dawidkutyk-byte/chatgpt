/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.util.TextUtils
 */
package org.apache.http.util;

import org.apache.http.util.TextUtils;

public class Asserts {
    public static void notNull(Object object, String name) {
        if (object != null) return;
        throw new IllegalStateException(name + " is null");
    }

    public static void notEmpty(CharSequence s, String name) {
        if (!TextUtils.isEmpty((CharSequence)s)) return;
        throw new IllegalStateException(name + " is empty");
    }

    public static void check(boolean expression, String message, Object ... args) {
        if (expression) return;
        throw new IllegalStateException(String.format(message, args));
    }

    public static void check(boolean expression, String message) {
        if (expression) return;
        throw new IllegalStateException(message);
    }

    public static void notBlank(CharSequence s, String name) {
        if (!TextUtils.isBlank((CharSequence)s)) return;
        throw new IllegalStateException(name + " is blank");
    }

    public static void check(boolean expression, String message, Object arg) {
        if (expression) return;
        throw new IllegalStateException(String.format(message, arg));
    }
}
