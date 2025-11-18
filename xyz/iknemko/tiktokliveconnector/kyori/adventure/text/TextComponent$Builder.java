/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent;

public static interface TextComponent.Builder
extends ComponentBuilder<TextComponent, TextComponent.Builder> {
    @NotNull
    public String content();

    @Contract(value="_ -> this")
    @NotNull
    public TextComponent.Builder content(@NotNull String var1);
}
