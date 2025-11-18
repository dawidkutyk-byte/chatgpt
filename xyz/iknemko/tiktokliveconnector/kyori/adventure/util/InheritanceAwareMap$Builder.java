/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.InheritanceAwareMap
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.util;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.InheritanceAwareMap;

public static interface InheritanceAwareMap.Builder<C, V>
extends AbstractBuilder<InheritanceAwareMap<C, V>> {
    @NotNull
    public InheritanceAwareMap.Builder<C, V> putAll(@NotNull InheritanceAwareMap<? extends C, ? extends V> var1);

    @NotNull
    public InheritanceAwareMap.Builder<C, V> strict(boolean var1);

    @NotNull
    public InheritanceAwareMap.Builder<C, V> put(@NotNull Class<? extends C> var1, @NotNull V var2);

    @NotNull
    public InheritanceAwareMap.Builder<C, V> remove(@NotNull Class<? extends C> var1);
}
