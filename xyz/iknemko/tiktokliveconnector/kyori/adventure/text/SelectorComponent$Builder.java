/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.SelectorComponent
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.SelectorComponent;

public static interface SelectorComponent.Builder
extends ComponentBuilder<SelectorComponent, SelectorComponent.Builder> {
    @Contract(value="_ -> this")
    @NotNull
    public SelectorComponent.Builder pattern(@NotNull String var1);

    @Contract(value="_ -> this")
    @NotNull
    public SelectorComponent.Builder separator(@Nullable ComponentLike var1);
}
