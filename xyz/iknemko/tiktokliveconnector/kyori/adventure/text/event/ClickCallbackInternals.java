/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.permission.PermissionChecker
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallback$Provider
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallbackInternals$Fallback
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Services
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.TriState
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import xyz.iknemko.tiktokliveconnector.kyori.adventure.permission.PermissionChecker;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallback;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallbackInternals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Services;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.TriState;

final class ClickCallbackInternals {
    static final ClickCallback.Provider PROVIDER;
    static final PermissionChecker ALWAYS_FALSE;

    private ClickCallbackInternals() {
    }

    static {
        ALWAYS_FALSE = PermissionChecker.always((TriState)TriState.FALSE);
        PROVIDER = Services.service(ClickCallback.Provider.class).orElseGet(Fallback::new);
    }
}
