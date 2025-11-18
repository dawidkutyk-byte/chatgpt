/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BuildableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilderApplicable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEventSource
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.MutableStyleSetter
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style$Merge
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration$State
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable$Builder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BuildableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilderApplicable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEventSource;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.MutableStyleSetter;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable;

@ApiStatus.NonExtendable
public interface ComponentBuilder<C extends BuildableComponent<C, B>, B extends ComponentBuilder<C, B>>
extends AbstractBuilder<C>,
Buildable.Builder<C>,
ComponentBuilderApplicable,
ComponentLike,
MutableStyleSetter<B> {
    @NotNull
    @Contract(value="_ -> this")
    public B append(ComponentLike ... var1);

    @NotNull
    @Contract(value="_, _ -> this")
    public B decoration(@NotNull TextDecoration var1, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TextDecoration.State var2);

    @NotNull
    @Contract(value="_ -> this")
    public B append(@NotNull Component var1);

    @NotNull
    public B resetStyle();

    @NotNull
    @Contract(value="_, _ -> this")
    default public B mergeStyle(@NotNull Component that, Style.Merge ... merges) {
        return this.mergeStyle(that, Style.Merge.merges((Style.Merge[])merges));
    }

    @Contract(value="_ -> this")
    @NotNull
    default public B append(@NotNull ComponentBuilder<?, ?> builder) {
        return this.append((Component)builder.build());
    }

    @NotNull
    @Contract(value="_ -> this")
    public B append(@NotNull Iterable<? extends ComponentLike> var1);

    @Contract(value="_ -> this")
    @NotNull
    default public B applicableApply(@NotNull ComponentBuilderApplicable applicable) {
        applicable.componentBuilderApply(this);
        return (B)this;
    }

    @Contract(value="_ -> this")
    @NotNull
    default public B decorations(@NotNull Map<TextDecoration, TextDecoration.State> decorations) {
        return (B)((ComponentBuilder)super.decorations(decorations));
    }

    @NotNull
    @Contract(value="_ -> this")
    public B insertion(@Nullable String var1);

    @NotNull
    @Contract(value="_ -> this")
    public B append(Component ... var1);

    @NotNull
    @Contract(value="_ -> this")
    public B applyDeep(@NotNull Consumer<? super ComponentBuilder<?, ?>> var1);

    @NotNull
    @Contract(value="_ -> this")
    public B style(@NotNull Style var1);

    @Contract(value="_ -> this")
    @NotNull
    public B style(@NotNull Consumer<Style.Builder> var1);

    @NotNull
    public C build();

    @NotNull
    @Contract(value="_ -> this")
    public B color(@Nullable TextColor var1);

    @Contract(value="_ -> this")
    @NotNull
    public B font(@Nullable Key var1);

    @NotNull
    @Contract(value="_ -> this")
    default public B apply(@NotNull Consumer<? super ComponentBuilder<?, ?>> consumer) {
        consumer.accept(this);
        return (B)this;
    }

    @NotNull
    @Contract(value="_, _ -> this")
    public B decorationIfAbsent(@NotNull TextDecoration var1, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TextDecoration.State var2);

    @NotNull
    @Contract(value="_ -> this")
    public B colorIfAbsent(@Nullable TextColor var1);

    @NotNull
    @Contract(value="_, _ -> this")
    default public B decorations(@NotNull Set<TextDecoration> decorations, boolean flag) {
        return (B)((ComponentBuilder)super.decorations(decorations, flag));
    }

    @Contract(value="_ -> this")
    @NotNull
    default public B mergeStyle(@NotNull Component that) {
        return this.mergeStyle(that, Style.Merge.all());
    }

    @NotNull
    default public B appendNewline() {
        return this.append((Component)Component.newline());
    }

    @Contract(value="_ -> this")
    @NotNull
    public B hoverEvent(@Nullable HoverEventSource<?> var1);

    @Contract(value="_, _ -> this")
    @NotNull
    default public B decoration(@NotNull TextDecoration decoration, boolean flag) {
        return this.decoration(decoration, TextDecoration.State.byBoolean((boolean)flag));
    }

    default public void componentBuilderApply(@NotNull ComponentBuilder<?, ?> component) {
        component.append(this);
    }

    @NotNull
    @Contract(value="_ -> this")
    public B mapChildrenDeep(@NotNull Function<BuildableComponent<?, ?>, ? extends BuildableComponent<?, ?>> var1);

    @NotNull
    default public B appendSpace() {
        return this.append((Component)Component.space());
    }

    @Contract(value="_ -> this")
    @NotNull
    public B clickEvent(@Nullable ClickEvent var1);

    @NotNull
    @Contract(value="_ -> this")
    public B mapChildren(@NotNull Function<BuildableComponent<?, ?>, ? extends BuildableComponent<?, ?>> var1);

    @NotNull
    public List<Component> children();

    @Contract(value="_ -> this")
    @NotNull
    default public B append(@NotNull ComponentLike component) {
        return this.append(component.asComponent());
    }

    @NotNull
    @Contract(value="_ -> this")
    default public B decorate(@NotNull TextDecoration decoration) {
        return this.decoration(decoration, TextDecoration.State.TRUE);
    }

    @NotNull
    @Contract(value="_, _ -> this")
    public B mergeStyle(@NotNull Component var1, @NotNull Set<Style.Merge> var2);

    @NotNull
    default public Component asComponent() {
        return this.build();
    }

    @Contract(value="_ -> this")
    @NotNull
    default public B decorate(TextDecoration ... decorations) {
        return (B)((ComponentBuilder)super.decorate(decorations));
    }
}
