/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.AbstractComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.KeybindComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.KeybindComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.KeybindComponentImpl$BuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import net.kyori.examination.Examinable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.AbstractComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.KeybindComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.KeybindComponentImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;

final class KeybindComponentImpl
extends AbstractComponent
implements KeybindComponent {
    private final String keybind;

    @NotNull
    public String keybind() {
        return this.keybind;
    }

    @NotNull
    public KeybindComponent children(@NotNull List<? extends ComponentLike> children) {
        return KeybindComponentImpl.create(children, this.style, this.keybind);
    }

    static KeybindComponent create(@NotNull List<? extends ComponentLike> children, @NotNull Style style, @NotNull String keybind) {
        return new KeybindComponentImpl(ComponentLike.asComponents(children, (Predicate)IS_NOT_EMPTY), Objects.requireNonNull(style, "style"), Objects.requireNonNull(keybind, "keybind"));
    }

    @NotNull
    public KeybindComponent.Builder toBuilder() {
        return new BuilderImpl((KeybindComponent)this);
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + this.keybind.hashCode();
        return result;
    }

    KeybindComponentImpl(@NotNull List<Component> children, @NotNull Style style, @NotNull String keybind) {
        super(children, style);
        this.keybind = keybind;
    }

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KeybindComponent)) {
            return false;
        }
        if (!super.equals(other)) {
            return false;
        }
        KeybindComponent that = (KeybindComponent)other;
        return Objects.equals(this.keybind, that.keybind());
    }

    @NotNull
    public KeybindComponent style(@NotNull Style style) {
        return KeybindComponentImpl.create(this.children, style, this.keybind);
    }

    @NotNull
    public KeybindComponent keybind(@NotNull String keybind) {
        if (!Objects.equals(this.keybind, keybind)) return KeybindComponentImpl.create(this.children, this.style, keybind);
        return this;
    }
}
