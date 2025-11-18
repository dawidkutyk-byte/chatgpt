/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.util;

import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;

public interface ComponentMessageThrowable {
    @Nullable
    public static Component getMessage(@Nullable Throwable throwable) {
        if (!(throwable instanceof ComponentMessageThrowable)) return null;
        return ((ComponentMessageThrowable)((Object)throwable)).componentMessage();
    }

    @Nullable
    public static Component getOrConvertMessage(@Nullable Throwable throwable) {
        if (throwable instanceof ComponentMessageThrowable) {
            return ((ComponentMessageThrowable)((Object)throwable)).componentMessage();
        }
        if (throwable == null) return null;
        String message = throwable.getMessage();
        if (message == null) return null;
        return Component.text((String)message);
    }

    @Nullable
    public Component componentMessage();
}
