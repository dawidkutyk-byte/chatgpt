/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBarImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBarImplementation
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBarImplementation$Provider
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Services
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar;

import java.util.Optional;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBarImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBarImplementation;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Services;

@ApiStatus.Internal
static final class BossBarImpl.ImplementationAccessor {
    private static final Optional<BossBarImplementation.Provider> SERVICE = Services.service(BossBarImplementation.Provider.class);

    private BossBarImpl.ImplementationAccessor() {
    }

    @NotNull
    static <I extends BossBarImplementation> I get(@NotNull BossBar bar, @NotNull Class<I> type) {
        @Nullable BossBarImplementation implementation = ((BossBarImpl)bar).implementation;
        if (implementation != null) return (I)((BossBarImplementation)type.cast(implementation));
        ((BossBarImpl)bar).implementation = implementation = SERVICE.get().create(bar);
        return (I)((BossBarImplementation)type.cast(implementation));
    }
}
