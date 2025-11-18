/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.viaversion.viaversion.api.connection.UserConnection
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.MessageType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$ActionBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion.ViaFacet$Chat
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion;

import com.viaversion.viaversion.api.connection.UserConnection;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.MessageType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion.ViaFacet;

public static class ViaFacet.ActionBar<V>
extends ViaFacet.Chat<V>
implements Facet.ActionBar<V, String> {
    public byte createMessageType(@NotNull MessageType type) {
        return 2;
    }

    public ViaFacet.ActionBar(@NotNull Class<? extends V> viewerClass, @NotNull Function<V, UserConnection> connectionFunction) {
        super(viewerClass, connectionFunction);
    }

    public void sendMessage(@NotNull V viewer, @NotNull String message) {
        this.sendMessage(viewer, Identity.nil(), message, MessageType.CHAT);
    }
}
