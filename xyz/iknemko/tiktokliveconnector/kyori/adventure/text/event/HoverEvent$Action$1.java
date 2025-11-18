/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent$Action$Renderer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.ComponentRenderer
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.ComponentRenderer;

class HoverEvent.Action.1
implements HoverEvent.Action.Renderer<Component> {
    HoverEvent.Action.1() {
    }

    @NotNull
    public <C> Component render(@NotNull ComponentRenderer<C> renderer, @NotNull C context, @NotNull Component value) {
        return renderer.render(value, context);
    }
}
