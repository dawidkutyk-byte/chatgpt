/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.ChatType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.ChatType$Bound
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.chat;

import net.kyori.examination.Examinable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.ChatType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;

static final class ChatTypeImpl.BoundImpl
implements ChatType.Bound {
    private final ChatType chatType;
    private final Component name;
    @Nullable
    private final Component target;

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    @NotNull
    public Component name() {
        return this.name;
    }

    ChatTypeImpl.BoundImpl(ChatType chatType, Component name, @Nullable Component target) {
        this.chatType = chatType;
        this.name = name;
        this.target = target;
    }

    @Nullable
    public Component target() {
        return this.target;
    }

    @NotNull
    public ChatType type() {
        return this.chatType;
    }
}
