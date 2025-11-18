/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.AbstractComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponentBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.AbstractComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponentBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;

abstract class NBTComponentImpl<C extends NBTComponent<C, B>, B extends NBTComponentBuilder<C, B>>
extends AbstractComponent
implements NBTComponent<C, B> {
    final boolean interpret;
    final String nbtPath;
    static final boolean INTERPRET_DEFAULT = false;
    @Nullable
    final Component separator;

    @NotNull
    public String nbtPath() {
        return this.nbtPath;
    }

    public boolean interpret() {
        return this.interpret;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NBTComponent)) {
            return false;
        }
        if (super.equals(other)) NBTComponent that;
        return Objects.equals(this.nbtPath, (that = (NBTComponent)other).nbtPath()) && this.interpret == that.interpret() && Objects.equals(this.separator, that.separator());
        return false;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + this.nbtPath.hashCode();
        result = 31 * result + Boolean.hashCode(this.interpret);
        result = 31 * result + Objects.hashCode(this.separator);
        return result;
    }

    NBTComponentImpl(@NotNull List<Component> children, @NotNull Style style, String nbtPath, boolean interpret, @Nullable Component separator) {
        super(children, style);
        this.nbtPath = nbtPath;
        this.interpret = interpret;
        this.separator = separator;
    }
}
