/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$BossBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$FakeEntity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;

public static interface Facet.BossBarEntity<V, P>
extends Facet.BossBar<V>,
Facet.FakeEntity<V, P> {
    public static final int OFFSET_YAW = 0;
    public static final int INVULNERABLE_KEY = 20;
    public static final int INVULNERABLE_TICKS = 890;
    public static final int OFFSET_MAGNITUDE = 40;
    public static final int OFFSET_PITCH = 30;

    default public void removeViewer(@NotNull V viewer) {
        this.teleport(viewer, null);
    }

    default public void bossBarNameChanged(@NotNull BossBar bar, @NotNull Component oldName, @NotNull Component newName) {
        this.name(newName);
    }

    default public void bossBarProgressChanged(@NotNull BossBar bar, float oldProgress, float newProgress) {
        this.health(newProgress);
    }

    default public void addViewer(@NotNull V viewer) {
        this.teleport(viewer, this.createPosition(viewer));
    }
}
