/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattener
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable$Builder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattener;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable;

public static interface ComponentFlattener.Builder
extends AbstractBuilder<ComponentFlattener>,
Buildable.Builder<ComponentFlattener> {
    @NotNull
    public ComponentFlattener.Builder nestingLimit(@Range(from=1L, to=0x7FFFFFFFL) int var1);

    @NotNull
    public <T extends Component> ComponentFlattener.Builder complexMapper(@NotNull Class<T> var1, @NotNull BiConsumer<T, Consumer<Component>> var2);

    @NotNull
    public <T extends Component> ComponentFlattener.Builder mapper(@NotNull Class<T> var1, @NotNull Function<T, String> var2);

    @NotNull
    public ComponentFlattener.Builder unknownMapper(@Nullable Function<Component, String> var1);
}
