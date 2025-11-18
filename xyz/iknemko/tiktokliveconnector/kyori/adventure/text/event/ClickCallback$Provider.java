/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallback
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallback$Options
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.PlatformAPI
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallback;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.PlatformAPI;

@PlatformAPI
@ApiStatus.Internal
public static interface ClickCallback.Provider {
    @NotNull
    public ClickEvent create(@NotNull ClickCallback<Audience> var1, @NotNull ClickCallback.Options var2);
}
