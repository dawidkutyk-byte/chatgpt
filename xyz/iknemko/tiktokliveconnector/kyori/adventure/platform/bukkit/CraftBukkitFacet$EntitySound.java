/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitAccess
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitAccess$EntitySound
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$PacketFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$PartialEntitySound
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitAccess;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound;

/*
 * Exception performing whole class analysis ignored.
 */
static class CraftBukkitFacet.EntitySound
extends CraftBukkitFacet.PacketFacet<Player>
implements CraftBukkitFacet.PartialEntitySound {
    private static final Class<?> CLASS_CLIENTBOUND_CUSTOM_SOUND = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findNmsClassName((String)"PacketPlayOutCustomSoundEffect"), MinecraftReflection.findMcClassName((String)"network.protocol.game.ClientboundCustomSoundPacket"), MinecraftReflection.findMcClassName((String)"network.protocol.game.PacketPlayOutCustomSoundEffect")});
    private static final Class<?> CLASS_VEC3 = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findNmsClassName((String)"Vec3D"), MinecraftReflection.findMcClassName((String)"world.phys.Vec3D"), MinecraftReflection.findMcClassName((String)"world.phys.Vec3")});
    private static final MethodHandle REGISTRY_GET_OPTIONAL;
    private static final MethodHandle NEW_RESOURCE_LOCATION;
    private static final Object REGISTRY_SOUND_EVENT;
    private static final MethodHandle NEW_CLIENTBOUND_CUSTOM_SOUND;
    private static final MethodHandle NEW_VEC3;
    private static final MethodHandle NEW_CLIENTBOUND_ENTITY_SOUND;

    public Object createForEntity(Sound sound, Entity entity) {
        try {
            Object nmsEntity = this.toNativeEntity(entity);
            if (nmsEntity == null) {
                return null;
            }
            Object soundCategory = this.toVanilla(sound.source());
            if (soundCategory == null) {
                return null;
            }
            Object nameRl = NEW_RESOURCE_LOCATION.invoke(sound.name().namespace(), sound.name().value());
            Optional event = REGISTRY_GET_OPTIONAL.invoke(REGISTRY_SOUND_EVENT, nameRl);
            long seed = sound.seed().orElseGet(() -> ThreadLocalRandom.current().nextLong());
            if (event.isPresent()) {
                return NEW_CLIENTBOUND_ENTITY_SOUND.invoke(event.get(), soundCategory, nmsEntity, sound.volume(), sound.pitch(), seed);
            }
            if (NEW_CLIENTBOUND_CUSTOM_SOUND == null) return null;
            if (NEW_VEC3 == null) return null;
            Location loc = entity.getLocation();
            return NEW_CLIENTBOUND_CUSTOM_SOUND.invoke(nameRl, soundCategory, NEW_VEC3.invoke(loc.getX(), loc.getY(), loc.getZ()), sound.volume(), sound.pitch(), seed);
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to send sound tracking an entity", (Object[])new Object[0]);
        }
        return null;
    }

    public void playSound(@NotNull Player viewer, Object message) {
        this.sendPacket(viewer, message);
    }

    CraftBukkitFacet.EntitySound() {
    }

    public boolean isSupported() {
        return super.isSupported() && NEW_CLIENTBOUND_ENTITY_SOUND != null && NEW_RESOURCE_LOCATION != null && REGISTRY_SOUND_EVENT != null && REGISTRY_GET_OPTIONAL != null && CraftBukkitFacet.access$1000() != null && CraftBukkitAccess.EntitySound.isSupported();
    }

    static {
        NEW_VEC3 = MinecraftReflection.findConstructor(CLASS_VEC3, (Class[])new Class[]{Double.TYPE, Double.TYPE, Double.TYPE});
        NEW_RESOURCE_LOCATION = MinecraftReflection.findConstructor((Class)CraftBukkitAccess.CLASS_RESOURCE_LOCATION, (Class[])new Class[]{String.class, String.class});
        REGISTRY_GET_OPTIONAL = MinecraftReflection.searchMethod((Class)CraftBukkitAccess.CLASS_REGISTRY, (Integer)1, (String)"getOptional", Optional.class, (Class[])new Class[]{CraftBukkitAccess.CLASS_RESOURCE_LOCATION});
        MethodHandle entitySoundPacketConstructor = MinecraftReflection.findConstructor((Class)CraftBukkitAccess.EntitySound.CLASS_CLIENTBOUND_ENTITY_SOUND, (Class[])new Class[]{CraftBukkitAccess.EntitySound.CLASS_SOUND_EVENT, CraftBukkitAccess.EntitySound.CLASS_SOUND_SOURCE, CraftBukkitFacet.access$1100(), Float.TYPE, Float.TYPE, Long.TYPE});
        if (entitySoundPacketConstructor == null && (entitySoundPacketConstructor = MinecraftReflection.findConstructor((Class)CraftBukkitAccess.EntitySound.CLASS_CLIENTBOUND_ENTITY_SOUND, (Class[])new Class[]{CraftBukkitAccess.EntitySound.CLASS_SOUND_EVENT, CraftBukkitAccess.EntitySound.CLASS_SOUND_SOURCE, CraftBukkitFacet.access$1100(), Float.TYPE, Float.TYPE})) != null) {
            entitySoundPacketConstructor = MethodHandles.dropArguments(entitySoundPacketConstructor, 5, new Class[]{Long.TYPE});
        }
        NEW_CLIENTBOUND_ENTITY_SOUND = entitySoundPacketConstructor;
        MethodHandle customSoundPacketConstructor = MinecraftReflection.findConstructor(CLASS_CLIENTBOUND_CUSTOM_SOUND, (Class[])new Class[]{CraftBukkitAccess.CLASS_RESOURCE_LOCATION, CraftBukkitAccess.EntitySound.CLASS_SOUND_SOURCE, CLASS_VEC3, Float.TYPE, Float.TYPE, Long.TYPE});
        if (customSoundPacketConstructor == null && (customSoundPacketConstructor = MinecraftReflection.findConstructor(CLASS_CLIENTBOUND_CUSTOM_SOUND, (Class[])new Class[]{CraftBukkitAccess.CLASS_RESOURCE_LOCATION, CraftBukkitAccess.EntitySound.CLASS_SOUND_SOURCE, CLASS_VEC3, Float.TYPE, Float.TYPE})) != null) {
            customSoundPacketConstructor = MethodHandles.dropArguments(customSoundPacketConstructor, 5, new Class[]{Long.TYPE});
        }
        NEW_CLIENTBOUND_CUSTOM_SOUND = customSoundPacketConstructor;
        Object registrySoundEvent = null;
        if (CraftBukkitAccess.CLASS_REGISTRY != null) {
            try {
                Field soundEventField = MinecraftReflection.findField((Class)CraftBukkitAccess.CLASS_REGISTRY, (String[])new String[]{"SOUND_EVENT"});
                if (soundEventField != null) {
                    registrySoundEvent = soundEventField.get(null);
                } else {
                    Object rootRegistry = null;
                    for (Field field : CraftBukkitAccess.CLASS_REGISTRY.getDeclaredFields()) {
                        int mask = 28;
                        if ((field.getModifiers() & 0x1C) != 28 || !field.getType().equals(CraftBukkitAccess.CLASS_WRITABLE_REGISTRY)) continue;
                        field.setAccessible(true);
                        rootRegistry = field.get(null);
                        break;
                    }
                    if (rootRegistry != null) {
                        registrySoundEvent = REGISTRY_GET_OPTIONAL.invoke(rootRegistry, NEW_RESOURCE_LOCATION.invoke("minecraft", "sound_event")).orElse(null);
                    }
                }
            }
            catch (Throwable thr) {
                Knob.logError((Throwable)thr, (String)"Failed to initialize EntitySound CraftBukkit facet", (Object[])new Object[0]);
            }
        }
        REGISTRY_SOUND_EVENT = registrySoundEvent;
    }
}
