/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound$Source
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound$Type
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundImpl$Eager
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundImpl$Lazy
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.sound;

import java.util.Objects;
import java.util.OptionalLong;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundImpl;

static final class SoundImpl.BuilderImpl
implements Sound.Builder {
    private Sound.Source source = Sound.Source.MASTER;
    private Supplier<? extends Sound.Type> lazyType;
    private float pitch = 1.0f;
    private static final float DEFAULT_VOLUME = 1.0f;
    private Key eagerType;
    private float volume = 1.0f;
    private OptionalLong seed = OptionalLong.empty();
    private static final float DEFAULT_PITCH = 1.0f;

    @NotNull
    public Sound build() {
        if (this.eagerType != null) {
            return new SoundImpl.Eager(this.eagerType, this.source, this.volume, this.pitch, this.seed);
        }
        if (this.lazyType == null) throw new IllegalStateException("A sound type must be provided to build a sound");
        return new SoundImpl.Lazy(this.lazyType, this.source, this.volume, this.pitch, this.seed);
    }

    @NotNull
    public Sound.Builder type(@NotNull Sound.Type type) {
        this.eagerType = Objects.requireNonNull(Objects.requireNonNull(type, "type").key(), "type.key()");
        this.lazyType = null;
        return this;
    }

    @NotNull
    public Sound.Builder volume(@Range(from=0L, to=0x7FFFFFFFL) float volume) {
        this.volume = volume;
        return this;
    }

    @NotNull
    public Sound.Builder seed(@NotNull OptionalLong seed) {
        this.seed = Objects.requireNonNull(seed, "seed");
        return this;
    }

    @NotNull
    public Sound.Builder pitch(@Range(from=-1L, to=1L) float pitch) {
        this.pitch = pitch;
        return this;
    }

    @NotNull
    public Sound.Builder source(@NotNull Sound.Source source) {
        this.source = Objects.requireNonNull(source, "source");
        return this;
    }

    @NotNull
    public Sound.Builder type(@NotNull Supplier<? extends Sound.Type> typeSupplier) {
        this.lazyType = Objects.requireNonNull(typeSupplier, "typeSupplier");
        this.eagerType = null;
        return this;
    }

    @NotNull
    public Sound.Builder type(@NotNull Key type) {
        this.eagerType = Objects.requireNonNull(type, "type");
        this.lazyType = null;
        return this;
    }

    @NotNull
    public Sound.Builder source(// Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Sound.Source.Provider source) {
        return this.source(source.soundSource());
    }

    SoundImpl.BuilderImpl() {
    }

    @NotNull
    public Sound.Builder seed(long seed) {
        this.seed = OptionalLong.of(seed);
        return this;
    }

    SoundImpl.BuilderImpl(@NotNull Sound existing) {
        if (existing instanceof SoundImpl.Eager) {
            this.type(((SoundImpl.Eager)existing).name);
        } else {
            if (!(existing instanceof SoundImpl.Lazy)) throw new IllegalArgumentException("Unknown sound type " + existing + ", must be Eager or Lazy");
            this.type(((SoundImpl.Lazy)existing).supplier);
        }
        this.source(existing.source()).volume(existing.volume()).pitch(existing.pitch()).seed(existing.seed());
    }
}
