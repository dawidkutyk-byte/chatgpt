/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.AbstractComponentBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BuildableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponentImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.AbstractComponentBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BuildableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponentImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;

/*
 * Exception performing whole class analysis ignored.
 */
static class TextComponentImpl.BuilderImpl
extends AbstractComponentBuilder<TextComponent, TextComponent.Builder>
implements TextComponent.Builder {
    private String content = "";

    @NotNull
    public TextComponent build() {
        if (!this.isEmpty()) return TextComponentImpl.create((List)this.children, (Style)this.buildStyle(), (String)this.content);
        return Component.empty();
    }

    @NotNull
    public TextComponent.Builder content(@NotNull String content) {
        this.content = Objects.requireNonNull(content, "content");
        return this;
    }

    TextComponentImpl.BuilderImpl() {
    }

    TextComponentImpl.BuilderImpl(@NotNull TextComponent component) {
        super((BuildableComponent)component);
        this.content = component.content();
    }

    private boolean isEmpty() {
        return this.content.isEmpty() && this.children.isEmpty() && !this.hasStyle();
    }

    @NotNull
    public String content() {
        return this.content;
    }
}
