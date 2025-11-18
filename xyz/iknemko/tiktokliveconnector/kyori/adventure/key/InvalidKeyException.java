/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.key;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class InvalidKeyException
extends RuntimeException {
    private final String keyValue;
    private static final long serialVersionUID = -5413304087321449434L;
    private final String keyNamespace;

    @NotNull
    public final String keyValue() {
        return this.keyValue;
    }

    @NotNull
    public final String keyNamespace() {
        return this.keyNamespace;
    }

    InvalidKeyException(@NotNull String keyNamespace, @NotNull String keyValue, @Nullable String message) {
        super(message);
        this.keyNamespace = keyNamespace;
        this.keyValue = keyValue;
    }
}
