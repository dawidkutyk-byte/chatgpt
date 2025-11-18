/*
 * Decompiled with CFR 0.152.
 */
package org.slf4j.helpers;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.Util;

public final class MessageFormatter {
    static final char DELIM_STOP = '}';
    static final char DELIM_START = '{';
    static final String DELIM_STR = "{}";
    private static final char ESCAPE_CHAR = '\\';

    private static void charArrayAppend(StringBuilder sbuf, char[] a) {
        sbuf.append('[');
        int len = a.length;
        int i = 0;
        while (true) {
            if (i >= len) {
                sbuf.append(']');
                return;
            }
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
            ++i;
        }
    }

    private static void byteArrayAppend(StringBuilder sbuf, byte[] a) {
        sbuf.append('[');
        int len = a.length;
        int i = 0;
        while (true) {
            if (i >= len) {
                sbuf.append(']');
                return;
            }
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
            ++i;
        }
    }

    public static final FormattingTuple format(String messagePattern, Object arg1, Object arg2) {
        return MessageFormatter.arrayFormat(messagePattern, new Object[]{arg1, arg2});
    }

    private static void safeObjectAppend(StringBuilder sbuf, Object o) {
        try {
            String oAsString = o.toString();
            sbuf.append(oAsString);
        }
        catch (Throwable t) {
            Util.report("SLF4J: Failed toString() invocation on an object of type [" + o.getClass().getName() + "]", t);
            sbuf.append("[FAILED toString()]");
        }
    }

    private static void floatArrayAppend(StringBuilder sbuf, float[] a) {
        sbuf.append('[');
        int len = a.length;
        int i = 0;
        while (true) {
            if (i >= len) {
                sbuf.append(']');
                return;
            }
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
            ++i;
        }
    }

    public static final FormattingTuple arrayFormat(String messagePattern, Object[] argArray, Throwable throwable) {
        if (messagePattern == null) {
            return new FormattingTuple(null, argArray, throwable);
        }
        if (argArray == null) {
            return new FormattingTuple(messagePattern);
        }
        int i = 0;
        StringBuilder sbuf = new StringBuilder(messagePattern.length() + 50);
        int L = 0;
        while (true) {
            if (L >= argArray.length) {
                sbuf.append(messagePattern, i, messagePattern.length());
                return new FormattingTuple(sbuf.toString(), argArray, throwable);
            }
            int j = messagePattern.indexOf(DELIM_STR, i);
            if (j == -1) {
                if (i != 0) break;
                return new FormattingTuple(messagePattern, argArray, throwable);
            }
            if (MessageFormatter.isEscapedDelimeter(messagePattern, j)) {
                if (!MessageFormatter.isDoubleEscaped(messagePattern, j)) {
                    --L;
                    sbuf.append(messagePattern, i, j - 1);
                    sbuf.append('{');
                    i = j + 1;
                } else {
                    sbuf.append(messagePattern, i, j - 1);
                    MessageFormatter.deeplyAppendParameter(sbuf, argArray[L], new HashMap<Object[], Object>());
                    i = j + 2;
                }
            } else {
                sbuf.append(messagePattern, i, j);
                MessageFormatter.deeplyAppendParameter(sbuf, argArray[L], new HashMap<Object[], Object>());
                i = j + 2;
            }
            ++L;
        }
        sbuf.append(messagePattern, i, messagePattern.length());
        return new FormattingTuple(sbuf.toString(), argArray, throwable);
    }

    static final boolean isEscapedDelimeter(String messagePattern, int delimeterStartIndex) {
        if (delimeterStartIndex == 0) {
            return false;
        }
        char potentialEscape = messagePattern.charAt(delimeterStartIndex - 1);
        if (potentialEscape != '\\') return false;
        return true;
    }

    public static final FormattingTuple arrayFormat(String messagePattern, Object[] argArray) {
        Throwable throwableCandidate = MessageFormatter.getThrowableCandidate(argArray);
        Object[] args = argArray;
        if (throwableCandidate == null) return MessageFormatter.arrayFormat(messagePattern, args, throwableCandidate);
        args = MessageFormatter.trimmedCopy(argArray);
        return MessageFormatter.arrayFormat(messagePattern, args, throwableCandidate);
    }

    static final boolean isDoubleEscaped(String messagePattern, int delimeterStartIndex) {
        if (delimeterStartIndex < 2) return false;
        if (messagePattern.charAt(delimeterStartIndex - 2) != '\\') return false;
        return true;
    }

