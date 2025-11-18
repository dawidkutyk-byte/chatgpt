/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponentImpl$BuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponentImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponentRenderer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponentImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponentImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponentRenderer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;

/*
 * Exception performing whole class analysis ignored.
 */
static final class VirtualComponentImpl.BuilderImpl<C>
extends TextComponentImpl.BuilderImpl {
    private final Class<C> contextType;
    private final VirtualComponentRenderer<C> renderer;

    VirtualComponentImpl.BuilderImpl(VirtualComponentImpl<C> other) {
        super(other);
        this.contextType = other.contextType();
        this.renderer = other.renderer();
    }

    @NotNull
    public TextComponent build() {
        return VirtualComponentImpl.createVirtual(this.contextType, this.renderer, (List)this.children, (Style)this.buildStyle());
    }
}
