/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$LocalPos
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$Pos
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$WorldPos
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$WorldPos$Coordinate
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScopedComponent
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScopedComponent;

/*
 * Exception performing whole class analysis ignored.
 */
public interface BlockNBTComponent
extends NBTComponent<BlockNBTComponent, Builder>,
ScopedComponent<BlockNBTComponent> {
    @NotNull
    public Pos pos();

    @NotNull
    @Contract(pure=true)
    default public BlockNBTComponent worldPos(// Could not load outer class - annotation placement on inner may be incorrect
    @NotNull BlockNBTComponent.WorldPos.Coordinate x, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull BlockNBTComponent.WorldPos.Coordinate y, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull BlockNBTComponent.WorldPos.Coordinate z) {
        return this.pos((Pos)WorldPos.worldPos((WorldPos.Coordinate)x, (WorldPos.Coordinate)y, (WorldPos.Coordinate)z));
    }

    @Contract(pure=true)
    @NotNull
    public BlockNBTComponent pos(@NotNull Pos var1);

    @Contract(pure=true)
    @NotNull
    default public BlockNBTComponent localPos(double left, double up, double forwards) {
        return this.pos((Pos)LocalPos.localPos((double)left, (double)up, (double)forwards));
    }

    @NotNull
    @Contract(pure=true)
    default public BlockNBTComponent relativeWorldPos(int x, int y, int z) {
        return this.worldPos(WorldPos.Coordinate.relative((int)x), WorldPos.Coordinate.relative((int)y), WorldPos.Coordinate.relative((int)z));
    }

    @Contract(pure=true)
    @NotNull
    default public BlockNBTComponent absoluteWorldPos(int x, int y, int z) {
        return this.worldPos(WorldPos.Coordinate.absolute((int)x), WorldPos.Coordinate.absolute((int)y), WorldPos.Coordinate.absolute((int)z));
    }

    @NotNull
    default public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.concat(Stream.of(ExaminableProperty.of((String)"pos", (Object)this.pos())), super.examinableProperties());
    }
}
