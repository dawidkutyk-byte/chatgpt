/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;

@FunctionalInterface
public interface FlattenerListener {
    default public boolean shouldContinue() {
        return true;
    }

    public void component(@NotNull String var1);

    default public void popStyle(@NotNull Style style) {
    }

    default public void pushStyle(@NotNull Style style) {
    }
}
