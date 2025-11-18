/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;

public interface ComponentDecoder<S, O extends Component> {
    @NotNull
    public O deserialize(@NotNull S var1);

    @Nullable
    @Contract(value="!null, _ -> !null; null, _ -> param2", pure=true)
    default public O deserializeOr(@Nullable S input, @Nullable O fallback) {
        if (input != null) return this.deserialize(input);
        return fallback;
    }

    @Contract(value="!null -> !null; null -> null", pure=true)
    @Nullable
    default public O deserializeOrNull(@Nullable S input) {
        return this.deserializeOr(input, null);
    }
}
