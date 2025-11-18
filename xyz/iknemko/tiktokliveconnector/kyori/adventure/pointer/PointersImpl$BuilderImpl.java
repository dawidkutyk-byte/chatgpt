/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointersImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointersImpl;

/*
 * Exception performing whole class analysis ignored.
 */
static final class PointersImpl.BuilderImpl
implements Pointers.Builder {
    private final Map<Pointer<?>, Supplier<?>> pointers;

    static /* synthetic */ Map access$000(PointersImpl.BuilderImpl x0) {
        return x0.pointers;
    }

    @NotNull
    public Pointers build() {
        return new PointersImpl(this);
    }

    PointersImpl.BuilderImpl(@NotNull PointersImpl pointers) {
        this.pointers = new HashMap(PointersImpl.access$100((PointersImpl)pointers));
    }

    PointersImpl.BuilderImpl() {
        this.pointers = new HashMap();
    }

    @NotNull
    public <T> Pointers.Builder withDynamic(@NotNull Pointer<T> pointer, @NotNull Supplier<@Nullable T> value) {
        this.pointers.put(Objects.requireNonNull(pointer, "pointer"), Objects.requireNonNull(value, "value"));
        return this;
    }
}
