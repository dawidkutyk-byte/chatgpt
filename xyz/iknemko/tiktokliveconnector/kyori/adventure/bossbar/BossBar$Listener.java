/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar$Color
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar$Flag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar$Overlay
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar;

import java.util.Set;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;

@ApiStatus.OverrideOnly
public static interface BossBar.Listener {
    default public void bossBarNameChanged(@NotNull BossBar bar, @NotNull Component oldName, @NotNull Component newName) {
    }

    default public void bossBarColorChanged(@NotNull BossBar bar, @NotNull BossBar.Color oldColor, @NotNull BossBar.Color newColor) {
    }

    default public void bossBarProgressChanged(@NotNull BossBar bar, float oldProgress, float newProgress) {
        this.bossBarPercentChanged(bar, oldProgress, newProgress);
    }

    default public void bossBarFlagsChanged(@NotNull BossBar bar, @NotNull Set<BossBar.Flag> flagsAdded, @NotNull Set<BossBar.Flag> flagsRemoved) {
    }

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    default public void bossBarPercentChanged(@NotNull BossBar bar, float oldProgress, float newProgress) {
    }

    default public void bossBarOverlayChanged(@NotNull BossBar bar, @NotNull BossBar.Overlay oldOverlay, @NotNull BossBar.Overlay newOverlay) {
    }
}
