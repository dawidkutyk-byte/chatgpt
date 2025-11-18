/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointersSupplier
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointersSupplier$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointersSupplierImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointersSupplier;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointersSupplierImpl;

static final class PointersSupplierImpl.BuilderImpl<T>
implements PointersSupplier.Builder<T> {
    private final Map<Pointer<?>, Function<T, ?>> resolvers = new HashMap();
    @Nullable
    private PointersSupplier<? super T> parent = null;

    @NotNull
    public PointersSupplier<T> build() {
        return new PointersSupplierImpl(this);
    }

    PointersSupplierImpl.BuilderImpl() {
    }

    @NotNull
    public <P> PointersSupplier.Builder<T> resolving(@NotNull Pointer<P> pointer, @NotNull Function<T, P> resolver) {
        this.resolvers.put(pointer, resolver);
        return this;
    }

    static /* synthetic */ PointersSupplier access$000(PointersSupplierImpl.BuilderImpl x0) {
        return x0.parent;
    }

    static /* synthetic */ Map access$100(PointersSupplierImpl.BuilderImpl x0) {
        return x0.resolvers;
    }

    @NotNull
    public PointersSupplier.Builder<T> parent(@Nullable PointersSupplier<? super T> parent) {
        this.parent = parent;
        return this;
    }
}
