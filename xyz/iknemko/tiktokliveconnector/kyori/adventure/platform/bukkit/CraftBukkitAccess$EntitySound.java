/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;

static final class CraftBukkitAccess.EntitySound {
    @Nullable
    static final Class<?> CLASS_SOUND_EVENT;
    @Nullable
    static final Class<?> CLASS_CLIENTBOUND_ENTITY_SOUND;
    @Nullable
    static final Class<?> CLASS_SOUND_SOURCE;
    @Nullable
    static final MethodHandle SOUND_SOURCE_GET_NAME;

    private CraftBukkitAccess.EntitySound() {
    }

    static boolean isSupported() {
        return SOUND_SOURCE_GET_NAME != null;
    }

    static {
        CLASS_CLIENTBOUND_ENTITY_SOUND = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findNmsClassName((String)"PacketPlayOutEntitySound"), MinecraftReflection.findMcClassName((String)"network.protocol.game.PacketPlayOutEntitySound"), MinecraftReflection.findMcClassName((String)"network.protocol.game.ClientboundSoundEntityPacket")});
        CLASS_SOUND_SOURCE = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findNmsClassName((String)"SoundCategory"), MinecraftReflection.findMcClassName((String)"sounds.SoundCategory"), MinecraftReflection.findMcClassName((String)"sounds.SoundSource")});
        CLASS_SOUND_EVENT = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findNmsClassName((String)"SoundEffect"), MinecraftReflection.findMcClassName((String)"sounds.SoundEffect"), MinecraftReflection.findMcClassName((String)"sounds.SoundEvent")});
        MethodHandle soundSourceGetName = null;
        if (CLASS_SOUND_SOURCE != null) {
            for (Method method : CLASS_SOUND_SOURCE.getDeclaredMethods()) {
                if (!method.getReturnType().equals(String.class) || method.getParameterCount() != 0 || "name".equals(method.getName()) || !Modifier.isPublic(method.getModifiers())) continue;
                try {
                    soundSourceGetName = MinecraftReflection.lookup().unreflect(method);
                }
                catch (IllegalAccessException illegalAccessException) {}
                break;
            }
        }
        SOUND_SOURCE_GET_NAME = soundSourceGetName;
    }
}
