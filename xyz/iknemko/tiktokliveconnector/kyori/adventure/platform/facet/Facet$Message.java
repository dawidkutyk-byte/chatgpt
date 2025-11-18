/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;

public static interface Facet.Message<V, M>
extends Facet<V> {
    public static final int PROTOCOL_JSON = 5;
    public static final int PROTOCOL_HEX_COLOR = 713;

    @Nullable
    public M createMessage(@NotNull V var1, @NotNull Component var2);
}
