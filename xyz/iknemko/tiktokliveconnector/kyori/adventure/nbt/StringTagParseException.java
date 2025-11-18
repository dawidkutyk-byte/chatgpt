/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.io.IOException;

class StringTagParseException
extends IOException {
    private final CharSequence buffer;
    private static final long serialVersionUID = -3001637554903912905L;
    private final int position;

    StringTagParseException(String message, CharSequence buffer, int position) {
        super(message);
        this.buffer = buffer;
        this.position = position;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "(at position " + this.position + ")";
    }
}
