/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.LongBinaryTagImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.NumberBinaryTag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.LongBinaryTagImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.NumberBinaryTag;

public interface LongBinaryTag
extends NumberBinaryTag {
    @NotNull
    default public BinaryTagType<LongBinaryTag> type() {
        return BinaryTagTypes.LONG;
    }

    @NotNull
    public static LongBinaryTag longBinaryTag(long value) {
        return new LongBinaryTagImpl(value);
    }

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    @NotNull
    public static LongBinaryTag of(long value) {
        return new LongBinaryTagImpl(value);
    }

    public long value();
}
