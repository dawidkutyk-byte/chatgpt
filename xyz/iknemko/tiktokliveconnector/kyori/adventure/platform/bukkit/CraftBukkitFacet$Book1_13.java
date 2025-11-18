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
import java.lang.reflect.InvocationTargetException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;

static final class CraftBukkitFacet.Book1_13
extends CraftBukkitFacet.AbstractBook {
    private static final Class<?> CLASS_RESOURCE_LOCATION;
    private static final MethodHandle NEW_PACKET_CUSTOM_PAYLOAD;
    private static final Object PACKET_TYPE_BOOK_OPEN;
    private static final Class<?> CLASS_PACKET_CUSTOM_PAYLOAD;
    private static final Class<?> CLASS_BYTE_BUF;
    private static final Class<?> CLASS_FRIENDLY_BYTE_BUF;
    private static final MethodHandle NEW_FRIENDLY_BYTE_BUF;

    CraftBukkitFacet.Book1_13() {
    }

    public boolean isSupported() {
        return super.isSupported() && CLASS_BYTE_BUF != null && NEW_PACKET_CUSTOM_PAYLOAD != null && PACKET_TYPE_BOOK_OPEN != null;
    }

    static {
        CLASS_BYTE_BUF = MinecraftReflection.findClass((String[])new String[]{"io.netty.buffer.ByteBuf"});
        CLASS_PACKET_CUSTOM_PAYLOAD = MinecraftReflection.findNmsClass((String)"PacketPlayOutCustomPayload");
        CLASS_FRIENDLY_BYTE_BUF = MinecraftReflection.findNmsClass((String)"PacketDataSerializer");
        CLASS_RESOURCE_LOCATION = MinecraftReflection.findNmsClass((String)"MinecraftKey");
        NEW_PACKET_CUSTOM_PAYLOAD = MinecraftReflection.findConstructor(CLASS_PACKET_CUSTOM_PAYLOAD, (Class[])new Class[]{CLASS_RESOURCE_LOCATION, CLASS_FRIENDLY_BYTE_BUF});
        NEW_FRIENDLY_BYTE_BUF = MinecraftReflection.findConstructor(CLASS_FRIENDLY_BYTE_BUF, (Class[])new Class[]{CLASS_BYTE_BUF});
        Object packetType = null;
        if (CLASS_RESOURCE_LOCATION != null) {
            try {
                packetType = CLASS_RESOURCE_LOCATION.getConstructor(String.class).newInstance("minecraft:book_open");
            }
            catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException reflectiveOperationException) {
                // empty catch block
            }
        }
        PACKET_TYPE_BOOK_OPEN = packetType;
    }

    protected void sendOpenPacket(@NotNull Player viewer) throws Throwable {
        ByteBuf data = Unpooled.buffer();
        data.writeByte(0);
        Object packetByteBuf = NEW_FRIENDLY_BYTE_BUF.invoke(data);
        this.sendMessage((CommandSender)viewer, NEW_PACKET_CUSTOM_PAYLOAD.invoke(PACKET_TYPE_BOOK_OPEN, packetByteBuf));
    }
}
