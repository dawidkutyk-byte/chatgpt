/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.DoubleBinaryTagImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.NumberBinaryTag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.DoubleBinaryTagImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.NumberBinaryTag;

public interface DoubleBinaryTag
extends NumberBinaryTag {
    @NotNull
    public static DoubleBinaryTag doubleBinaryTag(double value) {
        return new DoubleBinaryTagImpl(value);
    }

    public double value();

    @NotNull
    default public BinaryTagType<DoubleBinaryTag> type() {
        return BinaryTagTypes.DOUBLE;
    }

    @Deprecated
    @NotNull
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    public static DoubleBinaryTag of(double value) {
        return new DoubleBinaryTagImpl(value);
    }
}
