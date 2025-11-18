/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$WorldPos
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$WorldPos$Coordinate
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponentImpl$Tokens
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.Objects;
import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponentImpl;

/*
 * Exception performing whole class analysis ignored.
 */
static final class BlockNBTComponentImpl.WorldPosImpl
implements BlockNBTComponent.WorldPos {
    private final BlockNBTComponent.WorldPos.Coordinate x;
    private final BlockNBTComponent.WorldPos.Coordinate y;
    private final BlockNBTComponent.WorldPos.Coordinate z;

    BlockNBTComponentImpl.WorldPosImpl(BlockNBTComponent.WorldPos.Coordinate x, BlockNBTComponent.WorldPos.Coordinate y, BlockNBTComponent.WorldPos.Coordinate z) {
        this.x = Objects.requireNonNull(x, "x");
        this.y = Objects.requireNonNull(y, "y");
        this.z = Objects.requireNonNull(z, "z");
    }

    @NotNull
    public BlockNBTComponent.WorldPos.Coordinate x() {
        return this.x;
    }

    @NotNull
    public BlockNBTComponent.WorldPos.Coordinate y() {
        return this.y;
    }

    public int hashCode() {
        int result = this.x.hashCode();
        result = 31 * result + this.y.hashCode();
        result = 31 * result + this.z.hashCode();
        return result;
    }

    public String toString() {
        return this.x.toString() + ' ' + this.y.toString() + ' ' + this.z.toString();
    }

    @NotNull
    public String asString() {
        return BlockNBTComponentImpl.Tokens.serializeCoordinate((BlockNBTComponent.WorldPos.Coordinate)this.x()) + ' ' + BlockNBTComponentImpl.Tokens.serializeCoordinate((BlockNBTComponent.WorldPos.Coordinate)this.y()) + ' ' + BlockNBTComponentImpl.Tokens.serializeCoordinate((BlockNBTComponent.WorldPos.Coordinate)this.z());
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        BlockNBTComponent.WorldPos that = (BlockNBTComponent.WorldPos)other;
        if (other instanceof BlockNBTComponent.WorldPos) return this.x.equals(that.x()) && this.y.equals(that.y()) && this.z.equals(that.z());
        return false;
    }

    @NotNull
    public BlockNBTComponent.WorldPos.Coordinate z() {
        return this.z;
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"x", (Object)this.x), ExaminableProperty.of((String)"y", (Object)this.y), ExaminableProperty.of((String)"z", (Object)this.z));
    }
}
