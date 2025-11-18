/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$AbstractBook
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.lang.invoke.MethodHandle;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;

static final class CraftBukkitFacet.BookPost1_13
extends CraftBukkitFacet.AbstractBook {
    private static final Class<?> PACKET_OPEN_BOOK;
    private static final MethodHandle NEW_PACKET_OPEN_BOOK;
    private static final Object HAND_MAIN;
    private static final Class<?> CLASS_ENUM_HAND;

    CraftBukkitFacet.BookPost1_13() {
    }

    static {
        CLASS_ENUM_HAND = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findNmsClassName((String)"EnumHand"), MinecraftReflection.findMcClassName((String)"world.EnumHand"), MinecraftReflection.findMcClassName((String)"world.InteractionHand")});
        HAND_MAIN = MinecraftReflection.findEnum(CLASS_ENUM_HAND, (String)"MAIN_HAND", (int)0);
        PACKET_OPEN_BOOK = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findNmsClassName((String)"PacketPlayOutOpenBook"), MinecraftReflection.findMcClassName((String)"network.protocol.game.PacketPlayOutOpenBook"), MinecraftReflection.findMcClassName((String)"network.protocol.game.ClientboundOpenBookPacket")});
        NEW_PACKET_OPEN_BOOK = MinecraftReflection.findConstructor(PACKET_OPEN_BOOK, (Class[])new Class[]{CLASS_ENUM_HAND});
    }

    protected void sendOpenPacket(@NotNull Player viewer) throws Throwable {
        this.sendMessage((CommandSender)viewer, NEW_PACKET_OPEN_BOOK.invoke(HAND_MAIN));
    }

    public boolean isSupported() {
        return super.isSupported() && HAND_MAIN != null && NEW_PACKET_OPEN_BOOK != null;
    }
}
