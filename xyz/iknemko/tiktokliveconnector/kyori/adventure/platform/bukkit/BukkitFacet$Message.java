/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Message
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;

static class BukkitFacet.Message<V extends CommandSender>
extends BukkitFacet<V>
implements Facet.Message<V, String> {
    protected BukkitFacet.Message(@Nullable Class<? extends V> viewerClass) {
        super(viewerClass);
    }

    @NotNull
    public String createMessage(@NotNull V viewer, @NotNull Component message) {
        return BukkitComponentSerializer.legacy().serialize(message);
    }
}
