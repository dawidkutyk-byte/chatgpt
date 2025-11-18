/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.AbstractNBTComponentBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$Pos
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponentImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.AbstractNBTComponentBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponentImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;

/*
 * Exception performing whole class analysis ignored.
 */
static final class BlockNBTComponentImpl.BuilderImpl
extends AbstractNBTComponentBuilder<BlockNBTComponent, BlockNBTComponent.Builder>
implements BlockNBTComponent.Builder {
    @Nullable
    private BlockNBTComponent.Pos pos;

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull BlockNBTComponent.Builder pos(@NotNull BlockNBTComponent.Pos pos) {
        this.pos = Objects.requireNonNull(pos, "pos");
        return this;
    }

    BlockNBTComponentImpl.BuilderImpl(@NotNull BlockNBTComponent component) {
        super((NBTComponent)component);
        this.pos = component.pos();
    }

    BlockNBTComponentImpl.BuilderImpl() {
    }

    @NotNull
    public BlockNBTComponent build() {
        if (this.nbtPath == null) {
            throw new IllegalStateException("nbt path must be set");
        }
        if (this.pos != null) return BlockNBTComponentImpl.create((List)this.children, (Style)this.buildStyle(), (String)this.nbtPath, (boolean)this.interpret, (ComponentLike)this.separator, (BlockNBTComponent.Pos)this.pos);
        throw new IllegalStateException("pos must be set");
    }
}
