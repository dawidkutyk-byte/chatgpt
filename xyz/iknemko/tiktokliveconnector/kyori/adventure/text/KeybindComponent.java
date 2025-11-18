/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BuildableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.KeybindComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.KeybindComponent$KeybindLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScopedComponent
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.Objects;
import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BuildableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.KeybindComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScopedComponent;

public interface KeybindComponent
extends BuildableComponent<KeybindComponent, Builder>,
ScopedComponent<KeybindComponent> {
    @NotNull
    @Contract(pure=true)
    default public KeybindComponent keybind(@NotNull KeybindLike keybind) {
        return this.keybind(Objects.requireNonNull(keybind, "keybind").asKeybind());
    }

    @NotNull
    default public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.concat(Stream.of(ExaminableProperty.of((String)"keybind", (String)this.keybind())), super.examinableProperties());
    }

    @NotNull
    public String keybind();

    @NotNull
    @Contract(pure=true)
    public KeybindComponent keybind(@NotNull String var1);
}
