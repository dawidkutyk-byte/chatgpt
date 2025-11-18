/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattener
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattener$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattenerImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattenerImpl$Handler
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.FlattenerListener
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.InheritanceAwareMap
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.InheritanceAwareMap$Builder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattener;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattenerImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.FlattenerListener;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.InheritanceAwareMap;

/*
 * Exception performing whole class analysis ignored.
 */
static final class ComponentFlattenerImpl.BuilderImpl
implements ComponentFlattener.Builder {
    private int maxNestedDepth = -1;
    @Nullable
    private Function<Component, String> unknownHandler;
    private final InheritanceAwareMap.Builder<Component, ComponentFlattenerImpl.Handler> flatteners;

    ComponentFlattenerImpl.BuilderImpl() {
        this.flatteners = InheritanceAwareMap.builder().strict(true);
    }

    ComponentFlattenerImpl.BuilderImpl(InheritanceAwareMap<Component, ComponentFlattenerImpl.Handler> flatteners, @Nullable Function<Component, String> unknownHandler, int maxNestedDepth) {
        this.flatteners = InheritanceAwareMap.builder(flatteners).strict(true);
        this.unknownHandler = unknownHandler;
        this.maxNestedDepth = maxNestedDepth;
    }

    @NotNull
    public ComponentFlattener build() {
        return new ComponentFlattenerImpl((InheritanceAwareMap)this.flatteners.build(), this.unknownHandler, this.maxNestedDepth);
    }

    public <T extends Component> // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull ComponentFlattener.Builder mapper(@NotNull Class<T> type, @NotNull Function<T, String> converter) {
        this.flatteners.put(type, (self, component, listener, depth, nestedDepth) -> listener.component((String)converter.apply(component)));
        return this;
    }

    @NotNull
    public ComponentFlattener.Builder nestingLimit(@Range(from=1L, to=0x7FFFFFFFL) int limit) {
        if (limit != -1 && limit < 1) {
            throw new IllegalArgumentException("limit must be positive or ComponentFlattener.NO_NESTING_LIMIT");
        }
        this.maxNestedDepth = limit;
        return this;
    }

    public <T extends Component> // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull ComponentFlattener.Builder complexMapper(@NotNull Class<T> type, @NotNull BiConsumer<T, Consumer<Component>> converter) {
        this.flatteners.put(type, (self, component, listener, depth, nestedDepth) -> converter.accept(component, c -> ComponentFlattenerImpl.access$000((ComponentFlattenerImpl)self, (Component)c, (FlattenerListener)listener, (int)depth, (int)(nestedDepth + 1))));
        return this;
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull ComponentFlattener.Builder unknownMapper(@Nullable Function<Component, String> converter) {
        this.unknownHandler = converter;
        return this;
    }
}
