/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointersSupplier
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer;

import java.util.function.Function;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointersSupplier;

public static interface PointersSupplier.Builder<T>
extends AbstractBuilder<PointersSupplier<T>> {
    @Contract(value="_, _ -> this")
    @NotNull
    public <P> PointersSupplier.Builder<T> resolving(@NotNull Pointer<P> var1, @NotNull Function<T, P> var2);

    @Contract(value="_ -> this")
    @NotNull
    public PointersSupplier.Builder<T> parent(@Nullable PointersSupplier<? super T> var1);
}
