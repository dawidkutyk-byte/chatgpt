/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.ComponentRenderer
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.ComponentRenderer;

@FunctionalInterface
static interface HoverEvent.Action.Renderer<V> {
    @NotNull
    public <C> V render(@NotNull ComponentRenderer<C> var1, @NotNull C var2, @NotNull V var3);
}
