/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.builder;

import java.util.function.Consumer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface AbstractBuilder<R> {
    @NotNull
    @Contract(mutates="param1")
    public static <R, B extends AbstractBuilder<R>> R configureAndBuild(@NotNull B builder, @Nullable Consumer<? super B> consumer) {
        if (consumer == null) return builder.build();
        consumer.accept(builder);
        return builder.build();
    }

    @Contract(value="-> new", pure=true)
    @NotNull
    public R build();
}
