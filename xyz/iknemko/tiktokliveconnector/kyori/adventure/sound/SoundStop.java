/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound$Type
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundStopImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.sound;

import java.util.Objects;
import java.util.function.Supplier;
import net.kyori.examination.Examinable;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundStopImpl;

@ApiStatus.NonExtendable
public interface SoundStop
extends Examinable {
    @NotNull
    public static SoundStop source(// Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Sound.Source source) {
        Objects.requireNonNull(source, "source");
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    @NotNull
    public static SoundStop named(@NotNull Supplier<? extends Sound.Type> sound) {
        Objects.requireNonNull(sound, "sound");
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    @NotNull
    public static SoundStop named(// Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Sound.Type sound) {
        Objects.requireNonNull(sound, "sound");
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    @NotNull
    public static SoundStop named(@NotNull Key sound) {
        Objects.requireNonNull(sound, "sound");
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    @Nullable
    public Key sound();

    @NotNull
    public static SoundStop namedOnSource(@NotNull Key sound, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Sound.Source source) {
        Objects.requireNonNull(sound, "sound");
        Objects.requireNonNull(source, "source");
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
     @Nullable Sound.Source source();

    @NotNull
    public static SoundStop all() {
        return SoundStopImpl.ALL;
    }

    @NotNull
    public static SoundStop namedOnSource(// Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Sound.Type sound, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Sound.Source source) {
        Objects.requireNonNull(sound, "sound");
        return SoundStop.namedOnSource(sound.key(), source);
    }

    @NotNull
    public static SoundStop namedOnSource(@NotNull Supplier<? extends Sound.Type> sound, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Sound.Source source) {
        Objects.requireNonNull(sound, "sound");
        Objects.requireNonNull(source, "source");
        return new /* Unavailable Anonymous Inner Class!! */;
    }
}
