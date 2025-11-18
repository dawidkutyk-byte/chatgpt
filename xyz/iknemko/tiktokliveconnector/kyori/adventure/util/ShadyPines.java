/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.MonkeyBars
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.util;

import java.util.Set;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.MonkeyBars;

public final class ShadyPines {
    public static boolean equals(float a, float b) {
        return Float.floatToIntBits(a) == Float.floatToIntBits(b);
    }

    @Deprecated
    @SafeVarargs
    @NotNull
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    public static <E extends Enum<E>> Set<E> enumSet(Class<E> type, E ... constants) {
        return MonkeyBars.enumSet(type, constants);
    }

    public static boolean equals(double a, double b) {
        return Double.doubleToLongBits(a) == Double.doubleToLongBits(b);
    }

    private ShadyPines() {
    }
}
