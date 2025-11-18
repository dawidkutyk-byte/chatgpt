/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.JoinConfiguration
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable$Builder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.function.Function;
import java.util.function.Predicate;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.JoinConfiguration;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable;

public static interface JoinConfiguration.Builder
extends AbstractBuilder<JoinConfiguration>,
Buildable.Builder<JoinConfiguration> {
    @Contract(value="_ -> this")
    @NotNull
    public JoinConfiguration.Builder lastSeparatorIfSerial(@Nullable ComponentLike var1);

    @Contract(value="_ -> this")
    @NotNull
    public JoinConfiguration.Builder parentStyle(@NotNull Style var1);

    @NotNull
    @Contract(value="_ -> this")
    public JoinConfiguration.Builder prefix(@Nullable ComponentLike var1);

    @Contract(value="_ -> this")
    @NotNull
    public JoinConfiguration.Builder lastSeparator(@Nullable ComponentLike var1);

    @NotNull
    @Contract(value="_ -> this")
    public JoinConfiguration.Builder convertor(@NotNull Function<ComponentLike, Component> var1);

    @NotNull
    @Contract(value="_ -> this")
    public JoinConfiguration.Builder separator(@Nullable ComponentLike var1);

    @Contract(value="_ -> this")
    @NotNull
    public JoinConfiguration.Builder suffix(@Nullable ComponentLike var1);

    @NotNull
    @Contract(value="_ -> this")
    public JoinConfiguration.Builder predicate(@NotNull Predicate<ComponentLike> var1);
}
