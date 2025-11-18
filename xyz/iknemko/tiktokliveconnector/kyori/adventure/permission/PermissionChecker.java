/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.permission.PermissionCheckers
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.TriState
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.permission;

import java.util.Objects;
import java.util.function.Predicate;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.permission.PermissionCheckers;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.TriState;

public interface PermissionChecker
extends Predicate<String> {
    public static final Pointer<PermissionChecker> POINTER = Pointer.pointer(PermissionChecker.class, (Key)Key.key((String)"adventure", (String)"permission"));

    @NotNull
    public TriState value(@NotNull String var1);

    @NotNull
    public static PermissionChecker always(@NotNull TriState state) {
        Objects.requireNonNull(state);
        if (state == TriState.TRUE) {
            return PermissionCheckers.TRUE;
        }
        if (state != TriState.FALSE) return PermissionCheckers.NOT_SET;
        return PermissionCheckers.FALSE;
    }

    @Override
    default public boolean test(@NotNull String permission) {
        return this.value(permission) == TriState.TRUE;
    }
}
