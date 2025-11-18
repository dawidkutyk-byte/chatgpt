/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.title.Title
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.title.Title$Times
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.title.TitlePart
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.title;

import java.util.Objects;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.title.Title;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.title.TitlePart;

final class TitleImpl
implements Title {
    @Nullable
    private final Title.Times times;
    private final Component title;
    private final Component subtitle;

    public <T> @UnknownNullability T part(@NotNull TitlePart<T> part) {
        Objects.requireNonNull(part, "part");
        if (part == TitlePart.TITLE) {
            return (T)this.title;
        }
        if (part == TitlePart.SUBTITLE) {
            return (T)this.subtitle;
        }
        if (part != TitlePart.TIMES) throw new IllegalArgumentException("Don't know what " + part + " is.");
        return (T)this.times;
    }

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"title", (Object)this.title), ExaminableProperty.of((String)"subtitle", (Object)this.subtitle), ExaminableProperty.of((String)"times", (Object)this.times));
    }

    @Nullable
    public Title.Times times() {
        return this.times;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (this.getClass() != other.getClass()) {
            return false;
        }
        TitleImpl that = (TitleImpl)other;
        return this.title.equals(that.title) && this.subtitle.equals(that.subtitle) && Objects.equals(this.times, that.times);
    }

    TitleImpl(@NotNull Component title, @NotNull Component subtitle, @Nullable Title.Times times) {
        this.title = Objects.requireNonNull(title, "title");
        this.subtitle = Objects.requireNonNull(subtitle, "subtitle");
        this.times = times;
    }

    public int hashCode() {
        int result = this.title.hashCode();
        result = 31 * result + this.subtitle.hashCode();
        result = 31 * result + Objects.hashCode(this.times);
        return result;
    }

    @NotNull
    public Component title() {
        return this.title;
    }

    @NotNull
    public Component subtitle() {
        return this.subtitle;
    }
}
