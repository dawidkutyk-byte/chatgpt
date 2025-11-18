/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponent
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponent;

public interface NBTComponentBuilder<C extends NBTComponent<C, B>, B extends NBTComponentBuilder<C, B>>
extends ComponentBuilder<C, B> {
    @NotNull
    @Contract(value="_ -> this")
    public B nbtPath(@NotNull String var1);

    @Contract(value="_ -> this")
    @NotNull
    public B separator(@Nullable ComponentLike var1);

    @Contract(value="_ -> this")
    @NotNull
    public B interpret(boolean var1);
}
