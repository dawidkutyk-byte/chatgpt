/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.viaversion.viaversion.api.connection.UserConnection
 *  com.viaversion.viaversion.api.protocol.packet.PacketWrapper
 *  com.viaversion.viaversion.api.type.Type
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.MessageType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$ChatPacket
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion.ViaFacet$ProtocolBased
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion;

import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.protocol.packet.PacketWrapper;
import com.viaversion.viaversion.api.type.Type;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.MessageType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion.ViaFacet;

public static class ViaFacet.Chat<V>
extends ViaFacet.ProtocolBased<V>
implements Facet.ChatPacket<V, String> {
    public void sendMessage(@NotNull V viewer, @NotNull Identity source, @NotNull String message, @NotNull Object type) {
        PacketWrapper packet = this.createPacket(viewer);
        packet.write(Type.COMPONENT, (Object)this.parse(message));
        packet.write((Type)Type.BYTE, (Object)this.createMessageType(type instanceof MessageType ? (MessageType)type : MessageType.SYSTEM));
        packet.write(Type.UUID, (Object)source.uuid());
        this.sendPacket(packet);
    }

    public ViaFacet.Chat(@NotNull Class<? extends V> viewerClass, @NotNull Function<V, UserConnection> connectionFunction) {
        super("1_16", "1_15_2", 713, "CHAT_MESSAGE", viewerClass, connectionFunction);
    }
}
