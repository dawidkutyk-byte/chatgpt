/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilder;

@FunctionalInterface
public interface ComponentBuilderApplicable {
    @Contract(mutates="param")
    public void componentBuilderApply(@NotNull ComponentBuilder<?, ?> var1);
}
