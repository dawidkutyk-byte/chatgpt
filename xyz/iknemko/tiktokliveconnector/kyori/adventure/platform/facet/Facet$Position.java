/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;

public static interface Facet.Position<V, P>
extends Facet<V> {
    @NotNull
    public P createPosition(double var1, double var3, double var5);

    @Nullable
    public P createPosition(@NotNull V var1);
}
