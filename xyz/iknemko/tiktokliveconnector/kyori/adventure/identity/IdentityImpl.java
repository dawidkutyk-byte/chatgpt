/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.identity;

import java.util.UUID;
import net.kyori.examination.Examinable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;

final class IdentityImpl
implements Examinable,
Identity {
    private final UUID uuid;

    public int hashCode() {
        return this.uuid.hashCode();
    }

    IdentityImpl(UUID uuid) {
        this.uuid = uuid;
    }

    @NotNull
    public UUID uuid() {
        return this.uuid;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Identity)) {
            return false;
        }
        Identity that = (Identity)other;
        return this.uuid.equals(that.uuid());
    }

    public String toString() {
        return Internals.toString((Examinable)this);
    }
}
