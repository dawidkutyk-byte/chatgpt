/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.HSVLike
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.HSVLike;

public interface RGBLike {
    @NotNull
    default public HSVLike asHSV() {
        return HSVLike.fromRGB((int)this.red(), (int)this.green(), (int)this.blue());
    }

    public @Range(from=0L, to=255L) int blue();

    public @Range(from=0L, to=255L) int red();

    public @Range(from=0L, to=255L) int green();
}
