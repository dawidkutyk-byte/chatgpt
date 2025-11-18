/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.Translatable
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.Translatable;

public static interface TranslatableComponent.Builder
extends ComponentBuilder<TranslatableComponent, TranslatableComponent.Builder> {
    @Deprecated
    @NotNull
    @Contract(value="_ -> this")
    default public TranslatableComponent.Builder args(@NotNull Component arg) {
        return this.arguments(new ComponentLike[]{arg});
    }

    @NotNull
    @Contract(value="_ -> this")
    public TranslatableComponent.Builder key(@NotNull String var1);

    @Contract(pure=true)
    @NotNull
    default public TranslatableComponent.Builder key(@NotNull Translatable translatable) {
        return this.key(Objects.requireNonNull(translatable, "translatable").translationKey());
    }

    @Deprecated
    @Contract(value="_ -> this")
    @NotNull
    default public TranslatableComponent.Builder args(@NotNull ComponentBuilder<?, ?> arg) {
        return this.arguments(new ComponentLike[]{arg});
    }

    @Contract(value="_ -> this")
    @NotNull
    public TranslatableComponent.Builder arguments(ComponentLike ... var1);

    @Contract(value="_ -> this")
    @NotNull
    public TranslatableComponent.Builder arguments(@NotNull List<? extends ComponentLike> var1);

    @NotNull
    @Contract(value="_ -> this")
    public TranslatableComponent.Builder fallback(@Nullable String var1);

    @Deprecated
    @Contract(value="_ -> this")
    @NotNull
    default public TranslatableComponent.Builder args(ComponentBuilder<?, ?> ... args) {
        return this.arguments((ComponentLike[])args);
    }

    @Deprecated
    @NotNull
    @Contract(value="_ -> this")
    default public TranslatableComponent.Builder args(ComponentLike ... args) {
        return this.arguments(args);
    }

    @Deprecated
    @Contract(value="_ -> this")
    @NotNull
    default public TranslatableComponent.Builder args(@NotNull List<? extends ComponentLike> args) {
        return this.arguments(args);
    }
}
