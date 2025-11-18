/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$WorldPos$Coordinate
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$WorldPos$Coordinate$Type
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.Objects;
import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent;

static final class BlockNBTComponentImpl.WorldPosImpl.CoordinateImpl
implements BlockNBTComponent.WorldPos.Coordinate {
    private final BlockNBTComponent.WorldPos.Coordinate.Type type;
    private final int value;

    public int value() {
        return this.value;
    }

    public int hashCode() {
        int result = this.value;
        result = 31 * result + this.type.hashCode();
        return result;
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"value", (int)this.value), ExaminableProperty.of((String)"type", (Object)this.type));
    }

    public String toString() {
        return (this.type == BlockNBTComponent.WorldPos.Coordinate.Type.RELATIVE ? "~" : "") + this.value;
    }

    BlockNBTComponentImpl.WorldPosImpl.CoordinateImpl(int value, @NotNull BlockNBTComponent.WorldPos.Coordinate.Type type) {
        this.value = value;
        this.type = Objects.requireNonNull(type, "type");
    }

    @NotNull
    public BlockNBTComponent.WorldPos.Coordinate.Type type() {
        return this.type;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BlockNBTComponent.WorldPos.Coordinate)) {
            return false;
        }
        BlockNBTComponent.WorldPos.Coordinate that = (BlockNBTComponent.WorldPos.Coordinate)other;
        return this.value() == that.value() && this.type() == that.type();
    }
}
