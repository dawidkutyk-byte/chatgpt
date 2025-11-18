/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.chat.BaseComponent
 *  org.bukkit.command.CommandSender
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.SpigotFacet$Message
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Chat
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.SpigotFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;

static final class SpigotFacet.Chat
extends SpigotFacet.Message<CommandSender>
implements Facet.Chat<CommandSender, BaseComponent[]> {
    private static final boolean SUPPORTED = MinecraftReflection.hasClass((String[])new String[]{"org.bukkit.command.CommandSender$Spigot"});

    public void sendMessage(@NotNull CommandSender viewer, @NotNull Identity source, BaseComponent @NotNull [] message, @NotNull Object type) {
        viewer.spigot().sendMessage(message);
    }

    public boolean isSupported() {
        return super.isSupported() && SUPPORTED;
    }

    protected SpigotFacet.Chat() {
        super(CommandSender.class);
    }
}
