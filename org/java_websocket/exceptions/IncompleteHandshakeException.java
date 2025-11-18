/*
 * Decompiled with CFR 0.152.
 */
package org.java_websocket.exceptions;

public class IncompleteHandshakeException
extends RuntimeException {
    private final int preferredSize;
    private static final long serialVersionUID = 7906596804233893092L;

    public IncompleteHandshakeException() {
        this.preferredSize = 0;
    }

    public int getPreferredSize() {
        return this.preferredSize;
    }

    public IncompleteHandshakeException(int preferredSize) {
        this.preferredSize = preferredSize;
    }
}
