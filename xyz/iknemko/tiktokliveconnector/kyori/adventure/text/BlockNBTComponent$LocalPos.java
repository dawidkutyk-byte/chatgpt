/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$Pos
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponentImpl$LocalPosImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponentImpl;

public static interface BlockNBTComponent.LocalPos
extends BlockNBTComponent.Pos {
    public double up();

    public double forwards();

    @NotNull
    public static BlockNBTComponent.LocalPos localPos(double left, double up, double forwards) {
        return new BlockNBTComponentImpl.LocalPosImpl(left, up, forwards);
    }

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    @NotNull
    public static BlockNBTComponent.LocalPos of(double left, double up, double forwards) {
        return new BlockNBTComponentImpl.LocalPosImpl(left, up, forwards);
    }

    public double left();
}
