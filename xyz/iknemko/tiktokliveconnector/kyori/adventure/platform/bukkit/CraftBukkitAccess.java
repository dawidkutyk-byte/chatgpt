/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.lang.invoke.MethodHandle;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;

final class CraftBukkitAccess {
    @Nullable
    static final Class<?> CLASS_HOLDER;
    @Nullable
    static final Class<?> CLASS_NMS_ENTITY;
    @Nullable
    static final Class<?> CLASS_CHAT_COMPONENT;
    @Nullable
    static final Class<?> CLASS_BUILT_IN_REGISTRIES;
    @Nullable
    static final Class<?> CLASS_RESOURCE_LOCATION;
    @Nullable
    static final Class<?> CLASS_REGISTRY;
    @Nullable
    static final MethodHandle NEW_RESOURCE_LOCATION;
    @Nullable
    static final Class<?> CLASS_LEVEL;
    @Nullable
    static final Class<?> CLASS_WRITABLE_REGISTRY;
    @Nullable
    static final Class<?> CLASS_SERVER_LEVEL;
    @Nullable
    static final Class<?> CLASS_RESOURCE_KEY;
    @Nullable
    static final Class<?> CLASS_REGISTRY_ACCESS;

    private CraftBukkitAccess() {
    }

    static {
        CLASS_CHAT_COMPONENT = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findNmsClassName((String)"IChatBaseComponent"), MinecraftReflection.findMcClassName((String)"network.chat.IChatBaseComponent"), MinecraftReflection.findMcClassName((String)"network.chat.Component")});
        CLASS_REGISTRY = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findNmsClassName((String)"IRegistry"), MinecraftReflection.findMcClassName((String)"core.IRegistry"), MinecraftReflection.findMcClassName((String)"core.Registry")});
        CLASS_SERVER_LEVEL = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findMcClassName((String)"server.level.WorldServer"), MinecraftReflection.findMcClassName((String)"server.level.ServerLevel")});
        CLASS_LEVEL = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findMcClassName((String)"world.level.World"), MinecraftReflection.findMcClassName((String)"world.level.Level")});
        CLASS_REGISTRY_ACCESS = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findMcClassName((String)"core.IRegistryCustom"), MinecraftReflection.findMcClassName((String)"core.RegistryAccess")});
        CLASS_RESOURCE_KEY = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findMcClassName((String)"resources.ResourceKey")});
        CLASS_RESOURCE_LOCATION = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findNmsClassName((String)"MinecraftKey"), MinecraftReflection.findMcClassName((String)"resources.MinecraftKey"), MinecraftReflection.findMcClassName((String)"resources.ResourceLocation")});
        CLASS_NMS_ENTITY = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findNmsClassName((String)"Entity"), MinecraftReflection.findMcClassName((String)"world.entity.Entity")});
        CLASS_BUILT_IN_REGISTRIES = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findMcClassName((String)"core.registries.BuiltInRegistries")});
        CLASS_HOLDER = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findMcClassName((String)"core.Holder")});
        CLASS_WRITABLE_REGISTRY = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findNmsClassName((String)"IRegistryWritable"), MinecraftReflection.findMcClassName((String)"core.IRegistryWritable"), MinecraftReflection.findMcClassName((String)"core.WritableRegistry")});
        MethodHandle newResourceLocation = MinecraftReflection.findConstructor(CLASS_RESOURCE_LOCATION, (Class[])new Class[]{String.class, String.class});
        if (newResourceLocation == null) {
            newResourceLocation = MinecraftReflection.searchMethod(CLASS_RESOURCE_LOCATION, (Integer)9, (String)"fromNamespaceAndPath", CLASS_RESOURCE_LOCATION, (Class[])new Class[]{String.class, String.class});
        }
        NEW_RESOURCE_LOCATION = newResourceLocation;
    }
}
