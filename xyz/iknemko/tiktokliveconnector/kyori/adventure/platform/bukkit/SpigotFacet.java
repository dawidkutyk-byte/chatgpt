/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetBase
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetBase;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer;

class SpigotFacet<V extends CommandSender>
extends FacetBase<V> {
    static final Class<?> BUNGEE_COMPONENT_TYPE;
    private static final boolean SUPPORTED;
    private static final Class<?> BUNGEE_CHAT_MESSAGE_TYPE;

    protected SpigotFacet(@Nullable Class<? extends V> viewerClass) {
        super(viewerClass);
    }

    static /* synthetic */ Class access$000() {
        return BUNGEE_CHAT_MESSAGE_TYPE;
    }

    public boolean isSupported() {
        return super.isSupported() && SUPPORTED;
    }

    static {
        SUPPORTED = Knob.isEnabled((String)"spigot", (boolean)true) && BungeeComponentSerializer.isNative();
        BUNGEE_CHAT_MESSAGE_TYPE = MinecraftReflection.findClass((String[])new String[]{"net.md_5.bungee.api.ChatMessageType"});
        BUNGEE_COMPONENT_TYPE = MinecraftReflection.findClass((String[])new String[]{"net.md_5.bungee.api.chat.BaseComponent"});
    }
}
