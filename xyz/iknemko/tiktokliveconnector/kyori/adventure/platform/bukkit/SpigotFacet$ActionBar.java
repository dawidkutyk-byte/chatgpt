/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.ChatMessageType
 *  net.md_5.bungee.api.chat.BaseComponent
 *  org.bukkit.entity.Player
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.SpigotFacet$ChatWithType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$ActionBar
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.SpigotFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;

static final class SpigotFacet.ActionBar
extends SpigotFacet.ChatWithType
implements Facet.ActionBar<Player, BaseComponent[]> {
    public void sendMessage(@NotNull Player viewer, BaseComponent @NotNull [] message) {
        viewer.spigot().sendMessage(ChatMessageType.ACTION_BAR, message);
    }

    SpigotFacet.ActionBar() {
    }
}
