/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointersSupplier
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointersSupplierImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointersSupplier;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointersSupplierImpl;

/*
 * Exception performing whole class analysis ignored.
 */
static final class PointersSupplierImpl.ForwardingPointers<U>
implements Pointers {
    private final PointersSupplierImpl<U> supplier;
    private final U instance;

    PointersSupplierImpl.ForwardingPointers(@NotNull U instance, @NotNull PointersSupplierImpl<U> supplier) {
        this.instance = instance;
        this.supplier = supplier;
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Pointers.Builder toBuilder() {
        Pointers.Builder builder = PointersSupplierImpl.access$300(this.supplier) == null ? Pointers.builder() : (Pointers.Builder)PointersSupplierImpl.access$300(this.supplier).view(this.instance).toBuilder();
        Iterator iterator = PointersSupplierImpl.access$200(this.supplier).entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            builder.withDynamic((Pointer)entry.getKey(), () -> ((Function)entry.getValue()).apply(this.instance));
        }
        return builder;
    }

    @NotNull
    public <T> Optional<T> get(@NotNull Pointer<T> pointer) {
        PointersSupplier parent;
        Function resolver = (Function)PointersSupplierImpl.access$200(this.supplier).get(Objects.requireNonNull(pointer, "pointer"));
        if (resolver == null && (parent = PointersSupplierImpl.access$300(this.supplier)) != null) {
            resolver = parent.resolver(pointer);
        }
        if (resolver != null) return Optional.ofNullable(resolver.apply(this.instance));
        return Optional.empty();
    }

    public <T> boolean supports(@NotNull Pointer<T> pointer) {
        return this.supplier.supports(pointer);
    }
}
