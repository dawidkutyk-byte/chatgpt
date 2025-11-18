/*
 * Decompiled with CFR 0.152.
 */
package org.slf4j.helpers;

public class FormattingTuple {
    private String message;
    private Throwable throwable;
    public static FormattingTuple NULL = new FormattingTuple(null);
    private Object[] argArray;

    public Object[] getArgArray() {
        return this.argArray;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public FormattingTuple(String message) {
        this(message, null, null);
    }

    public FormattingTuple(String message, Object[] argArray, Throwable throwable) {
        this.message = message;
        this.throwable = throwable;
        this.argArray = argArray;
    }

    public String getMessage() {
        return this.message;
    }
}
