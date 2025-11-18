/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound$Source
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound$Type
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundImpl$BuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundStop
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.sound;

import java.util.Objects;
import java.util.OptionalLong;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.kyori.examination.Examinable;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundStop;

@ApiStatus.NonExtendable
public interface Sound
extends Examinable {
    @NotNull
    public static Sound sound(@NotNull Type type, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Sound.Source.Provider source, float volume, float pitch) {
        return Sound.sound(type, source.soundSource(), volume, pitch);
    }

    @NotNull
    public static Builder sound(@NotNull Sound existing) {
        return new SoundImpl.BuilderImpl(existing);
    }

    public float pitch();

    public float volume();

    @NotNull
    public OptionalLong seed();

    @NotNull
    public static Sound sound(@NotNull Supplier<? extends Type> type, @NotNull Source source, float volume, float pitch) {
        return (Sound)Sound.sound().type(type).source(source).volume(volume).pitch(pitch).build();
    }

    @NotNull
    public Source source();

    @NotNull
    public static Sound sound(@NotNull Key name, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Sound.Source.Provider source, float volume, float pitch) {
        return Sound.sound(name, source.soundSource(), volume, pitch);
    }

    @NotNull
    public static Sound sound(@NotNull Type type, @NotNull Source source, float volume, float pitch) {
        Objects.requireNonNull(type, "type");
        return Sound.sound(type.key(), source, volume, pitch);
    }

    @NotNull
    public Key name();

    @NotNull
    public SoundStop asStop();

    @NotNull
    public static Builder sound() {
        return new SoundImpl.BuilderImpl();
    }

    @NotNull
    public static Sound sound(@NotNull Key name, @NotNull Source source, float volume, float pitch) {
        return (Sound)Sound.sound().type(name).source(source).volume(volume).pitch(pitch).build();
    }

    @NotNull
    public static Sound sound(@NotNull Supplier<? extends Type> type, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Sound.Source.Provider source, float volume, float pitch) {
        return Sound.sound(type, source.soundSource(), volume, pitch);
    }

    @NotNull
    public static Sound sound(@NotNull Consumer<Builder> configurer) {
        return (Sound)AbstractBuilder.configureAndBuild((AbstractBuilder)Sound.sound(), configurer);
    }
}
