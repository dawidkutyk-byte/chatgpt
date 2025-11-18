/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$PacketFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$ActionBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;

/*
 * Exception performing whole class analysis ignored.
 */
static class CraftBukkitFacet.ActionBar
extends CraftBukkitFacet.PacketFacet<Player>
implements Facet.ActionBar<Player, Object> {
    public boolean isSupported() {
        return super.isSupported() && CraftBukkitFacet.access$600() != null;
    }

    @Nullable
    public Object createMessage(@NotNull Player viewer, @NotNull Component message) {
        try {
            return CraftBukkitFacet.access$700().invoke(CraftBukkitFacet.access$600(), super.createMessage((CommandSender)viewer, message));
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to invoke PacketPlayOutTitle constructor: %s", (Object[])new Object[]{message});
            return null;
        }
    }

    CraftBukkitFacet.ActionBar() {
    }
}
