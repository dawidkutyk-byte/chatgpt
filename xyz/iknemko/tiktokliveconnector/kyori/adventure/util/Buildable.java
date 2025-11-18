/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable$Builder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.util;

import java.util.function.Consumer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable;

public interface Buildable<R, B extends Builder<R>> {
    @Contract(value="-> new", pure=true)
    @NotNull
    public B toBuilder();

    @Deprecated
    @NotNull
    @Contract(mutates="param1")
    public static <R extends Buildable<R, B>, B extends Builder<R>> R configureAndBuild(@NotNull B builder, @Nullable Consumer<? super B> consumer) {
        return (R)((Buildable)AbstractBuilder.configureAndBuild(builder, consumer));
    }
}
