/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder;

@Deprecated
public static interface Buildable.Builder<R>
extends AbstractBuilder<R> {
    @NotNull
    @Contract(value="-> new", pure=true)
    public R build();
}
