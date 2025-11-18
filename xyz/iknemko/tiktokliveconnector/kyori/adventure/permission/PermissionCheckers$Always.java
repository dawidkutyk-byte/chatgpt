/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.permission.PermissionChecker
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.TriState
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.permission;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.permission.PermissionChecker;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.TriState;

private static final class PermissionCheckers.Always
implements PermissionChecker {
    private final TriState value;

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (this.getClass() != other.getClass()) {
            return false;
        }
        PermissionCheckers.Always always = (PermissionCheckers.Always)other;
        return this.value == always.value;
    }

    public String toString() {
        return PermissionChecker.class.getSimpleName() + ".always(" + this.value + ")";
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    @NotNull
    public TriState value(@NotNull String permission) {
        return this.value;
    }

    private PermissionCheckers.Always(TriState value) {
        this.value = value;
    }
}
