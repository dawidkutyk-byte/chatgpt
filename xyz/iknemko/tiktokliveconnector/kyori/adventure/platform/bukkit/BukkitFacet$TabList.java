/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet$Message
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$TabList
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;

static final class BukkitFacet.TabList
extends BukkitFacet.Message<Player>
implements Facet.TabList<Player, String> {
    private static final boolean SUPPORTED = MinecraftReflection.hasMethod(Player.class, (String)"setPlayerListHeader", (Class[])new Class[]{String.class});

    public void send(Player viewer, @Nullable String header, @Nullable String footer) {
        if (header != null && footer != null) {
            viewer.setPlayerListHeaderFooter(header, footer);
        } else if (header != null) {
            viewer.setPlayerListHeader(header);
        } else {
            if (footer == null) return;
            viewer.setPlayerListFooter(footer);
        }
    }

    BukkitFacet.TabList() {
        super(Player.class);
    }

    public boolean isSupported() {
        return SUPPORTED && super.isSupported();
    }
}
