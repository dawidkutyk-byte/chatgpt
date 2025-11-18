/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar;

public interface BossBarViewer {
    public @UnmodifiableView @NotNull Iterable<? extends BossBar> activeBossBars();
}
