/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent$Action$Renderer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent$ShowEntity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.ComponentRenderer
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.ComponentRenderer;

/*
 * Exception performing whole class analysis ignored.
 */
class HoverEvent.Action.3
implements HoverEvent.Action.Renderer<HoverEvent.ShowEntity> {
    HoverEvent.Action.3() {
    }

    @NotNull
    public <C> HoverEvent.ShowEntity render(@NotNull ComponentRenderer<C> renderer, @NotNull C context, @NotNull HoverEvent.ShowEntity value) {
        if (HoverEvent.ShowEntity.access$100((HoverEvent.ShowEntity)value) != null) return value.name(renderer.render(HoverEvent.ShowEntity.access$100((HoverEvent.ShowEntity)value), context));
        return value;
    }
}
