/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.viaversion.viaversion.api.connection.UserConnection
 *  com.viaversion.viaversion.api.protocol.packet.PacketWrapper
 *  com.viaversion.viaversion.api.type.Type
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar$Flag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$BossBarPacket
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion.ViaFacet$ProtocolBased
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion;

import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.protocol.packet.PacketWrapper;
import com.viaversion.viaversion.api.type.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion.ViaFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;

public static final class ViaFacet.BossBar<V>
extends ViaFacet.ProtocolBased<V>
implements Facet.BossBarPacket<V> {
    private String title;
    private UUID id;
    private int overlay;
    private int color;
    private float health;
    private byte flags;
    private final Set<V> viewers;

    public void close() {
        this.broadcastPacket(1);
        this.viewers.clear();
    }

    public void sendPacket(@NotNull V viewer, int action) {
        PacketWrapper packet = this.createPacket(viewer);
        packet.write(Type.UUID, (Object)this.id);
        packet.write((Type)Type.VAR_INT, (Object)action);
        if (action == 0 || action == 3) {
            packet.write(Type.COMPONENT, (Object)this.parse(this.title));
        }
        if (action == 0 || action == 2) {
            packet.write((Type)Type.FLOAT, (Object)Float.valueOf(this.health));
        }
        if (action == 0 || action == 4) {
            packet.write((Type)Type.VAR_INT, (Object)this.color);
            packet.write((Type)Type.VAR_INT, (Object)this.overlay);
        }
        if (action == 0 || action == 5) {
            packet.write((Type)Type.BYTE, (Object)this.flags);
        }
        this.sendPacket(packet);
    }

    public void bossBarNameChanged(@NotNull BossBar bar, @NotNull Component oldName, @NotNull Component newName) {
        if (this.viewers.isEmpty()) return;
        this.title = this.createMessage(this.viewers.iterator().next(), newName);
        this.broadcastPacket(3);
    }

    public boolean isEmpty() {
        return this.id == null || this.viewers.isEmpty();
    }

    public void broadcastPacket(int action) {
        if (this.isEmpty()) {
            return;
        }
        Iterator<V> iterator = this.viewers.iterator();
        while (iterator.hasNext()) {
            V viewer = iterator.next();
            this.sendPacket(viewer, action);
        }
    }

    public void removeViewer(@NotNull V viewer) {
        if (!this.viewers.remove(viewer)) return;
        this.sendPacket(viewer, 1);
    }

    public void addViewer(@NotNull V viewer) {
        if (!this.viewers.add(viewer)) return;
        this.sendPacket(viewer, 0);
    }

    public void bossBarInitialized(@NotNull BossBar bar) {
        super.bossBarInitialized(bar);
        this.id = UUID.randomUUID();
        this.broadcastPacket(0);
    }

    public void bossBarOverlayChanged(@NotNull BossBar bar, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull BossBar.Overlay oldOverlay, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull BossBar.Overlay newOverlay) {
        this.overlay = this.createOverlay(newOverlay);
        this.broadcastPacket(4);
    }

    public void bossBarFlagsChanged(@NotNull BossBar bar, @NotNull Set<BossBar.Flag> flagsAdded, @NotNull Set<BossBar.Flag> flagsRemoved) {
        this.flags = this.createFlag(this.flags, flagsAdded, flagsRemoved);
        this.broadcastPacket(5);
    }

    public void bossBarProgressChanged(@NotNull BossBar bar, float oldPercent, float newPercent) {
        this.health = newPercent;
        this.broadcastPacket(2);
    }

    private ViaFacet.BossBar(@NotNull String fromProtocol, @NotNull String toProtocol, @NotNull Class<? extends V> viewerClass, @NotNull Function<V, UserConnection> connectionFunction, Collection<V> viewers) {
        super(fromProtocol, toProtocol, 356, "BOSSBAR", viewerClass, connectionFunction);
        this.viewers = new CopyOnWriteArraySet<V>(viewers);
    }

    public void bossBarColorChanged(@NotNull BossBar bar, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull BossBar.Color oldColor, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull BossBar.Color newColor) {
        this.color = this.createColor(newColor);
        this.broadcastPacket(4);
    }
}
