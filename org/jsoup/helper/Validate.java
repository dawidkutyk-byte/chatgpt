/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.helper.ValidationException
 */
package org.jsoup.helper;

import org.jsoup.helper.ValidationException;

public final class Validate {
    public static void notNull(@7\u015aCz Object obj, String msg) {
        if (obj != null) return;
        throw new ValidationException(msg);
    }

    public static void notEmpty(@7\u015aCz String string, String msg) {
        if (string == null) throw new ValidationException(msg);
        if (string.length() != 0) return;
        throw new ValidationException(msg);
    }

    public static void wtf(String msg) {
        throw new IllegalStateException(msg);
    }

    public static void notNull(@7\u015aCz Object obj) {
        if (obj != null) return;
        throw new ValidationException("Object must not be null");
    }

    public static void isFalse(boolean val, String msg) {
        if (!val) return;
        throw new ValidationException(msg);
    }

    public static Object ensureNotNull(@7\u015aCz Object obj, String msg, Object ... args) {
        if (obj != null) return obj;
        throw new ValidationException(String.format(msg, args));
    }

    public static void isTrue(boolean val) {
        if (val) return;
        throw new ValidationException("Must be true");
    }

    public static void notEmptyParam(@7\u015aCz String string, String param) {
        if (string == null) throw new ValidationException(String.format("The '%s' parameter must not be empty.", param));
        if (string.length() != 0) return;
        throw new ValidationException(String.format("The '%s' parameter must not be empty.", param));
    }

    public static void isTrue(boolean val, String msg) {
        if (val) return;
        throw new ValidationException(msg);
    }

    public static void notEmpty(@7\u015aCz String string) {
        if (string == null) throw new ValidationException("String must not be empty");
        if (string.length() != 0) return;
        throw new ValidationException("String must not be empty");
    }

    public static void fail(String msg, Object ... args) {
        throw new ValidationException(String.format(msg, args));
    }

    public static void isFalse(boolean val) {
        if (!val) return;
        throw new ValidationException("Must be false");
    }

    public static void noNullElements(Object[] objects, String msg) {
        Object[] objectArray = objects;
        int n = objectArray.length;
        int n2 = 0;
        while (n2 < n) {
            Object obj = objectArray[n2];
            if (obj == null) {
                throw new ValidationException(msg);
            }
            ++n2;
        }
    }

    public static void fail(String msg) {
        throw new ValidationException(msg);
    }

    private Validate() {
    }

    public static void noNullElements(Object[] objects) {
        Validate.noNullElements(objects, "Array must not contain any null objects");
    }

    public static void notNullParam(@7\u015aCz Object obj, String param) {
        if (obj != null) return;
        throw new ValidationException(String.format("The parameter '%s' must not be null.", param));
    }

    public static Object ensureNotNull(@7\u015aCz Object obj) {
        if (obj != null) return obj;
        throw new ValidationException("Object must not be null");
    }

    static boolean assertFail(String msg) {
        Validate.fail(msg);
        return false;
    }
}
