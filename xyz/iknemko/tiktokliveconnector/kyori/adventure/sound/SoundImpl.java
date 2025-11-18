/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound$Emitter
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound$Source
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundStop
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.ShadyPines
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.sound;

import java.util.OptionalLong;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundStop;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.ShadyPines;

abstract class SoundImpl
implements Sound {
    private final Sound.Source source;
    static final Sound.Emitter EMITTER_SELF = new /* Unavailable Anonymous Inner Class!! */;
    private final float volume;
    private SoundStop stop;
    private final OptionalLong seed;
    private final float pitch;

    @NotNull
    public Sound.Source source() {
        return this.source;
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"name", (Object)this.name()), ExaminableProperty.of((String)"source", (Object)this.source), ExaminableProperty.of((String)"volume", (float)this.volume), ExaminableProperty.of((String)"pitch", (float)this.pitch), ExaminableProperty.of((String)"seed", (Object)this.seed));
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SoundImpl)) {
            return false;
        }
        SoundImpl that = (SoundImpl)other;
        return this.name().equals(that.name()) && this.source == that.source && ShadyPines.equals((float)this.volume, (float)that.volume) && ShadyPines.equals((float)this.pitch, (float)that.pitch) && this.seed.equals(that.seed);
    }

    @NotNull
    public SoundStop asStop() {
        if (this.stop != null) return this.stop;
        this.stop = SoundStop.namedOnSource((Key)this.name(), (Sound.Source)this.source());
        return this.stop;
    }

    public int hashCode() {
        int result = this.name().hashCode();
        result = 31 * result + this.source.hashCode();
        result = 31 * result + Float.hashCode(this.volume);
        result = 31 * result + Float.hashCode(this.pitch);
        result = 31 * result + this.seed.hashCode();
        return result;
    }

    @NotNull
    public OptionalLong seed() {
        return this.seed;
    }

    public float volume() {
        return this.volume;
    }

    SoundImpl(@NotNull Sound.Source source, float volume, float pitch, OptionalLong seed) {
        this.source = source;
        this.volume = volume;
        this.pitch = pitch;
        this.seed = seed;
    }

    public float pitch() {
        return this.pitch;
    }

    public String toString() {
        return Internals.toString((Examinable)this);
    }
}
