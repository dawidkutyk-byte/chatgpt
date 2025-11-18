/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson;

import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration;

final class TextColorWrapper {
    @Nullable
    final TextColor color;
    @Nullable
    final TextDecoration decoration;
    final boolean reset;

    TextColorWrapper(@Nullable TextColor color, @Nullable TextDecoration decoration, boolean reset) {
        this.color = color;
        this.decoration = decoration;
        this.reset = reset;
    }
}
