/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BuildableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEventSource
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style$Merge
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.ARGBLike
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BuildableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEventSource;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.ARGBLike;

abstract class AbstractComponentBuilder<C extends BuildableComponent<C, B>, B extends ComponentBuilder<C, B>>
implements ComponentBuilder<C, B> {
    private // Could not load outer class - annotation placement on inner may be incorrect
    @Nullable Style.Builder styleBuilder;
    @Nullable
    private Style style;
    protected List<Component> children = Collections.emptyList();

    @NotNull
    public B append(@NotNull Iterable<? extends ComponentLike> components) {
        Objects.requireNonNull(components, "components");
        boolean prepared = false;
        Iterator<? extends ComponentLike> iterator = components.iterator();
        while (iterator.hasNext()) {
            ComponentLike like = iterator.next();
            Component component = Objects.requireNonNull(like, "components[?]").asComponent();
            if (component == Component.empty()) continue;
            if (!prepared) {
                this.prepareChildren();
                prepared = true;
            }
            this.children.add(Objects.requireNonNull(component, "components[?]"));
        }
        return (B)this;
    }

    @NotNull
    public B append(Component ... components) {
        return this.append((ComponentLike[])components);
    }

    @NotNull
    public B applyDeep(@NotNull Consumer<? super ComponentBuilder<?, ?>> consumer) {
        this.apply(consumer);
        if (this.children == Collections.emptyList()) {
            return (B)this;
        }
        ListIterator<Component> it = this.children.listIterator();
        while (it.hasNext()) {
            Component child = it.next();
            if (!(child instanceof BuildableComponent)) continue;
            ComponentBuilder childBuilder = ((BuildableComponent)child).toBuilder();
            childBuilder.applyDeep(consumer);
            it.set((Component)childBuilder.build());
        }
        return (B)this;
    }

    @NotNull
    public B shadowColorIfAbsent(@Nullable ARGBLike argb) {
        this.styleBuilder().shadowColorIfAbsent(argb);
        return (B)this;
    }

    private // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Style.Builder styleBuilder() {
        if (this.styleBuilder != null) return this.styleBuilder;
        if (this.style != null) {
            this.styleBuilder = this.style.toBuilder();
            this.style = null;
        } else {
            this.styleBuilder = Style.style();
        }
        return this.styleBuilder;
    }

    protected AbstractComponentBuilder(@NotNull C component) {
        List children = component.children();
        if (!children.isEmpty()) {
            this.children = new ArrayList<Component>(children);
        }
        if (!component.hasStyling()) return;
        this.style = component.style();
    }

    protected final boolean hasStyle() {
        return this.styleBuilder != null || this.style != null;
    }

    @NotNull
    public B color(@Nullable TextColor color) {
        this.styleBuilder().color(color);
        return (B)this;
    }

    @NotNull
    public B clickEvent(@Nullable ClickEvent event) {
        this.styleBuilder().clickEvent(event);
        return (B)this;
    }

    @NotNull
    public B resetStyle() {
        this.style = null;
        this.styleBuilder = null;
        return (B)this;
    }

    @NotNull
    public B shadowColor(@Nullable ARGBLike argb) {
        this.styleBuilder().shadowColor(argb);
        return (B)this;
    }

    @NotNull
    public B mapChildren(@NotNull Function<BuildableComponent<?, ?>, ? extends BuildableComponent<?, ?>> function) {
        if (this.children == Collections.emptyList()) {
            return (B)this;
        }
        ListIterator<Component> it = this.children.listIterator();
        while (it.hasNext()) {
            BuildableComponent<?, ?> mappedChild;
            Component child = it.next();
            if (!(child instanceof BuildableComponent) || child == (mappedChild = Objects.requireNonNull(function.apply((BuildableComponent)child), "mappedChild"))) continue;
            it.set((Component)mappedChild);
        }
        return (B)this;
    }

    @NotNull
    public B style(@NotNull Consumer<Style.Builder> consumer) {
        consumer.accept(this.styleBuilder());
        return (B)this;
    }

    @NotNull
    protected Style buildStyle() {
        if (this.styleBuilder != null) {
            return this.styleBuilder.build();
        }
        if (this.style == null) return Style.empty();
        return this.style;
    }

    @NotNull
    public List<Component> children() {
        return Collections.unmodifiableList(this.children);
    }

    @NotNull
    public B colorIfAbsent(@Nullable TextColor color) {
        this.styleBuilder().colorIfAbsent(color);
        return (B)this;
    }

    @NotNull
    public B mergeStyle(@NotNull Component that, @NotNull Set<Style.Merge> merges) {
        Style thatStyle = Objects.requireNonNull(that, "that").style();
        if (thatStyle.isEmpty() && merges.isEmpty()) {
            return (B)this;
        }
        this.styleBuilder().merge(thatStyle, merges);
        return (B)this;
    }

    @NotNull
    public B font(@Nullable Key font) {
        this.styleBuilder().font(font);
        return (B)this;
    }

    protected AbstractComponentBuilder() {
    }

    @NotNull
    public B append(@NotNull Component component) {
        if (component == Component.empty()) {
            return (B)this;
        }
        this.prepareChildren();
        this.children.add(Objects.requireNonNull(component, "component"));
        return (B)this;
    }

    @NotNull
    public B style(@NotNull Style style) {
        this.style = style;
        this.styleBuilder = null;
        return (B)this;
    }

    @NotNull
    public B decoration(@NotNull TextDecoration decoration, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TextDecoration.State state) {
        this.styleBuilder().decoration(decoration, state);
        return (B)this;
    }

    @NotNull
    public B append(ComponentLike ... components) {
        Objects.requireNonNull(components, "components");
        boolean prepared = false;
        int i = 0;
        int length = components.length;
        while (i < length) {
            Component component = Objects.requireNonNull(components[i], "components[?]").asComponent();
            if (component != Component.empty()) {
                if (!prepared) {
                    this.prepareChildren();
                    prepared = true;
                }
                this.children.add(Objects.requireNonNull(component, "components[?]"));
            }
            ++i;
        }
        return (B)this;
    }

    @NotNull
    public B decorationIfAbsent(@NotNull TextDecoration decoration, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TextDecoration.State state) {
        this.styleBuilder().decorationIfAbsent(decoration, state);
        return (B)this;
    }

    @NotNull
    public B insertion(@Nullable String insertion) {
        this.styleBuilder().insertion(insertion);
        return (B)this;
    }

    @NotNull
    public B hoverEvent(@Nullable HoverEventSource<?> source) {
        this.styleBuilder().hoverEvent(source);
        return (B)this;
    }

    @NotNull
    public B mapChildrenDeep(@NotNull Function<BuildableComponent<?, ?>, ? extends BuildableComponent<?, ?>> function) {
        if (this.children == Collections.emptyList()) {
            return (B)this;
        }
        ListIterator<Component> it = this.children.listIterator();
        while (it.hasNext()) {
            Component child = it.next();
            if (!(child instanceof BuildableComponent)) continue;
            BuildableComponent<?, ?> mappedChild = Objects.requireNonNull(function.apply((BuildableComponent)child), "mappedChild");
            if (mappedChild.children().isEmpty()) {
                if (child == mappedChild) continue;
                it.set((Component)mappedChild);
                continue;
            }
            ComponentBuilder builder = mappedChild.toBuilder();
            builder.mapChildrenDeep(function);
            it.set((Component)builder.build());
        }
        return (B)this;
    }

    private void prepareChildren() {
        if (this.children != Collections.emptyList()) return;
        this.children = new ArrayList<Component>();
    }
}
