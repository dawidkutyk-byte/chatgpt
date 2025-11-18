/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.MessageType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Chat
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.MessageType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;

public static interface Facet.ChatPacket<V, M>
extends Facet.Chat<V, M> {
    public static final byte TYPE_ACTION_BAR = 2;
    public static final byte TYPE_CHAT = 0;
    public static final byte TYPE_SYSTEM = 1;

    default public byte createMessageType(@NotNull MessageType type) {
        if (type == MessageType.CHAT) {
            return 0;
        }
        if (type == MessageType.SYSTEM) {
            return 1;
        }
        Knob.logUnsupported((Object)this, (Object)type);
        return 0;
    }
}
