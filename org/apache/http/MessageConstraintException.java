/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http;

import java.nio.charset.CharacterCodingException;

public class MessageConstraintException
extends CharacterCodingException {
    private final String message;
    private static final long serialVersionUID = 6077207720446368695L;

    @Override
    public String getMessage() {
        return this.message;
    }

    public MessageConstraintException(String message) {
        this.message = message;
    }
}
