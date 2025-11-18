/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$PacketFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$ActionBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.lang.invoke.MethodHandle;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;

static class CraftBukkitFacet.ActionBar_1_17
extends CraftBukkitFacet.PacketFacet<Player>
implements Facet.ActionBar<Player, Object> {
    @Nullable
    private static final Class<?> CLASS_SET_ACTION_BAR_TEXT_PACKET = MinecraftReflection.findMcClass((String[])new String[]{"network.protocol.game.ClientboundSetActionBarTextPacket"});
    @Nullable
    private static final MethodHandle CONSTRUCTOR_ACTION_BAR = MinecraftReflection.findConstructor(CLASS_SET_ACTION_BAR_TEXT_PACKET, (Class[])new Class[]{CraftBukkitFacet.access$500()});

    public boolean isSupported() {
        return super.isSupported() && CONSTRUCTOR_ACTION_BAR != null;
    }

    CraftBukkitFacet.ActionBar_1_17() {
    }

    @Nullable
    public Object createMessage(@NotNull Player viewer, @NotNull Component message) {
        try {
            return CONSTRUCTOR_ACTION_BAR.invoke(super.createMessage((CommandSender)viewer, message));
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to invoke PacketPlayOutTitle constructor: %s", (Object[])new Object[]{message});
            return null;
        }
    }
}
