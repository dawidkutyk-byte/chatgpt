/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.identity;

import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity;

final class NilIdentity
implements Identity {
    static final Identity INSTANCE;
    static final UUID NIL_UUID;

    public String toString() {
        return "Identity.nil()";
    }

    public int hashCode() {
        return 0;
    }

    NilIdentity() {
    }

    @NotNull
    public UUID uuid() {
        return NIL_UUID;
    }

    public boolean equals(@Nullable Object that) {
        return this == that;
    }

    static {
        NIL_UUID = new UUID(0L, 0L);
        INSTANCE = new NilIdentity();
    }
}
