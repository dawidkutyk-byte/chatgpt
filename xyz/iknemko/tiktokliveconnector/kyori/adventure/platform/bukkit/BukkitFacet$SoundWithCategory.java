/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.SoundCategory
 *  org.bukkit.entity.Player
 *  org.bukkit.util.Vector
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet$Sound
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound$Source
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundStop
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundStop;

static class BukkitFacet.SoundWithCategory
extends BukkitFacet.Sound {
    private static final boolean SUPPORTED = MinecraftReflection.hasMethod(Player.class, (String)"stopSound", (Class[])new Class[]{String.class, MinecraftReflection.findClass((String[])new String[]{"org.bukkit.SoundCategory"})});

    BukkitFacet.SoundWithCategory() {
    }

    public void playSound(@NotNull Player viewer, @NotNull Sound sound, @NotNull Vector vector) {
        SoundCategory category = this.category(sound.source());
        if (category == null) {
            super.playSound(viewer, sound, vector);
        } else {
            String name = BukkitFacet.SoundWithCategory.name((Key)sound.name());
            viewer.playSound(vector.toLocation(viewer.getWorld()), name, category, sound.volume(), sound.pitch());
        }
    }

    public void stopSound(@NotNull Player viewer, @NotNull SoundStop stop) {
        SoundCategory category = this.category(stop.source());
        if (category == null) {
            super.stopSound(viewer, stop);
        } else {
            String name = BukkitFacet.SoundWithCategory.name((Key)stop.sound());
            viewer.stopSound(name, category);
        }
    }

    public boolean isSupported() {
        return super.isSupported() && SUPPORTED;
    }

    @Nullable
    private SoundCategory category(// Could not load outer class - annotation placement on inner may be incorrect
    @Nullable Sound.Source source) {
        if (source == null) {
            return null;
        }
        if (source == Sound.Source.MASTER) {
            return SoundCategory.MASTER;
        }
        if (source == Sound.Source.MUSIC) {
            return SoundCategory.MUSIC;
        }
        if (source == Sound.Source.RECORD) {
            return SoundCategory.RECORDS;
        }
        if (source == Sound.Source.WEATHER) {
            return SoundCategory.WEATHER;
        }
        if (source == Sound.Source.BLOCK) {
            return SoundCategory.BLOCKS;
        }
        if (source == Sound.Source.HOSTILE) {
            return SoundCategory.HOSTILE;
        }
        if (source == Sound.Source.NEUTRAL) {
            return SoundCategory.NEUTRAL;
        }
        if (source == Sound.Source.PLAYER) {
            return SoundCategory.PLAYERS;
        }
        if (source == Sound.Source.AMBIENT) {
            return SoundCategory.AMBIENT;
        }
        if (source == Sound.Source.VOICE) {
            return SoundCategory.VOICE;
        }
        Knob.logUnsupported((Object)((Object)this), (Object)source);
        return null;
    }
}
