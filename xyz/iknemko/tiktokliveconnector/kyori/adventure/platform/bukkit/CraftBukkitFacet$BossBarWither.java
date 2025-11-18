/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Wither
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$FakeEntity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$BossBarEntity
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.util.Collection;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;

static final class CraftBukkitFacet.BossBarWither
extends CraftBukkitFacet.FakeEntity<Wither>
implements Facet.BossBarEntity<Player, Location> {
    private volatile boolean initialized = false;

    @NotNull
    public Location createPosition(@NotNull Player viewer) {
        Location position = super.createPosition(viewer);
        position.setPitch(position.getPitch() - 30.0f);
        position.setYaw(position.getYaw() + 0.0f);
        position.add(position.getDirection().multiply(40));
        return position;
    }

    private CraftBukkitFacet.BossBarWither(@NotNull Collection<Player> viewers) {
        super(Wither.class, viewers.iterator().next().getWorld().getSpawnLocation());
        this.invisible(true);
        this.metadata(20, 890);
    }

    public void bossBarInitialized(@NotNull BossBar bar) {
        super.bossBarInitialized(bar);
        this.initialized = true;
    }

    public boolean isEmpty() {
        return !this.initialized || this.viewers.isEmpty();
    }
}
