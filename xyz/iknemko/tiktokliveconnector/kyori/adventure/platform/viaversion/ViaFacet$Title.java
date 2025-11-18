/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.viaversion.viaversion.api.connection.UserConnection
 *  com.viaversion.viaversion.api.protocol.packet.PacketWrapper
 *  com.viaversion.viaversion.api.type.Type
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$TitlePacket
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion.ViaFacet$ProtocolBased
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion;

import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.protocol.packet.PacketWrapper;
import com.viaversion.viaversion.api.type.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion.ViaFacet;

public static class ViaFacet.Title<V>
extends ViaFacet.ProtocolBased<V>
implements Facet.TitlePacket<V, String, List<Consumer<PacketWrapper>>, Consumer<V>> {
    protected ViaFacet.Title(@NotNull String fromProtocol, @NotNull String toProtocol, int minProtocol, @NotNull Class<? extends V> viewerClass, @NotNull Function<V, UserConnection> connectionFunction) {
        super(fromProtocol, toProtocol, minProtocol, "TITLE", viewerClass, connectionFunction);
    }

    public void contributeSubtitle(@NotNull List<Consumer<PacketWrapper>> coll, @NotNull String subtitle) {
        coll.add(packet -> {
            packet.write((Type)Type.VAR_INT, (Object)1);
            packet.write(Type.COMPONENT, (Object)this.parse(subtitle));
        });
    }

    public void contributeTimes(@NotNull List<Consumer<PacketWrapper>> coll, int inTicks, int stayTicks, int outTicks) {
        coll.add(packet -> {
            packet.write((Type)Type.VAR_INT, (Object)3);
            packet.write((Type)Type.INT, (Object)inTicks);
            packet.write((Type)Type.INT, (Object)stayTicks);
            packet.write((Type)Type.INT, (Object)outTicks);
        });
    }

    public void contributeTitle(@NotNull List<Consumer<PacketWrapper>> coll, @NotNull String title) {
        coll.add(packet -> {
            packet.write((Type)Type.VAR_INT, (Object)0);
            packet.write(Type.COMPONENT, (Object)this.parse(title));
        });
    }

    public void showTitle(@NotNull V viewer, @NotNull Consumer<V> title) {
        title.accept(viewer);
    }

    public void resetTitle(@NotNull V viewer) {
        PacketWrapper packet = this.createPacket(viewer);
        packet.write((Type)Type.VAR_INT, (Object)5);
        this.sendPacket(packet);
    }

    public void clearTitle(@NotNull V viewer) {
        PacketWrapper packet = this.createPacket(viewer);
        packet.write((Type)Type.VAR_INT, (Object)4);
        this.sendPacket(packet);
    }

    public ViaFacet.Title(@NotNull Class<? extends V> viewerClass, @NotNull Function<V, UserConnection> connectionFunction) {
        this("1_16", "1_15_2", 713, viewerClass, connectionFunction);
    }

    @NotNull
    public List<Consumer<PacketWrapper>> createTitleCollection() {
        return new ArrayList<Consumer<PacketWrapper>>();
    }

    @Nullable
    public Consumer<V> completeTitle(@NotNull List<Consumer<PacketWrapper>> coll) {
        return v -> {
            int i = 0;
            int length = coll.size();
            while (i < length) {
                PacketWrapper pkt = this.createPacket(v);
                ((Consumer)coll.get(i)).accept(pkt);
                this.sendPacket(pkt);
                ++i;
            }
        };
    }
}
