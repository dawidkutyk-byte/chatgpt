/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallback
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallback$Provider
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallback;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent;

static final class ClickCallbackInternals.Fallback
implements ClickCallback.Provider {
    @NotNull
    public ClickEvent create(@NotNull ClickCallback<Audience> callback, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull ClickCallback.Options options) {
        return ClickEvent.suggestCommand((String)"Callbacks are not supported on this platform!");
    }

    ClickCallbackInternals.Fallback() {
    }
}
