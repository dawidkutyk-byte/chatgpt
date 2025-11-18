/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound;

public static interface Facet.EntitySound<V, M>
extends Facet<V> {
    public M createForSelf(V var1, @NotNull Sound var2);

    public M createForEmitter(@NotNull Sound var1, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Sound.Emitter var2);

    public void playSound(@NotNull V var1, M var2);
}
