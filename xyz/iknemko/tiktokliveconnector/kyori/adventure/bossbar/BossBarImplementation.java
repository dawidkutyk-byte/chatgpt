/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBarImpl$ImplementationAccessor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBarViewer
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar;

import java.util.Collections;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBarImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBarViewer;

@ApiStatus.Internal
public interface BossBarImplementation {
    @NotNull
    @ApiStatus.Internal
    public static <I extends BossBarImplementation> I get(@NotNull BossBar bar, @NotNull Class<I> type) {
        return (I)BossBarImpl.ImplementationAccessor.get((BossBar)bar, type);
    }

    @ApiStatus.Internal
    @NotNull
    default public Iterable<? extends BossBarViewer> viewers() {
        return Collections.emptyList();
    }
}
