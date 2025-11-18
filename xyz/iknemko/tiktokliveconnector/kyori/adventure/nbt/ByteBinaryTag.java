/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ByteBinaryTagImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.NumberBinaryTag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ByteBinaryTagImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.NumberBinaryTag;

public interface ByteBinaryTag
extends NumberBinaryTag {
    public static final ByteBinaryTag ZERO = new ByteBinaryTagImpl(0);
    public static final ByteBinaryTag ONE = new ByteBinaryTagImpl(1);

    public byte value();

    @NotNull
    public static ByteBinaryTag byteBinaryTag(byte value) {
        if (value == 0) {
            return ZERO;
        }
        if (value != 1) return new ByteBinaryTagImpl(value);
        return ONE;
    }

    @NotNull
    default public BinaryTagType<ByteBinaryTag> type() {
        return BinaryTagTypes.BYTE;
    }

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    @NotNull
    public static ByteBinaryTag of(byte value) {
        return ByteBinaryTag.byteBinaryTag(value);
    }
}
