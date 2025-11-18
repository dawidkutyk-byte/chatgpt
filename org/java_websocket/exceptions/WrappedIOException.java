/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.WebSocket
 */
package org.java_websocket.exceptions;

import java.io.IOException;
import org.java_websocket.WebSocket;

public class WrappedIOException
extends Exception {
    private final transient WebSocket connection;
    private final IOException ioException;

    public IOException getIOException() {
        return this.ioException;
    }

    public WebSocket getConnection() {
        return this.connection;
    }

    public WrappedIOException(WebSocket connection, IOException ioException) {
        this.connection = connection;
        this.ioException = ioException;
    }
}
