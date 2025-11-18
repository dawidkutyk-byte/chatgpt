/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.MessageType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar$Listener
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.ChatType$Bound
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.SignedMessage
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.inventory.Book
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$ActionBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Book
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$BossBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$BossBar$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Chat
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$EntitySound
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Message
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Pointers
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Sound
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$TabList
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Title
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetAudienceProvider
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetBossBarListener
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound$Emitter
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundStop
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattener
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.title.Title
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.title.Title$Times
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.title.TitlePart
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet;

import java.io.Closeable;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.MessageType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.ChatType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.SignedMessage;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.inventory.Book;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetAudienceProvider;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetBossBarListener;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundStop;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattener;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.title.Title;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.title.TitlePart;

@ApiStatus.Internal
public class FacetAudience<V>
implements Audience,
Closeable {
    @Nullable
    private V viewer;
    @Nullable
    private final Map<BossBar, Facet.BossBar<V>> bossBars;
    private final // Could not load outer class - annotation placement on inner may be incorrect
    @Nullable Facet.EntitySound<V, Object> entitySound;
    private final // Could not load outer class - annotation placement on inner may be incorrect
    @Nullable Facet.Chat<V, Object> chat;
    private final // Could not load outer class - annotation placement on inner may be incorrect
    @Nullable Facet.TabList<V, Object> tabList;
    @NotNull
    private final Collection<? extends Facet.Pointers<V>> pointerProviders;
    private final // Could not load outer class - annotation placement on inner may be incorrect
    @Nullable Facet.Title<V, Object, Object, Object> title;
    private final // Could not load outer class - annotation placement on inner may be incorrect
    @Nullable Facet.Sound<V, Object> sound;
    @NotNull
    protected final FacetAudienceProvider<V, FacetAudience<V>> provider;
    @NotNull
    private final Set<V> viewers;
    private volatile Pointers pointers;
    private final // Could not load outer class - annotation placement on inner may be incorrect
    @Nullable Facet.BossBar.Builder<V, // Could not load outer class - annotation placement on inner may be incorrect
    Facet.BossBar<V>> bossBar;
    private final // Could not load outer class - annotation placement on inner may be incorrect
    @Nullable Facet.Book<V, Object, Object> book;
    private final // Could not load outer class - annotation placement on inner may be incorrect
    @Nullable Facet.ActionBar<V, Object> actionBar;

    public void sendPlayerListFooter(@NotNull Component footer) {
        if (this.tabList == null) return;
        Object footerFormatted = this.createMessage(footer, (Facet.Message<V, Object>)this.tabList);
        if (footerFormatted == null) {
            return;
        }
        Iterator<V> iterator = this.viewers.iterator();
        while (iterator.hasNext()) {
            V viewer = iterator.next();
            this.tabList.send(viewer, null, footerFormatted);
        }
    }

    public void playSound(@NotNull Sound sound, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Sound.Emitter emitter) {
        if (this.entitySound == null) {
            return;
        }
        if (emitter != Sound.Emitter.self()) {
            Object message = this.entitySound.createForEmitter(sound, emitter);
            if (message == null) {
                return;
            }
            Iterator<V> iterator = this.viewers.iterator();
            while (iterator.hasNext()) {
                V viewer = iterator.next();
                this.entitySound.playSound(viewer, message);
            }
            return;
        }
        Iterator<V> iterator = this.viewers.iterator();
        while (iterator.hasNext()) {
            V viewer = iterator.next();
            Object message = this.entitySound.createForSelf(viewer, sound);
            if (message == null) continue;
            this.entitySound.playSound(viewer, message);
        }
    }

    public void playSound(@NotNull Sound original) {
        if (this.sound == null) {
            return;
        }
        Iterator<V> iterator = this.viewers.iterator();
        while (iterator.hasNext()) {
            V viewer = iterator.next();
            Object position = this.sound.createPosition(viewer);
            if (position == null) continue;
            this.sound.playSound(viewer, original, position);
        }
    }

    public void sendActionBar(@NotNull Component original) {
        if (this.actionBar == null) {
            return;
        }
        Object message = this.createMessage(original, (Facet.Message<V, Object>)this.actionBar);
        if (message == null) {
            return;
        }
        Iterator<V> iterator = this.viewers.iterator();
        while (iterator.hasNext()) {
            V viewer = iterator.next();
            this.actionBar.sendMessage(viewer, message);
        }
    }

    @Nullable
    private Object createMessage(@NotNull Component original, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Facet.Message<V, Object> facet) {
        Component message = this.provider.componentRenderer.render(original, (Object)this);
        V viewer = this.viewer;
        return viewer == null ? null : facet.createMessage(viewer, message);
    }

    public void sendMessage(@NotNull SignedMessage signedMessage, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull ChatType.Bound boundChatType) {
        if (signedMessage.isSystem()) {
            Component content = signedMessage.unsignedContent() != null ? signedMessage.unsignedContent() : Component.text((String)signedMessage.message());
            this.sendMessage(content, boundChatType);
        } else {
            super.sendMessage(signedMessage, boundChatType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void showBossBar(@NotNull BossBar bar) {
        FacetBossBarListener listener;
        if (this.bossBar == null) return;
        if (this.bossBars == null) {
            return;
        }
        Object object = this.bossBars;
        synchronized (object) {
            listener = this.bossBars.get(bar);
            if (listener == null) {
                listener = new FacetBossBarListener(this.bossBar.createBossBar(this.viewers), message -> this.provider.componentRenderer.render(message, (Object)this));
                this.bossBars.put(bar, (Facet.BossBar<FacetBossBarListener>)listener);
            }
        }
        if (listener.isEmpty()) {
            listener.bossBarInitialized(bar);
            bar.addListener((BossBar.Listener)listener);
        }
        object = this.viewers.iterator();
        while (object.hasNext()) {
            Object viewer = object.next();
            listener.addViewer(viewer);
        }
    }

    @ApiStatus.OverrideOnly
    protected void contributePointers(Pointers.Builder builder) {
    }

    public void sendMessage(@NotNull Component original, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull ChatType.Bound boundChatType) {
        if (this.chat == null) {
            return;
        }
        Object message = this.createMessage(original, (Facet.Message<V, Object>)this.chat);
        if (message == null) {
            return;
        }
        Component name = this.provider.componentRenderer.render(boundChatType.name(), (Object)this);
        Component target = null;
        if (boundChatType.target() != null) {
            target = this.provider.componentRenderer.render(boundChatType.target(), (Object)this);
        }
        ChatType.Bound renderedType = boundChatType.type().bind((ComponentLike)name, target);
        Iterator<V> iterator = this.viewers.iterator();
        while (iterator.hasNext()) {
            V viewer = iterator.next();
            this.chat.sendMessage(viewer, Identity.nil(), message, (Object)renderedType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void refresh() {
        Object object = this;
        synchronized (object) {
            this.pointers = null;
        }
        if (this.bossBars == null) {
            return;
        }
        object = this.bossBars.entrySet().iterator();
        while (object.hasNext()) {
            Map.Entry entry = (Map.Entry)object.next();
            BossBar bar = (BossBar)entry.getKey();
            Facet.BossBar listener = (Facet.BossBar)entry.getValue();
            listener.bossBarNameChanged(bar, bar.name(), bar.name());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @NotNull
    public Pointers pointers() {
        if (this.pointers != null) return this.pointers;
        FacetAudience facetAudience = this;
        synchronized (facetAudience) {
            if (this.pointers != null) return this.pointers;
            V viewer = this.viewer;
            if (viewer == null) {
                return Pointers.empty();
            }
            Pointers.Builder builder = Pointers.builder();
            this.contributePointers(builder);
            Iterator<Facet.Pointers<V>> iterator = this.pointerProviders.iterator();
            while (true) {
                if (!iterator.hasNext()) {
                    this.pointers = (Pointers)builder.build();
                    return this.pointers;
                }
                Facet.Pointers<V> provider = iterator.next();
                if (!provider.isApplicable(viewer)) continue;
                provider.contributePointers(viewer, builder);
            }
        }
    }

    public FacetAudience(@NotNull FacetAudienceProvider provider, @NotNull Collection<? extends V> viewers, @Nullable Collection<? extends Facet.Chat> chat, @Nullable Collection<? extends Facet.ActionBar> actionBar, @Nullable Collection<? extends Facet.Title> title, @Nullable Collection<? extends Facet.Sound> sound, @Nullable Collection<? extends Facet.EntitySound> entitySound, @Nullable Collection<? extends Facet.Book> book, @Nullable Collection<? extends Facet.BossBar.Builder> bossBar, @Nullable Collection<? extends Facet.TabList> tabList, @Nullable Collection<? extends Facet.Pointers> pointerProviders) {
        this.provider = Objects.requireNonNull(provider, "audience provider");
        this.viewers = new CopyOnWriteArraySet<V>();
        for (V viewer : Objects.requireNonNull(viewers, "viewers")) {
            this.addViewer(viewer);
        }
        this.refresh();
        this.chat = (Facet.Chat)Facet.of(chat, this.viewer);
        this.actionBar = (Facet.ActionBar)Facet.of(actionBar, this.viewer);
        this.title = (Facet.Title)Facet.of(title, this.viewer);
        this.sound = (Facet.Sound)Facet.of(sound, this.viewer);
        this.entitySound = (Facet.EntitySound)Facet.of(entitySound, this.viewer);
        this.book = (Facet.Book)Facet.of(book, this.viewer);
        this.bossBar = (Facet.BossBar.Builder)Facet.of(bossBar, this.viewer);
        this.bossBars = this.bossBar == null ? null : Collections.synchronizedMap(new IdentityHashMap(4));
        this.tabList = (Facet.TabList)Facet.of(tabList, this.viewer);
        this.pointerProviders = pointerProviders == null ? Collections.emptyList() : pointerProviders;
    }

    public void clearTitle() {
        if (this.title == null) {
            return;
        }
        Iterator<V> iterator = this.viewers.iterator();
        while (iterator.hasNext()) {
            V viewer = iterator.next();
            this.title.clearTitle(viewer);
        }
    }

    @Override
    public void close() {
        if (this.bossBars != null) {
            for (BossBar bar : new LinkedList<BossBar>(this.bossBars.keySet())) {
                this.hideBossBar(bar);
            }
            this.bossBars.clear();
        }
        Iterator<Object> iterator = this.viewers.iterator();
        while (true) {
            if (!iterator.hasNext()) {
                this.viewers.clear();
                return;
            }
            Object viewer = iterator.next();
            this.removeViewer(viewer);
        }
    }

    public void showTitle(@NotNull Title original) {
        if (this.title == null) {
            return;
        }
        Object mainTitle = this.createMessage(original.title(), (Facet.Message<V, Object>)this.title);
        Object subTitle = this.createMessage(original.subtitle(), (Facet.Message<V, Object>)this.title);
        // Could not load outer class - annotation placement on inner may be incorrect
        @Nullable Title.Times times = original.times();
        int inTicks = times == null ? -1 : this.title.toTicks(times.fadeIn());
        int stayTicks = times == null ? -1 : this.title.toTicks(times.stay());
        int outTicks = times == null ? -1 : this.title.toTicks(times.fadeOut());
        Object collection = this.title.createTitleCollection();
        if (inTicks != -1 || stayTicks != -1 || outTicks != -1) {
            this.title.contributeTimes(collection, inTicks, stayTicks, outTicks);
        }
        this.title.contributeSubtitle(collection, subTitle);
        this.title.contributeTitle(collection, mainTitle);
        Object title = this.title.completeTitle(collection);
        if (title == null) {
            return;
        }
        Iterator<V> iterator = this.viewers.iterator();
        while (iterator.hasNext()) {
            V viewer = iterator.next();
            this.title.showTitle(viewer, title);
        }
    }

    public void openBook(@NotNull Book original) {
        if (this.book == null) {
            return;
        }
        String title = this.toPlain(original.title());
        String author = this.toPlain(original.author());
        LinkedList<Object> pages = new LinkedList<Object>();
        for (Component originalPage : original.pages()) {
            Object page = this.createMessage(originalPage, (Facet.Message<V, Object>)this.book);
            if (page == null) continue;
            pages.add(page);
        }
        if (title == null) return;
        if (author == null) return;
        if (pages.isEmpty()) {
            return;
        }
        Object book = this.book.createBook(title, author, pages);
        if (book == null) {
            return;
        }
        Iterator<V> iterator = this.viewers.iterator();
        while (iterator.hasNext()) {
            V viewer = iterator.next();
            this.book.openBook(viewer, book);
        }
    }

    public void hideBossBar(@NotNull BossBar bar) {
        if (this.bossBars == null) {
            return;
        }
        Facet.BossBar<V> listener = this.bossBars.get(bar);
        if (listener == null) {
            return;
        }
        Iterator<V> iterator = this.viewers.iterator();
        while (true) {
            if (!iterator.hasNext()) {
                if (!listener.isEmpty()) return;
                if (this.bossBars.remove(bar) == null) return;
                bar.removeListener(listener);
                listener.close();
                return;
            }
            V viewer = iterator.next();
            listener.removeViewer(viewer);
        }
    }

    public void playSound(@NotNull Sound original, double x, double y, double z) {
        if (this.sound == null) {
            return;
        }
        Object position = this.sound.createPosition(x, y, z);
        Iterator<V> iterator = this.viewers.iterator();
        while (iterator.hasNext()) {
            V viewer = iterator.next();
            this.sound.playSound(viewer, original, position);
        }
    }

    public void sendMessage(@NotNull Identity source, @NotNull Component original, @NotNull MessageType type) {
        if (this.chat == null) {
            return;
        }
        Object message = this.createMessage(original, (Facet.Message<V, Object>)this.chat);
        if (message == null) {
            return;
        }
        Iterator<V> iterator = this.viewers.iterator();
        while (iterator.hasNext()) {
            V viewer = iterator.next();
            this.chat.sendMessage(viewer, source, message, (Object)type);
        }
    }

    private String toPlain(Component comp) {
        if (comp == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        ComponentFlattener.basic().flatten(this.provider.componentRenderer.render(comp, (Object)this), builder::append);
        return builder.toString();
    }

    public void resetTitle() {
        if (this.title == null) {
            return;
        }
        Iterator<V> iterator = this.viewers.iterator();
        while (iterator.hasNext()) {
            V viewer = iterator.next();
            this.title.resetTitle(viewer);
        }
    }

    public <T> void sendTitlePart(@NotNull TitlePart<T> part, @NotNull T value) {
        Object message;
        if (this.title == null) {
            return;
        }
        Objects.requireNonNull(value, "value");
        Object collection = this.title.createTitleCollection();
        if (part == TitlePart.TITLE) {
            message = this.createMessage((Component)value, (Facet.Message<V, Object>)this.title);
            if (message != null) {
                this.title.contributeTitle(collection, message);
            }
        } else if (part == TitlePart.SUBTITLE) {
            message = this.createMessage((Component)value, (Facet.Message<V, Object>)this.title);
            if (message != null) {
                this.title.contributeSubtitle(collection, message);
            }
        } else {
            if (part != TitlePart.TIMES) throw new IllegalArgumentException("Unknown TitlePart '" + part + "'");
            Title.Times times = (Title.Times)value;
            int inTicks = this.title.toTicks(times.fadeIn());
            int stayTicks = this.title.toTicks(times.stay());
            int outTicks = this.title.toTicks(times.fadeOut());
            if (inTicks != -1 || stayTicks != -1 || outTicks != -1) {
                this.title.contributeTimes(collection, inTicks, stayTicks, outTicks);
            }
        }
        Object title = this.title.completeTitle(collection);
        if (title == null) {
            return;
        }
        Iterator<V> iterator = this.viewers.iterator();
        while (iterator.hasNext()) {
            V viewer = iterator.next();
            this.title.showTitle(viewer, title);
        }
    }

    public void sendPlayerListHeaderAndFooter(@NotNull Component header, @NotNull Component footer) {
        if (this.tabList == null) return;
        Object headerFormatted = this.createMessage(header, (Facet.Message<V, Object>)this.tabList);
        Object footerFormatted = this.createMessage(footer, (Facet.Message<V, Object>)this.tabList);
        if (headerFormatted == null) return;
        if (footerFormatted == null) {
            return;
        }
        Iterator<V> iterator = this.viewers.iterator();
        while (iterator.hasNext()) {
            V viewer = iterator.next();
            this.tabList.send(viewer, headerFormatted, footerFormatted);
        }
    }

    public void stopSound(@NotNull SoundStop original) {
        if (this.sound == null) {
            return;
        }
        Iterator<V> iterator = this.viewers.iterator();
        while (iterator.hasNext()) {
            V viewer = iterator.next();
            this.sound.stopSound(viewer, original);
        }
    }

    public void removeViewer(@NotNull V viewer) {
        if (this.viewers.remove(viewer) && this.viewer == viewer) {
            this.viewer = this.viewers.isEmpty() ? null : this.viewers.iterator().next();
            this.refresh();
        }
        if (this.bossBars == null) {
            return;
        }
        Iterator<Facet.BossBar<V>> iterator = this.bossBars.values().iterator();
        while (iterator.hasNext()) {
            Facet.BossBar<V> listener = iterator.next();
            listener.removeViewer(viewer);
        }
    }

    public void addViewer(@NotNull V viewer) {
        if (!this.viewers.add(viewer)) return;
        if (this.viewer != null) return;
        this.viewer = viewer;
        this.refresh();
    }

    public void sendPlayerListHeader(@NotNull Component header) {
        if (this.tabList == null) return;
        Object headerFormatted = this.createMessage(header, (Facet.Message<V, Object>)this.tabList);
        if (headerFormatted == null) {
            return;
        }
        Iterator<V> iterator = this.viewers.iterator();
        while (iterator.hasNext()) {
            V viewer = iterator.next();
            this.tabList.send(viewer, headerFormatted, null);
        }
    }
}
