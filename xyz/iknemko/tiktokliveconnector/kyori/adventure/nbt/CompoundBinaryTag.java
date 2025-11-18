/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ByteBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTagImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundTagBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundTagSetter
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListBinaryTag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ByteBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTagImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundTagBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundTagSetter;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListBinaryTag;

public interface CompoundBinaryTag
extends BinaryTag,
CompoundTagSetter<CompoundBinaryTag>,
Iterable<Map.Entry<String, ? extends BinaryTag>> {
    public long @NotNull [] getLongArray(@NotNull String var1);

    public short getShort(@NotNull String var1, short var2);

    default public byte getByte(@NotNull String key) {
        return this.getByte(key, (byte)0);
    }

    @NotNull
    public static Builder builder() {
        return new CompoundTagBuilder();
    }

    @NotNull
    public static Collector<Map.Entry<String, ? extends BinaryTag>, ?, CompoundBinaryTag> toCompoundTag() {
        return CompoundBinaryTag.toCompoundTag(Map.Entry::getKey, Map.Entry::getValue);
    }

    @NotNull
    default public ListBinaryTag getList(@NotNull String key) {
        return this.getList(key, ListBinaryTag.empty());
    }

    @NotNull
    default public BinaryTagType<CompoundBinaryTag> type() {
        return BinaryTagTypes.COMPOUND;
    }

    default public boolean getBoolean(@NotNull String key) {
        return this.getBoolean(key, false);
    }

    public boolean isEmpty();

    @NotNull
    public static Collector<Map.Entry<String, ? extends BinaryTag>, ?, CompoundBinaryTag> toCompoundTag(@NotNull CompoundBinaryTag initial) {
        return CompoundBinaryTag.toCompoundTag(initial, Map.Entry::getKey, Map.Entry::getValue);
    }

    @NotNull
    default public ListBinaryTag getList(@NotNull String key, @NotNull BinaryTagType<? extends BinaryTag> expectedType) {
        return this.getList(key, expectedType, ListBinaryTag.empty());
    }

    public float getFloat(@NotNull String var1, float var2);

    @NotNull
    default public String getString(@NotNull String key) {
        return this.getString(key, "");
    }

    @NotNull
    public static <T> Collector<T, ?, CompoundBinaryTag> toCompoundTag(@NotNull CompoundBinaryTag initial, @NotNull Function<T, String> keyLens, @NotNull Function<T, ? extends BinaryTag> valueLens) {
        Objects.requireNonNull(initial, "initial");
        Objects.requireNonNull(keyLens, "keyLens");
        Objects.requireNonNull(valueLens, "valueLens");
        return Collector.of(() -> (Builder)CompoundBinaryTag.builder().put(initial), (b, ent) -> b.put((String)keyLens.apply(ent), (BinaryTag)valueLens.apply(ent)), (l, r) -> (Builder)l.put(r.build()), Builder::build, Collector.Characteristics.UNORDERED);
    }

    default public int getInt(@NotNull String key) {
        return this.getInt(key, 0);
    }

    @Nullable
    public BinaryTag get(String var1);

    @NotNull
    public CompoundBinaryTag getCompound(@NotNull String var1, @NotNull CompoundBinaryTag var2);

    default public float getFloat(@NotNull String key) {
        return this.getFloat(key, 0.0f);
    }

    @NotNull
    public static CompoundBinaryTag empty() {
        return CompoundBinaryTagImpl.EMPTY;
    }

    public byte getByte(@NotNull String var1, byte var2);

    default public short getShort(@NotNull String key) {
        return this.getShort(key, (short)0);
    }

    public int @NotNull [] getIntArray(@NotNull String var1);

    public int @NotNull [] getIntArray(@NotNull String var1, int @NotNull [] var2);

    public long getLong(@NotNull String var1, long var2);

    public int getInt(@NotNull String var1, int var2);

    default public long getLong(@NotNull String key) {
        return this.getLong(key, 0L);
    }

    default public boolean getBoolean(@NotNull String key, boolean defaultValue) {
        BinaryTag tag = this.get(key);
        if (!(tag instanceof ByteBinaryTag)) return defaultValue;
        return ((ByteBinaryTag)tag).value() != 0;
    }

    @NotNull
    public String getString(@NotNull String var1, @NotNull String var2);

    public long @NotNull [] getLongArray(@NotNull String var1, long @NotNull [] var2);

    @NotNull
    public ListBinaryTag getList(@NotNull String var1, @NotNull ListBinaryTag var2);

    default public double getDouble(@NotNull String key) {
        return this.getDouble(key, 0.0);
    }

    @NotNull
    default public CompoundBinaryTag getCompound(@NotNull String key) {
        return this.getCompound(key, CompoundBinaryTag.empty());
    }

    @NotNull
    public static CompoundBinaryTag from(@NotNull Map<String, ? extends BinaryTag> tags) {
        if (!tags.isEmpty()) return new CompoundBinaryTagImpl(new HashMap<String, BinaryTag>(tags));
        return CompoundBinaryTag.empty();
    }

    public byte @NotNull [] getByteArray(@NotNull String var1);

    @NotNull
    public Set<String> keySet();

    public Stream<Map.Entry<String, ? extends BinaryTag>> stream();

    @NotNull
    public static <T> Collector<T, ?, CompoundBinaryTag> toCompoundTag(@NotNull Function<T, String> keyLens, @NotNull Function<T, ? extends BinaryTag> valueLens) {
        Objects.requireNonNull(keyLens, "keyLens");
        Objects.requireNonNull(valueLens, "valueLens");
        return Collector.of(CompoundBinaryTag::builder, (b, ent) -> b.put((String)keyLens.apply(ent), (BinaryTag)valueLens.apply(ent)), (l, r) -> (Builder)l.put(r.build()), Builder::build, Collector.Characteristics.UNORDERED);
    }

    public byte @NotNull [] getByteArray(@NotNull String var1, byte @NotNull [] var2);

    public double getDouble(@NotNull String var1, double var2);

    @NotNull
    public ListBinaryTag getList(@NotNull String var1, @NotNull BinaryTagType<? extends BinaryTag> var2, @NotNull ListBinaryTag var3);

    public int size();
}
