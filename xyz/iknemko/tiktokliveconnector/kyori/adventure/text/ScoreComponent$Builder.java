/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScoreComponent
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScoreComponent;

public static interface ScoreComponent.Builder
extends ComponentBuilder<ScoreComponent, ScoreComponent.Builder> {
    @NotNull
    @Contract(value="_ -> this")
    public ScoreComponent.Builder name(@NotNull String var1);

    @Deprecated
    @NotNull
    @Contract(value="_ -> this")
    public ScoreComponent.Builder value(@Nullable String var1);

    @Contract(value="_ -> this")
    @NotNull
    public ScoreComponent.Builder objective(@NotNull String var1);
}
