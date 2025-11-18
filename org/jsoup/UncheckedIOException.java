/*
 * Decompiled with CFR 0.152.
 */
package org.jsoup;

import java.io.IOException;

public class UncheckedIOException
extends java.io.UncheckedIOException {
    public UncheckedIOException(IOException cause) {
        super(cause);
    }

    public IOException ioException() {
        return this.getCause();
    }

    public UncheckedIOException(String message) {
        super(new IOException(message));
    }
}
