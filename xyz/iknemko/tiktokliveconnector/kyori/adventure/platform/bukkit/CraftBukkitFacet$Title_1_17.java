/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$PacketFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Title
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.lang.invoke.MethodHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;

/*
 * Exception performing whole class analysis ignored.
 */
static class CraftBukkitFacet.Title_1_17
extends CraftBukkitFacet.PacketFacet<Player>
implements Facet.Title<Player, Object, List<Object>, List<?>> {
    private static final Class<?> PACKET_SET_TITLE_ANIMATION;
    private static final Class<?> PACKET_SET_TITLE;
    private static final Class<?> PACKET_CLEAR_TITLES;
    private static final MethodHandle CONSTRUCTOR_SET_TITLE_ANIMATION;
    private static final MethodHandle CONSTRUCTOR_SET_TITLE;
    private static final Class<?> PACKET_SET_SUBTITLE;
    private static final MethodHandle CONSTRUCTOR_SET_SUBTITLE;
    private static final MethodHandle CONSTRUCTOR_CLEAR_TITLES;

    @NotNull
    public List<Object> createTitleCollection() {
        return new ArrayList<Object>();
    }

    public void contributeTitle(@NotNull List<Object> coll, @NotNull Object title) {
        try {
            coll.add(CONSTRUCTOR_SET_TITLE.invoke(title));
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to invoke title packet constructor", (Object[])new Object[0]);
        }
    }

    public boolean isSupported() {
        return super.isSupported() && CONSTRUCTOR_SET_TITLE != null && CONSTRUCTOR_SET_SUBTITLE != null && CONSTRUCTOR_SET_TITLE_ANIMATION != null && CONSTRUCTOR_CLEAR_TITLES != null;
    }

    @Nullable
    public List<?> completeTitle(@NotNull List<Object> coll) {
        return coll;
    }

    public void contributeTimes(@NotNull List<Object> coll, int inTicks, int stayTicks, int outTicks) {
        try {
            coll.add(CONSTRUCTOR_SET_TITLE_ANIMATION.invoke(inTicks, stayTicks, outTicks));
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to invoke title animations packet constructor", (Object[])new Object[0]);
        }
    }

    public void showTitle(@NotNull Player viewer, @NotNull List<?> packets) {
        Iterator<?> iterator = packets.iterator();
        while (iterator.hasNext()) {
            Object packet = iterator.next();
            this.sendMessage((CommandSender)viewer, packet);
        }
    }

    public void contributeSubtitle(@NotNull List<Object> coll, @NotNull Object subtitle) {
        try {
            coll.add(CONSTRUCTOR_SET_SUBTITLE.invoke(subtitle));
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to invoke subtitle packet constructor", (Object[])new Object[0]);
        }
    }

    public void clearTitle(@NotNull Player viewer) {
        try {
            if (CONSTRUCTOR_CLEAR_TITLES != null) {
                this.sendPacket(viewer, CONSTRUCTOR_CLEAR_TITLES.invoke(false));
            } else {
                viewer.sendTitle("", "", -1, -1, -1);
            }
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to clear title", (Object[])new Object[0]);
        }
    }

    public void resetTitle(@NotNull Player viewer) {
        try {
            if (CONSTRUCTOR_CLEAR_TITLES != null) {
                this.sendPacket(viewer, CONSTRUCTOR_CLEAR_TITLES.invoke(true));
            } else {
                viewer.resetTitle();
            }
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to clear title", (Object[])new Object[0]);
        }
    }

    CraftBukkitFacet.Title_1_17() {
    }

    static {
        PACKET_SET_TITLE = MinecraftReflection.findMcClass((String[])new String[]{"network.protocol.game.ClientboundSetTitleTextPacket"});
        PACKET_SET_SUBTITLE = MinecraftReflection.findMcClass((String[])new String[]{"network.protocol.game.ClientboundSetSubtitleTextPacket"});
        PACKET_SET_TITLE_ANIMATION = MinecraftReflection.findMcClass((String[])new String[]{"network.protocol.game.ClientboundSetTitlesAnimationPacket"});
        PACKET_CLEAR_TITLES = MinecraftReflection.findMcClass((String[])new String[]{"network.protocol.game.ClientboundClearTitlesPacket"});
        CONSTRUCTOR_SET_TITLE = MinecraftReflection.findConstructor(PACKET_SET_TITLE, (Class[])new Class[]{CraftBukkitFacet.access$500()});
        CONSTRUCTOR_SET_SUBTITLE = MinecraftReflection.findConstructor(PACKET_SET_SUBTITLE, (Class[])new Class[]{CraftBukkitFacet.access$500()});
        CONSTRUCTOR_SET_TITLE_ANIMATION = MinecraftReflection.findConstructor(PACKET_SET_TITLE_ANIMATION, (Class[])new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE});
        CONSTRUCTOR_CLEAR_TITLES = MinecraftReflection.findConstructor(PACKET_CLEAR_TITLES, (Class[])new Class[]{Boolean.TYPE});
    }
}
