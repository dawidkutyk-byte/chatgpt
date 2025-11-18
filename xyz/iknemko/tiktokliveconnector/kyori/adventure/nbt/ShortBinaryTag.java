/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.NumberBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ShortBinaryTagImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.NumberBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ShortBinaryTagImpl;

public interface ShortBinaryTag
extends NumberBinaryTag {
    @NotNull
    default public BinaryTagType<ShortBinaryTag> type() {
        return BinaryTagTypes.SHORT;
    }

    @Deprecated
    @NotNull
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    public static ShortBinaryTag of(short value) {
        return new ShortBinaryTagImpl(value);
    }

    @NotNull
    public static ShortBinaryTag shortBinaryTag(short value) {
        return new ShortBinaryTagImpl(value);
    }

    public short value();
}
