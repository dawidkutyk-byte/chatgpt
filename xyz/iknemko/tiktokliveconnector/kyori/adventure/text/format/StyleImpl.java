/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEventSource
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.DecorationMap
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.ShadowColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style$Merge
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style$Merge$Strategy
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleImpl$BuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration$State
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.ARGBLike
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEventSource;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.DecorationMap;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.ShadowColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.ARGBLike;

final class StyleImpl
implements Style {
    @Nullable
    final ClickEvent clickEvent;
    @Nullable
    final HoverEvent<?> hoverEvent;
    @Nullable
    final TextColor color;
    static final StyleImpl EMPTY = new StyleImpl(null, null, null, (Map<TextDecoration, TextDecoration.State>)DecorationMap.EMPTY, null, null, null);
    @Nullable
    final String insertion;
    @NotNull
    final DecorationMap decorations;
    @Nullable
    final Key font;
    @Nullable
    final ShadowColor shadowColor;

    @NotNull
    public Style decorationIfAbsent(@NotNull TextDecoration decoration, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TextDecoration.State state) {
        Objects.requireNonNull(state, "state");
        // Could not load outer class - annotation placement on inner may be incorrect
        @Nullable TextDecoration.State oldState = this.decorations.get((Object)decoration);
        if (oldState == TextDecoration.State.NOT_SET) {
            return new StyleImpl(this.font, this.color, this.shadowColor, (Map<TextDecoration, TextDecoration.State>)this.decorations.with(decoration, state), this.clickEvent, this.hoverEvent, this.insertion);
        }
        if (oldState == null) throw new IllegalArgumentException(String.format("unknown decoration '%s'", decoration));
        return this;
    }

    @Nullable
    public HoverEvent<?> hoverEvent() {
        return this.hoverEvent;
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.concat(this.decorations.examinableProperties(), Stream.of(ExaminableProperty.of((String)"color", (Object)this.color), ExaminableProperty.of((String)"shadowColor", (Object)this.shadowColor), ExaminableProperty.of((String)"clickEvent", (Object)this.clickEvent), ExaminableProperty.of((String)"hoverEvent", this.hoverEvent), ExaminableProperty.of((String)"insertion", (String)this.insertion), ExaminableProperty.of((String)"font", (Object)this.font)));
    }

    @NotNull
    public Style.Builder toBuilder() {
        return new BuilderImpl(this);
    }

    @NotNull
    public Style font(@Nullable Key font) {
        if (!Objects.equals(this.font, font)) return new StyleImpl(font, this.color, this.shadowColor, (Map<TextDecoration, TextDecoration.State>)this.decorations, this.clickEvent, this.hoverEvent, this.insertion);
        return this;
    }

    @NotNull
    public Style shadowColorIfAbsent(@Nullable ARGBLike argb) {
        if (this.shadowColor != null) return this;
        return this.shadowColor(argb);
    }

    @NotNull
    public Style merge(@NotNull Style that, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Style.Merge.Strategy strategy, @NotNull Set<Style.Merge> merges) {
        if (StyleImpl.nothingToMerge(that, strategy, merges)) {
            return this;
        }
        if (this.isEmpty() && Style.Merge.hasAll(merges)) {
            return that;
        }
        Style.Builder builder = this.toBuilder();
        builder.merge(that, strategy, merges);
        return builder.build();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StyleImpl)) {
            return false;
        }
        StyleImpl that = (StyleImpl)other;
        return Objects.equals(this.color, that.color) && this.decorations.equals((Object)that.decorations) && Objects.equals(this.shadowColor, that.shadowColor) && Objects.equals(this.clickEvent, that.clickEvent) && Objects.equals(this.hoverEvent, that.hoverEvent) && Objects.equals(this.insertion, that.insertion) && Objects.equals(this.font, that.font);
    }

    @NotNull
    public Style insertion(@Nullable String insertion) {
        if (!Objects.equals(this.insertion, insertion)) return new StyleImpl(this.font, this.color, this.shadowColor, (Map<TextDecoration, TextDecoration.State>)this.decorations, this.clickEvent, this.hoverEvent, insertion);
        return this;
    }

    @NotNull
    public Style decoration(@NotNull TextDecoration decoration, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TextDecoration.State state) {
        Objects.requireNonNull(state, "state");
        if (this.decoration(decoration) != state) return new StyleImpl(this.font, this.color, this.shadowColor, (Map<TextDecoration, TextDecoration.State>)this.decorations.with(decoration, state), this.clickEvent, this.hoverEvent, this.insertion);
        return this;
    }

    @Nullable
    public ShadowColor shadowColor() {
        return this.shadowColor;
    }

    @NotNull
    public String toString() {
        return Internals.toString((Examinable)this);
    }

    @NotNull
    public Style hoverEvent(@Nullable HoverEventSource<?> source) {
        return new StyleImpl(this.font, this.color, this.shadowColor, (Map<TextDecoration, TextDecoration.State>)this.decorations, this.clickEvent, HoverEventSource.unbox(source), this.insertion);
    }

    @Nullable
    public String insertion() {
        return this.insertion;
    }

    public int hashCode() {
        int result = Objects.hashCode(this.color);
        result = 31 * result + Objects.hashCode(this.shadowColor);
        result = 31 * result + this.decorations.hashCode();
        result = 31 * result + Objects.hashCode(this.clickEvent);
        result = 31 * result + Objects.hashCode(this.hoverEvent);
        result = 31 * result + Objects.hashCode(this.insertion);
        result = 31 * result + Objects.hashCode(this.font);
        return result;
    }

    @NotNull
    public Style unmerge(@NotNull Style that) {
        if (this.isEmpty()) {
            return this;
        }
        BuilderImpl builder = new BuilderImpl(this);
        if (Objects.equals(this.font(), that.font())) {
            builder.font(null);
        }
        if (Objects.equals(this.color(), that.color())) {
            builder.color(null);
        }
        if (Objects.equals(this.shadowColor(), that.shadowColor())) {
            builder.shadowColor(null);
        }
        for (TextDecoration decoration : DecorationMap.DECORATIONS) {
            if (this.decoration(decoration) != that.decoration(decoration)) continue;
            builder.decoration(decoration, TextDecoration.State.NOT_SET);
        }
        if (Objects.equals(this.clickEvent(), that.clickEvent())) {
            builder.clickEvent(null);
        }
        if (Objects.equals(this.hoverEvent(), that.hoverEvent())) {
            builder.hoverEvent(null);
        }
        if (!Objects.equals(this.insertion(), that.insertion())) return builder.build();
        builder.insertion(null);
        return builder.build();
    }

    @NotNull
    public Style decorations(@NotNull Map<TextDecoration, TextDecoration.State> decorations) {
        return new StyleImpl(this.font, this.color, this.shadowColor, (Map<TextDecoration, TextDecoration.State>)DecorationMap.merge(decorations, (Map)this.decorations), this.clickEvent, this.hoverEvent, this.insertion);
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }

    @Nullable
    public ClickEvent clickEvent() {
        return this.clickEvent;
    }

    static boolean nothingToMerge(@NotNull Style mergeFrom, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Style.Merge.Strategy strategy, @NotNull Set<Style.Merge> merges) {
        if (strategy == Style.Merge.Strategy.NEVER) {
            return true;
        }
        if (mergeFrom.isEmpty()) {
            return true;
        }
        if (!merges.isEmpty()) return false;
        return true;
    }

    @NotNull
    public Map<TextDecoration, TextDecoration.State> decorations() {
        return this.decorations;
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TextDecoration.State decoration(@NotNull TextDecoration decoration) {
        // Could not load outer class - annotation placement on inner may be incorrect
        @Nullable TextDecoration.State state = this.decorations.get((Object)decoration);
        if (state == null) throw new IllegalArgumentException(String.format("unknown decoration '%s'", decoration));
        return state;
    }

    @NotNull
    public Style clickEvent(@Nullable ClickEvent event) {
        return new StyleImpl(this.font, this.color, this.shadowColor, (Map<TextDecoration, TextDecoration.State>)this.decorations, event, this.hoverEvent, this.insertion);
    }

    @NotNull
    public Style color(@Nullable TextColor color) {
        if (!Objects.equals(this.color, color)) return new StyleImpl(this.font, color, this.shadowColor, (Map<TextDecoration, TextDecoration.State>)this.decorations, this.clickEvent, this.hoverEvent, this.insertion);
        return this;
    }

    @Nullable
    public Key font() {
        return this.font;
    }

    @NotNull
    public Style shadowColor(@Nullable ARGBLike argb) {
        if (!Objects.equals(this.shadowColor, argb)) return new StyleImpl(this.font, this.color, argb == null ? null : ShadowColor.shadowColor((ARGBLike)argb), (Map<TextDecoration, TextDecoration.State>)this.decorations, this.clickEvent, this.hoverEvent, this.insertion);
        return this;
    }

    @NotNull
    public Style colorIfAbsent(@Nullable TextColor color) {
        if (this.color != null) return this;
        return this.color(color);
    }

    @Nullable
    public TextColor color() {
        return this.color;
    }

    StyleImpl(@Nullable Key font, @Nullable TextColor color, @Nullable ShadowColor shadowColor, @NotNull Map<TextDecoration, TextDecoration.State> decorations, @Nullable ClickEvent clickEvent, @Nullable HoverEvent<?> hoverEvent, @Nullable String insertion) {
        this.font = font;
        this.color = color;
        this.shadowColor = shadowColor;
        this.decorations = DecorationMap.fromMap(decorations);
        this.clickEvent = clickEvent;
        this.hoverEvent = hoverEvent;
        this.insertion = insertion;
    }
}
