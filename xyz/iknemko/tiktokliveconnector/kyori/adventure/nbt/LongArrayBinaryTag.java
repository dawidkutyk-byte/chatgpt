/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ArrayBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.LongArrayBinaryTagImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.util.function.LongConsumer;
import java.util.stream.LongStream;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ArrayBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.LongArrayBinaryTagImpl;

public interface LongArrayBinaryTag
extends ArrayBinaryTag,
Iterable<Long> {
    @NotNull
    public static LongArrayBinaryTag longArrayBinaryTag(long ... value) {
        return new LongArrayBinaryTagImpl(value);
    }

    public  @NotNull Spliterator.OfLong spliterator();

    public long get(int var1);

    public long @NotNull [] value();

    @NotNull
    public LongStream stream();

    @NotNull
    default public BinaryTagType<LongArrayBinaryTag> type() {
        return BinaryTagTypes.LONG_ARRAY;
    }

    public  @NotNull PrimitiveIterator.OfLong iterator();

    @Deprecated
    @NotNull
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    public static LongArrayBinaryTag of(long ... value) {
        return new LongArrayBinaryTagImpl(value);
    }

    public void forEachLong(@NotNull LongConsumer var1);

    public int size();
}
