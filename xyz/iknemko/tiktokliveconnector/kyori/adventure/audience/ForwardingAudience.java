/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ZX\u00f3v
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.MessageType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.SignedMessage
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identified
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.inventory.Book
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackRequest
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundStop
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.title.TitlePart
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.audience;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.MessageType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.SignedMessage;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identified;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.inventory.Book;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackRequest;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundStop;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.title.TitlePart;

@FunctionalInterface
public interface ForwardingAudience
extends Audience {
    default public void showDialog(@NotNull ZX\u00f3v dialog) {
        Iterator<? extends Audience> iterator = this.audiences().iterator();
        while (iterator.hasNext()) {
            Audience audience = iterator.next();
            audience.showDialog(dialog);
        }
    }

    default public void showBossBar(@NotNull BossBar bar) {
        Iterator<? extends Audience> iterator = this.audiences().iterator();
        while (iterator.hasNext()) {
            Audience audience = iterator.next();
            audience.showBossBar(bar);
        }
    }

    default public void playSound(@NotNull Sound sound, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Sound.Emitter emitter) {
        Iterator<? extends Audience> iterator = this.audiences().iterator();
        while (iterator.hasNext()) {
            Audience audience = iterator.next();
            audience.playSound(sound, emitter);
        }
    }

    default public void sendPlayerListHeader(@NotNull Component header) {
        Iterator<? extends Audience> iterator = this.audiences().iterator();
        while (iterator.hasNext()) {
            Audience audience = iterator.next();
            audience.sendPlayerListHeader(header);
        }
    }

    default public void playSound(@NotNull Sound sound) {
        Iterator<? extends Audience> iterator = this.audiences().iterator();
        while (iterator.hasNext()) {
            Audience audience = iterator.next();
            audience.playSound(sound);
        }
    }

    default public void resetTitle() {
        Iterator<? extends Audience> iterator = this.audiences().iterator();
        while (iterator.hasNext()) {
            Audience audience = iterator.next();
            audience.resetTitle();
        }
    }

    default public void sendMessage(@NotNull Component message) {
        Iterator<? extends Audience> iterator = this.audiences().iterator();
        while (iterator.hasNext()) {
            Audience audience = iterator.next();
            audience.sendMessage(message);
        }
    }

    default public void stopSound(@NotNull SoundStop stop) {
        Iterator<? extends Audience> iterator = this.audiences().iterator();
        while (iterator.hasNext()) {
            Audience audience = iterator.next();
            audience.stopSound(stop);
        }
    }

    default public void openBook(@NotNull Book book) {
        Iterator<? extends Audience> iterator = this.audiences().iterator();
        while (iterator.hasNext()) {
            Audience audience = iterator.next();
            audience.openBook(book);
        }
    }

    default public void clearTitle() {
        Iterator<? extends Audience> iterator = this.audiences().iterator();
        while (iterator.hasNext()) {
            Audience audience = iterator.next();
            audience.clearTitle();
        }
    }

    default public void sendPlayerListHeaderAndFooter(@NotNull Component header, @NotNull Component footer) {
        Iterator<? extends Audience> iterator = this.audiences().iterator();
        while (iterator.hasNext()) {
            Audience audience = iterator.next();
            audience.sendPlayerListHeaderAndFooter(header, footer);
        }
    }

    @NotNull
    @ApiStatus.OverrideOnly
    public Iterable<? extends Audience> audiences();

    default public void sendPlayerListFooter(@NotNull Component footer) {
        Iterator<? extends Audience> iterator = this.audiences().iterator();
        while (iterator.hasNext()) {
            Audience audience = iterator.next();
            audience.sendPlayerListFooter(footer);
        }
    }

    default public <T> void sendTitlePart(@NotNull TitlePart<T> part, @NotNull T value) {
        Iterator<? extends Audience> iterator = this.audiences().iterator();
        while (iterator.hasNext()) {
            Audience audience = iterator.next();
            audience.sendTitlePart(part, value);
        }
    }

    @Deprecated
    default public void sendMessage(@NotNull Identity source, @NotNull Component message, @NotNull MessageType type) {
        Iterator<? extends Audience> iterator = this.audiences().iterator();
        while (iterator.hasNext()) {
            Audience audience = iterator.next();
            audience.sendMessage(source, message, type);
        }
    }

    default public void sendActionBar(@NotNull Component message) {
        Iterator<? extends Audience> iterator = this.audiences().iterator();
        while (iterator.hasNext()) {
            Audience audience = iterator.next();
            audience.sendActionBar(message);
        }
    }

    default public void forEachAudience(@NotNull Consumer<? super Audience> action) {
        Iterator<? extends Audience> iterator = this.audiences().iterator();
        while (iterator.hasNext()) {
            Audience audience = iterator.next();
            audience.forEachAudience(action);
        }
    }

    default public void removeResourcePacks(@NotNull UUID id, UUID ... others) {
        Iterator<? extends Audience> iterator = this.audiences().iterator();
        while (iterator.hasNext()) {
            Audience audience = iterator.next();
            audience.removeResourcePacks(id, others);
        }
    }

    default public void sendResourcePacks(@NotNull ResourcePackRequest request) {
        Iterator<? extends Audience> iterator = this.audiences().iterator();
        while (iterator.hasNext()) {
            Audience audience = iterator.next();
            audience.sendResourcePacks(request);
        }
    }

    default public void deleteMessage(// Could not load outer class - annotation placement on inner may be incorrect
    @NotNull SignedMessage.Signature signature) {
        Iterator<? extends Audience> iterator = this.audiences().iterator();
        while (iterator.hasNext()) {
            Audience audience = iterator.next();
            audience.deleteMessage(signature);
        }
    }

    default public void clearResourcePacks() {
        Iterator<? extends Audience> iterator = this.audiences().iterator();
        while (iterator.hasNext()) {
            Audience audience = iterator.next();
            audience.clearResourcePacks();
        }
    }

    default public void removeResourcePacks(@NotNull Iterable<UUID> ids) {
        Iterator<? extends Audience> iterator = this.audiences().iterator();
        while (iterator.hasNext()) {
            Audience audience = iterator.next();
            audience.removeResourcePacks(ids);
        }
    }

    @NotNull
    default public Pointers pointers() {
        return Pointers.empty();
    }

    default public void hideBossBar(@NotNull BossBar bar) {
        Iterator<? extends Audience> iterator = this.audiences().iterator();
        while (iterator.hasNext()) {
            Audience audience = iterator.next();
            audience.hideBossBar(bar);
        }
    }

    default public void playSound(@NotNull Sound sound, double x, double y, double z) {
        Iterator<? extends Audience> iterator = this.audiences().iterator();
        while (iterator.hasNext()) {
            Audience audience = iterator.next();
            audience.playSound(sound, x, y, z);
        }
    }

    @NotNull
    default public Audience filterAudience(@NotNull Predicate<? super Audience> filter) {
        @Nullable ArrayList<Audience> audiences = null;
        for (Audience audience : this.audiences()) {
            Audience filtered;
            if (!filter.test((Audience)audience) || (filtered = audience.filterAudience(filter)) == Audience.empty()) continue;
            if (audiences == null) {
                audiences = new ArrayList<Audience>();
            }
            audiences.add(filtered);
        }
        return audiences != null ? Audience.audience(audiences) : Audience.empty();
    }

    default public void sendMessage(@NotNull Component message, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull ChatType.Bound boundChatType) {
        Iterator<? extends Audience> iterator = this.audiences().iterator();
        while (iterator.hasNext()) {
            Audience audience = iterator.next();
            audience.sendMessage(message, boundChatType);
        }
    }

    default public void sendMessage(@NotNull SignedMessage signedMessage, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull ChatType.Bound boundChatType) {
        Iterator<? extends Audience> iterator = this.audiences().iterator();
        while (iterator.hasNext()) {
            Audience audience = iterator.next();
            audience.sendMessage(signedMessage, boundChatType);
        }
    }

    @Deprecated
    default public void sendMessage(@NotNull Identified source, @NotNull Component message, @NotNull MessageType type) {
        Iterator<? extends Audience> iterator = this.audiences().iterator();
        while (iterator.hasNext()) {
            Audience audience = iterator.next();
            audience.sendMessage(source, message, type);
        }
    }
}
