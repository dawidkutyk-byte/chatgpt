/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound$Source
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound$Type
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.sound;

import java.util.OptionalLong;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundImpl;

static final class SoundImpl.Lazy
extends SoundImpl {
    final Supplier<? extends Sound.Type> supplier;

    SoundImpl.Lazy(@NotNull Supplier<? extends Sound.Type> supplier, @NotNull Sound.Source source, float volume, float pitch, OptionalLong seed) {
        super(source, volume, pitch, seed);
        this.supplier = supplier;
    }

    @NotNull
    public Key name() {
        return this.supplier.get().key();
    }
}
