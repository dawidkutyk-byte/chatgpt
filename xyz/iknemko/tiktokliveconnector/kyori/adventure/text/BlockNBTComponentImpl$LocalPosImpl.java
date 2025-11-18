/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$LocalPos
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponentImpl$Tokens
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.ShadyPines
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponentImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.ShadyPines;

/*
 * Exception performing whole class analysis ignored.
 */
static final class BlockNBTComponentImpl.LocalPosImpl
implements BlockNBTComponent.LocalPos {
    private final double up;
    private final double left;
    private final double forwards;

    BlockNBTComponentImpl.LocalPosImpl(double left, double up, double forwards) {
        this.left = left;
        this.up = up;
        this.forwards = forwards;
    }

    public double left() {
        return this.left;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        BlockNBTComponent.LocalPos that = (BlockNBTComponent.LocalPos)other;
        if (other instanceof BlockNBTComponent.LocalPos) return ShadyPines.equals((double)that.left(), (double)this.left()) && ShadyPines.equals((double)that.up(), (double)this.up()) && ShadyPines.equals((double)that.forwards(), (double)this.forwards());
        return false;
    }

    public String toString() {
        return String.format("^%f ^%f ^%f", this.left, this.up, this.forwards);
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"left", (double)this.left), ExaminableProperty.of((String)"up", (double)this.up), ExaminableProperty.of((String)"forwards", (double)this.forwards));
    }

    public int hashCode() {
        int result = Double.hashCode(this.left);
        result = 31 * result + Double.hashCode(this.up);
        result = 31 * result + Double.hashCode(this.forwards);
        return result;
    }

    public double up() {
        return this.up;
    }

    @NotNull
    public String asString() {
        return BlockNBTComponentImpl.Tokens.serializeLocal((double)this.left) + ' ' + BlockNBTComponentImpl.Tokens.serializeLocal((double)this.up) + ' ' + BlockNBTComponentImpl.Tokens.serializeLocal((double)this.forwards);
    }

    public double forwards() {
        return this.forwards;
    }
}
