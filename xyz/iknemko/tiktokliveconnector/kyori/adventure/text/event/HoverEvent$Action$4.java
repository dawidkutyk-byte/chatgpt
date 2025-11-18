/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent$Action$Renderer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.ComponentRenderer
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.ComponentRenderer;

class HoverEvent.Action.4
implements HoverEvent.Action.Renderer<String> {
    @NotNull
    public <C> String render(@NotNull ComponentRenderer<C> renderer, @NotNull C context, @NotNull String value) {
        return value;
    }

    HoverEvent.Action.4() {
    }
}
