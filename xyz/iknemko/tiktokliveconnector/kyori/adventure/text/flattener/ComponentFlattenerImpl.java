/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.KeybindComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScoreComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.SelectorComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattener
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattenerImpl$BuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattenerImpl$Handler
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattenerImpl$StackEntry
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.FlattenerListener
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.InheritanceAwareMap
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.KeybindComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScoreComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.SelectorComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattener;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattenerImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.FlattenerListener;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.InheritanceAwareMap;

final class ComponentFlattenerImpl
implements ComponentFlattener {
    static final ComponentFlattener BASIC = (ComponentFlattener)new BuilderImpl().mapper(KeybindComponent.class, component -> component.keybind()).mapper(ScoreComponent.class, component -> {
        @Nullable String value = component.value();
        return value != null ? value : "";
    }).mapper(SelectorComponent.class, SelectorComponent::pattern).mapper(TextComponent.class, TextComponent::content).mapper(TranslatableComponent.class, component -> {
        @Nullable String fallback = component.fallback();
        return fallback != null ? fallback : component.key();
    }).build();
    private final Function<Component, String> unknownHandler;
    static final ComponentFlattener TEXT_ONLY = (ComponentFlattener)new BuilderImpl().mapper(TextComponent.class, TextComponent::content).build();
    private final InheritanceAwareMap<Component, Handler> flatteners;
    private static final int MAX_DEPTH = 512;
    private final int maxNestedDepth;

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull ComponentFlattener.Builder toBuilder() {
        return new BuilderImpl(this.flatteners, this.unknownHandler, this.maxNestedDepth);
    }

    static /* synthetic */ void access$000(ComponentFlattenerImpl x0, Component x1, FlattenerListener x2, int x3, int x4) {
        x0.flatten0(x1, x2, x3, x4);
    }

    @Nullable
    private <T extends Component> Handler flattener(T test) {
        Handler flattener = (Handler)this.flatteners.get(test.getClass());
        if (flattener != null) return flattener;
        if (this.unknownHandler == null) return flattener;
        return (self, component, listener, depth, nestedDepth) -> listener.component(this.unknownHandler.apply(component));
    }

    private void flatten0(@NotNull Component input, @NotNull FlattenerListener listener, int depth, int nestedDepth) {
        Objects.requireNonNull(input, "input");
        Objects.requireNonNull(listener, "listener");
        if (input == Component.empty()) {
            return;
        }
        if (this.maxNestedDepth != -1 && nestedDepth > this.maxNestedDepth) {
            throw new IllegalStateException("Exceeded maximum nesting depth of " + this.maxNestedDepth + " while attempting to flatten components!");
        }
        ArrayDeque<StackEntry> componentStack = new ArrayDeque<StackEntry>();
        ArrayDeque<Style> styleStack = new ArrayDeque<Style>();
        componentStack.push(new StackEntry(input, depth));
        block0: while (true) {
            if (componentStack.isEmpty()) {
                while (!styleStack.isEmpty()) {
                    Style style = (Style)styleStack.pop();
                    listener.popStyle(style);
                }
                return;
            }
            StackEntry entry = (StackEntry)componentStack.pop();
            int currentDepth = entry.depth;
            if (currentDepth > 512) {
                throw new IllegalStateException("Exceeded maximum depth of 512 while attempting to flatten components!");
            }
            Component component = entry.component;
            // Could not load outer class - annotation placement on inner may be incorrect
            @Nullable ComponentFlattenerImpl.Handler flattener = this.flattener(component);
            Style componentStyle = component.style();
            listener.pushStyle(componentStyle);
            styleStack.push(componentStyle);
            if (flattener != null) {
                flattener.handle(this, component, listener, currentDepth, nestedDepth);
            }
            if (!component.children().isEmpty() && listener.shouldContinue()) {
                List children = component.children();
                int i = children.size() - 1;
                while (true) {
                    if (i < 0) continue block0;
                    componentStack.push(new StackEntry((Component)children.get(i), currentDepth + 1));
                    --i;
                }
            }
            Style style = (Style)styleStack.pop();
            listener.popStyle(style);
        }
    }

    ComponentFlattenerImpl(InheritanceAwareMap<Component, Handler> flatteners, @Nullable Function<Component, String> unknownHandler, int maxNestedDepth) {
        this.flatteners = flatteners;
        this.unknownHandler = unknownHandler;
        this.maxNestedDepth = maxNestedDepth;
    }

    public void flatten(@NotNull Component input, @NotNull FlattenerListener listener) {
        this.flatten0(input, listener, 0, 0);
    }
}
