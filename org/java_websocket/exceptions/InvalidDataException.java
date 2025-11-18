/*
 * Decompiled with CFR 0.152.
 */
package org.java_websocket.exceptions;

public class InvalidDataException
extends Exception {
    private final int closecode;
    private static final long serialVersionUID = 3731842424390998726L;

    public InvalidDataException(int closecode, String s, Throwable t) {
        super(s, t);
        this.closecode = closecode;
    }

    public InvalidDataException(int closecode, String s) {
        super(s);
        this.closecode = closecode;
    }

    public InvalidDataException(int closecode) {
        this.closecode = closecode;
    }

    public int getCloseCode() {
        return this.closecode;
    }

    public InvalidDataException(int closecode, Throwable t) {
        super(t);
        this.closecode = closecode;
    }
}
