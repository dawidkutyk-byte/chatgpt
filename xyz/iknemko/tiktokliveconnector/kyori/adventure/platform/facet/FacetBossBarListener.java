/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar$Flag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$BossBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet;

import java.util.Set;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;

class FacetBossBarListener<V>
implements Facet.BossBar<V> {
    private final Facet.BossBar<V> facet;
    private final Function<Component, Component> translator;

    public void bossBarInitialized(@NotNull BossBar bar) {
        this.facet.bossBarInitialized(bar);
        this.bossBarNameChanged(bar, bar.name(), bar.name());
    }

    public void bossBarProgressChanged(@NotNull BossBar bar, float oldPercent, float newPercent) {
        this.facet.bossBarProgressChanged(bar, oldPercent, newPercent);
    }

    public void close() {
        this.facet.close();
    }

    public boolean isEmpty() {
        return this.facet.isEmpty();
    }

    public void removeViewer(@NotNull V viewer) {
        this.facet.removeViewer(viewer);
    }

    public void addViewer(@NotNull V viewer) {
        this.facet.addViewer(viewer);
    }

    public void bossBarFlagsChanged(@NotNull BossBar bar, @NotNull Set<BossBar.Flag> flagsAdded, @NotNull Set<BossBar.Flag> flagsRemoved) {
        this.facet.bossBarFlagsChanged(bar, flagsAdded, flagsRemoved);
    }

    FacetBossBarListener(// Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Facet.BossBar<V> facet, @NotNull Function<Component, Component> translator) {
        this.facet = facet;
        this.translator = translator;
    }

    public void bossBarColorChanged(@NotNull BossBar bar, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull BossBar.Color oldColor, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull BossBar.Color newColor) {
        this.facet.bossBarColorChanged(bar, oldColor, newColor);
    }

    public void bossBarNameChanged(@NotNull BossBar bar, @NotNull Component oldName, @NotNull Component newName) {
        this.facet.bossBarNameChanged(bar, oldName, this.translator.apply(newName));
    }

    public void bossBarOverlayChanged(@NotNull BossBar bar, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull BossBar.Overlay oldOverlay, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull BossBar.Overlay newOverlay) {
        this.facet.bossBarOverlayChanged(bar, oldOverlay, newOverlay);
    }
}
