/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.MessageType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$PacketFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Chat
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.MessageType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;

/*
 * Exception performing whole class analysis ignored.
 */
static class CraftBukkitFacet.Chat
extends CraftBukkitFacet.PacketFacet<CommandSender>
implements Facet.Chat<CommandSender, Object> {
    public void sendMessage(@NotNull CommandSender viewer, @NotNull Identity source, @NotNull Object message, @NotNull Object type) {
        Object messageType = type == MessageType.CHAT ? CraftBukkitFacet.access$300() : CraftBukkitFacet.access$400();
        try {
            this.sendMessage(viewer, CraftBukkitFacet.access$200().invoke(message, messageType, source.uuid()));
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to invoke PacketPlayOutChat constructor: %s %s", (Object[])new Object[]{message, messageType});
        }
    }

    CraftBukkitFacet.Chat() {
    }

    public boolean isSupported() {
        return super.isSupported() && CraftBukkitFacet.access$200() != null;
    }
}
