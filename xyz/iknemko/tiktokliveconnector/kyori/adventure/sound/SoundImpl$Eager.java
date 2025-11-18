/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound$Source
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.sound;

import java.util.OptionalLong;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundImpl;

static final class SoundImpl.Eager
extends SoundImpl {
    final Key name;

    @NotNull
    public Key name() {
        return this.name;
    }

    SoundImpl.Eager(@NotNull Key name, @NotNull Sound.Source source, float volume, float pitch, OptionalLong seed) {
        super(source, volume, pitch, seed);
        this.name = name;
    }
}
