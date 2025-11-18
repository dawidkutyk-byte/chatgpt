/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackCallbacks
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackStatus
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.resource;

import java.util.UUID;
import java.util.function.BiConsumer;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackCallbacks;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackStatus;

@FunctionalInterface
public interface ResourcePackCallback {
    @NotNull
    public static ResourcePackCallback noOp() {
        return ResourcePackCallbacks.NO_OP;
    }

    public void packEventReceived(@NotNull UUID var1, @NotNull ResourcePackStatus var2, @NotNull Audience var3);

    @NotNull
    public static ResourcePackCallback onTerminal(@NotNull BiConsumer<UUID, Audience> success, @NotNull BiConsumer<UUID, Audience> failure) {
        return (uuid, status, audience) -> {
            if (status == ResourcePackStatus.SUCCESSFULLY_LOADED) {
                success.accept(uuid, audience);
            } else {
                if (status.intermediate()) return;
                failure.accept(uuid, audience);
            }
        };
    }
}
