/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.exceptions.InvalidDataException
 */
package org.java_websocket.exceptions;

import org.java_websocket.exceptions.InvalidDataException;

public class LimitExceededException
extends InvalidDataException {
    private static final long serialVersionUID = 6908339749836826785L;
    private final int limit;

    public LimitExceededException(String s, int limit) {
        super(1009, s);
        this.limit = limit;
    }

    public int getLimit() {
        return this.limit;
    }

    public LimitExceededException() {
        this(Integer.MAX_VALUE);
    }

    public LimitExceededException(int limit) {
        super(1009);
        this.limit = limit;
    }

    public LimitExceededException(String s) {
        this(s, Integer.MAX_VALUE);
    }
}
