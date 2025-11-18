/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.AbstractComponentBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponentBuilder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.AbstractComponentBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponentBuilder;

abstract class AbstractNBTComponentBuilder<C extends NBTComponent<C, B>, B extends NBTComponentBuilder<C, B>>
extends AbstractComponentBuilder<C, B>
implements NBTComponentBuilder<C, B> {
    @Nullable
    protected String nbtPath;
    protected boolean interpret = false;
    @Nullable
    protected Component separator;

    AbstractNBTComponentBuilder() {
    }

    AbstractNBTComponentBuilder(@NotNull C component) {
        super(component);
        this.nbtPath = component.nbtPath();
        this.interpret = component.interpret();
        this.separator = component.separator();
    }

    @NotNull
    public B nbtPath(@NotNull String nbtPath) {
        this.nbtPath = Objects.requireNonNull(nbtPath, "nbtPath");
        return (B)((Object)this);
    }

    @NotNull
    public B interpret(boolean interpret) {
        this.interpret = interpret;
        return (B)((Object)this);
    }

    @NotNull
    public B separator(@Nullable ComponentLike separator) {
        this.separator = ComponentLike.unbox((ComponentLike)separator);
        return (B)((Object)this);
    }
}
