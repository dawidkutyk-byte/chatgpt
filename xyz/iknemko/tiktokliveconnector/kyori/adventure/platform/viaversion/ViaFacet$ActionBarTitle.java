/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.viaversion.viaversion.api.connection.UserConnection
 *  com.viaversion.viaversion.api.protocol.packet.PacketWrapper
 *  com.viaversion.viaversion.api.type.Type
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$ActionBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion.ViaFacet$ProtocolBased
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion;

import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.protocol.packet.PacketWrapper;
import com.viaversion.viaversion.api.type.Type;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion.ViaFacet;

public static class ViaFacet.ActionBarTitle<V>
extends ViaFacet.ProtocolBased<V>
implements Facet.ActionBar<V, String> {
    public void sendMessage(@NotNull V viewer, @NotNull String message) {
        PacketWrapper packet = this.createPacket(viewer);
        packet.write((Type)Type.VAR_INT, (Object)2);
        packet.write(Type.COMPONENT, (Object)this.parse(message));
        this.sendPacket(packet);
    }

    public ViaFacet.ActionBarTitle(@NotNull Class<? extends V> viewerClass, @NotNull Function<V, UserConnection> connectionFunction) {
        super("1_11", "1_10", 310, "TITLE", viewerClass, connectionFunction);
    }
}
