/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitEmitter
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitAccess$EntitySound
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$EntitySound
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound$Source
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitEmitter;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitAccess;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound;

/*
 * Exception performing whole class analysis ignored.
 */
private static interface CraftBukkitFacet.PartialEntitySound
extends Facet.EntitySound<Player, Object> {
    public static final Map<String, Object> MC_SOUND_SOURCE_BY_NAME = new ConcurrentHashMap<String, Object>();

    default public Object createForSelf(Player viewer, @NotNull Sound sound) {
        return this.createForEntity(sound, (Entity)viewer);
    }

    public Object createForEntity(Sound var1, Entity var2);

    default public Object toNativeEntity(Entity entity) throws Throwable {
        if (CraftBukkitFacet.access$900().isInstance(entity)) return CraftBukkitFacet.access$1000().invoke(entity);
        return null;
    }

    default public Object toVanilla(Sound.Source source) throws Throwable {
        if (!MC_SOUND_SOURCE_BY_NAME.isEmpty()) return MC_SOUND_SOURCE_BY_NAME.get(Sound.Source.NAMES.key((Object)source));
        T[] TArray = CraftBukkitAccess.EntitySound.CLASS_SOUND_SOURCE.getEnumConstants();
        int n = TArray.length;
        int n2 = 0;
        while (n2 < n) {
            Object enumConstant = TArray[n2];
            MC_SOUND_SOURCE_BY_NAME.put(CraftBukkitAccess.EntitySound.SOUND_SOURCE_GET_NAME.invoke(enumConstant), enumConstant);
            ++n2;
        }
        return MC_SOUND_SOURCE_BY_NAME.get(Sound.Source.NAMES.key((Object)source));
    }

    default public Object createForEmitter(@NotNull Sound sound, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Sound.Emitter emitter) {
        Entity entity;
        if (emitter instanceof BukkitEmitter) {
            entity = ((BukkitEmitter)emitter).entity;
        } else {
            if (!(emitter instanceof Entity)) return null;
            entity = (Entity)emitter;
        }
        return this.createForEntity(sound, entity);
    }
}
