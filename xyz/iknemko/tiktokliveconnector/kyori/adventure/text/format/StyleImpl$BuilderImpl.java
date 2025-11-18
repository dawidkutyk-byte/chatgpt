/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
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
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration$State
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.ARGBLike
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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

/*
 * Exception performing whole class analysis ignored.
 */
static final class StyleImpl.BuilderImpl
implements Style.Builder {
    DecorationMap decorations;
    @Nullable
    String insertion;
    @Nullable
    Key font;
    @Nullable
    ShadowColor shadowColor;
    @Nullable
    TextColor color;
    @Nullable
    HoverEvent<?> hoverEvent;
    @Nullable
    ClickEvent clickEvent;

    private boolean isEmpty() {
        return this.color == null && this.shadowColor == null && this.decorations == DecorationMap.EMPTY && this.clickEvent == null && this.hoverEvent == null && this.insertion == null && this.font == null;
    }

    @NotNull
    public Style.Builder insertion(@Nullable String insertion) {
        this.insertion = insertion;
        return this;
    }

    @NotNull
    public Style.Builder shadowColor(@Nullable ARGBLike argb) {
        this.shadowColor = argb == null ? null : ShadowColor.shadowColor((ARGBLike)argb);
        return this;
    }

    @NotNull
    public Style.Builder merge(@NotNull Style that, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Style.Merge.Strategy strategy, @NotNull Set<Style.Merge> merges) {
        String insertion;
        ShadowColor shadowColor;
        TextColor color;
        Objects.requireNonNull(that, "style");
        Objects.requireNonNull(strategy, "strategy");
        Objects.requireNonNull(merges, "merges");
        if (StyleImpl.nothingToMerge((Style)that, (Style.Merge.Strategy)strategy, merges)) {
            return this;
        }
        if (merges.contains(Style.Merge.COLOR) && (color = that.color()) != null && (strategy == Style.Merge.Strategy.ALWAYS || strategy == Style.Merge.Strategy.IF_ABSENT_ON_TARGET && this.color == null)) {
            this.color(color);
        }
        if (merges.contains(Style.Merge.SHADOW_COLOR) && (shadowColor = that.shadowColor()) != null && (strategy == Style.Merge.Strategy.ALWAYS || strategy == Style.Merge.Strategy.IF_ABSENT_ON_TARGET && this.shadowColor == null)) {
            this.shadowColor((ARGBLike)shadowColor);
        }
        if (merges.contains(Style.Merge.DECORATIONS)) {
            for (TextDecoration decoration : DecorationMap.DECORATIONS) {
                TextDecoration.State state = that.decoration(decoration);
                if (state == TextDecoration.State.NOT_SET) continue;
                if (strategy == Style.Merge.Strategy.ALWAYS) {
                    this.decoration(decoration, state);
                    continue;
                }
                if (strategy != Style.Merge.Strategy.IF_ABSENT_ON_TARGET) continue;
                this.decorationIfAbsent(decoration, state);
            }
        }
        if (merges.contains(Style.Merge.EVENTS)) {
            HoverEvent hoverEvent;
            ClickEvent clickEvent = that.clickEvent();
            if (clickEvent != null && (strategy == Style.Merge.Strategy.ALWAYS || strategy == Style.Merge.Strategy.IF_ABSENT_ON_TARGET && this.clickEvent == null)) {
                this.clickEvent(clickEvent);
            }
            if ((hoverEvent = that.hoverEvent()) != null && (strategy == Style.Merge.Strategy.ALWAYS || strategy == Style.Merge.Strategy.IF_ABSENT_ON_TARGET && this.hoverEvent == null)) {
                this.hoverEvent((HoverEventSource<?>)hoverEvent);
            }
        }
        if (merges.contains(Style.Merge.INSERTION) && (insertion = that.insertion()) != null && (strategy == Style.Merge.Strategy.ALWAYS || strategy == Style.Merge.Strategy.IF_ABSENT_ON_TARGET && this.insertion == null)) {
            this.insertion(insertion);
        }
        if (!merges.contains(Style.Merge.FONT)) return this;
        Key font = that.font();
        if (font == null) return this;
        if (strategy != Style.Merge.Strategy.ALWAYS) {
            if (strategy != Style.Merge.Strategy.IF_ABSENT_ON_TARGET) return this;
            if (this.font != null) return this;
        }
        this.font(font);
        return this;
    }

    StyleImpl.BuilderImpl() {
        this.decorations = DecorationMap.EMPTY;
    }

    @NotNull
    public Style.Builder colorIfAbsent(@Nullable TextColor color) {
        if (this.color != null) return this;
        this.color = color;
        return this;
    }

    @NotNull
    public Style.Builder shadowColorIfAbsent(@Nullable ARGBLike argb) {
        if (this.shadowColor != null) return this;
        this.shadowColor = argb == null ? null : ShadowColor.shadowColor((ARGBLike)argb);
        return this;
    }

    @NotNull
    public Style.Builder clickEvent(@Nullable ClickEvent event) {
        this.clickEvent = event;
        return this;
    }

    @NotNull
    public Style.Builder decorationIfAbsent(@NotNull TextDecoration decoration, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TextDecoration.State state) {
        Objects.requireNonNull(state, "state");
        // Could not load outer class - annotation placement on inner may be incorrect
        @Nullable TextDecoration.State oldState = this.decorations.get((Object)decoration);
        if (oldState == TextDecoration.State.NOT_SET) {
            this.decorations = this.decorations.with(decoration, state);
        }
        if (oldState == null) throw new IllegalArgumentException(String.format("unknown decoration '%s'", decoration));
        return this;
    }

    @NotNull
    public Style.Builder font(@Nullable Key font) {
        this.font = font;
        return this;
    }

    @NotNull
    public Style.Builder color(@Nullable TextColor color) {
        this.color = color;
        return this;
    }

    @NotNull
    public Style.Builder decoration(@NotNull TextDecoration decoration, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TextDecoration.State state) {
        Objects.requireNonNull(state, "state");
        Objects.requireNonNull(decoration, "decoration");
        this.decorations = this.decorations.with(decoration, state);
        return this;
    }

    StyleImpl.BuilderImpl(@NotNull StyleImpl style) {
        this.color = style.color;
        this.shadowColor = style.shadowColor;
        this.decorations = style.decorations;
        this.clickEvent = style.clickEvent;
        this.hoverEvent = style.hoverEvent;
        this.insertion = style.insertion;
        this.font = style.font;
    }

    @NotNull
    public StyleImpl build() {
        if (!this.isEmpty()) return new StyleImpl(this.font, this.color, this.shadowColor, (Map)this.decorations, this.clickEvent, this.hoverEvent, this.insertion);
        return StyleImpl.EMPTY;
    }

    @NotNull
    public Style.Builder hoverEvent(@Nullable HoverEventSource<?> source) {
        this.hoverEvent = HoverEventSource.unbox(source);
        return this;
    }
}
