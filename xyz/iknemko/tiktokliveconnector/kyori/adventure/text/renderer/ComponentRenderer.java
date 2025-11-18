/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer;

import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;

public interface ComponentRenderer<C> {
    default public <T> ComponentRenderer<T> mapContext(Function<T, C> transformer) {
        return (component, ctx) -> this.render(component, transformer.apply(ctx));
    }

    @NotNull
    public Component render(@NotNull Component var1, @NotNull C var2);
}
