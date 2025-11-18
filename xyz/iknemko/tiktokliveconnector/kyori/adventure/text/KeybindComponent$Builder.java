/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.KeybindComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.KeybindComponent$KeybindLike
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.Objects;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.KeybindComponent;

public static interface KeybindComponent.Builder
extends ComponentBuilder<KeybindComponent, KeybindComponent.Builder> {
    @Contract(value="_ -> this")
    @NotNull
    public KeybindComponent.Builder keybind(@NotNull String var1);

    @Contract(pure=true)
    @NotNull
    default public KeybindComponent.Builder keybind(@NotNull KeybindComponent.KeybindLike keybind) {
        return this.keybind(Objects.requireNonNull(keybind, "keybind").asKeybind());
    }
}
