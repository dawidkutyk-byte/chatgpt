/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration$State
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecorationAndState
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format;

import java.util.Objects;
import net.kyori.examination.Examinable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecorationAndState;

final class TextDecorationAndStateImpl
implements TextDecorationAndState {
    private final TextDecoration decoration;
    private final TextDecoration.State state;

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    public int hashCode() {
        int result = this.decoration.hashCode();
        result = 31 * result + this.state.hashCode();
        return result;
    }

    @NotNull
    public TextDecoration decoration() {
        return this.decoration;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (this.getClass() != other.getClass()) {
            return false;
        }
        TextDecorationAndStateImpl that = (TextDecorationAndStateImpl)other;
        return this.decoration == that.decoration && this.state == that.state;
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TextDecoration.State state() {
        return this.state;
    }

    TextDecorationAndStateImpl(TextDecoration decoration, TextDecoration.State state) {
        this.decoration = decoration;
        this.state = Objects.requireNonNull(state, "state");
    }
}
