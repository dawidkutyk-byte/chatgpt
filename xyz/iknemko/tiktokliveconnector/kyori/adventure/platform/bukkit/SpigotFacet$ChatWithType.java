/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.ChatMessageType
 *  net.md_5.bungee.api.chat.BaseComponent
 *  org.bukkit.entity.Player
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.MessageType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.SpigotFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.SpigotFacet$Message
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Chat
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.MessageType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.SpigotFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;

/*
 * Exception performing whole class analysis ignored.
 */
static class SpigotFacet.ChatWithType
extends SpigotFacet.Message<Player>
implements Facet.Chat<Player, BaseComponent[]> {
    private static final boolean SUPPORTED;
    private static final Class<?> PLAYER_CLASS;

    public boolean isSupported() {
        return super.isSupported() && SUPPORTED;
    }

    static {
        PLAYER_CLASS = MinecraftReflection.findClass((String[])new String[]{"org.bukkit.entity.Player$Spigot"});
        SUPPORTED = MinecraftReflection.hasMethod(PLAYER_CLASS, (String)"sendMessage", (Class[])new Class[]{SpigotFacet.access$000(), BUNGEE_COMPONENT_TYPE});
    }

    public void sendMessage(@NotNull Player viewer, @NotNull Identity source, BaseComponent @NotNull [] message, @NotNull Object type) {
        ChatMessageType chat = type instanceof MessageType ? this.createType((MessageType)type) : ChatMessageType.SYSTEM;
        if (chat == null) return;
        viewer.spigot().sendMessage(chat, message);
    }

    @Nullable
    private ChatMessageType createType(@NotNull MessageType type) {
        if (type == MessageType.CHAT) {
            return ChatMessageType.CHAT;
        }
        if (type == MessageType.SYSTEM) {
            return ChatMessageType.SYSTEM;
        }
        Knob.logUnsupported((Object)((Object)this), (Object)type);
        return null;
    }

    protected SpigotFacet.ChatWithType() {
        super(Player.class);
    }
}
