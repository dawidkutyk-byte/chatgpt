/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.entity.Player
 *  org.bukkit.util.Vector
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet$Position
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Sound
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundStop
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.lang.invoke.MethodHandle;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundStop;

static class BukkitFacet.Sound
extends BukkitFacet.Position
implements Facet.Sound<Player, Vector> {
    private static final boolean STOP_SUPPORTED;
    private static final boolean KEY_SUPPORTED;
    private static final MethodHandle STOP_ALL_SUPPORTED;

    BukkitFacet.Sound() {
    }

    static {
        KEY_SUPPORTED = MinecraftReflection.hasClass((String[])new String[]{"org.bukkit.NamespacedKey"});
        STOP_SUPPORTED = MinecraftReflection.hasMethod(Player.class, (String)"stopSound", (Class[])new Class[]{String.class});
        STOP_ALL_SUPPORTED = MinecraftReflection.findMethod(Player.class, (String)"stopAllSounds", Void.TYPE, (Class[])new Class[0]);
    }

    public void playSound(@NotNull Player viewer, @NotNull Sound sound, @NotNull Vector vector) {
        String name = BukkitFacet.Sound.name(sound.name());
        Location location = vector.toLocation(viewer.getWorld());
        viewer.playSound(location, name, sound.volume(), sound.pitch());
    }

    public void stopSound(@NotNull Player viewer, @NotNull SoundStop stop) {
        if (!STOP_SUPPORTED) return;
        String name = BukkitFacet.Sound.name(stop.sound());
        if (name.isEmpty() && STOP_ALL_SUPPORTED != null) {
            try {
                STOP_ALL_SUPPORTED.invoke(viewer);
            }
            catch (Throwable error) {
                Knob.logError((Throwable)error, (String)"Could not invoke stopAllSounds on %s", (Object[])new Object[]{viewer});
            }
            return;
        }
        viewer.stopSound(name);
    }

    @NotNull
    protected static String name(@Nullable Key name) {
        if (name == null) {
            return "";
        }
        if (!KEY_SUPPORTED) return name.value();
        return name.asString();
    }
}
