/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializer$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.PlatformAPI
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson;

import java.util.function.Consumer;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.PlatformAPI;

@PlatformAPI
@ApiStatus.Internal
public static interface GsonComponentSerializer.Provider {
    @PlatformAPI
    @NotNull
    @ApiStatus.Internal
    public Consumer<GsonComponentSerializer.Builder> builder();

    @PlatformAPI
    @NotNull
    @ApiStatus.Internal
    public GsonComponentSerializer gsonLegacy();

    @PlatformAPI
    @ApiStatus.Internal
    @NotNull
    public GsonComponentSerializer gson();
}
