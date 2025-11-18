/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEventSource
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style$Merge
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration$State
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.ARGBLike
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEventSource;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.ARGBLike;

public interface ScopedComponent<C extends Component>
extends Component {
    @NotNull
    default public C color(@Nullable TextColor color) {
        return (C)super.color(color);
    }

    @NotNull
    default public C mergeStyle(@NotNull Component that, @NotNull Set<Style.Merge> merges) {
        return (C)super.mergeStyle(that, merges);
    }

    @NotNull
    default public C decoration(@NotNull TextDecoration decoration, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TextDecoration.State state) {
        return (C)super.decoration(decoration, state);
    }

    @NotNull
    default public C colorIfAbsent(@Nullable TextColor color) {
        return (C)super.colorIfAbsent(color);
    }

    @NotNull
    default public C decoration(@NotNull TextDecoration decoration, boolean flag) {
        return (C)super.decoration(decoration, flag);
    }

    @NotNull
    default public C applyFallbackStyle(StyleBuilderApplicable ... style) {
        return (C)super.applyFallbackStyle(style);
    }

    @NotNull
    default public C shadowColor(@Nullable ARGBLike argb) {
        return (C)super.shadowColor(argb);
    }

    @NotNull
    default public C insertion(@Nullable String insertion) {
        return (C)super.insertion(insertion);
    }

    @NotNull
    default public C appendNewline() {
        return (C)super.appendNewline();
    }

    @NotNull
    default public C clickEvent(@Nullable ClickEvent event) {
        return (C)super.clickEvent(event);
    }

    @NotNull
    public C style(@NotNull Style var1);

    @NotNull
    default public C append(@NotNull ComponentBuilder<?, ?> builder) {
        return (C)super.append(builder);
    }

    @NotNull
    public C children(@NotNull List<? extends ComponentLike> var1);

    @NotNull
    default public C shadowColorIfAbsent(@Nullable ARGBLike argb) {
        return (C)super.shadowColorIfAbsent(argb);
    }

    @NotNull
    default public C append(@NotNull List<? extends ComponentLike> components) {
        return (C)super.append(components);
    }

    @NotNull
    default public C style(@NotNull Consumer<Style.Builder> consumer, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Style.Merge.Strategy strategy) {
        return (C)super.style(consumer, strategy);
    }

    @NotNull
    default public C asComponent() {
        return (C)super.asComponent();
    }

    @NotNull
    default public C applyFallbackStyle(@NotNull Style style) {
        return (C)super.applyFallbackStyle(style);
    }

    @NotNull
    default public C append(ComponentLike ... components) {
        return (C)super.append(components);
    }

    @NotNull
    default public C decorate(@NotNull TextDecoration decoration) {
        return (C)super.decorate(decoration);
    }

    @NotNull
    default public C appendSpace() {
        return (C)super.appendSpace();
    }

    @NotNull
    default public C decorations(@NotNull Map<TextDecoration, TextDecoration.State> decorations) {
        return (C)super.decorations(decorations);
    }

    @NotNull
    default public C mergeStyle(@NotNull Component that) {
        return (C)super.mergeStyle(that);
    }

    @NotNull
    default public C append(@NotNull ComponentLike like) {
        return (C)super.append(like);
    }

    @NotNull
    default public C decorationIfAbsent(@NotNull TextDecoration decoration, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TextDecoration.State state) {
        return (C)super.decorationIfAbsent(decoration, state);
    }

    @NotNull
    default public C append(@NotNull Component component) {
        return (C)super.append(component);
    }

    @NotNull
    default public C style(// Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Style.Builder style) {
        return (C)super.style(style);
    }

    @NotNull
    default public C mergeStyle(@NotNull Component that, Style.Merge ... merges) {
        return (C)super.mergeStyle(that, merges);
    }

    @NotNull
    default public C font(@Nullable Key key) {
        return (C)super.font(key);
    }

    @NotNull
    default public C style(@NotNull Consumer<Style.Builder> style) {
        return (C)super.style(style);
    }

    @NotNull
    default public C hoverEvent(@Nullable HoverEventSource<?> event) {
        return (C)super.hoverEvent(event);
    }
}
