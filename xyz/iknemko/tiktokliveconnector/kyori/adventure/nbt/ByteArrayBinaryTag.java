/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ArrayBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ByteArrayBinaryTagImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ArrayBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ByteArrayBinaryTagImpl;

public interface ByteArrayBinaryTag
extends ArrayBinaryTag,
Iterable<Byte> {
    public byte get(int var1);

    public byte @NotNull [] value();

    @NotNull
    default public BinaryTagType<ByteArrayBinaryTag> type() {
        return BinaryTagTypes.BYTE_ARRAY;
    }

    public int size();

    @NotNull
    public static ByteArrayBinaryTag byteArrayBinaryTag(byte ... value) {
        return new ByteArrayBinaryTagImpl(value);
    }

    @Deprecated
    @NotNull
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    public static ByteArrayBinaryTag of(byte ... value) {
        return new ByteArrayBinaryTagImpl(value);
    }
}
