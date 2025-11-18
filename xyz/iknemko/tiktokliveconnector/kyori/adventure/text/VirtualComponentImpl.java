/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponentImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponentImpl$BuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponentRenderer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponentImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponentImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponentRenderer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;

final class VirtualComponentImpl<C>
extends TextComponentImpl
implements VirtualComponent {
    private final Class<C> contextType;
    private final VirtualComponentRenderer<C> renderer;

    private VirtualComponentImpl(@NotNull List<Component> children, @NotNull Style style, @NotNull String content, @NotNull Class<C> contextType, @NotNull VirtualComponentRenderer<C> renderer) {
        super(children, style, content);
        this.contextType = contextType;
        this.renderer = renderer;
    }

    VirtualComponent create0(@NotNull List<? extends ComponentLike> children, @NotNull Style style, @NotNull String content) {
        return new VirtualComponentImpl<C>(ComponentLike.asComponents(children, (Predicate)IS_NOT_EMPTY), style, content, this.contextType, this.renderer);
    }

    static <C> VirtualComponent createVirtual(@NotNull Class<C> contextType, @NotNull VirtualComponentRenderer<C> renderer) {
        return VirtualComponentImpl.createVirtual(contextType, renderer, Collections.emptyList(), Style.empty());
    }

    static <C> VirtualComponent createVirtual(@NotNull Class<C> contextType, @NotNull VirtualComponentRenderer<C> renderer, List<? extends ComponentLike> children, Style style) {
        List filteredChildren = ComponentLike.asComponents(children, (Predicate)IS_NOT_EMPTY);
        return new VirtualComponentImpl<C>(filteredChildren, style, "", contextType, renderer);
    }

    @NotNull
    public VirtualComponentRenderer<C> renderer() {
        return this.renderer;
    }

    @NotNull
    public String content() {
        return this.renderer.fallbackString();
    }

    @NotNull
    public Class<C> contextType() {
        return this.contextType;
    }

    @NotNull
    public TextComponent.Builder toBuilder() {
        return new BuilderImpl(this);
    }
}
