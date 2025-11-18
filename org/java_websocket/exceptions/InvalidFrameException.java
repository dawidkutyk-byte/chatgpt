/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.exceptions.InvalidDataException
 */
package org.java_websocket.exceptions;

import org.java_websocket.exceptions.InvalidDataException;

public class InvalidFrameException
extends InvalidDataException {
    private static final long serialVersionUID = -9016496369828887591L;

    public InvalidFrameException(String s, Throwable t) {
        super(1002, s, t);
    }

    public InvalidFrameException() {
        super(1002);
    }

    public InvalidFrameException(Throwable t) {
        super(1002, t);
    }

    public InvalidFrameException(String s) {
        super(1002, s);
    }
}
