/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundStop
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.sound;

import java.util.Objects;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundStop;

abstract class SoundStopImpl
implements SoundStop {
    static final SoundStop ALL = new /* Unavailable Anonymous Inner Class!! */;
    private final // Could not load outer class - annotation placement on inner may be incorrect
     @Nullable Sound.Source source;

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"name", (Object)this.sound()), ExaminableProperty.of((String)"source", (Object)this.source));
    }

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
     @Nullable Sound.Source source() {
        return this.source;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SoundStopImpl)) {
            return false;
        }
        SoundStopImpl that = (SoundStopImpl)other;
        return Objects.equals(this.sound(), that.sound()) && Objects.equals(this.source, that.source);
    }

    SoundStopImpl(// Could not load outer class - annotation placement on inner may be incorrect
     @Nullable Sound.Source source) {
        this.source = source;
    }

    public int hashCode() {
        int result = Objects.hashCode(this.sound());
        result = 31 * result + Objects.hashCode(this.source);
        return result;
    }
}
