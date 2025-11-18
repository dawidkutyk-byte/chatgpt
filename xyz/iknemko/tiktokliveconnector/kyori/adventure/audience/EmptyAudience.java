/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.MessageType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.SignedMessage
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identified
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfoLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackRequest
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.audience;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.MessageType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.SignedMessage;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identified;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfoLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackRequest;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;

final class EmptyAudience
implements Audience {
    static final EmptyAudience INSTANCE = new EmptyAudience();

    public void removeResourcePacks(@NotNull ResourcePackRequest request) {
    }

    public void sendMessage(@NotNull ComponentLike message) {
    }

    @Deprecated
    public void sendMessage(@NotNull Identified source, @NotNull Component message, @NotNull MessageType type) {
    }

    public int hashCode() {
        return 0;
    }

    public void sendPlayerListFooter(@NotNull ComponentLike footer) {
    }

    public void sendPlayerListHeader(@NotNull ComponentLike header) {
    }

    public void sendMessage(@NotNull SignedMessage signedMessage, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull ChatType.Bound boundChatType) {
    }

    public <T> @UnknownNullability T getOrDefaultFrom(@NotNull Pointer<T> pointer, @NotNull Supplier<? extends T> defaultValue) {
        return defaultValue.get();
    }

    public void sendMessage(@NotNull Component message) {
    }

    public void sendResourcePacks(@NotNull ResourcePackInfoLike request, ResourcePackInfoLike ... others) {
    }

    @Deprecated
    public void sendMessage(@NotNull Identity source, @NotNull Component message, @NotNull MessageType type) {
    }

    public void sendMessage(@NotNull Component message, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull ChatType.Bound boundChatType) {
    }

    @NotNull
    public <T> Optional<T> get(@NotNull Pointer<T> pointer) {
        return Optional.empty();
    }

    public void sendPlayerListHeaderAndFooter(@NotNull ComponentLike header, @NotNull ComponentLike footer) {
    }

    EmptyAudience() {
    }

    @NotNull
    public Audience filterAudience(@NotNull Predicate<? super Audience> filter) {
        return this;
    }

    @Nullable
    @Contract(value="_, null -> null; _, !null -> !null")
    public <T> T getOrDefault(@NotNull Pointer<T> pointer, @Nullable T defaultValue) {
        return defaultValue;
    }

    public String toString() {
        return "EmptyAudience";
    }

    public void sendActionBar(@NotNull ComponentLike message) {
    }

    public boolean equals(Object that) {
        return this == that;
    }

    public void deleteMessage(// Could not load outer class - annotation placement on inner may be incorrect
    @NotNull SignedMessage.Signature signature) {
    }

    public void removeResourcePacks(@NotNull ResourcePackInfoLike request, ResourcePackInfoLike ... others) {
    }

    public void openBook(// Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Book.Builder book) {
    }

    public void forEachAudience(@NotNull Consumer<? super Audience> action) {
    }
}
