/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Message
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet;

import java.time.Duration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;

public static interface Facet.Title<V, M, C, T>
extends Facet.Message<V, M> {
    public static final long MAX_SECONDS = 0x666666666666666L;
    public static final int PROTOCOL_ACTION_BAR = 310;

    public void contributeSubtitle(@NotNull C var1, @NotNull M var2);

    public void showTitle(@NotNull V var1, @NotNull T var2);

    public void contributeTimes(@NotNull C var1, int var2, int var3, int var4);

    @NotNull
    public C createTitleCollection();

    public void clearTitle(@NotNull V var1);

    default public int toTicks(@Nullable Duration duration) {
        if (duration == null) return -1;
        if (duration.isNegative()) {
            return -1;
        }
        if (duration.getSeconds() <= 0x666666666666666L) return (int)(duration.getSeconds() * 20L + (long)(duration.getNano() / 50000000));
        return Integer.MAX_VALUE;
    }

    public void resetTitle(@NotNull V var1);

    @Nullable
    public T completeTitle(@NotNull C var1);

    public void contributeTitle(@NotNull C var1, @NotNull M var2);
}
