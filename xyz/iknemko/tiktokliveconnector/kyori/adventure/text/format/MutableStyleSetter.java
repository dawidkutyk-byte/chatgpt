/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleSetter
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration$State
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleSetter;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration;

@ApiStatus.NonExtendable
public interface MutableStyleSetter<T extends MutableStyleSetter<?>>
extends StyleSetter<T> {
    @Contract(value="_ -> this")
    @NotNull
    default public T decorate(TextDecoration ... decorations) {
        int i = 0;
        int length = decorations.length;
        while (i < length) {
            this.decorate(decorations[i]);
            ++i;
        }
        return (T)this;
    }

    @Contract(value="_ -> this")
    @NotNull
    default public T decorations(@NotNull Map<TextDecoration, TextDecoration.State> decorations) {
        Objects.requireNonNull(decorations, "decorations");
        Iterator<Map.Entry<TextDecoration, TextDecoration.State>> iterator = decorations.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<TextDecoration, TextDecoration.State> entry = iterator.next();
            this.decoration(entry.getKey(), entry.getValue());
        }
        return (T)this;
    }

    @Contract(value="_, _ -> this")
    @NotNull
    default public T decorations(@NotNull Set<TextDecoration> decorations, boolean flag) {
        TextDecoration.State state = TextDecoration.State.byBoolean((boolean)flag);
        decorations.forEach(decoration -> this.decoration((TextDecoration)decoration, state));
        return (T)this;
    }
}
