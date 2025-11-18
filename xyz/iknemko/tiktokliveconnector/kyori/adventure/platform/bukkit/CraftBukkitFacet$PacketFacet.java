/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Message
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;

/*
 * Exception performing whole class analysis ignored.
 */
static class CraftBukkitFacet.PacketFacet<V extends CommandSender>
extends CraftBukkitFacet<V>
implements Facet.Message<V, Object> {
    public void sendMessage(@NotNull V player, @Nullable Object packet) {
        this.sendPacket((Player)player, packet);
    }

    @Nullable
    public Object createMessage(@NotNull V viewer, @NotNull Component message) {
        try {
            return MinecraftComponentSerializer.get().serialize(message);
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to serialize net.minecraft.server IChatBaseComponent: %s", (Object[])new Object[]{message});
            return null;
        }
    }

    public void sendPacket(@NotNull Player player, @Nullable Object packet) {
        if (packet == null) {
            return;
        }
        try {
            CraftBukkitFacet.access$100().invoke(CraftBukkitFacet.access$000().invoke(CRAFT_PLAYER_GET_HANDLE.invoke(player)), packet);
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to invoke CraftBukkit sendPacket: %s", (Object[])new Object[]{packet});
        }
    }

    protected CraftBukkitFacet.PacketFacet() {
        super(CLASS_CRAFT_PLAYER);
    }
}
