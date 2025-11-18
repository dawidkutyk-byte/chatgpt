/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ZX\u00f3v
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audiences
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.ForwardingAudience
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.MessageType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.SignedMessage
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identified
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.inventory.Book
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackCallback
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackRequest
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundStop
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.title.TitlePart
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.audience;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audiences;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.ForwardingAudience;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.MessageType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.SignedMessage;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identified;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.inventory.Book;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackCallback;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackRequest;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundStop;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.title.TitlePart;

public static interface ForwardingAudience.Single
extends ForwardingAudience {
    default public void showDialog(@NotNull ZX\u00f3v dialog) {
        this.audience().showDialog(dialog);
    }

    default public void playSound(@NotNull Sound sound, double x, double y, double z) {
        this.audience().playSound(sound, x, y, z);
    }

    default public <T> @UnknownNullability T getOrDefaultFrom(@NotNull Pointer<T> pointer, @NotNull Supplier<? extends T> defaultValue) {
        return (T)this.audience().getOrDefaultFrom(pointer, defaultValue);
    }

    default public void removeResourcePacks(@NotNull Iterable<UUID> ids) {
        this.audience().removeResourcePacks(ids);
    }

    default public void forEachAudience(@NotNull Consumer<? super Audience> action) {
        this.audience().forEachAudience(action);
    }

    default public void playSound(@NotNull Sound sound, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Sound.Emitter emitter) {
        this.audience().playSound(sound, emitter);
    }

    @NotNull
    @ApiStatus.OverrideOnly
    public Audience audience();

    default public void sendPlayerListHeader(@NotNull Component header) {
        this.audience().sendPlayerListHeader(header);
    }

    default public void deleteMessage(// Could not load outer class - annotation placement on inner may be incorrect
    @NotNull SignedMessage.Signature signature) {
        this.audience().deleteMessage(signature);
    }

    default public void sendPlayerListFooter(@NotNull Component footer) {
        this.audience().sendPlayerListFooter(footer);
    }

    default public void sendActionBar(@NotNull Component message) {
        this.audience().sendActionBar(message);
    }

    default public void hideBossBar(@NotNull BossBar bar) {
        this.audience().hideBossBar(bar);
    }

    default public void sendMessage(@NotNull Component message) {
        this.audience().sendMessage(message);
    }

    default public void clearResourcePacks() {
        this.audience().clearResourcePacks();
    }

    default public void sendPlayerListHeaderAndFooter(@NotNull Component header, @NotNull Component footer) {
        this.audience().sendPlayerListHeaderAndFooter(header, footer);
    }

    @Deprecated
    @NotNull
    default public Iterable<? extends Audience> audiences() {
        return Collections.singleton(this.audience());
    }

    default public void resetTitle() {
        this.audience().resetTitle();
    }

    default public <T> void sendTitlePart(@NotNull TitlePart<T> part, @NotNull T value) {
        this.audience().sendTitlePart(part, value);
    }

    @NotNull
    default public Audience filterAudience(@NotNull Predicate<? super Audience> filter) {
        Audience audience = this.audience();
        return filter.test((Audience)audience) ? this : Audience.empty();
    }

    default public void clearTitle() {
        this.audience().clearTitle();
    }

    @NotNull
    default public Pointers pointers() {
        return this.audience().pointers();
    }

    default public void sendMessage(@NotNull Component message, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull ChatType.Bound boundChatType) {
        this.audience().sendMessage(message, boundChatType);
    }

    default public void playSound(@NotNull Sound sound) {
        this.audience().playSound(sound);
    }

    default public void showBossBar(@NotNull BossBar bar) {
        this.audience().showBossBar(bar);
    }

    default public void stopSound(@NotNull SoundStop stop) {
        this.audience().stopSound(stop);
    }

    @Deprecated
    default public void sendMessage(@NotNull Identity source, @NotNull Component message, @NotNull MessageType type) {
        this.audience().sendMessage(source, message, type);
    }

    default public void sendResourcePacks(@NotNull ResourcePackRequest request) {
        this.audience().sendResourcePacks(request.callback(Audiences.unwrapCallback((Audience)this, (Audience)this.audience(), (ResourcePackCallback)request.callback())));
    }

    @Deprecated
    default public void sendMessage(@NotNull Identified source, @NotNull Component message, @NotNull MessageType type) {
        this.audience().sendMessage(source, message, type);
    }

    default public void sendMessage(@NotNull SignedMessage signedMessage, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull ChatType.Bound boundChatType) {
        this.audience().sendMessage(signedMessage, boundChatType);
    }

    @NotNull
    default public <T> Optional<T> get(@NotNull Pointer<T> pointer) {
        return this.audience().get(pointer);
    }

    default public void removeResourcePacks(@NotNull UUID id, UUID ... others) {
        this.audience().removeResourcePacks(id, others);
    }

    default public void openBook(@NotNull Book book) {
        this.audience().openBook(book);
    }

    @Contract(value="_, null -> null; _, !null -> !null")
    @Nullable
    default public <T> T getOrDefault(@NotNull Pointer<T> pointer, @Nullable T defaultValue) {
        return (T)this.audience().getOrDefault(pointer, defaultValue);
    }
}
