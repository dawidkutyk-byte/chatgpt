/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;

public interface VirtualComponentRenderer<C> {
    public @UnknownNullability ComponentLike apply(@NotNull C var1);

    @NotNull
    default public String fallbackString() {
        return "";
    }
}
