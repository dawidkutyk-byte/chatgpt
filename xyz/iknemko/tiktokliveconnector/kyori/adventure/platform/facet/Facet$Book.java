/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Message
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;

public static interface Facet.Book<V, M, B>
extends Facet.Message<V, M> {
    @Nullable
    public B createBook(@NotNull String var1, @NotNull String var2, @NotNull Iterable<M> var3);

    public void openBook(@NotNull V var1, @NotNull B var2);
}
