/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound$Source
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound$Type
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.sound;

import java.util.OptionalLong;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound;

public static interface Sound.Builder
extends AbstractBuilder<Sound> {
    @NotNull
    public Sound.Builder seed(long var1);

    @NotNull
    public Sound.Builder pitch(@Range(from=-1L, to=1L) float var1);

    @NotNull
    public Sound.Builder source(// Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Sound.Source.Provider var1);

    @NotNull
    public Sound.Builder source(@NotNull Sound.Source var1);

    @NotNull
    public Sound.Builder seed(@NotNull OptionalLong var1);

    @NotNull
    public Sound.Builder type(@NotNull Sound.Type var1);

    @NotNull
    public Sound.Builder volume(@Range(from=0L, to=0x7FFFFFFFL) float var1);

    @NotNull
    public Sound.Builder type(@NotNull Key var1);

    @NotNull
    public Sound.Builder type(@NotNull Supplier<? extends Sound.Type> var1);
}
