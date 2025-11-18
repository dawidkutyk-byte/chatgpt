/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.ChatType$Bound
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitAccess
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitAccess$Chat1_19_3
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$Chat
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.util.NoSuchElementException;
import java.util.Optional;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.ChatType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitAccess;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;

static class CraftBukkitFacet.Chat1_19_3
extends CraftBukkitFacet.Chat {
    public boolean isSupported() {
        return super.isSupported() && CraftBukkitAccess.Chat1_19_3.isSupported();
    }

    public void sendMessage(@NotNull CommandSender viewer, @NotNull Identity source, @NotNull Object message, @NotNull Object type) {
        if (!(type instanceof ChatType.Bound)) {
            super.sendMessage(viewer, source, message, type);
        } else {
            ChatType.Bound bound = (ChatType.Bound)type;
            try {
                Object boundNetwork;
                Object nameComponent = this.createMessage(viewer, bound.name());
                Object targetComponent = bound.target() != null ? this.createMessage(viewer, bound.target()) : null;
                Object registryAccess = CraftBukkitAccess.Chat1_19_3.ACTUAL_GET_REGISTRY_ACCESS.invoke(CraftBukkitAccess.Chat1_19_3.SERVER_PLAYER_GET_LEVEL.invoke(CRAFT_PLAYER_GET_HANDLE.invoke(viewer)));
                Object chatTypeRegistry = CraftBukkitAccess.Chat1_19_3.REGISTRY_ACCESS_GET_REGISTRY_OPTIONAL.invoke(registryAccess, CraftBukkitAccess.Chat1_19_3.CHAT_TYPE_RESOURCE_KEY).orElseThrow(NoSuchElementException::new);
                Object typeResourceLocation = CraftBukkitAccess.NEW_RESOURCE_LOCATION.invoke(bound.type().key().namespace(), bound.type().key().value());
                if (CraftBukkitAccess.Chat1_19_3.CHAT_TYPE_BOUND_NETWORK_CONSTRUCTOR != null) {
                    Object chatTypeObject = CraftBukkitAccess.Chat1_19_3.REGISTRY_GET_OPTIONAL.invoke(chatTypeRegistry, typeResourceLocation).orElseThrow(NoSuchElementException::new);
                    int networkId = CraftBukkitAccess.Chat1_19_3.REGISTRY_GET_ID.invoke(chatTypeRegistry, chatTypeObject);
                    if (networkId < 0) {
                        throw new IllegalArgumentException("Could not get a valid network id from " + type);
                    }
                    boundNetwork = CraftBukkitAccess.Chat1_19_3.CHAT_TYPE_BOUND_NETWORK_CONSTRUCTOR.invoke(networkId, nameComponent, targetComponent);
                } else {
                    Object chatTypeHolder = CraftBukkitAccess.Chat1_19_3.REGISTRY_GET_HOLDER.invoke(chatTypeRegistry, typeResourceLocation).orElseThrow(NoSuchElementException::new);
                    boundNetwork = CraftBukkitAccess.Chat1_19_3.CHAT_TYPE_BOUND_CONSTRUCTOR.invoke(chatTypeHolder, nameComponent, Optional.ofNullable(targetComponent));
                }
                this.sendMessage(viewer, CraftBukkitAccess.Chat1_19_3.DISGUISED_CHAT_PACKET_CONSTRUCTOR.invoke(message, boundNetwork));
            }
            catch (Throwable error) {
                Knob.logError((Throwable)error, (String)"Failed to send a 1.19.3+ message: %s %s", (Object[])new Object[]{message, type});
            }
        }
    }

    CraftBukkitFacet.Chat1_19_3() {
    }
}
