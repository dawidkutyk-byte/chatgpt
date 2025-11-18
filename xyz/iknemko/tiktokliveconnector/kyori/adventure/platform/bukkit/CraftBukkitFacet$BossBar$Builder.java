/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$BossBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$BossBar$Builder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.util.Collection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;

/*
 * Exception performing whole class analysis ignored.
 */
public static class CraftBukkitFacet.BossBar.Builder
extends CraftBukkitFacet<Player>
implements Facet.BossBar.Builder<Player, CraftBukkitFacet.BossBar> {
    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull CraftBukkitFacet.BossBar createBossBar(@NotNull Collection<Player> viewers) {
        return new CraftBukkitFacet.BossBar(viewers, null);
    }

    protected CraftBukkitFacet.BossBar.Builder() {
        super(Player.class);
    }

    public boolean isSupported() {
        return super.isSupported() && CraftBukkitFacet.BossBar.access$1800() != null && CraftBukkitFacet.BossBar.access$1900() != null && CraftBukkitFacet.BossBar.access$2000() != null && CraftBukkitFacet.BossBar.access$2100() != null;
    }
}
