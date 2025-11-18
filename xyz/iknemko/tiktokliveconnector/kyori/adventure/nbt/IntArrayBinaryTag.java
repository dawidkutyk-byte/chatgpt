/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ArrayBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.IntArrayBinaryTagImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ArrayBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.IntArrayBinaryTagImpl;

public interface IntArrayBinaryTag
extends ArrayBinaryTag,
Iterable<Integer> {
    public int get(int var1);

    @NotNull
    default public BinaryTagType<IntArrayBinaryTag> type() {
        return BinaryTagTypes.INT_ARRAY;
    }

    public int @NotNull [] value();

    @Deprecated
    @NotNull
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    public static IntArrayBinaryTag of(int ... value) {
        return new IntArrayBinaryTagImpl(value);
    }

    @NotNull
    public IntStream stream();

    public void forEachInt(@NotNull IntConsumer var1);

    public int size();

    @NotNull
    public static IntArrayBinaryTag intArrayBinaryTag(int ... value) {
        return new IntArrayBinaryTagImpl(value);
    }

    public  @NotNull PrimitiveIterator.OfInt iterator();

    public  @NotNull Spliterator.OfInt spliterator();
}
