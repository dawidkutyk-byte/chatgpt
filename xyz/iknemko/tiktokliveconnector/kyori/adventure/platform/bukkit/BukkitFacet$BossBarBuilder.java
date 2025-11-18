/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet$BossBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$BossBar$Builder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.util.Collection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;

static class BukkitFacet.BossBarBuilder
extends BukkitFacet<Player>
implements Facet.BossBar.Builder<Player, BukkitFacet.BossBar> {
    private static final boolean SUPPORTED = MinecraftReflection.hasClass((String[])new String[]{"org.bukkit.boss.BossBar"});

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull BukkitFacet.BossBar createBossBar(@NotNull Collection<Player> viewers) {
        return new BukkitFacet.BossBar(viewers);
    }

    public boolean isSupported() {
        return super.isSupported() && SUPPORTED;
    }

    protected BukkitFacet.BossBarBuilder() {
        super(Player.class);
    }
}
