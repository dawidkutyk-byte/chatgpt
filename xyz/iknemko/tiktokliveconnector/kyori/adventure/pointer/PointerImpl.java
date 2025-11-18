/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer;

import net.kyori.examination.Examinable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer;

final class PointerImpl<T>
implements Pointer<T> {
    private final Class<T> type;
    private final Key key;

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    public int hashCode() {
        int result = this.type.hashCode();
        result = 31 * result + this.key.hashCode();
        return result;
    }

    @NotNull
    public Key key() {
        return this.key;
    }

    PointerImpl(Class<T> type, Key key) {
        this.type = type;
        this.key = key;
    }

    @NotNull
    public Class<T> type() {
        return this.type;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (this.getClass() != other.getClass()) {
            return false;
        }
        PointerImpl that = (PointerImpl)other;
        return this.type.equals(that.type) && this.key.equals(that.key);
    }
}
