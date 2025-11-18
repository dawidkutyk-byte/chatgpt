/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValueConversionImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import java.util.Objects;
import java.util.function.BiFunction;
import net.kyori.examination.Examinable;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValueConversionImpl;

@ApiStatus.NonExtendable
public static interface DataComponentValueConverterRegistry.Conversion<I, O>
extends Examinable {
    @NotNull
    @Contract(pure=true)
    public Class<I> source();

    @NotNull
    @Contract(pure=true)
    public Class<O> destination();

    @NotNull
    public static <I1, O1> DataComponentValueConverterRegistry.Conversion<I1, O1> convert(@NotNull Class<I1> src, @NotNull Class<O1> dst, @NotNull BiFunction<Key, I1, O1> op) {
        return new DataComponentValueConversionImpl(Objects.requireNonNull(src, "src"), Objects.requireNonNull(dst, "dst"), Objects.requireNonNull(op, "op"));
    }

    @NotNull
    public O convert(@NotNull Key var1, @NotNull I var2);
}
