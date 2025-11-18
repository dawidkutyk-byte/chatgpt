/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.viaversion.viaversion.api.connection.UserConnection
 *  com.viaversion.viaversion.api.protocol.packet.PacketWrapper
 *  com.viaversion.viaversion.api.type.Type
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$TabList
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion.ViaFacet$ProtocolBased
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion;

import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.protocol.packet.PacketWrapper;
import com.viaversion.viaversion.api.type.Type;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion.ViaFacet;

public static final class ViaFacet.TabList<V>
extends ViaFacet.ProtocolBased<V>
implements Facet.TabList<V, String> {
    public ViaFacet.TabList(@NotNull Class<? extends V> viewerClass, @NotNull Function<V, UserConnection> userConnection) {
        super("1_16", "1_15_2", 713, "TAB_LIST", viewerClass, userConnection);
    }

    public void send(V viewer, @Nullable String header, @Nullable String footer) {
        PacketWrapper packet = this.createPacket(viewer);
        packet.write(Type.COMPONENT, (Object)this.parse(header));
        packet.write(Type.COMPONENT, (Object)this.parse(footer));
        this.sendPacket(packet);
    }
}
