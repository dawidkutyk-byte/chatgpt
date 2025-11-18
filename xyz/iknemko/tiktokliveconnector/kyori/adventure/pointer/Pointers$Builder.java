/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable$Builder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer;

import java.util.function.Supplier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable;

public static interface Pointers.Builder
extends AbstractBuilder<Pointers>,
Buildable.Builder<Pointers> {
    @Contract(value="_, _ -> this")
    @NotNull
    public <T> Pointers.Builder withDynamic(@NotNull Pointer<T> var1, @NotNull Supplier<@Nullable T> var2);

    @Contract(value="_, _ -> this")
    @NotNull
    default public <T> Pointers.Builder withStatic(@NotNull Pointer<T> pointer, @Nullable T value) {
        return this.withDynamic(pointer, () -> value);
    }
}
