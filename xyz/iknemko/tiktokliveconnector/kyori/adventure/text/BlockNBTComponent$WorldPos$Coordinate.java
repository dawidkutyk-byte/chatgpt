/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$WorldPos$Coordinate$Type
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponentImpl$WorldPosImpl$CoordinateImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import net.kyori.examination.Examinable;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponentImpl;

public static interface BlockNBTComponent.WorldPos.Coordinate
extends Examinable {
    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    @NotNull
    public static BlockNBTComponent.WorldPos.Coordinate of(int value, @NotNull Type type) {
        return new BlockNBTComponentImpl.WorldPosImpl.CoordinateImpl(value, type);
    }

    @NotNull
    public static BlockNBTComponent.WorldPos.Coordinate coordinate(int value, @NotNull Type type) {
        return new BlockNBTComponentImpl.WorldPosImpl.CoordinateImpl(value, type);
    }

    @NotNull
    public static BlockNBTComponent.WorldPos.Coordinate absolute(int value) {
        return BlockNBTComponent.WorldPos.Coordinate.coordinate(value, Type.ABSOLUTE);
    }

    @NotNull
    public static BlockNBTComponent.WorldPos.Coordinate relative(int value) {
        return BlockNBTComponent.WorldPos.Coordinate.coordinate(value, Type.RELATIVE);
    }

    @NotNull
    public Type type();

    public int value();
}
