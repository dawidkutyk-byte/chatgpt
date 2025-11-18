/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilderApplicable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponentImpl$BuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilderApplicable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponentImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable;

public final class LinearComponents {
    private LinearComponents() {
    }

    private static IllegalStateException nothingComponentLike() {
        return new IllegalStateException("Cannot build component linearly - nothing component-like was given");
    }

    @NotNull
    public static Component linear(ComponentBuilderApplicable ... applicables) {
        int length = applicables.length;
        if (length == 0) {
            return Component.empty();
        }
        if (length == 1) {
            ComponentBuilderApplicable ap0 = applicables[0];
            if (!(ap0 instanceof ComponentLike)) throw LinearComponents.nothingComponentLike();
            return ((ComponentLike)ap0).asComponent();
        }
        TextComponentImpl.BuilderImpl builder = new TextComponentImpl.BuilderImpl();
        Style.Builder style = null;
        for (int i = 0; i < length; ++i) {
            ComponentBuilderApplicable applicable = applicables[i];
            if (applicable instanceof StyleBuilderApplicable) {
                if (style == null) {
                    style = Style.style();
                }
                style.apply((StyleBuilderApplicable)applicable);
                continue;
            }
            if (style != null && applicable instanceof ComponentLike) {
                builder.applicableApply((ComponentBuilderApplicable)((ComponentLike)applicable).asComponent().style(style));
                continue;
            }
            builder.applicableApply(applicable);
        }
        int size = builder.children.size();
        if (size == 0) {
            throw LinearComponents.nothingComponentLike();
        }
        if (size != 1) return builder.build();
        if (builder.hasStyle()) return builder.build();
        return (Component)builder.children.get(0);
    }
}
