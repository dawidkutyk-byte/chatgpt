/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$TabList
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.PaperFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializer
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.lang.invoke.MethodHandle;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.PaperFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializer;

/*
 * Exception performing whole class analysis ignored.
 */
static class PaperFacet.TabList
extends CraftBukkitFacet.TabList {
    private static final MethodHandle NATIVE_GSON_COMPONENT_SERIALIZER_DESERIALIZE_METHOD_BOUND;
    private static final boolean SUPPORTED;

    public boolean isSupported() {
        return SUPPORTED && super.isSupported() && (CLIENTBOUND_TAB_LIST_PACKET_SET_HEADER != null && CLIENTBOUND_TAB_LIST_PACKET_SET_FOOTER != null || PaperFacet.access$200() != null);
    }

    static {
        SUPPORTED = MinecraftReflection.hasField((Class)CLASS_CRAFT_PLAYER, (Class)PaperFacet.NATIVE_COMPONENT_CLASS, (String[])new String[]{"playerListHeader"}) && MinecraftReflection.hasField((Class)CLASS_CRAFT_PLAYER, (Class)PaperFacet.NATIVE_COMPONENT_CLASS, (String[])new String[]{"playerListFooter"});
        NATIVE_GSON_COMPONENT_SERIALIZER_DESERIALIZE_METHOD_BOUND = PaperFacet.TabList.createBoundNativeDeserializeMethodHandle();
    }

    protected Object create117Packet(Player viewer, @Nullable Object header, @Nullable Object footer) throws Throwable {
        if (CLIENTBOUND_TAB_LIST_PACKET_SET_FOOTER == null && CLIENTBOUND_TAB_LIST_PACKET_SET_HEADER == null) {
            return CLIENTBOUND_TAB_LIST_PACKET_CTOR.invoke(PaperFacet.access$200().invoke(header == null ? this.createMessage(viewer, (Component)Component.empty()) : header), PaperFacet.access$200().invoke(footer == null ? this.createMessage(viewer, (Component)Component.empty()) : footer));
        }
        Object packet = CLIENTBOUND_TAB_LIST_PACKET_CTOR.invoke(null, null);
        CLIENTBOUND_TAB_LIST_PACKET_SET_HEADER.invoke(packet, header == null ? this.createMessage(viewer, (Component)Component.empty()) : header);
        CLIENTBOUND_TAB_LIST_PACKET_SET_FOOTER.invoke(packet, footer == null ? this.createMessage(viewer, (Component)Component.empty()) : footer);
        return packet;
    }

    @Nullable
    private static MethodHandle createBoundNativeDeserializeMethodHandle() {
        if (!SUPPORTED) return null;
        try {
            return PaperFacet.access$100().bindTo(PaperFacet.access$000().invoke());
        }
        catch (Throwable throwable) {
            Knob.logError((Throwable)throwable, (String)"Failed to access native GsonComponentSerializer", (Object[])new Object[0]);
            return null;
        }
    }

    @Nullable
    public Object createMessage(@NotNull Player viewer, @NotNull Component message) {
        try {
            return NATIVE_GSON_COMPONENT_SERIALIZER_DESERIALIZE_METHOD_BOUND.invoke((String)GsonComponentSerializer.gson().serialize(message));
        }
        catch (Throwable throwable) {
            Knob.logError((Throwable)throwable, (String)"Failed to create native Component message", (Object[])new Object[0]);
            return null;
        }
    }

    PaperFacet.TabList() {
    }
}
