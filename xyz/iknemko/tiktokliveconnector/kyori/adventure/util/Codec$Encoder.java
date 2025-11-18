/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.util;

import org.jetbrains.annotations.NotNull;

public static interface Codec.Encoder<D, E, X extends Throwable> {
    @NotNull
    public E encode(@NotNull D var1) throws X;
}
