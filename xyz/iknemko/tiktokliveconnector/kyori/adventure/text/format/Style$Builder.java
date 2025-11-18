/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEventSource
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.MutableStyleSetter
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style$Merge
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style$Merge$Strategy
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration$State
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable$Builder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format;

import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEventSource;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.MutableStyleSetter;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable;

/*
 * Exception performing whole class analysis ignored.
 */
public static interface Style.Builder
extends AbstractBuilder<Style>,
Buildable.Builder<Style>,
MutableStyleSetter<Style.Builder> {
    @NotNull
    public Style build();

    @Contract(value="_ -> this")
    @NotNull
    default public Style.Builder decorate(@NotNull TextDecoration decoration) {
        return (Style.Builder)super.decorate(decoration);
    }

    @Contract(value="_, _ -> this")
    @NotNull
    default public Style.Builder merge(@NotNull Style that, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Style.Merge.Strategy strategy) {
        return this.merge(that, strategy, Style.Merge.all());
    }

    @NotNull
    @Contract(value="_, _ -> this")
    default public Style.Builder merge(@NotNull Style that, Style.Merge ... merges) {
        if (merges.length != 0) return this.merge(that, Style.Merge.merges((Style.Merge[])merges));
        return this;
    }

    @Contract(value="_ -> this")
    @NotNull
    public Style.Builder font(@Nullable Key var1);

    @Contract(value="_ -> this")
    @NotNull
    default public Style.Builder merge(@NotNull Style that) {
        return this.merge(that, Style.Merge.all());
    }

    @NotNull
    @Contract(value="_ -> this")
    public Style.Builder hoverEvent(@Nullable HoverEventSource<?> var1);

    @Contract(value="_ -> this")
    @NotNull
    default public Style.Builder decorations(@NotNull Map<TextDecoration, TextDecoration.State> decorations) {
        return (Style.Builder)super.decorations(decorations);
    }

    @Contract(value="_ -> this")
    @NotNull
    public Style.Builder insertion(@Nullable String var1);

    @Contract(value="_, _ -> this")
    @NotNull
    public Style.Builder decoration(@NotNull TextDecoration var1, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TextDecoration.State var2);

    @NotNull
    @Contract(value="_ -> this")
    default public Style.Builder apply(@NotNull StyleBuilderApplicable applicable) {
        applicable.styleApply(this);
        return this;
    }

    @Contract(value="_ -> this")
    @NotNull
    public Style.Builder colorIfAbsent(@Nullable TextColor var1);

    @NotNull
    @Contract(value="_, _ -> this")
    public Style.Builder decorationIfAbsent(@NotNull TextDecoration var1, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TextDecoration.State var2);

    @Contract(value="_, _, _ -> this")
    @NotNull
    public Style.Builder merge(@NotNull Style var1, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Style.Merge.Strategy var2, @NotNull Set<Style.Merge> var3);

    @Contract(value="_, _ -> this")
    @NotNull
    default public Style.Builder merge(@NotNull Style that, @NotNull Set<Style.Merge> merges) {
        return this.merge(that, Style.Merge.Strategy.ALWAYS, merges);
    }

    @Contract(value="_ -> this")
    @NotNull
    public Style.Builder color(@Nullable TextColor var1);

    @Contract(value="_, _, _ -> this")
    @NotNull
    default public Style.Builder merge(@NotNull Style that, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Style.Merge.Strategy strategy, Style.Merge ... merges) {
        if (merges.length != 0) return this.merge(that, strategy, Style.Merge.merges((Style.Merge[])merges));
        return this;
    }

    @Contract(value="_ -> this")
    @NotNull
    default public Style.Builder decorate(TextDecoration ... decorations) {
        return (Style.Builder)super.decorate(decorations);
    }

    @NotNull
    @Contract(value="_, _ -> this")
    default public Style.Builder decoration(@NotNull TextDecoration decoration, boolean flag) {
        return (Style.Builder)super.decoration(decoration, flag);
    }

    @NotNull
    @Contract(value="_ -> this")
    public Style.Builder clickEvent(@Nullable ClickEvent var1);
}
