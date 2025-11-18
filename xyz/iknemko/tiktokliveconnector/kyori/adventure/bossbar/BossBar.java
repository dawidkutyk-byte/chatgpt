/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar$Color
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar$Flag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar$Listener
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar$Overlay
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBarImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBarViewer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar;

import java.util.Set;
import net.kyori.examination.Examinable;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBarImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBarViewer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;

@ApiStatus.NonExtendable
public interface BossBar
extends Examinable {
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    public static final float MAX_PERCENT = 1.0f;
    public static final float MAX_PROGRESS = 1.0f;
    public static final float MIN_PROGRESS = 0.0f;
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    public static final float MIN_PERCENT = 0.0f;

    @NotNull
    @Contract(value="_ -> this")
    public BossBar color(@NotNull Color var1);

    @NotNull
    @Contract(value="_ -> this")
    public BossBar addListener(@NotNull Listener var1);

    @Contract(value="_ -> this")
    @NotNull
    public BossBar flags(@NotNull Set<Flag> var1);

    @NotNull
    public static BossBar bossBar(@NotNull ComponentLike name, float progress, @NotNull Color color, @NotNull Overlay overlay, @NotNull Set<Flag> flags) {
        BossBarImpl.checkProgress((float)progress);
        return BossBar.bossBar(name.asComponent(), progress, color, overlay, flags);
    }

    public boolean hasFlag(@NotNull Flag var1);

    @Contract(value="_ -> this")
    @NotNull
    public BossBar removeFlag(@NotNull Flag var1);

    @NotNull
    public Color color();

    @Contract(value="_ -> this")
    @NotNull
    public BossBar addFlags(@NotNull Iterable<Flag> var1);

    @NotNull
    public static BossBar bossBar(@NotNull Component name, float progress, @NotNull Color color, @NotNull Overlay overlay, @NotNull Set<Flag> flags) {
        BossBarImpl.checkProgress((float)progress);
        return new BossBarImpl(name, progress, color, overlay, flags);
    }

    @NotNull
    public static BossBar bossBar(@NotNull Component name, float progress, @NotNull Color color, @NotNull Overlay overlay) {
        BossBarImpl.checkProgress((float)progress);
        return new BossBarImpl(name, progress, color, overlay);
    }

    @Contract(value="_ -> this")
    @NotNull
    public BossBar addFlags(Flag ... var1);

    @NotNull
    default public BossBar removeViewer(@NotNull Audience viewer) {
        viewer.hideBossBar(this);
        return this;
    }

    @NotNull
    public Component name();

    @NotNull
    @Contract(value="_ -> this")
    public BossBar removeListener(@NotNull Listener var1);

    @Contract(value="_ -> this")
    @NotNull
    default public BossBar name(@NotNull ComponentLike name) {
        return this.name(name.asComponent());
    }

    @Contract(value="_ -> this")
    @NotNull
    public BossBar overlay(@NotNull Overlay var1);

    @NotNull
    @Contract(value="_ -> this")
    public BossBar addFlag(@NotNull Flag var1);

    @Contract(value="_ -> this")
    @NotNull
    public BossBar removeFlags(Flag ... var1);

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    @Contract(value="_ -> this")
    @NotNull
    default public BossBar percent(float progress) {
        return this.progress(progress);
    }

    @NotNull
    public Overlay overlay();

    @NotNull
    default public BossBar addViewer(@NotNull Audience viewer) {
        viewer.showBossBar(this);
        return this;
    }

    public float progress();

    @NotNull
    public @UnmodifiableView Iterable<? extends BossBarViewer> viewers();

    @NotNull
    @Contract(value="_ -> this")
    public BossBar progress(float var1);

    @Contract(value="_ -> this")
    @NotNull
    public BossBar name(@NotNull Component var1);

    @NotNull
    @Contract(value="_ -> this")
    public BossBar removeFlags(@NotNull Iterable<Flag> var1);

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    default public float percent() {
        return this.progress();
    }

    @NotNull
    public static BossBar bossBar(@NotNull ComponentLike name, float progress, @NotNull Color color, @NotNull Overlay overlay) {
        BossBarImpl.checkProgress((float)progress);
        return BossBar.bossBar(name.asComponent(), progress, color, overlay);
    }

    @NotNull
    public @UnmodifiableView Set<// Could not load outer class - annotation placement on inner may be incorrect
    Flag> flags();
}
