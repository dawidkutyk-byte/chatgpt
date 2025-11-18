/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointersImpl$BuilderImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer;

import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointersImpl;

class PointersImpl.1
implements Pointers {
    @NotNull
    public <T> Optional<T> get(@NotNull Pointer<T> pointer) {
        return Optional.empty();
    }

    PointersImpl.1() {
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Pointers.Builder toBuilder() {
        return new PointersImpl.BuilderImpl();
    }

    public String toString() {
        return "EmptyPointers";
    }

    public <T> boolean supports(@NotNull Pointer<T> pointer) {
        return false;
    }
}
