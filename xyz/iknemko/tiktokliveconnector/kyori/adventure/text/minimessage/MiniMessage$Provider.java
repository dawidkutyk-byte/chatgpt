/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessage
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessage$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.PlatformAPI
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage;

import java.util.function.Consumer;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessage;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.PlatformAPI;

@PlatformAPI
@ApiStatus.Internal
public static interface MiniMessage.Provider {
    @PlatformAPI
    @ApiStatus.Internal
    @NotNull
    public Consumer<MiniMessage.Builder> builder();

    @PlatformAPI
    @NotNull
    @ApiStatus.Internal
    public MiniMessage miniMessage();
}
