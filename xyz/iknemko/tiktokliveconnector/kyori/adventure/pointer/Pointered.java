/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers
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

public interface Pointered {
    @NotNull
    default public Pointers pointers() {
        return Pointers.empty();
    }

    @NotNull
    default public <T> Optional<T> get(@NotNull Pointer<T> pointer) {
        return this.pointers().get(pointer);
    }

    default public <T> @UnknownNullability T getOrDefaultFrom(@NotNull Pointer<T> pointer, @NotNull Supplier<? extends T> defaultValue) {
        return (T)this.pointers().getOrDefaultFrom(pointer, defaultValue);
    }

    @Nullable
    @Contract(value="_, null -> _; _, !null -> !null")
    default public <T> T getOrDefault(@NotNull Pointer<T> pointer, @Nullable T defaultValue) {
        return (T)this.pointers().getOrDefault(pointer, defaultValue);
    }
}
