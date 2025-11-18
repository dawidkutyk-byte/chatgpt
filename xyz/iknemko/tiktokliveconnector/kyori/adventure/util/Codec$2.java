/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Codec
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Codec$Decoder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Codec$Encoder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.util;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Codec;

static class Codec.2
implements Codec<D, E, DX, EX> {
    final /* synthetic */ Codec.Encoder val$encoder;
    final /* synthetic */ Codec.Decoder val$decoder;

    Codec.2() {
        this.val$decoder = decoder;
        this.val$encoder = encoder;
    }

    @NotNull
    public E encode(@NotNull D decoded) throws Throwable {
        return this.val$encoder.encode(decoded);
    }

    @NotNull
    public D decode(@NotNull E encoded) throws Throwable {
        return this.val$decoder.decode(encoded);
    }
}
