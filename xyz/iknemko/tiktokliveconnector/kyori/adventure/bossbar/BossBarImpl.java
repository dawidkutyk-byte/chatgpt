/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar$Color
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar$Flag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar$Listener
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar$Overlay
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBarImplementation
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBarViewer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.HackyBossBarPlatformBridge
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBarImplementation;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBarViewer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.HackyBossBarPlatformBridge;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;

final class BossBarImpl
extends HackyBossBarPlatformBridge
implements BossBar {
    private BossBar.Color color;
    private float progress;
    private Component name;
    private BossBar.Overlay overlay;
    @Nullable
    BossBarImplementation implementation;
    private final List<BossBar.Listener> listeners = new CopyOnWriteArrayList<BossBar.Listener>();
    private final Set<BossBar.Flag> flags = EnumSet.noneOf(BossBar.Flag.class);

    @NotNull
    public BossBar.Overlay overlay() {
        return this.overlay;
    }

    @NotNull
    public BossBar color(@NotNull BossBar.Color newColor) {
        Objects.requireNonNull(newColor, "color");
        BossBar.Color oldColor = this.color;
        if (newColor == oldColor) return this;
        this.color = newColor;
        this.forEachListener(listener -> listener.bossBarColorChanged((BossBar)this, oldColor, newColor));
        return this;
    }

    public boolean hasFlag(@NotNull BossBar.Flag flag) {
        return this.flags.contains(flag);
    }

    @NotNull
    public BossBar addListener(@NotNull BossBar.Listener listener) {
        this.listeners.add(listener);
        return this;
    }

    @NotNull
    private BossBar editFlags(@NotNull BossBar.Flag flag, @NotNull BiPredicate<Set<BossBar.Flag>, BossBar.Flag> predicate, BiConsumer<BossBarImpl, Set<BossBar.Flag>> onChange) {
        if (!predicate.test(this.flags, flag)) return this;
        onChange.accept(this, Collections.singleton(flag));
        return this;
    }

    @NotNull
    public BossBar removeFlags(@NotNull Iterable<BossBar.Flag> flags) {
        return this.editFlags(flags, Set::remove, BossBarImpl::onFlagsRemoved);
    }

    @NotNull
    public Set<BossBar.Flag> flags() {
        return Collections.unmodifiableSet(this.flags);
    }

    @NotNull
    private BossBar editFlags(Iterable<BossBar.Flag> flags, BiPredicate<Set<BossBar.Flag>, BossBar.Flag> predicate, BiConsumer<BossBarImpl, Set<BossBar.Flag>> onChange) {
        EnumSet<BossBar.Flag> changes = null;
        Iterator<BossBar.Flag> iterator = flags.iterator();
        while (true) {
            if (!iterator.hasNext()) {
                if (changes == null) return this;
                onChange.accept(this, changes);
                return this;
            }
            BossBar.Flag flag = iterator.next();
            if (!predicate.test(this.flags, flag)) continue;
            if (changes == null) {
                changes = EnumSet.noneOf(BossBar.Flag.class);
            }
            changes.add(flag);
        }
    }

    private void forEachListener(@NotNull Consumer<BossBar.Listener> consumer) {
        Iterator<BossBar.Listener> iterator = this.listeners.iterator();
        while (iterator.hasNext()) {
            BossBar.Listener listener = iterator.next();
            consumer.accept(listener);
        }
    }

    BossBarImpl(@NotNull Component name, float progress, @NotNull BossBar.Color color, @NotNull BossBar.Overlay overlay) {
        this.name = Objects.requireNonNull(name, "name");
        this.progress = progress;
        this.color = Objects.requireNonNull(color, "color");
        this.overlay = Objects.requireNonNull(overlay, "overlay");
    }

    BossBarImpl(@NotNull Component name, float progress, @NotNull BossBar.Color color, @NotNull BossBar.Overlay overlay, @NotNull Set<BossBar.Flag> flags) {
        this(name, progress, color, overlay);
        this.flags.addAll(flags);
    }

    @NotNull
    public BossBar overlay(@NotNull BossBar.Overlay newOverlay) {
        Objects.requireNonNull(newOverlay, "overlay");
        BossBar.Overlay oldOverlay = this.overlay;
        if (newOverlay == oldOverlay) return this;
        this.overlay = newOverlay;
        this.forEachListener(listener -> listener.bossBarOverlayChanged((BossBar)this, oldOverlay, newOverlay));
        return this;
    }

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    @NotNull
    public Iterable<? extends BossBarViewer> viewers() {
        if (this.implementation == null) return Collections.emptyList();
        return this.implementation.viewers();
    }

    private static void onFlagsRemoved(BossBarImpl bar, Set<BossBar.Flag> flagsRemoved) {
        bar.forEachListener(listener -> listener.bossBarFlagsChanged((BossBar)bar, Collections.emptySet(), flagsRemoved));
    }

    @NotNull
    public Component name() {
        return this.name;
    }

    @NotNull
    public BossBar addFlags(@NotNull Iterable<BossBar.Flag> flags) {
        return this.editFlags(flags, Set::add, BossBarImpl::onFlagsAdded);
    }

    private static void onFlagsAdded(BossBarImpl bar, Set<BossBar.Flag> flagsAdded) {
        bar.forEachListener(listener -> listener.bossBarFlagsChanged((BossBar)bar, flagsAdded, Collections.emptySet()));
    }

    @NotNull
    private BossBar editFlags(BossBar.Flag[] flags, BiPredicate<Set<BossBar.Flag>, BossBar.Flag> predicate, BiConsumer<BossBarImpl, Set<BossBar.Flag>> onChange) {
        if (flags.length == 0) {
            return this;
        }
        EnumSet<BossBar.Flag> changes = null;
        int i = 0;
        int length = flags.length;
        while (true) {
            if (i >= length) {
                if (changes == null) return this;
                onChange.accept(this, changes);
                return this;
            }
            if (predicate.test(this.flags, flags[i])) {
                if (changes == null) {
                    changes = EnumSet.noneOf(BossBar.Flag.class);
                }
                changes.add(flags[i]);
            }
            ++i;
        }
    }

    public float progress() {
        return this.progress;
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"name", (Object)this.name), ExaminableProperty.of((String)"progress", (float)this.progress), ExaminableProperty.of((String)"color", (Object)this.color), ExaminableProperty.of((String)"overlay", (Object)this.overlay), ExaminableProperty.of((String)"flags", this.flags));
    }

    @NotNull
    public BossBar progress(float newProgress) {
        BossBarImpl.checkProgress(newProgress);
        float oldProgress = this.progress;
        if (newProgress == oldProgress) return this;
        this.progress = newProgress;
        this.forEachListener(listener -> listener.bossBarProgressChanged((BossBar)this, oldProgress, newProgress));
        return this;
    }

    @NotNull
    public BossBar addFlag(@NotNull BossBar.Flag flag) {
        return this.editFlags(flag, Set::add, BossBarImpl::onFlagsAdded);
    }

    @NotNull
    public BossBar removeFlags(BossBar.Flag ... flags) {
        return this.editFlags(flags, Set::remove, BossBarImpl::onFlagsRemoved);
    }

    @NotNull
    public BossBar name(@NotNull Component newName) {
        Objects.requireNonNull(newName, "name");
        Component oldName = this.name;
        this.name = newName;
        this.forEachListener(listener -> listener.bossBarNameChanged((BossBar)this, oldName, newName));
        return this;
    }

    @NotNull
    public BossBar flags(@NotNull Set<BossBar.Flag> newFlags) {
        if (newFlags.isEmpty() && !this.flags.isEmpty()) {
            EnumSet<BossBar.Flag> oldFlags = EnumSet.copyOf(this.flags);
            this.flags.clear();
            this.forEachListener(listener -> listener.bossBarFlagsChanged((BossBar)this, Collections.emptySet(), oldFlags));
        } else {
            if (this.flags.equals(newFlags)) return this;
            EnumSet<BossBar.Flag> oldFlags = EnumSet.copyOf(this.flags);
            this.flags.clear();
            this.flags.addAll(newFlags);
            EnumSet<BossBar.Flag> added = EnumSet.copyOf(newFlags);
            added.removeIf(oldFlags::contains);
            EnumSet<BossBar.Flag> removed = EnumSet.copyOf(oldFlags);
            removed.removeIf(this.flags::contains);
            this.forEachListener(listener -> listener.bossBarFlagsChanged((BossBar)this, added, removed));
        }
        return this;
    }

    @NotNull
    public BossBar.Color color() {
        return this.color;
    }

    @NotNull
    public BossBar removeListener(@NotNull BossBar.Listener listener) {
        this.listeners.remove(listener);
        return this;
    }

    @NotNull
    public BossBar removeFlag(@NotNull BossBar.Flag flag) {
        return this.editFlags(flag, Set::remove, BossBarImpl::onFlagsRemoved);
    }

    static void checkProgress(float progress) {
        if (progress < 0.0f) throw new IllegalArgumentException("progress must be between 0.0 and 1.0, was " + progress);
        if (!(progress > 1.0f)) return;
        throw new IllegalArgumentException("progress must be between 0.0 and 1.0, was " + progress);
    }

    @NotNull
    public BossBar addFlags(BossBar.Flag ... flags) {
        return this.editFlags(flags, Set::add, BossBarImpl::onFlagsAdded);
    }
}
