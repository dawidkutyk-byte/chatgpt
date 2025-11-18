/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar$Listener
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet;

import java.io.Closeable;
import java.util.Collections;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar;

public static interface Facet.BossBar<V>
extends BossBar.Listener,
Closeable {
    public static final int PROTOCOL_BOSS_BAR = 356;

    public void removeViewer(@NotNull V var1);

    default public void bossBarInitialized(@NotNull BossBar bar) {
        this.bossBarNameChanged(bar, bar.name(), bar.name());
        this.bossBarColorChanged(bar, bar.color(), bar.color());
        this.bossBarProgressChanged(bar, bar.progress(), bar.progress());
        this.bossBarFlagsChanged(bar, bar.flags(), Collections.emptySet());
        this.bossBarOverlayChanged(bar, bar.overlay(), bar.overlay());
    }

    @Override
    public void close();

    public boolean isEmpty();

    public void addViewer(@NotNull V var1);
}
