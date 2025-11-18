/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointersSupplier$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointersSupplierImpl$BuilderImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer;

import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointersSupplier;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointersSupplierImpl;

public interface PointersSupplier<T> {
    @NotNull
    public static <T> Builder<T> builder() {
        return new PointersSupplierImpl.BuilderImpl();
    }

    @Nullable
    public <P> Function<? super T, P> resolver(@NotNull Pointer<P> var1);

    public <P> boolean supports(@NotNull Pointer<P> var1);

    @NotNull
    public Pointers view(@NotNull T var1);
}
