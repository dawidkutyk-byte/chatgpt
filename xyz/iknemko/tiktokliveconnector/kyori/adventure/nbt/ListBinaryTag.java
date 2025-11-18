/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ByteArrayBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.IntArrayBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListBinaryTag$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListBinaryTagImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListTagBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListTagSetter
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.LongArrayBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.NumberBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.StringBinaryTag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Stream;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ByteArrayBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.IntArrayBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListBinaryTagImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListTagBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListTagSetter;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.LongArrayBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.NumberBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.StringBinaryTag;

public interface ListBinaryTag
extends ListTagSetter<ListBinaryTag, BinaryTag>,
BinaryTag,
Iterable<BinaryTag> {
    @NotNull
    public static ListBinaryTag listBinaryTag(@NotNull BinaryTagType<? extends BinaryTag> type, @NotNull List<BinaryTag> tags) {
        if (tags.isEmpty()) {
            return ListBinaryTag.empty();
        }
        if (type == BinaryTagTypes.END) {
            throw new IllegalArgumentException("Cannot create a list of " + BinaryTagTypes.END);
        }
        ListBinaryTagImpl.validateTagType(tags, (type == BinaryTagTypes.LIST_WILDCARD ? 1 : 0) != 0);
        return new ListBinaryTagImpl(type, type == BinaryTagTypes.LIST_WILDCARD, new ArrayList<BinaryTag>(tags));
    }

    @NotNull
    default public ListBinaryTag getList(@Range(from=0L, to=0x7FFFFFFFL) int index, @NotNull ListBinaryTag defaultValue) {
        return this.getList(index, null, defaultValue);
    }

    @NotNull
    public static Builder<BinaryTag> builder() {
        return new ListTagBuilder(false);
    }

    @NotNull
    public ListBinaryTag unwrapHeterogeneity();

    public boolean isEmpty();

    @NotNull
    public BinaryTag get(@Range(from=0L, to=0x7FFFFFFFL) int var1);

    @NotNull
    default public ListBinaryTag getList(@Range(from=0L, to=0x7FFFFFFFL) int index) {
        return this.getList(index, null, ListBinaryTag.empty());
    }

    @NotNull
    default public BinaryTagType<ListBinaryTag> type() {
        return BinaryTagTypes.LIST;
    }

    default public float getFloat(@Range(from=0L, to=0x7FFFFFFFL) int index, float defaultValue) {
        BinaryTag tag = this.get(index);
        if (!tag.type().numeric()) return defaultValue;
        return ((NumberBinaryTag)tag).floatValue();
    }

    @Deprecated
    @NotNull
    default public BinaryTagType<? extends BinaryTag> listType() {
        return this.elementType();
    }

    @NotNull
    public Stream<BinaryTag> stream();

    @NotNull
    default public CompoundBinaryTag getCompound(@Range(from=0L, to=0x7FFFFFFFL) int index, @NotNull CompoundBinaryTag defaultValue) {
        BinaryTag tag = this.get(index);
        if (tag.type() != BinaryTagTypes.COMPOUND) return defaultValue;
        return (CompoundBinaryTag)tag;
    }

    @NotNull
    public static <T extends BinaryTag> Builder<T> builder(@NotNull BinaryTagType<T> type) {
        if (type != BinaryTagTypes.END) return new ListTagBuilder(false, type);
        throw new IllegalArgumentException("Cannot create a list of " + BinaryTagTypes.END);
    }

    default public long getLong(@Range(from=0L, to=0x7FFFFFFFL) int index) {
        return this.getLong(index, 0L);
    }

    default public float getFloat(@Range(from=0L, to=0x7FFFFFFFL) int index) {
        return this.getFloat(index, 0.0f);
    }

    @NotNull
    public BinaryTagType<? extends BinaryTag> elementType();

    default public double getDouble(@Range(from=0L, to=0x7FFFFFFFL) int index, double defaultValue) {
        BinaryTag tag = this.get(index);
        if (!tag.type().numeric()) return defaultValue;
        return ((NumberBinaryTag)tag).doubleValue();
    }

    default public int getInt(@Range(from=0L, to=0x7FFFFFFFL) int index, int defaultValue) {
        BinaryTag tag = this.get(index);
        if (!tag.type().numeric()) return defaultValue;
        return ((NumberBinaryTag)tag).intValue();
    }

    @NotNull
    default public String getString(@Range(from=0L, to=0x7FFFFFFFL) int index) {
        return this.getString(index, "");
    }

    @NotNull
    public ListBinaryTag set(int var1, @NotNull BinaryTag var2, @Nullable Consumer<? super BinaryTag> var3);

    @NotNull
    default public ListBinaryTag getList(@Range(from=0L, to=0x7FFFFFFFL) int index, @Nullable BinaryTagType<?> elementType) {
        return this.getList(index, elementType, ListBinaryTag.empty());
    }

    default public double getDouble(@Range(from=0L, to=0x7FFFFFFFL) int index) {
        return this.getDouble(index, 0.0);
    }

    default public short getShort(@Range(from=0L, to=0x7FFFFFFFL) int index, short defaultValue) {
        BinaryTag tag = this.get(index);
        if (!tag.type().numeric()) return defaultValue;
        return ((NumberBinaryTag)tag).shortValue();
    }

    default public byte @NotNull [] getByteArray(@Range(from=0L, to=0x7FFFFFFFL) int index, byte @NotNull [] defaultValue) {
        BinaryTag tag = this.get(index);
        if (tag.type() != BinaryTagTypes.BYTE_ARRAY) return defaultValue;
        return ((ByteArrayBinaryTag)tag).value();
    }

    @NotNull
    public static ListBinaryTag from(@NotNull Iterable<? extends BinaryTag> tags) {
        return ((Builder)ListBinaryTag.builder().add(tags)).build();
    }

    default public int @NotNull [] getIntArray(@Range(from=0L, to=0x7FFFFFFFL) int index) {
        BinaryTag tag = this.get(index);
        if (tag.type() != BinaryTagTypes.INT_ARRAY) return new int[0];
        return ((IntArrayBinaryTag)tag).value();
    }

    @NotNull
    default public String getString(@Range(from=0L, to=0x7FFFFFFFL) int index, @NotNull String defaultValue) {
        BinaryTag tag = this.get(index);
        if (tag.type() != BinaryTagTypes.STRING) return defaultValue;
        return ((StringBinaryTag)tag).value();
    }

    default public byte getByte(@Range(from=0L, to=0x7FFFFFFFL) int index) {
        return this.getByte(index, (byte)0);
    }

    default public long getLong(@Range(from=0L, to=0x7FFFFFFFL) int index, long defaultValue) {
        BinaryTag tag = this.get(index);
        if (!tag.type().numeric()) return defaultValue;
        return ((NumberBinaryTag)tag).longValue();
    }

    @NotNull
    default public CompoundBinaryTag getCompound(@Range(from=0L, to=0x7FFFFFFFL) int index) {
        return this.getCompound(index, CompoundBinaryTag.empty());
    }

    default public short getShort(@Range(from=0L, to=0x7FFFFFFFL) int index) {
        return this.getShort(index, (short)0);
    }

    default public int @NotNull [] getIntArray(@Range(from=0L, to=0x7FFFFFFFL) int index, int @NotNull [] defaultValue) {
        BinaryTag tag = this.get(index);
        if (tag.type() != BinaryTagTypes.INT_ARRAY) return defaultValue;
        return ((IntArrayBinaryTag)tag).value();
    }

    @NotNull
    public ListBinaryTag remove(int var1, @Nullable Consumer<? super BinaryTag> var2);

    default public int getInt(@Range(from=0L, to=0x7FFFFFFFL) int index) {
        return this.getInt(index, 0);
    }

    public int size();

    default public long @NotNull [] getLongArray(@Range(from=0L, to=0x7FFFFFFFL) int index, long @NotNull [] defaultValue) {
        BinaryTag tag = this.get(index);
        if (tag.type() != BinaryTagTypes.LONG_ARRAY) return defaultValue;
        return ((LongArrayBinaryTag)tag).value();
    }

    default public byte getByte(@Range(from=0L, to=0x7FFFFFFFL) int index, byte defaultValue) {
        BinaryTag tag = this.get(index);
        if (!tag.type().numeric()) return defaultValue;
        return ((NumberBinaryTag)tag).byteValue();
    }

    @NotNull
    public static Builder<BinaryTag> heterogeneousListBinaryTag() {
        return new ListTagBuilder(true);
    }

    @NotNull
    public ListBinaryTag wrapHeterogeneity();

    @NotNull
    public static Collector<BinaryTag, ?, ListBinaryTag> toListTag() {
        return ListBinaryTag.toListTag(null);
    }

    default public long @NotNull [] getLongArray(@Range(from=0L, to=0x7FFFFFFFL) int index) {
        BinaryTag tag = this.get(index);
        if (tag.type() != BinaryTagTypes.LONG_ARRAY) return new long[0];
        return ((LongArrayBinaryTag)tag).value();
    }

    default public byte @NotNull [] getByteArray(@Range(from=0L, to=0x7FFFFFFFL) int index) {
        BinaryTag tag = this.get(index);
        if (tag.type() != BinaryTagTypes.BYTE_ARRAY) return new byte[0];
        return ((ByteArrayBinaryTag)tag).value();
    }

    @NotNull
    default public ListBinaryTag getList(@Range(from=0L, to=0x7FFFFFFFL) int index, @Nullable BinaryTagType<?> elementType, @NotNull ListBinaryTag defaultValue) {
        BinaryTag tag = this.get(index);
        if (tag.type() != BinaryTagTypes.LIST) return defaultValue;
        ListBinaryTag list = (ListBinaryTag)tag;
        if (elementType == null) return list;
        if (list.elementType() != elementType) return defaultValue;
        return list;
    }

    @NotNull
    public static ListBinaryTag empty() {
        return ListBinaryTagImpl.EMPTY;
    }

    @Deprecated
    @NotNull
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    public static ListBinaryTag of(@NotNull BinaryTagType<? extends BinaryTag> type, @NotNull List<BinaryTag> tags) {
        return ListBinaryTag.listBinaryTag(type, tags);
    }

    @NotNull
    public static Collector<BinaryTag, ?, ListBinaryTag> toListTag(@Nullable ListBinaryTag initial) {
        return Collector.of(initial == null ? ListBinaryTag::builder : () -> (Builder)ListBinaryTag.builder().add((Iterable)initial), ListTagSetter::add, (l, r) -> (Builder)l.add((Iterable)r.build()), Builder::build, new Collector.Characteristics[0]);
    }
}
