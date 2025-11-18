/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http.util;

public final class LangUtils {
    public static final int HASH_SEED = 17;
    public static final int HASH_OFFSET = 37;

    public static boolean equals(Object[] a1, Object[] a2) {
        if (a1 == null) {
            return a2 == null;
        }
        if (a2 == null) return false;
        if (a1.length != a2.length) return false;
        int i = 0;
        while (i < a1.length) {
            if (!LangUtils.equals(a1[i], a2[i])) {
                return false;
            }
            ++i;
        }
        return true;
    }

    private LangUtils() {
    }

    public static int hashCode(int seed, Object obj) {
        return LangUtils.hashCode(seed, obj != null ? obj.hashCode() : 0);
    }

    public static int hashCode(int seed, int hashcode) {
        return seed * 37 + hashcode;
    }

    public static int hashCode(int seed, boolean b) {
        return LangUtils.hashCode(seed, b ? 1 : 0);
    }

    public static boolean equals(Object obj1, Object obj2) {
        return obj1 == null ? obj2 == null : obj1.equals(obj2);
    }
}
