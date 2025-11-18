/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Keyed
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.KeyedValueImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.key;

import java.util.Objects;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Keyed;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.KeyedValueImpl;

public interface KeyedValue<T>
extends Keyed {
    @Deprecated
    @NotNull
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    public static <T> KeyedValue<T> of(@NotNull Key key, @NotNull T value) {
        return new KeyedValueImpl(key, Objects.requireNonNull(value, "value"));
    }

    @NotNull
    public T value();

    @NotNull
    public static <T> KeyedValue<T> keyedValue(@NotNull Key key, @NotNull T value) {
        return new KeyedValueImpl(key, Objects.requireNonNull(value, "value"));
    }
}
