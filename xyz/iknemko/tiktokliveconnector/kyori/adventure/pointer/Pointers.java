/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointersImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointersImpl$BuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer;

import java.util.Optional;
import java.util.function.Supplier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointersImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable;

public interface Pointers
extends Buildable<Pointers, Builder> {
    public <T> boolean supports(@NotNull Pointer<T> var1);

    @Contract(pure=true)
    @NotNull
    public static Pointers empty() {
        return PointersImpl.EMPTY;
    }

    @NotNull
    @Contract(pure=true)
    public static Builder builder() {
        return new PointersImpl.BuilderImpl();
    }

    @Nullable
    @Contract(value="_, null -> _; _, !null -> !null")
    default public <T> T getOrDefault(@NotNull Pointer<T> pointer, @Nullable T defaultValue) {
        return this.get(pointer).orElse(defaultValue);
    }

    @NotNull
    public <T> Optional<T> get(@NotNull Pointer<T> var1);

    default public <T> @UnknownNullability T getOrDefaultFrom(@NotNull Pointer<T> pointer, @NotNull Supplier<? extends T> defaultValue) {
        return this.get(pointer).orElseGet(defaultValue);
    }
}
