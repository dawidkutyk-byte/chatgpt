/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$AbstractBook
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.lang.invoke.MethodHandle;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;

static final class CraftBukkitFacet.BookPre1_13
extends CraftBukkitFacet.AbstractBook {
    private static final MethodHandle NEW_PACKET_CUSTOM_PAYLOAD;
    private static final Class<?> CLASS_PACKET_DATA_SERIALIZER;
    private static final String PACKET_TYPE_BOOK_OPEN = "MC|BOpen";
    private static final Class<?> CLASS_PACKET_CUSTOM_PAYLOAD;
    private static final Class<?> CLASS_BYTE_BUF;
    private static final MethodHandle NEW_PACKET_BYTE_BUF;

    public boolean isSupported() {
        return super.isSupported() && CLASS_BYTE_BUF != null && CLASS_PACKET_CUSTOM_PAYLOAD != null && NEW_PACKET_CUSTOM_PAYLOAD != null;
    }

    static {
        CLASS_BYTE_BUF = MinecraftReflection.findClass((String[])new String[]{"io.netty.buffer.ByteBuf"});
        CLASS_PACKET_CUSTOM_PAYLOAD = MinecraftReflection.findNmsClass((String)"PacketPlayOutCustomPayload");
        CLASS_PACKET_DATA_SERIALIZER = MinecraftReflection.findNmsClass((String)"PacketDataSerializer");
        NEW_PACKET_CUSTOM_PAYLOAD = MinecraftReflection.findConstructor(CLASS_PACKET_CUSTOM_PAYLOAD, (Class[])new Class[]{String.class, CLASS_PACKET_DATA_SERIALIZER});
        NEW_PACKET_BYTE_BUF = MinecraftReflection.findConstructor(CLASS_PACKET_DATA_SERIALIZER, (Class[])new Class[]{CLASS_BYTE_BUF});
    }

    protected void sendOpenPacket(@NotNull Player viewer) throws Throwable {
        ByteBuf data = Unpooled.buffer();
        data.writeByte(0);
        Object packetByteBuf = NEW_PACKET_BYTE_BUF.invoke(data);
        this.sendMessage((CommandSender)viewer, NEW_PACKET_CUSTOM_PAYLOAD.invoke(PACKET_TYPE_BOOK_OPEN, packetByteBuf));
    }

    CraftBukkitFacet.BookPre1_13() {
    }
}
