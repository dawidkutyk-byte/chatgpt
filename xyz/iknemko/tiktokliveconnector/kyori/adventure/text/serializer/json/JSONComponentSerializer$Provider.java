/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.PlatformAPI
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json;

import java.util.function.Supplier;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.PlatformAPI;

@PlatformAPI
@ApiStatus.Internal
public static interface JSONComponentSerializer.Provider {
    @PlatformAPI
    @ApiStatus.Internal
    @NotNull
    public JSONComponentSerializer instance();

    @PlatformAPI
    @NotNull
    @ApiStatus.Internal
    public @NotNull Supplier<// Could not load outer class - annotation placement on inner may be incorrect
    @NotNull JSONComponentSerializer.Builder> builder();
}
