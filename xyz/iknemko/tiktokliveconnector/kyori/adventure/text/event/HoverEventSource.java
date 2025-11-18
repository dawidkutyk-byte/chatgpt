/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import java.util.function.UnaryOperator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent;

public interface HoverEventSource<V> {
    @NotNull
    default public HoverEvent<V> asHoverEvent() {
        return this.asHoverEvent(UnaryOperator.identity());
    }

    @Nullable
    public static <V> HoverEvent<V> unbox(@Nullable HoverEventSource<V> source) {
        return source != null ? source.asHoverEvent() : null;
    }

    @NotNull
    public HoverEvent<V> asHoverEvent(@NotNull UnaryOperator<V> var1);
}
