/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.InheritanceAwareMapImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.InheritanceAwareMapImpl$BuilderImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.util;

import org.jetbrains.annotations.CheckReturnValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.InheritanceAwareMapImpl;

public interface InheritanceAwareMap<C, V> {
    @CheckReturnValue
    @NotNull
    public InheritanceAwareMap<C, V> with(@NotNull Class<? extends C> var1, @NotNull V var2);

    @NotNull
    public static <K, E> InheritanceAwareMap<K, E> empty() {
        return InheritanceAwareMapImpl.EMPTY;
    }

    public boolean containsKey(@NotNull Class<? extends C> var1);

    public static <K, E> // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull InheritanceAwareMap.Builder<K, E> builder(InheritanceAwareMap<? extends K, ? extends E> existing) {
        return new InheritanceAwareMapImpl.BuilderImpl().putAll(existing);
    }

    @NotNull
    @CheckReturnValue
    public InheritanceAwareMap<C, V> without(@NotNull Class<? extends C> var1);

    public static <K, E> // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull InheritanceAwareMap.Builder<K, E> builder() {
        return new InheritanceAwareMapImpl.BuilderImpl();
    }

    @Nullable
    public V get(@NotNull Class<? extends C> var1);
}
