/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointersImpl$BuilderImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointersImpl;

/*
 * Exception performing whole class analysis ignored.
 */
final class PointersImpl
implements Pointers {
    static final Pointers EMPTY = new /* Unavailable Anonymous Inner Class!! */;
    private final Map<Pointer<?>, Supplier<?>> pointers;

    static /* synthetic */ Map access$100(PointersImpl x0) {
        return x0.pointers;
    }

    public <T> boolean supports(@NotNull Pointer<T> pointer) {
        Objects.requireNonNull(pointer, "pointer");
        return this.pointers.containsKey(pointer);
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Pointers.Builder toBuilder() {
        return new BuilderImpl(this);
    }

    PointersImpl(@NotNull BuilderImpl builder) {
        this.pointers = new HashMap(BuilderImpl.access$000((BuilderImpl)builder));
    }

    @NotNull
    public <T> Optional<T> get(@NotNull Pointer<T> pointer) {
        Objects.requireNonNull(pointer, "pointer");
        Supplier<?> supplier = this.pointers.get(pointer);
        if (supplier != null) return Optional.ofNullable(supplier.get());
        return Optional.empty();
    }
}
