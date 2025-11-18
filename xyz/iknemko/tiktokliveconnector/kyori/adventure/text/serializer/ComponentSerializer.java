/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.ComponentDecoder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.ComponentEncoder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.ComponentDecoder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.ComponentEncoder;

public interface ComponentSerializer<I extends Component, O extends Component, R>
extends ComponentEncoder<I, R>,
ComponentDecoder<R, O> {
    @Nullable
    @Contract(value="!null, _ -> !null; null, _ -> param2", pure=true)
    default public O deserializeOr(@Nullable R input, @Nullable O fallback) {
        return (O)super.deserializeOr(input, fallback);
    }

    @Deprecated
    @Contract(value="!null -> !null; null -> null", pure=true)
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    @Nullable
    default public O deseializeOrNull(@Nullable R input) {
        return (O)super.deserializeOrNull(input);
    }

    @Nullable
    @Contract(value="!null -> !null; null -> null", pure=true)
    default public O deserializeOrNull(@Nullable R input) {
        return (O)super.deserializeOr(input, null);
    }

    @NotNull
    public R serialize(@NotNull I var1);

    @Nullable
    @Contract(value="!null -> !null; null -> null", pure=true)
    default public R serializeOrNull(@Nullable I component) {
        return this.serializeOr(component, null);
    }

    @Nullable
    @Contract(value="!null, _ -> !null; null, _ -> param2", pure=true)
    default public R serializeOr(@Nullable I component, @Nullable R fallback) {
        if (component != null) return this.serialize(component);
        return fallback;
    }

    @NotNull
    public O deserialize(@NotNull R var1);
}
