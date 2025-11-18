/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$Pos
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$WorldPos$Coordinate
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponentImpl$WorldPosImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponentImpl;

public static interface BlockNBTComponent.WorldPos
extends BlockNBTComponent.Pos {
    @NotNull
    public static BlockNBTComponent.WorldPos worldPos(@NotNull Coordinate x, @NotNull Coordinate y, @NotNull Coordinate z) {
        return new BlockNBTComponentImpl.WorldPosImpl(x, y, z);
    }

    @NotNull
    public Coordinate x();

    @NotNull
    public Coordinate z();

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    @NotNull
    public static BlockNBTComponent.WorldPos of(@NotNull Coordinate x, @NotNull Coordinate y, @NotNull Coordinate z) {
        return new BlockNBTComponentImpl.WorldPosImpl(x, y, z);
    }

    @NotNull
    public Coordinate y();
}
