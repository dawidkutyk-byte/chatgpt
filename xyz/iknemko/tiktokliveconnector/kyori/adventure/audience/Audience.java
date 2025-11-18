/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ZX\u00f3v
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audiences
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.EmptyAudience
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.ForwardingAudience
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.ForwardingAudienceOverrideNotRequired
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.MessageType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.SignedMessage
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identified
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.inventory.Book
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointered
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfo
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfoLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackRequest
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackRequestLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundStop
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.title.Title
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.title.Title$Times
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.title.TitlePart
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.audience;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collector;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audiences;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.EmptyAudience;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.ForwardingAudience;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.ForwardingAudienceOverrideNotRequired;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.MessageType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.SignedMessage;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identified;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.inventory.Book;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointered;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfo;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfoLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackRequest;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackRequestLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundStop;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.title.Title;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.title.TitlePart;

public interface Audience
extends Pointered {
    @ForwardingAudienceOverrideNotRequired
    default public void sendPlayerListHeader(@NotNull ComponentLike header) {
        this.sendPlayerListHeader(header.asComponent());
    }

    @ForwardingAudienceOverrideNotRequired
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    default public void sendMessage(@NotNull Identified source, @NotNull ComponentLike message, @NotNull MessageType type) {
        this.sendMessage(source, message.asComponent(), type);
    }

    default public <T> void sendTitlePart(@NotNull TitlePart<T> part, @NotNull T value) {
    }

    @Deprecated
    @ForwardingAudienceOverrideNotRequired
    default public void sendMessage(@NotNull Identified source, @NotNull Component message) {
        this.sendMessage(source, message, MessageType.CHAT);
    }

    default public void clearResourcePacks() {
    }

    default public void showBossBar(@NotNull BossBar bar) {
    }

    default public void deleteMessage(// Could not load outer class - annotation placement on inner may be incorrect
    @NotNull SignedMessage.Signature signature) {
    }

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    default public void sendMessage(@NotNull Identity source, @NotNull Component message, @NotNull MessageType type) {
    }

    default public void sendPlayerListHeaderAndFooter(@NotNull Component header, @NotNull Component footer) {
    }

    @NotNull
    public static Audience empty() {
        return EmptyAudience.INSTANCE;
    }

    @ForwardingAudienceOverrideNotRequired
    default public void removeResourcePacks(@NotNull ResourcePackRequest request) {
        List infos = request.packs();
        if (infos.size() == 1) {
            this.removeResourcePacks(((ResourcePackInfo)infos.get(0)).id(), new UUID[0]);
        } else if (infos.isEmpty()) {
            return;
        }
        UUID[] otherReqs = new UUID[infos.size() - 1];
        int i = 0;
        while (true) {
            if (i >= otherReqs.length) {
                this.removeResourcePacks(((ResourcePackInfo)infos.get(0)).id(), otherReqs);
                return;
            }
            otherReqs[i] = ((ResourcePackInfo)infos.get(i + 1)).id();
            ++i;
        }
    }

    @Deprecated
    @ForwardingAudienceOverrideNotRequired
    default public void sendMessage(@NotNull Identity source, @NotNull ComponentLike message) {
        this.sendMessage(source, message.asComponent());
    }

    @ForwardingAudienceOverrideNotRequired
    @Deprecated
    default public void sendMessage(@NotNull Identified source, @NotNull ComponentLike message) {
        this.sendMessage(source, message.asComponent());
    }

    @ForwardingAudienceOverrideNotRequired
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    default public void sendMessage(@NotNull ComponentLike message, @NotNull MessageType type) {
        this.sendMessage(message.asComponent(), type);
    }

    default public void playSound(@NotNull Sound sound, double x, double y, double z) {
    }

    default public void sendMessage(@NotNull Component message) {
        this.sendMessage(message, MessageType.SYSTEM);
    }

    @ForwardingAudienceOverrideNotRequired
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    default public void sendMessage(@NotNull Component message, @NotNull MessageType type) {
        this.sendMessage(Identity.nil(), message, type);
    }

    @NotNull
    public static Audience audience(Audience ... audiences) {
        int length = audiences.length;
        if (length == 0) {
            return Audience.empty();
        }
        if (length != 1) return Audience.audience(Arrays.asList(audiences));
        return audiences[0];
    }

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    default public void sendMessage(@NotNull Identified source, @NotNull Component message, @NotNull MessageType type) {
        this.sendMessage(source.identity(), message, type);
    }

    @ForwardingAudienceOverrideNotRequired
    default public void stopSound(@NotNull Sound sound) {
        this.stopSound(Objects.requireNonNull(sound, "sound").asStop());
    }

    default public void playSound(@NotNull Sound sound, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Sound.Emitter emitter) {
    }

    @ForwardingAudienceOverrideNotRequired
    default public void removeResourcePacks(@NotNull ResourcePackRequestLike request) {
        this.removeResourcePacks(request.asResourcePackRequest());
    }

    @ForwardingAudienceOverrideNotRequired
    default public void removeResourcePacks(@NotNull ResourcePackInfoLike request, ResourcePackInfoLike ... others) {
        UUID[] otherReqs = new UUID[others.length];
        int i = 0;
        while (true) {
            if (i >= others.length) {
                this.removeResourcePacks(request.asResourcePackInfo().id(), otherReqs);
                return;
            }
            otherReqs[i] = others[i].asResourcePackInfo().id();
            ++i;
        }
    }

    @ForwardingAudienceOverrideNotRequired
    default public void sendResourcePacks(@NotNull ResourcePackInfoLike first, ResourcePackInfoLike ... others) {
        this.sendResourcePacks(ResourcePackRequest.addingRequest((ResourcePackInfoLike)first, (ResourcePackInfoLike[])others));
    }

    default public void forEachAudience(@NotNull Consumer<? super Audience> action) {
        action.accept(this);
    }

    default public void openBook(@NotNull Book book) {
    }

    @ForwardingAudienceOverrideNotRequired
    default public void sendPlayerListFooter(@NotNull ComponentLike footer) {
        this.sendPlayerListFooter(footer.asComponent());
    }

    default public void resetTitle() {
    }

    @ForwardingAudienceOverrideNotRequired
    default public void sendResourcePacks(@NotNull ResourcePackRequestLike request) {
        this.sendResourcePacks(request.asResourcePackRequest());
    }

    default public void clearTitle() {
    }

    @ForwardingAudienceOverrideNotRequired
    default public void sendPlayerListHeaderAndFooter(@NotNull ComponentLike header, @NotNull ComponentLike footer) {
        this.sendPlayerListHeaderAndFooter(header.asComponent(), footer.asComponent());
    }

    @ForwardingAudienceOverrideNotRequired
    default public void sendMessage(@NotNull ComponentLike message) {
        this.sendMessage(message.asComponent());
    }

    @ForwardingAudienceOverrideNotRequired
    default public void openBook(// Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Book.Builder book) {
        this.openBook(book.build());
    }

    @NotNull
    default public Audience filterAudience(@NotNull Predicate<? super Audience> filter) {
        return filter.test(this) ? this : Audience.empty();
    }

    default public void sendActionBar(@NotNull Component message) {
    }

    @ForwardingAudienceOverrideNotRequired
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    default public void sendMessage(@NotNull Identity source, @NotNull ComponentLike message, @NotNull MessageType type) {
        this.sendMessage(source, message.asComponent(), type);
    }

    @ForwardingAudienceOverrideNotRequired
    default public void deleteMessage(@NotNull SignedMessage signedMessage) {
        if (!signedMessage.canDelete()) return;
        this.deleteMessage(Objects.requireNonNull(signedMessage.signature()));
    }

    default public void showDialog(@NotNull ZX\u00f3v dialog) {
    }

    default public void stopSound(@NotNull SoundStop stop) {
    }

    default public void sendMessage(@NotNull Component message, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull ChatType.Bound boundChatType) {
        this.sendMessage(message, MessageType.CHAT);
    }

    default public void playSound(@NotNull Sound sound) {
    }

    default public void sendPlayerListFooter(@NotNull Component footer) {
        this.sendPlayerListHeaderAndFooter((Component)Component.empty(), footer);
    }

    @ForwardingAudienceOverrideNotRequired
    default public void showTitle(@NotNull Title title) {
        Title.Times times = title.times();
        if (times != null) {
            this.sendTitlePart(TitlePart.TIMES, times);
        }
        this.sendTitlePart(TitlePart.SUBTITLE, title.subtitle());
        this.sendTitlePart(TitlePart.TITLE, title.title());
    }

    @ForwardingAudienceOverrideNotRequired
    default public void sendMessage(@NotNull ComponentLike message, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull ChatType.Bound boundChatType) {
        this.sendMessage(message.asComponent(), boundChatType);
    }

    @NotNull
    public static Collector<? super Audience, ?, ForwardingAudience> toAudience() {
        return Audiences.COLLECTOR;
    }

    default public void removeResourcePacks(@NotNull Iterable<UUID> ids) {
        UUID[] others;
        Iterator<UUID> it = ids.iterator();
        if (!it.hasNext()) {
            return;
        }
        UUID id = it.next();
        if (!it.hasNext()) {
            others = new UUID[]{};
        } else if (ids instanceof Collection) {
            others = new UUID[((Collection)ids).size() - 1];
            for (int i = 0; i < others.length; ++i) {
                others[i] = it.next();
            }
        } else {
            ArrayList<UUID> othersList = new ArrayList<UUID>();
            while (it.hasNext()) {
                othersList.add(it.next());
            }
            others = othersList.toArray(new UUID[0]);
        }
        this.removeResourcePacks(id, others);
    }

    default public void removeResourcePacks(@NotNull UUID id, UUID ... others) {
    }

    @ForwardingAudienceOverrideNotRequired
    default public void sendActionBar(@NotNull ComponentLike message) {
        this.sendActionBar(message.asComponent());
    }

    @Deprecated
    @ForwardingAudienceOverrideNotRequired
    default public void sendMessage(@NotNull Identity source, @NotNull Component message) {
        this.sendMessage(source, message, MessageType.CHAT);
    }

    default public void sendMessage(@NotNull SignedMessage signedMessage, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull ChatType.Bound boundChatType) {
        Component content;
        Object object = content = signedMessage.unsignedContent() != null ? signedMessage.unsignedContent() : Component.text((String)signedMessage.message());
        if (signedMessage.isSystem()) {
            this.sendMessage(content);
        } else {
            this.sendMessage(signedMessage.identity(), content, MessageType.CHAT);
        }
    }

    default public void sendResourcePacks(@NotNull ResourcePackRequest request) {
    }

    default public void sendPlayerListHeader(@NotNull Component header) {
        this.sendPlayerListHeaderAndFooter(header, (Component)Component.empty());
    }

    @NotNull
    public static ForwardingAudience audience(@NotNull Iterable<? extends Audience> audiences) {
        return () -> audiences;
    }

    default public void hideBossBar(@NotNull BossBar bar) {
    }
}
