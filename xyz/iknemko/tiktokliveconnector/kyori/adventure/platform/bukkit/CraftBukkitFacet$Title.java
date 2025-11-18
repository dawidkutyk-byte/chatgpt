/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$PacketFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Title
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;

/*
 * Exception performing whole class analysis ignored.
 */
static class CraftBukkitFacet.Title
extends CraftBukkitFacet.PacketFacet<Player>
implements Facet.Title<Player, Object, List<Object>, List<?>> {
    public void contributeTitle(@NotNull List<Object> coll, @NotNull Object title) {
        try {
            coll.add(CraftBukkitFacet.access$700().invoke(CraftBukkitFacet.access$1300(), title));
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to invoke title packet constructor", (Object[])new Object[0]);
        }
    }

    CraftBukkitFacet.Title() {
    }

    public void contributeTimes(@NotNull List<Object> coll, int inTicks, int stayTicks, int outTicks) {
        try {
            coll.add(CraftBukkitFacet.access$1200().invoke(inTicks, stayTicks, outTicks));
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to invoke title animations packet constructor", (Object[])new Object[0]);
        }
    }

    public void contributeSubtitle(@NotNull List<Object> coll, @NotNull Object subtitle) {
        try {
            coll.add(CraftBukkitFacet.access$700().invoke(CraftBukkitFacet.access$1400(), subtitle));
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to invoke subtitle packet constructor", (Object[])new Object[0]);
        }
    }

    public boolean isSupported() {
        return super.isSupported() && CraftBukkitFacet.access$700() != null && CraftBukkitFacet.access$1200() != null;
    }

    @Nullable
    public List<?> completeTitle(@NotNull List<Object> coll) {
        return coll;
    }

    public void showTitle(@NotNull Player viewer, @NotNull List<?> packets) {
        Iterator<?> iterator = packets.iterator();
        while (iterator.hasNext()) {
            Object packet = iterator.next();
            this.sendMessage((CommandSender)viewer, packet);
        }
    }

    public void resetTitle(@NotNull Player viewer) {
        try {
            if (CraftBukkitFacet.access$1600() != null) {
                this.sendPacket(viewer, CraftBukkitFacet.access$700().invoke(CraftBukkitFacet.access$1600(), null));
            } else {
                viewer.resetTitle();
            }
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to clear title", (Object[])new Object[0]);
        }
    }

    @NotNull
    public List<Object> createTitleCollection() {
        return new ArrayList<Object>();
    }

    public void clearTitle(@NotNull Player viewer) {
        try {
            if (CraftBukkitFacet.access$1500() != null) {
                this.sendPacket(viewer, CraftBukkitFacet.access$700().invoke(CraftBukkitFacet.access$1500(), null));
            } else {
                viewer.sendTitle("", "", -1, -1, -1);
            }
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to clear title", (Object[])new Object[0]);
        }
    }
}
