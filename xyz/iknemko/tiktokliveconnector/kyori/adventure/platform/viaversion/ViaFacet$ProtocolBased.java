/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.viaversion.viaversion.api.connection.UserConnection
 *  com.viaversion.viaversion.api.protocol.Protocol
 *  com.viaversion.viaversion.api.protocol.packet.ClientboundPacketType
 *  com.viaversion.viaversion.api.protocol.packet.PacketWrapper
 *  com.viaversion.viaversion.libs.gson.JsonElement
 *  com.viaversion.viaversion.libs.gson.JsonParser
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion.ViaFacet
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion;

import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.protocol.Protocol;
import com.viaversion.viaversion.api.protocol.packet.ClientboundPacketType;
import com.viaversion.viaversion.api.protocol.packet.PacketWrapper;
import com.viaversion.viaversion.libs.gson.JsonElement;
import com.viaversion.viaversion.libs.gson.JsonParser;
import java.text.MessageFormat;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion.ViaFacet;

public static class ViaFacet.ProtocolBased<V>
extends ViaFacet<V> {
    private final Class<? extends ClientboundPacketType> packetClass;
    private final int packetId;
    private final Class<? extends Protocol<?, ?, ?, ?>> protocolClass;

    public void sendPacket(@NotNull PacketWrapper packet) {
        if (packet.user() == null) {
            return;
        }
        try {
            packet.scheduleSend(this.protocolClass);
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to send ViaVersion packet: %s %s", (Object[])new Object[]{packet.user(), packet});
        }
    }

    protected ViaFacet.ProtocolBased(@NotNull String fromProtocol, @NotNull String toProtocol, int minProtocol, @NotNull String packetName, @NotNull Class<? extends V> viewerClass, @NotNull Function<V, UserConnection> connectionFunction) {
        super(viewerClass, connectionFunction, minProtocol);
        String protocolClassName = MessageFormat.format("{0}.protocols.protocol{1}to{2}.Protocol{1}To{2}", "com.viaversion.viaversion", fromProtocol, toProtocol);
        String packetClassName = MessageFormat.format("{0}.protocols.protocol{1}to{2}.ClientboundPackets{1}", "com.viaversion.viaversion", fromProtocol, toProtocol);
        Class<?> protocolClass = null;
        Class<?> packetClass = null;
        int packetId = -1;
        try {
            protocolClass = Class.forName(protocolClassName);
            packetClass = Class.forName(packetClassName);
            for (ClientboundPacketType type : (ClientboundPacketType[])packetClass.getEnumConstants()) {
                if (!type.getName().equals(packetName)) continue;
                packetId = type.getId();
                break;
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        this.protocolClass = protocolClass;
        this.packetClass = packetClass;
        this.packetId = packetId;
    }

    @NotNull
    public JsonElement parse(@NotNull String message) {
        return JsonParser.parseString((String)message);
    }

    public boolean isSupported() {
        return super.isSupported() && this.protocolClass != null && this.packetClass != null && this.packetId >= 0;
    }

    public PacketWrapper createPacket(@NotNull V viewer) {
        return PacketWrapper.create((int)this.packetId, null, (UserConnection)this.findConnection(viewer));
    }
}
