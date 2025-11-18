/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointersSupplier
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointersSupplierImpl$BuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointersSupplierImpl$ForwardingPointers
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointersSupplier;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointersSupplierImpl;

/*
 * Exception performing whole class analysis ignored.
 */
final class PointersSupplierImpl<T>
implements PointersSupplier<T> {
    @Nullable
    private final PointersSupplier<? super T> parent;
    private final Map<Pointer<?>, Function<T, ?>> resolvers;

    static /* synthetic */ PointersSupplier access$300(PointersSupplierImpl x0) {
        return x0.parent;
    }

    PointersSupplierImpl(@NotNull BuilderImpl<T> builder) {
        this.parent = BuilderImpl.access$000(builder);
        this.resolvers = new HashMap(BuilderImpl.access$100(builder));
    }

    @Nullable
    public <P> Function<? super T, P> resolver(@NotNull Pointer<P> pointer) {
        Function<T, ?> resolver = this.resolvers.get(Objects.requireNonNull(pointer, "pointer"));
        if (resolver != null) {
            return resolver;
        }
        if (this.parent != null) return this.parent.resolver(pointer);
        return null;
    }

    @NotNull
    public Pointers view(@NotNull T instance) {
        return new ForwardingPointers(instance, this);
    }

    static /* synthetic */ Map access$200(PointersSupplierImpl x0) {
        return x0.resolvers;
    }

    public <P> boolean supports(@NotNull Pointer<P> pointer) {
        if (this.resolvers.containsKey(Objects.requireNonNull(pointer, "pointer"))) {
            return true;
        }
        if (this.parent != null) return this.parent.supports(pointer);
        return false;
    }
}
