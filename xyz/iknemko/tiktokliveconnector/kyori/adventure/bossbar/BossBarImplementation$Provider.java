/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBarImplementation
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBarImplementation;

@ApiStatus.Internal
public static interface BossBarImplementation.Provider {
    @NotNull
    @ApiStatus.Internal
    public BossBarImplementation create(@NotNull BossBar var1);
}
