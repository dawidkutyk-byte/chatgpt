/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.viaversion.viaversion.api.Via
 *  com.viaversion.viaversion.api.connection.UserConnection
 *  org.bukkit.entity.Player
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.connection.UserConnection;
import java.util.function.Function;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

static final class BukkitFacet.ViaHook
implements Function<Player, UserConnection> {
    BukkitFacet.ViaHook() {
    }

    @Override
    public UserConnection apply(@NotNull Player player) {
        return Via.getManager().getConnectionManager().getConnectedClient(player.getUniqueId());
    }
}
