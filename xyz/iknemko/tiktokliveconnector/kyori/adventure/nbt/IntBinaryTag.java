/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.IntBinaryTagImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.NumberBinaryTag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.IntBinaryTagImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.NumberBinaryTag;

public interface IntBinaryTag
extends NumberBinaryTag {
    @NotNull
    default public BinaryTagType<IntBinaryTag> type() {
        return BinaryTagTypes.INT;
    }

    public int value();

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    @NotNull
    public static IntBinaryTag of(int value) {
        return new IntBinaryTagImpl(value);
    }

    @NotNull
    public static IntBinaryTag intBinaryTag(int value) {
        return new IntBinaryTagImpl(value);
    }
}
