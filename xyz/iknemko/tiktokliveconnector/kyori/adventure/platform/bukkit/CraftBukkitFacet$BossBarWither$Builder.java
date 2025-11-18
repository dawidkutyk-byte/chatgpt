/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$BossBarWither
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$BossBar$Builder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.util.Collection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;

public static class CraftBukkitFacet.BossBarWither.Builder
extends CraftBukkitFacet<Player>
implements Facet.BossBar.Builder<Player, CraftBukkitFacet.BossBarWither> {
    @NotNull
    public CraftBukkitFacet.BossBarWither createBossBar(@NotNull Collection<Player> viewers) {
        return new CraftBukkitFacet.BossBarWither(viewers, null);
    }

    protected CraftBukkitFacet.BossBarWither.Builder() {
        super(Player.class);
    }
}
