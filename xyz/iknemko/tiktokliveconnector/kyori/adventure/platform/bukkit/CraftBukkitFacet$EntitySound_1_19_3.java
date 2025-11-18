/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitAccess
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitAccess$EntitySound_1_19_3
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$PacketFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$PartialEntitySound
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitAccess;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound;

static class CraftBukkitFacet.EntitySound_1_19_3
extends CraftBukkitFacet.PacketFacet<Player>
implements CraftBukkitFacet.PartialEntitySound {
    public Object createForEntity(Sound sound, Entity entity) {
        try {
            Object resLoc = CraftBukkitAccess.NEW_RESOURCE_LOCATION.invoke(sound.name().namespace(), sound.name().value());
            Optional possibleSoundEvent = CraftBukkitAccess.EntitySound_1_19_3.REGISTRY_GET_OPTIONAL.invoke(CraftBukkitAccess.EntitySound_1_19_3.SOUND_EVENT_REGISTRY, resLoc);
            Object soundEvent = possibleSoundEvent.isPresent() ? possibleSoundEvent.get() : CraftBukkitAccess.EntitySound_1_19_3.SOUND_EVENT_CREATE_VARIABLE_RANGE.invoke(resLoc);
            Object soundEventHolder = CraftBukkitAccess.EntitySound_1_19_3.REGISTRY_WRAP_AS_HOLDER.invoke(CraftBukkitAccess.EntitySound_1_19_3.SOUND_EVENT_REGISTRY, soundEvent);
            long seed = sound.seed().orElseGet(() -> ThreadLocalRandom.current().nextLong());
            return CraftBukkitAccess.EntitySound_1_19_3.NEW_CLIENTBOUND_ENTITY_SOUND.invoke(soundEventHolder, this.toVanilla(sound.source()), this.toNativeEntity(entity), sound.volume(), sound.pitch(), seed);
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to send sound tracking an entity", (Object[])new Object[0]);
            return null;
        }
    }

    public boolean isSupported() {
        return CraftBukkitAccess.EntitySound_1_19_3.isSupported() && super.isSupported();
    }

    public void playSound(@NotNull Player viewer, Object packet) {
        this.sendPacket(viewer, packet);
    }

    CraftBukkitFacet.EntitySound_1_19_3() {
    }
}
