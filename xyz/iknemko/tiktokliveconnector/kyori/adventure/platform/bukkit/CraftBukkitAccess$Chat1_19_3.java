/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitAccess
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.lang.invoke.MethodHandle;
import java.util.Optional;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitAccess;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;

static final class CraftBukkitAccess.Chat1_19_3 {
    @Nullable
    static final MethodHandle LEVEL_GET_REGISTRY_ACCESS;
    @Nullable
    static final MethodHandle REGISTRY_GET_OPTIONAL;
    @Nullable
    static final MethodHandle SERVER_PLAYER_GET_LEVEL;
    @Nullable
    static final MethodHandle SERVER_LEVEL_GET_REGISTRY_ACCESS;
    @Nullable
    static final MethodHandle DISGUISED_CHAT_PACKET_CONSTRUCTOR;
    @Nullable
    static final MethodHandle REGISTRY_GET_HOLDER;
    @Nullable
    static final MethodHandle CHAT_TYPE_BOUND_CONSTRUCTOR;
    static final Object CHAT_TYPE_RESOURCE_KEY;
    @Nullable
    static final MethodHandle REGISTRY_ACCESS_GET_REGISTRY_OPTIONAL;
    @Nullable
    static final MethodHandle RESOURCE_KEY_CREATE;
    @Nullable
    static final MethodHandle REGISTRY_GET_ID;
    @Nullable
    static final MethodHandle ACTUAL_GET_REGISTRY_ACCESS;
    @Nullable
    static final MethodHandle CHAT_TYPE_BOUND_NETWORK_CONSTRUCTOR;

    private CraftBukkitAccess.Chat1_19_3() {
    }

