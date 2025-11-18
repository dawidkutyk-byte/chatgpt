/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Position
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet;

import java.io.Closeable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;

public static interface Facet.FakeEntity<V, P>
extends Facet.Position<V, P>,
Closeable {
    public void health(float var1);

    public void teleport(@NotNull V var1, @Nullable P var2);

    public void invisible(boolean var1);

    @Override
    public void close();

    public void metadata(int var1, @NotNull Object var2);

    public void name(@NotNull Component var1);
}
