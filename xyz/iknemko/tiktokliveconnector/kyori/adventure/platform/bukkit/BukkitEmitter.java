/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Entity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound$Emitter
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import org.bukkit.entity.Entity;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound;

final class BukkitEmitter
implements Sound.Emitter {
    final Entity entity;

    BukkitEmitter(Entity entity) {
        this.entity = entity;
    }
}
