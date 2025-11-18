/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.PlatformAPI
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy;

import java.util.function.Consumer;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.PlatformAPI;

@PlatformAPI
@ApiStatus.Internal
public static interface LegacyComponentSerializer.Provider {
    @PlatformAPI
    @NotNull
    @ApiStatus.Internal
    public LegacyComponentSerializer legacyAmpersand();

    @PlatformAPI
    @NotNull
    @ApiStatus.Internal
    public LegacyComponentSerializer legacySection();

    @PlatformAPI
    @ApiStatus.Internal
    @NotNull
    public Consumer<LegacyComponentSerializer.Builder> legacy();
}
