/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$PacketFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.PaperFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$TabList
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Field;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.PaperFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;

/*
 * Exception performing whole class analysis ignored.
 */
static class CraftBukkitFacet.TabList
extends CraftBukkitFacet.PacketFacet<Player>
implements Facet.TabList<Player, Object> {
    @Nullable
    protected static final MethodHandle CLIENTBOUND_TAB_LIST_PACKET_CTOR;
    @Nullable
    protected static final MethodHandle CLIENTBOUND_TAB_LIST_PACKET_SET_FOOTER;
    @Nullable
    private static final Field CRAFT_PLAYER_TAB_LIST_HEADER;
    @Nullable
    private static final MethodHandle CLIENTBOUND_TAB_LIST_PACKET_CTOR_PRE_1_17;
    private static final Class<?> CLIENTBOUND_TAB_LIST_PACKET;
    @Nullable
    private static final Field CRAFT_PLAYER_TAB_LIST_FOOTER;
    @Nullable
    protected static final MethodHandle CLIENTBOUND_TAB_LIST_PACKET_SET_HEADER;

    static {
        CLIENTBOUND_TAB_LIST_PACKET = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findNmsClassName((String)"PacketPlayOutPlayerListHeaderFooter"), MinecraftReflection.findMcClassName((String)"network.protocol.game.PacketPlayOutPlayerListHeaderFooter"), MinecraftReflection.findMcClassName((String)"network.protocol.game.ClientboundTabListPacket")});
        CLIENTBOUND_TAB_LIST_PACKET_CTOR_PRE_1_17 = MinecraftReflection.findConstructor(CLIENTBOUND_TAB_LIST_PACKET, (Class[])new Class[0]);
        CLIENTBOUND_TAB_LIST_PACKET_CTOR = MinecraftReflection.findConstructor(CLIENTBOUND_TAB_LIST_PACKET, (Class[])new Class[]{CraftBukkitFacet.access$500(), CraftBukkitFacet.access$500()});
        CRAFT_PLAYER_TAB_LIST_HEADER = MinecraftReflection.findField((Class)CLASS_CRAFT_PLAYER, (String[])new String[]{"playerListHeader"});
        CRAFT_PLAYER_TAB_LIST_FOOTER = MinecraftReflection.findField((Class)CLASS_CRAFT_PLAYER, (String[])new String[]{"playerListFooter"});
        CLIENTBOUND_TAB_LIST_PACKET_SET_HEADER = CraftBukkitFacet.TabList.first(MinecraftReflection.findSetterOf((Field)MinecraftReflection.findField(CLIENTBOUND_TAB_LIST_PACKET, (Class)PaperFacet.NATIVE_COMPONENT_CLASS, (String[])new String[]{"adventure$header"})), MinecraftReflection.findSetterOf((Field)MinecraftReflection.findField(CLIENTBOUND_TAB_LIST_PACKET, (Class)CraftBukkitFacet.access$500(), (String[])new String[]{"header", "a"})));
        CLIENTBOUND_TAB_LIST_PACKET_SET_FOOTER = CraftBukkitFacet.TabList.first(MinecraftReflection.findSetterOf((Field)MinecraftReflection.findField(CLIENTBOUND_TAB_LIST_PACKET, (Class)PaperFacet.NATIVE_COMPONENT_CLASS, (String[])new String[]{"adventure$footer"})), MinecraftReflection.findSetterOf((Field)MinecraftReflection.findField(CLIENTBOUND_TAB_LIST_PACKET, (Class)CraftBukkitFacet.access$500(), (String[])new String[]{"footer", "b"})));
    }

    public void send(Player viewer, @Nullable Object header, @Nullable Object footer) {
        try {
            Object packet;
            if (CRAFT_PLAYER_TAB_LIST_HEADER != null && CRAFT_PLAYER_TAB_LIST_FOOTER != null) {
                if (header == null) {
                    header = CRAFT_PLAYER_TAB_LIST_HEADER.get(viewer);
                } else {
                    CRAFT_PLAYER_TAB_LIST_HEADER.set(viewer, header);
                }
                if (footer == null) {
                    footer = CRAFT_PLAYER_TAB_LIST_FOOTER.get(viewer);
                } else {
                    CRAFT_PLAYER_TAB_LIST_FOOTER.set(viewer, footer);
                }
            }
            if (CLIENTBOUND_TAB_LIST_PACKET_CTOR != null) {
                packet = this.create117Packet(viewer, header, footer);
            } else {
                packet = CLIENTBOUND_TAB_LIST_PACKET_CTOR_PRE_1_17.invoke();
                CLIENTBOUND_TAB_LIST_PACKET_SET_HEADER.invoke(packet, header == null ? this.createMessage((CommandSender)viewer, (Component)Component.empty()) : header);
                CLIENTBOUND_TAB_LIST_PACKET_SET_FOOTER.invoke(packet, footer == null ? this.createMessage((CommandSender)viewer, (Component)Component.empty()) : footer);
            }
            this.sendPacket(viewer, packet);
        }
        catch (Throwable thr) {
            Knob.logError((Throwable)thr, (String)"Failed to send tab list header and footer to %s", (Object[])new Object[]{viewer});
        }
    }

    public boolean isSupported() {
        return (CLIENTBOUND_TAB_LIST_PACKET_CTOR != null || CLIENTBOUND_TAB_LIST_PACKET_CTOR_PRE_1_17 != null && CLIENTBOUND_TAB_LIST_PACKET_SET_HEADER != null && CLIENTBOUND_TAB_LIST_PACKET_SET_FOOTER != null) && super.isSupported();
    }

    protected Object create117Packet(Player viewer, @Nullable Object header, @Nullable Object footer) throws Throwable {
        return CLIENTBOUND_TAB_LIST_PACKET_CTOR.invoke(header == null ? this.createMessage((CommandSender)viewer, (Component)Component.empty()) : header, footer == null ? this.createMessage((CommandSender)viewer, (Component)Component.empty()) : footer);
    }

    CraftBukkitFacet.TabList() {
    }

    private static MethodHandle first(MethodHandle ... handles) {
        int i = 0;
        while (i < handles.length) {
            MethodHandle handle = handles[i];
            if (handle != null) {
                return handle;
            }
            ++i;
        }
        return null;
    }
}
