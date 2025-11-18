/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  org.bukkit.util.Vector
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Position
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;

static class BukkitFacet.Position
extends BukkitFacet<Player>
implements Facet.Position<Player, Vector> {
    @NotNull
    public Vector createPosition(@NotNull Player viewer) {
        return viewer.getLocation().toVector();
    }

    protected BukkitFacet.Position() {
        super(Player.class);
    }

    @NotNull
    public Vector createPosition(double x, double y, double z) {
        return new Vector(x, y, z);
    }
}
