/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.AudienceProvider
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitAudiences$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitAudiencesImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitEmitter
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.util.function.Predicate;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.AudienceProvider;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitAudiences;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitAudiencesImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitEmitter;

public interface BukkitAudiences
extends AudienceProvider {
    public static // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Sound.Emitter asEmitter(@NotNull Entity entity) {
        return new BukkitEmitter(entity);
    }

    @NotNull
    public Audience player(@NotNull Player var1);

    @NotNull
    public Audience sender(@NotNull CommandSender var1);

    @NotNull
    public static BukkitAudiences create(@NotNull Plugin plugin) {
        return BukkitAudiencesImpl.instanceFor((Plugin)plugin);
    }

    @NotNull
    public Audience filter(@NotNull Predicate<CommandSender> var1);

    @NotNull
    public static Builder builder(@NotNull Plugin plugin) {
        return BukkitAudiencesImpl.builder((Plugin)plugin);
    }
}
