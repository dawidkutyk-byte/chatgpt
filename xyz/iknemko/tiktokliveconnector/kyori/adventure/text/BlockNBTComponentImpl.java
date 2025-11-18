/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$Pos
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponentImpl$BuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponentImpl
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
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponentImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponentImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;

final class BlockNBTComponentImpl
extends NBTComponentImpl<BlockNBTComponent, BlockNBTComponent.Builder>
implements BlockNBTComponent {
    private final BlockNBTComponent.Pos pos;

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + this.pos.hashCode();
        return result;
    }

    @NotNull
    public BlockNBTComponent pos(@NotNull BlockNBTComponent.Pos pos) {
        return BlockNBTComponentImpl.create(this.children, this.style, this.nbtPath, this.interpret, (ComponentLike)this.separator, pos);
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull BlockNBTComponent.Builder toBuilder() {
        return new BuilderImpl((BlockNBTComponent)this);
    }

    @Nullable
    public Component separator() {
        return this.separator;
    }

    @NotNull
    public BlockNBTComponent interpret(boolean interpret) {
        if (this.interpret != interpret) return BlockNBTComponentImpl.create(this.children, this.style, this.nbtPath, interpret, (ComponentLike)this.separator, this.pos);
        return this;
    }

    @NotNull
    public BlockNBTComponent children(@NotNull List<? extends ComponentLike> children) {
        return BlockNBTComponentImpl.create(children, this.style, this.nbtPath, this.interpret, (ComponentLike)this.separator, this.pos);
    }

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    @NotNull
    public BlockNBTComponent separator(@Nullable ComponentLike separator) {
        return BlockNBTComponentImpl.create(this.children, this.style, this.nbtPath, this.interpret, separator, this.pos);
    }

    BlockNBTComponentImpl(@NotNull List<Component> children, @NotNull Style style, String nbtPath, boolean interpret, @Nullable Component separator, @NotNull BlockNBTComponent.Pos pos) {
        super(children, style, nbtPath, interpret, separator);
        this.pos = pos;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BlockNBTComponent)) {
            return false;
        }
        if (!super.equals(other)) {
            return false;
        }
        BlockNBTComponent that = (BlockNBTComponent)other;
        return Objects.equals(this.pos, that.pos());
    }

    @NotNull
    public BlockNBTComponent.Pos pos() {
        return this.pos;
    }

    @NotNull
    public BlockNBTComponent style(@NotNull Style style) {
        return BlockNBTComponentImpl.create(this.children, style, this.nbtPath, this.interpret, (ComponentLike)this.separator, this.pos);
    }

    @NotNull
    public BlockNBTComponent nbtPath(@NotNull String nbtPath) {
        if (!Objects.equals(this.nbtPath, nbtPath)) return BlockNBTComponentImpl.create(this.children, this.style, nbtPath, this.interpret, (ComponentLike)this.separator, this.pos);
        return this;
    }

    static BlockNBTComponent create(@NotNull List<? extends ComponentLike> children, @NotNull Style style, String nbtPath, boolean interpret, @Nullable ComponentLike separator, @NotNull BlockNBTComponent.Pos pos) {
        return new BlockNBTComponentImpl(ComponentLike.asComponents(children, (Predicate)IS_NOT_EMPTY), Objects.requireNonNull(style, "style"), Objects.requireNonNull(nbtPath, "nbtPath"), interpret, ComponentLike.unbox((ComponentLike)separator), Objects.requireNonNull(pos, "pos"));
    }
}
