/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$LocalPos
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$Pos
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$WorldPos
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$WorldPos$Coordinate
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponentBuilder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponentBuilder;

/*
 * Exception performing whole class analysis ignored.
 */
public static interface BlockNBTComponent.Builder
extends NBTComponentBuilder<BlockNBTComponent, BlockNBTComponent.Builder> {
    @Contract(value="_, _, _ -> this")
    @NotNull
    default public BlockNBTComponent.Builder relativeWorldPos(int x, int y, int z) {
        return this.worldPos(BlockNBTComponent.WorldPos.Coordinate.relative((int)x), BlockNBTComponent.WorldPos.Coordinate.relative((int)y), BlockNBTComponent.WorldPos.Coordinate.relative((int)z));
    }

    @Contract(value="_ -> this")
    @NotNull
    public BlockNBTComponent.Builder pos(@NotNull BlockNBTComponent.Pos var1);

    @NotNull
    @Contract(value="_, _, _ -> this")
    default public BlockNBTComponent.Builder absoluteWorldPos(int x, int y, int z) {
        return this.worldPos(BlockNBTComponent.WorldPos.Coordinate.absolute((int)x), BlockNBTComponent.WorldPos.Coordinate.absolute((int)y), BlockNBTComponent.WorldPos.Coordinate.absolute((int)z));
    }

    @NotNull
    @Contract(value="_, _, _ -> this")
    default public BlockNBTComponent.Builder localPos(double left, double up, double forwards) {
        return this.pos((BlockNBTComponent.Pos)BlockNBTComponent.LocalPos.localPos((double)left, (double)up, (double)forwards));
    }

    @NotNull
    @Contract(value="_, _, _ -> this")
    default public BlockNBTComponent.Builder worldPos(// Could not load outer class - annotation placement on inner may be incorrect
    @NotNull BlockNBTComponent.WorldPos.Coordinate x, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull BlockNBTComponent.WorldPos.Coordinate y, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull BlockNBTComponent.WorldPos.Coordinate z) {
        return this.pos((BlockNBTComponent.Pos)BlockNBTComponent.WorldPos.worldPos((BlockNBTComponent.WorldPos.Coordinate)x, (BlockNBTComponent.WorldPos.Coordinate)y, (BlockNBTComponent.WorldPos.Coordinate)z));
    }
}