    private static void deeplyAppendParameter(StringBuilder sbuf, Object o, Map<Object[], Object> seenMap) {
        if (o == null) {
            sbuf.append("null");
            return;
        }
        if (!o.getClass().isArray()) {
            MessageFormatter.safeObjectAppend(sbuf, o);
        } else if (o instanceof boolean[]) {
            MessageFormatter.booleanArrayAppend(sbuf, (boolean[])o);
        } else if (o instanceof byte[]) {
            MessageFormatter.byteArrayAppend(sbuf, (byte[])o);
        } else if (o instanceof char[]) {
            MessageFormatter.charArrayAppend(sbuf, (char[])o);
        } else if (o instanceof short[]) {
            MessageFormatter.shortArrayAppend(sbuf, (short[])o);
        } else if (o instanceof int[]) {
            MessageFormatter.intArrayAppend(sbuf, (int[])o);
        } else if (o instanceof long[]) {
            MessageFormatter.longArrayAppend(sbuf, (long[])o);
        } else if (o instanceof float[]) {
            MessageFormatter.floatArrayAppend(sbuf, (float[])o);
        } else if (o instanceof double[]) {
            MessageFormatter.doubleArrayAppend(sbuf, (double[])o);
        } else {
            MessageFormatter.objectArrayAppend(sbuf, (Object[])o, seenMap);
        }
    }

    private static void objectArrayAppend(StringBuilder sbuf, Object[] a, Map<Object[], Object> seenMap) {
        sbuf.append('[');
        if (!seenMap.containsKey(a)) {
            seenMap.put(a, null);
            int len = a.length;
            for (int i = 0; i < len; ++i) {
                MessageFormatter.deeplyAppendParameter(sbuf, a[i], seenMap);
                if (i == len - 1) continue;
                sbuf.append(", ");
            }
            seenMap.remove(a);
        } else {
            sbuf.append("...");
        }
        sbuf.append(']');
    }

    private static void shortArrayAppend(StringBuilder sbuf, short[] a) {
        sbuf.append('[');
        int len = a.length;
        int i = 0;
        while (true) {
            if (i >= len) {
                sbuf.append(']');
                return;
            }
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
            ++i;
        }
    }

    public static final FormattingTuple format(String messagePattern, Object arg) {
        return MessageFormatter.arrayFormat(messagePattern, new Object[]{arg});
    }

    private static void booleanArrayAppend(StringBuilder sbuf, boolean[] a) {
        sbuf.append('[');
        int len = a.length;
        int i = 0;
        while (true) {
            if (i >= len) {
                sbuf.append(']');
                return;
            }
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
            ++i;
        }
    }

    private static void intArrayAppend(StringBuilder sbuf, int[] a) {
        sbuf.append('[');
        int len = a.length;
        int i = 0;
        while (true) {
            if (i >= len) {
                sbuf.append(']');
                return;
            }
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
            ++i;
        }
    }

    static final Throwable getThrowableCandidate(Object[] argArray) {
        if (argArray == null) return null;
        if (argArray.length == 0) {
            return null;
        }
        Object lastEntry = argArray[argArray.length - 1];
        if (!(lastEntry instanceof Throwable)) return null;
        return (Throwable)lastEntry;
    }

    private static void doubleArrayAppend(StringBuilder sbuf, double[] a) {
        sbuf.append('[');
        int len = a.length;
        int i = 0;
        while (true) {
            if (i >= len) {
                sbuf.append(']');
                return;
            }
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
            ++i;
        }
    }

    private static void longArrayAppend(StringBuilder sbuf, long[] a) {
        sbuf.append('[');
        int len = a.length;
        int i = 0;
        while (true) {
            if (i >= len) {
                sbuf.append(']');
                return;
            }
            sbuf.append(a[i]);
            if (i != len - 1) {
                sbuf.append(", ");
            }
            ++i;
        }
    }

    private static Object[] trimmedCopy(Object[] argArray) {
        if (argArray == null) throw new IllegalStateException("non-sensical empty or null argument array");
        if (argArray.length == 0) {
            throw new IllegalStateException("non-sensical empty or null argument array");
        }
        int trimemdLen = argArray.length - 1;
        Object[] trimmed = new Object[trimemdLen];
        System.arraycopy(argArray, 0, trimmed, 0, trimemdLen);
        return trimmed;
    }
}
