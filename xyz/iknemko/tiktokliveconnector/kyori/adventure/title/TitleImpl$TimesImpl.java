/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.title.Title$Times
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.title;

import java.time.Duration;
import java.util.Objects;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.title.Title;

static class TitleImpl.TimesImpl
implements Title.Times {
    private final Duration fadeIn;
    private final Duration fadeOut;
    private final Duration stay;

    @NotNull
    public Duration stay() {
        return this.stay;
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"fadeIn", (Object)this.fadeIn), ExaminableProperty.of((String)"stay", (Object)this.stay), ExaminableProperty.of((String)"fadeOut", (Object)this.fadeOut));
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TitleImpl.TimesImpl)) {
            return false;
        }
        TitleImpl.TimesImpl that = (TitleImpl.TimesImpl)other;
        return this.fadeIn.equals(that.fadeIn) && this.stay.equals(that.stay) && this.fadeOut.equals(that.fadeOut);
    }

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    @NotNull
    public Duration fadeIn() {
        return this.fadeIn;
    }

    public int hashCode() {
        int result = this.fadeIn.hashCode();
        result = 31 * result + this.stay.hashCode();
        result = 31 * result + this.fadeOut.hashCode();
        return result;
    }

    @NotNull
    public Duration fadeOut() {
        return this.fadeOut;
    }

    TitleImpl.TimesImpl(@NotNull Duration fadeIn, @NotNull Duration stay, @NotNull Duration fadeOut) {
        this.fadeIn = Objects.requireNonNull(fadeIn, "fadeIn");
        this.stay = Objects.requireNonNull(stay, "stay");
        this.fadeOut = Objects.requireNonNull(fadeOut, "fadeOut");
    }
}
