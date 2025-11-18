/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.SignedMessage
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.SignedMessage$Signature
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.chat;

import java.security.SecureRandom;
import java.time.Instant;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.SignedMessage;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;

final class SignedMessageImpl
implements SignedMessage {
    private final long salt;
    static final SecureRandom RANDOM = new SecureRandom();
    private final Instant instant = Instant.now();
    private final Component unsignedContent;
    private final String message;

    @NotNull
    public Identity identity() {
        return Identity.nil();
    }

    @Nullable
    public Component unsignedContent() {
        return this.unsignedContent;
    }

    @NotNull
    public String message() {
        return this.message;
    }

    public SignedMessage.Signature signature() {
        return null;
    }

    SignedMessageImpl(String message, Component unsignedContent) {
        this.salt = RANDOM.nextLong();
        this.message = message;
        this.unsignedContent = unsignedContent;
    }

    @NotNull
    public Instant timestamp() {
        return this.instant;
    }

    public long salt() {
        return this.salt;
    }
}
