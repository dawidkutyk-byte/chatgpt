/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.inventory.ItemStack
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitAccess
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.lang.invoke.MethodHandle;
import java.util.List;
import java.util.Optional;
import org.bukkit.inventory.ItemStack;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitAccess;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;

static final class CraftBukkitAccess.Book_1_20_5 {
    static final Class<?> CLASS_CRAFT_REGISTRY;
    static final Class<?> PACKET_OPEN_BOOK;
    static final MethodHandle CREATE_FILTERABLE;
    static final MethodHandle GET_REGISTRY;
    static final MethodHandle NEW_PACKET_OPEN_BOOK;
    static final MethodHandle CRAFT_ITEMSTACK_CRAFT_MIRROR;
    static final Class<?> CLASS_MC_BOOK_CONTENT;
    static final MethodHandle MC_ITEMSTACK_SET;
    static final MethodHandle CREATE_REGISTRY_KEY;
    static final MethodHandle REGISTRY_GET_OPTIONAL;
    static final Class<?> CLASS_ENUM_HAND;
    static final Object WRITTEN_BOOK_COMPONENT_TYPE;
    static final Class<?> CLASS_MC_DATA_COMPONENT_TYPE;
    static final MethodHandle NEW_BOOK_CONTENT;
    static final Class<?> CLASS_MC_FILTERABLE;
    static final MethodHandle CRAFT_ITEMSTACK_NMS_COPY;
    static final Class<?> CLASS_MC_ITEMSTACK;
    static final Class<?> CLASS_CRAFT_ITEMSTACK;
    static final Object HAND_MAIN;

    static boolean isSupported() {
        return WRITTEN_BOOK_COMPONENT_TYPE != null && CREATE_FILTERABLE != null && NEW_BOOK_CONTENT != null && CRAFT_ITEMSTACK_NMS_COPY != null && MC_ITEMSTACK_SET != null && CRAFT_ITEMSTACK_CRAFT_MIRROR != null && NEW_PACKET_OPEN_BOOK != null && HAND_MAIN != null;
    }

    CraftBukkitAccess.Book_1_20_5() {
    }

    static {
        Object componentType;
        block5: {
            CLASS_CRAFT_ITEMSTACK = MinecraftReflection.findCraftClass((String)"inventory.CraftItemStack");
            CLASS_MC_ITEMSTACK = MinecraftReflection.findMcClass((String[])new String[]{"world.item.ItemStack"});
            CLASS_MC_DATA_COMPONENT_TYPE = MinecraftReflection.findMcClass((String[])new String[]{"core.component.DataComponentType"});
            CLASS_MC_BOOK_CONTENT = MinecraftReflection.findMcClass((String[])new String[]{"world.item.component.WrittenBookContent"});
            CLASS_MC_FILTERABLE = MinecraftReflection.findMcClass((String[])new String[]{"server.network.Filterable"});
            CLASS_CRAFT_REGISTRY = MinecraftReflection.findCraftClass((String)"CraftRegistry");
            CREATE_FILTERABLE = MinecraftReflection.searchMethod(CLASS_MC_FILTERABLE, (Integer)9, (String)"passThrough", CLASS_MC_FILTERABLE, (Class[])new Class[]{Object.class});
            GET_REGISTRY = MinecraftReflection.findStaticMethod(CLASS_CRAFT_REGISTRY, (String)"getMinecraftRegistry", (Class)CraftBukkitAccess.CLASS_REGISTRY, (Class[])new Class[]{CraftBukkitAccess.CLASS_RESOURCE_KEY});
            CREATE_REGISTRY_KEY = MinecraftReflection.searchMethod((Class)CraftBukkitAccess.CLASS_RESOURCE_KEY, (Integer)9, (String)"createRegistryKey", (Class)CraftBukkitAccess.CLASS_RESOURCE_KEY, (Class[])new Class[]{CraftBukkitAccess.CLASS_RESOURCE_LOCATION});
            NEW_BOOK_CONTENT = MinecraftReflection.findConstructor(CLASS_MC_BOOK_CONTENT, (Class[])new Class[]{CLASS_MC_FILTERABLE, String.class, Integer.TYPE, List.class, Boolean.TYPE});
            REGISTRY_GET_OPTIONAL = MinecraftReflection.searchMethod((Class)CraftBukkitAccess.CLASS_REGISTRY, (Integer)1, (String)"getOptional", Optional.class, (Class[])new Class[]{CraftBukkitAccess.CLASS_RESOURCE_LOCATION});
            CLASS_ENUM_HAND = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findNmsClassName((String)"EnumHand"), MinecraftReflection.findMcClassName((String)"world.EnumHand"), MinecraftReflection.findMcClassName((String)"world.InteractionHand")});
            HAND_MAIN = MinecraftReflection.findEnum(CLASS_ENUM_HAND, (String)"MAIN_HAND", (int)0);
            MC_ITEMSTACK_SET = MinecraftReflection.searchMethod(CLASS_MC_ITEMSTACK, (Integer)1, (String)"set", Object.class, (Class[])new Class[]{CLASS_MC_DATA_COMPONENT_TYPE, Object.class});
            CRAFT_ITEMSTACK_NMS_COPY = MinecraftReflection.findStaticMethod(CLASS_CRAFT_ITEMSTACK, (String)"asNMSCopy", CLASS_MC_ITEMSTACK, (Class[])new Class[]{ItemStack.class});
            CRAFT_ITEMSTACK_CRAFT_MIRROR = MinecraftReflection.findStaticMethod(CLASS_CRAFT_ITEMSTACK, (String)"asCraftMirror", CLASS_CRAFT_ITEMSTACK, (Class[])new Class[]{CLASS_MC_ITEMSTACK});
            PACKET_OPEN_BOOK = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findMcClassName((String)"network.protocol.game.PacketPlayOutOpenBook"), MinecraftReflection.findMcClassName((String)"network.protocol.game.ClientboundOpenBookPacket")});
            NEW_PACKET_OPEN_BOOK = MinecraftReflection.findConstructor(PACKET_OPEN_BOOK, (Class[])new Class[]{CLASS_ENUM_HAND});
            Object componentTypeRegistry = null;
            componentType = null;
            try {
                if (GET_REGISTRY == null || CREATE_REGISTRY_KEY == null || CraftBukkitAccess.NEW_RESOURCE_LOCATION == null || REGISTRY_GET_OPTIONAL == null) break block5;
                Object registryKey = CREATE_REGISTRY_KEY.invoke(CraftBukkitAccess.NEW_RESOURCE_LOCATION.invoke("minecraft", "data_component_type"));
                try {
                    componentTypeRegistry = GET_REGISTRY.invoke(registryKey);
                }
                catch (Exception exception) {
                    // empty catch block
                }
                if (componentTypeRegistry != null) {
                    componentType = REGISTRY_GET_OPTIONAL.invoke(componentTypeRegistry, CraftBukkitAccess.NEW_RESOURCE_LOCATION.invoke("minecraft", "written_book_content")).orElse(null);
                }
            }
            catch (Throwable error) {
                Knob.logError((Throwable)error, (String)"Failed to initialize Book_1_20_5 CraftBukkit facet", (Object[])new Object[0]);
            }
        }
        WRITTEN_BOOK_COMPONENT_TYPE = componentType;
    }
}
