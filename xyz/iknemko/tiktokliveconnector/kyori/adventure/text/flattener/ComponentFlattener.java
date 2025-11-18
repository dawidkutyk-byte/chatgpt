/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattener$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattenerImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattenerImpl$BuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.FlattenerListener
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattener;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattenerImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.FlattenerListener;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable;

public interface ComponentFlattener
extends Buildable<ComponentFlattener, Builder> {
    public static final int NO_NESTING_LIMIT = -1;

    public void flatten(@NotNull Component var1, @NotNull FlattenerListener var2);

    @NotNull
    public static Builder builder() {
        return new ComponentFlattenerImpl.BuilderImpl();
    }

    @NotNull
    public static ComponentFlattener basic() {
        return ComponentFlattenerImpl.BASIC;
    }

    @NotNull
    public static ComponentFlattener textOnly() {
        return ComponentFlattenerImpl.TEXT_ONLY;
    }
}
