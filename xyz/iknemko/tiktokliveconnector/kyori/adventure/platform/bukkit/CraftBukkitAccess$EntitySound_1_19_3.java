/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitAccess
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitAccess$EntitySound
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Field;
import java.util.Optional;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitAccess;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;

static final class CraftBukkitAccess.EntitySound_1_19_3 {
    @Nullable
    static final MethodHandle NEW_CLIENTBOUND_ENTITY_SOUND;
    @Nullable
    static final MethodHandle REGISTRY_GET_OPTIONAL;
    @Nullable
    static final MethodHandle SOUND_EVENT_CREATE_VARIABLE_RANGE;
    @Nullable
    static final Object SOUND_EVENT_REGISTRY;
    @Nullable
    static final MethodHandle REGISTRY_WRAP_AS_HOLDER;

    static boolean isSupported() {
        return NEW_CLIENTBOUND_ENTITY_SOUND != null && SOUND_EVENT_REGISTRY != null && CraftBukkitAccess.NEW_RESOURCE_LOCATION != null && REGISTRY_GET_OPTIONAL != null && REGISTRY_WRAP_AS_HOLDER != null && SOUND_EVENT_CREATE_VARIABLE_RANGE != null;
    }

    private CraftBukkitAccess.EntitySound_1_19_3() {
    }

    static {
        REGISTRY_GET_OPTIONAL = MinecraftReflection.searchMethod((Class)CraftBukkitAccess.CLASS_REGISTRY, (Integer)1, (String)"getOptional", Optional.class, (Class[])new Class[]{CraftBukkitAccess.CLASS_RESOURCE_LOCATION});
        REGISTRY_WRAP_AS_HOLDER = MinecraftReflection.searchMethod((Class)CraftBukkitAccess.CLASS_REGISTRY, (Integer)1, (String)"wrapAsHolder", (Class)CraftBukkitAccess.CLASS_HOLDER, (Class[])new Class[]{Object.class});
        SOUND_EVENT_CREATE_VARIABLE_RANGE = MinecraftReflection.searchMethod((Class)CraftBukkitAccess.EntitySound.CLASS_SOUND_EVENT, (Integer)9, (String)"createVariableRangeEvent", (Class)CraftBukkitAccess.EntitySound.CLASS_SOUND_EVENT, (Class[])new Class[]{CraftBukkitAccess.CLASS_RESOURCE_LOCATION});
        NEW_CLIENTBOUND_ENTITY_SOUND = MinecraftReflection.findConstructor((Class)CraftBukkitAccess.EntitySound.CLASS_CLIENTBOUND_ENTITY_SOUND, (Class[])new Class[]{CraftBukkitAccess.CLASS_HOLDER, CraftBukkitAccess.EntitySound.CLASS_SOUND_SOURCE, CraftBukkitAccess.CLASS_NMS_ENTITY, Float.TYPE, Float.TYPE, Long.TYPE});
        Object soundEventRegistry = null;
        try {
            Field soundEventRegistryField = MinecraftReflection.findField((Class)CraftBukkitAccess.CLASS_BUILT_IN_REGISTRIES, (Class)CraftBukkitAccess.CLASS_REGISTRY, (String[])new String[]{"SOUND_EVENT"});
            if (soundEventRegistryField != null) {
                soundEventRegistry = soundEventRegistryField.get(null);
            } else if (CraftBukkitAccess.CLASS_BUILT_IN_REGISTRIES != null && REGISTRY_GET_OPTIONAL != null && CraftBukkitAccess.NEW_RESOURCE_LOCATION != null) {
                Object rootRegistry = null;
                for (Field field : CraftBukkitAccess.CLASS_BUILT_IN_REGISTRIES.getDeclaredFields()) {
                    int mask = 26;
                    if ((field.getModifiers() & 0x1A) != 26 || !field.getType().equals(CraftBukkitAccess.CLASS_WRITABLE_REGISTRY)) continue;
                    field.setAccessible(true);
                    rootRegistry = field.get(null);
                    break;
                }
                if (rootRegistry != null) {
                    soundEventRegistry = REGISTRY_GET_OPTIONAL.invoke(rootRegistry, CraftBukkitAccess.NEW_RESOURCE_LOCATION.invoke("minecraft", "sound_event")).orElse(null);
                }
            }
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to initialize EntitySound_1_19_3 CraftBukkit facet", (Object[])new Object[0]);
        }
        SOUND_EVENT_REGISTRY = soundEventRegistry;
    }
}
