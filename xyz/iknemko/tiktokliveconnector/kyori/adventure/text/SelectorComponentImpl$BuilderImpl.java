/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.AbstractComponentBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BuildableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.SelectorComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.SelectorComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.SelectorComponentImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.AbstractComponentBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BuildableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.SelectorComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.SelectorComponentImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;

/*
 * Exception performing whole class analysis ignored.
 */
static final class SelectorComponentImpl.BuilderImpl
extends AbstractComponentBuilder<SelectorComponent, SelectorComponent.Builder>
implements SelectorComponent.Builder {
    @Nullable
    private String pattern;
    @Nullable
    private Component separator;

    @NotNull
    public SelectorComponent build() {
        if (this.pattern != null) return SelectorComponentImpl.create((List)this.children, (Style)this.buildStyle(), (String)this.pattern, (ComponentLike)this.separator);
        throw new IllegalStateException("pattern must be set");
    }

    SelectorComponentImpl.BuilderImpl() {
    }

    SelectorComponentImpl.BuilderImpl(@NotNull SelectorComponent component) {
        super((BuildableComponent)component);
        this.pattern = component.pattern();
        this.separator = component.separator();
    }

    @NotNull
    public SelectorComponent.Builder separator(@Nullable ComponentLike separator) {
        this.separator = ComponentLike.unbox((ComponentLike)separator);
        return this;
    }

    @NotNull
    public SelectorComponent.Builder pattern(@NotNull String pattern) {
        this.pattern = Objects.requireNonNull(pattern, "pattern");
        return this;
    }
}
