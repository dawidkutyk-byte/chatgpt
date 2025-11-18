/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.ChatTypeImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.ChatTypeImpl$BoundImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Keyed
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.chat;

import java.util.Objects;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.ChatTypeImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Keyed;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;

public interface ChatType
extends Examinable,
Keyed {
    public static final ChatType SAY_COMMAND;
    public static final ChatType MSG_COMMAND_OUTGOING;
    public static final ChatType TEAM_MSG_COMMAND_OUTGOING;
    public static final ChatType EMOTE_COMMAND;
    public static final ChatType CHAT;
    public static final ChatType TEAM_MSG_COMMAND_INCOMING;
    public static final ChatType MSG_COMMAND_INCOMING;

    @NotNull
    default public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"key", (Object)this.key()));
    }

    @NotNull
    public static ChatType chatType(@NotNull Keyed key) {
        return key instanceof ChatType ? (ChatType)key : new ChatTypeImpl(Objects.requireNonNull(key, "key").key());
    }

    @Contract(value="_ -> new", pure=true)
    default public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull ChatType.Bound bind(@NotNull ComponentLike name) {
        return this.bind(name, null);
    }

    static {
        CHAT = new ChatTypeImpl(Key.key((String)"chat"));
        SAY_COMMAND = new ChatTypeImpl(Key.key((String)"say_command"));
        MSG_COMMAND_INCOMING = new ChatTypeImpl(Key.key((String)"msg_command_incoming"));
        MSG_COMMAND_OUTGOING = new ChatTypeImpl(Key.key((String)"msg_command_outgoing"));
        TEAM_MSG_COMMAND_INCOMING = new ChatTypeImpl(Key.key((String)"team_msg_command_incoming"));
        TEAM_MSG_COMMAND_OUTGOING = new ChatTypeImpl(Key.key((String)"team_msg_command_outgoing"));
        EMOTE_COMMAND = new ChatTypeImpl(Key.key((String)"emote_command"));
    }

    @Contract(value="_, _ -> new", pure=true)
    default public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull ChatType.Bound bind(@NotNull ComponentLike name, @Nullable ComponentLike target) {
        return new ChatTypeImpl.BoundImpl(this, Objects.requireNonNull(name.asComponent(), "name"), ComponentLike.unbox((ComponentLike)target));
    }
}
