/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.AbstractComponentBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BuildableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.KeybindComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.KeybindComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.KeybindComponentImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.AbstractComponentBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BuildableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.KeybindComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.KeybindComponentImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;

/*
 * Exception performing whole class analysis ignored.
 */
static final class KeybindComponentImpl.BuilderImpl
extends AbstractComponentBuilder<KeybindComponent, KeybindComponent.Builder>
implements KeybindComponent.Builder {
    @Nullable
    private String keybind;

    @NotNull
    public KeybindComponent.Builder keybind(@NotNull String keybind) {
        this.keybind = Objects.requireNonNull(keybind, "keybind");
        return this;
    }

    KeybindComponentImpl.BuilderImpl() {
    }

    @NotNull
    public KeybindComponent build() {
        if (this.keybind != null) return KeybindComponentImpl.create((List)this.children, (Style)this.buildStyle(), (String)this.keybind);
        throw new IllegalStateException("keybind must be set");
    }

    KeybindComponentImpl.BuilderImpl(@NotNull KeybindComponent component) {
        super((BuildableComponent)component);
        this.keybind = component.keybind();
    }
}
