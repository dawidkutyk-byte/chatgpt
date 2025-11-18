/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilderApplicable
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilderApplicable;

@FunctionalInterface
public interface StyleBuilderApplicable
extends ComponentBuilderApplicable {
    @Contract(mutates="param")
    public void styleApply(// Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Style.Builder var1);

    default public void componentBuilderApply(@NotNull ComponentBuilder<?, ?> component) {
        component.style(this::styleApply);
    }
}