    static {
        RESOURCE_KEY_CREATE = MinecraftReflection.searchMethod((Class)CraftBukkitAccess.CLASS_RESOURCE_KEY, (Integer)9, (String)"create", (Class)CraftBukkitAccess.CLASS_RESOURCE_KEY, (Class[])new Class[]{CraftBukkitAccess.CLASS_RESOURCE_KEY, CraftBukkitAccess.CLASS_RESOURCE_LOCATION});
        SERVER_PLAYER_GET_LEVEL = MinecraftReflection.searchMethod((Class)CraftBukkitFacet.CRAFT_PLAYER_GET_HANDLE.type().returnType(), (Integer)1, (String)"getLevel", (Class)CraftBukkitAccess.CLASS_SERVER_LEVEL, (Class[])new Class[0]);
        SERVER_LEVEL_GET_REGISTRY_ACCESS = MinecraftReflection.searchMethod((Class)CraftBukkitAccess.CLASS_SERVER_LEVEL, (Integer)1, (String)"registryAccess", (Class)CraftBukkitAccess.CLASS_REGISTRY_ACCESS, (Class[])new Class[0]);
        LEVEL_GET_REGISTRY_ACCESS = MinecraftReflection.searchMethod((Class)CraftBukkitAccess.CLASS_LEVEL, (Integer)1, (String)"registryAccess", (Class)CraftBukkitAccess.CLASS_REGISTRY_ACCESS, (Class[])new Class[0]);
        ACTUAL_GET_REGISTRY_ACCESS = SERVER_LEVEL_GET_REGISTRY_ACCESS == null ? LEVEL_GET_REGISTRY_ACCESS : SERVER_LEVEL_GET_REGISTRY_ACCESS;
        REGISTRY_ACCESS_GET_REGISTRY_OPTIONAL = MinecraftReflection.searchMethod((Class)CraftBukkitAccess.CLASS_REGISTRY_ACCESS, (Integer)1, (String)"registry", Optional.class, (Class[])new Class[]{CraftBukkitAccess.CLASS_RESOURCE_KEY});
        REGISTRY_GET_OPTIONAL = MinecraftReflection.searchMethod((Class)CraftBukkitAccess.CLASS_REGISTRY, (Integer)1, (String)"getOptional", Optional.class, (Class[])new Class[]{CraftBukkitAccess.CLASS_RESOURCE_LOCATION});
        REGISTRY_GET_HOLDER = MinecraftReflection.searchMethod((Class)CraftBukkitAccess.CLASS_REGISTRY, (Integer)1, (String)"getHolder", Optional.class, (Class[])new Class[]{CraftBukkitAccess.CLASS_RESOURCE_LOCATION});
        REGISTRY_GET_ID = MinecraftReflection.searchMethod((Class)CraftBukkitAccess.CLASS_REGISTRY, (Integer)1, (String)"getId", Integer.TYPE, (Class[])new Class[]{Object.class});
        MethodHandle boundNetworkConstructor = null;
        MethodHandle boundConstructor = null;
        MethodHandle disguisedChatPacketConstructor = null;
        Object chatTypeResourceKey = null;
        try {
            MethodHandle createRegistryKey;
            Class disguisedChatPacketClass;
            Class parentClass;
            Class<?> classChatTypeBound;
            Class parentClass2;
            Class<?> classChatTypeBoundNetwork = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findMcClassName((String)"network.chat.ChatType$BoundNetwork")});
            if (classChatTypeBoundNetwork == null && (parentClass2 = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findMcClassName((String)"network.chat.ChatMessageType")})) != null) {
                for (Class<?> childClass : parentClass2.getClasses()) {
                    boundNetworkConstructor = MinecraftReflection.findConstructor(childClass, (Class[])new Class[]{Integer.TYPE, CraftBukkitAccess.CLASS_CHAT_COMPONENT, CraftBukkitAccess.CLASS_CHAT_COMPONENT});
                    if (boundNetworkConstructor == null) continue;
                    classChatTypeBoundNetwork = childClass;
                    break;
                }
            }
            if ((classChatTypeBound = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findMcClassName((String)"network.chat.ChatType$BoundNetwork")})) == null && (parentClass = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findMcClassName((String)"network.chat.ChatMessageType")})) != null) {
                for (Class<?> childClass : parentClass.getClasses()) {
                    boundConstructor = MinecraftReflection.findConstructor(childClass, (Class[])new Class[]{CraftBukkitAccess.CLASS_HOLDER, CraftBukkitAccess.CLASS_CHAT_COMPONENT, Optional.class});
                    if (boundConstructor == null) continue;
                    classChatTypeBound = childClass;
                    break;
                }
            }
            if ((disguisedChatPacketClass = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findMcClassName((String)"network.protocol.game.ClientboundDisguisedChatPacket")})) != null) {
                if (classChatTypeBoundNetwork != null) {
                    disguisedChatPacketConstructor = MinecraftReflection.findConstructor((Class)disguisedChatPacketClass, (Class[])new Class[]{CraftBukkitAccess.CLASS_CHAT_COMPONENT, classChatTypeBoundNetwork});
                } else if (classChatTypeBound != null) {
                    disguisedChatPacketConstructor = MinecraftReflection.findConstructor((Class)disguisedChatPacketClass, (Class[])new Class[]{CraftBukkitAccess.CLASS_CHAT_COMPONENT, classChatTypeBound});
                }
            }
            if (CraftBukkitAccess.NEW_RESOURCE_LOCATION != null && RESOURCE_KEY_CREATE != null && (createRegistryKey = MinecraftReflection.searchMethod((Class)CraftBukkitAccess.CLASS_RESOURCE_KEY, (Integer)9, (String)"createRegistryKey", (Class)CraftBukkitAccess.CLASS_RESOURCE_KEY, (Class[])new Class[]{CraftBukkitAccess.CLASS_RESOURCE_LOCATION})) != null) {
                chatTypeResourceKey = createRegistryKey.invoke(CraftBukkitAccess.NEW_RESOURCE_LOCATION.invoke("minecraft", "chat_type"));
            }
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to initialize 1.19.3 chat support", (Object[])new Object[0]);
        }
        DISGUISED_CHAT_PACKET_CONSTRUCTOR = disguisedChatPacketConstructor;
        CHAT_TYPE_BOUND_NETWORK_CONSTRUCTOR = boundNetworkConstructor;
        CHAT_TYPE_BOUND_CONSTRUCTOR = boundConstructor;
        CHAT_TYPE_RESOURCE_KEY = chatTypeResourceKey;
    }

    static boolean isSupported() {
        return ACTUAL_GET_REGISTRY_ACCESS != null && REGISTRY_ACCESS_GET_REGISTRY_OPTIONAL != null && REGISTRY_GET_OPTIONAL != null && (CHAT_TYPE_BOUND_NETWORK_CONSTRUCTOR != null || CHAT_TYPE_BOUND_CONSTRUCTOR != null) && DISGUISED_CHAT_PACKET_CONSTRUCTOR != null && CHAT_TYPE_RESOURCE_KEY != null;
    }
}
