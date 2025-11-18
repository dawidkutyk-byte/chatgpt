/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.ChatType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.chat;

import net.kyori.examination.Examinable;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.ChatType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;

final class ChatTypeImpl
implements ChatType {
    private final Key key;

    @NotNull
    public Key key() {
        return this.key;
    }

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    ChatTypeImpl(@NotNull Key key) {
        this.key = key;
    }
}
