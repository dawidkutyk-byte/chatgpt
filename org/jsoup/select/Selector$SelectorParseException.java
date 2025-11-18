/*
 * Decompiled with CFR 0.152.
 */
package org.jsoup.select;

public static class Selector.SelectorParseException
extends IllegalStateException {
    public Selector.SelectorParseException(String msg) {
        super(msg);
    }

    public Selector.SelectorParseException(Throwable cause, String msg, Object ... msgArgs) {
        super(String.format(msg, msgArgs), cause);
    }

    public Selector.SelectorParseException(String msg, Object ... msgArgs) {
        super(String.format(msg, msgArgs));
    }
}
