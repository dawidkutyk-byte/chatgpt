/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Codec$Decoder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Codec$Encoder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.util;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Codec;

public interface Codec<D, E, DX extends Throwable, EX extends Throwable> {
    @NotNull
    public D decode(@NotNull E var1) throws DX;

    @NotNull
    public E encode(@NotNull D var1) throws EX;

    @NotNull
    public static <D, E, DX extends Throwable, EX extends Throwable> Codec<D, E, DX, EX> codec(@NotNull Decoder<D, E, DX> decoder, @NotNull Encoder<D, E, EX> encoder) {
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    @NotNull
    public static <D, E, DX extends Throwable, EX extends Throwable> Codec<D, E, DX, EX> of(@NotNull Decoder<D, E, DX> decoder, @NotNull Encoder<D, E, EX> encoder) {
        return new /* Unavailable Anonymous Inner Class!! */;
    }
}
