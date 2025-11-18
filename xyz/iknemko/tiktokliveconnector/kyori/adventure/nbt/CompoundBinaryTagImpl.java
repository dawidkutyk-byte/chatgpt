/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.AbstractBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ByteArrayBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.IntArrayBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.LongArrayBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.NumberBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.StringBinaryTag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.Debug;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.AbstractBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ByteArrayBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.IntArrayBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.LongArrayBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.NumberBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.StringBinaryTag;

@Debug.Renderer(text="\"CompoundBinaryTag[length=\" + this.tags.size() + \"]\"", childrenArray="this.tags.entrySet().toArray()", hasChildren="!this.tags.isEmpty()")
final class CompoundBinaryTagImpl
extends AbstractBinaryTag
implements CompoundBinaryTag {
    private final Map<String, BinaryTag> tags;
    private final int hashCode;
    static final CompoundBinaryTag EMPTY = new CompoundBinaryTagImpl(Collections.<String, BinaryTag>emptyMap());

    public byte @NotNull [] getByteArray(@NotNull String key) {
        if (!this.contains(key, BinaryTagTypes.BYTE_ARRAY)) return new byte[0];
        return ((ByteArrayBinaryTag)this.tags.get(key)).value();
    }

    @NotNull
    public String getString(@NotNull String key, @NotNull String defaultValue) {
        if (!this.contains(key, BinaryTagTypes.STRING)) return defaultValue;
        return ((StringBinaryTag)this.tags.get(key)).value();
    }

    @NotNull
    public CompoundBinaryTag put(@NotNull CompoundBinaryTag tag) {
        return this.edit(map -> {
            Iterator iterator = tag.keySet().iterator();
            while (iterator.hasNext()) {
                String key = (String)iterator.next();
                map.put(key, tag.get(key));
            }
        });
    }

    @NotNull
    public Iterator<Map.Entry<String, ? extends BinaryTag>> iterator() {
        return this.tags.entrySet().iterator();
    }

    @Nullable
    public BinaryTag get(String key) {
        return this.tags.get(key);
    }

    public double getDouble(@NotNull String key, double defaultValue) {
        if (!this.contains(key, BinaryTagTypes.DOUBLE)) return defaultValue;
        return ((NumberBinaryTag)this.tags.get(key)).doubleValue();
    }

    @NotNull
    public ListBinaryTag getList(@NotNull String key, @NotNull ListBinaryTag defaultValue) {
        if (!this.contains(key, BinaryTagTypes.LIST)) return defaultValue;
        return (ListBinaryTag)this.tags.get(key);
    }

    @NotNull
    public CompoundBinaryTag getCompound(@NotNull String key, @NotNull CompoundBinaryTag defaultValue) {
        if (!this.contains(key, BinaryTagTypes.COMPOUND)) return defaultValue;
        return (CompoundBinaryTag)this.tags.get(key);
    }

    public int @NotNull [] getIntArray(@NotNull String key, int @NotNull [] defaultValue) {
        if (!this.contains(key, BinaryTagTypes.INT_ARRAY)) return defaultValue;
        return ((IntArrayBinaryTag)this.tags.get(key)).value();
    }

    @NotNull
    public Set<String> keySet() {
        return Collections.unmodifiableSet(this.tags.keySet());
    }

    public byte getByte(@NotNull String key, byte defaultValue) {
        if (!this.contains(key, BinaryTagTypes.BYTE)) return defaultValue;
        return ((NumberBinaryTag)this.tags.get(key)).byteValue();
    }

    public float getFloat(@NotNull String key, float defaultValue) {
        if (!this.contains(key, BinaryTagTypes.FLOAT)) return defaultValue;
        return ((NumberBinaryTag)this.tags.get(key)).floatValue();
    }

    public boolean equals(Object that) {
        return this == that || that instanceof CompoundBinaryTagImpl && this.tags.equals(((CompoundBinaryTagImpl)((Object)that)).tags);
    }

    public boolean isEmpty() {
        return this.tags.isEmpty();
    }

    CompoundBinaryTagImpl(Map<String, BinaryTag> tags) {
        this.tags = Collections.unmodifiableMap(tags);
        this.hashCode = tags.hashCode();
    }

    public Stream<Map.Entry<String, ? extends BinaryTag>> stream() {
        return this.tags.entrySet().stream();
    }

    private CompoundBinaryTag edit(Consumer<Map<String, BinaryTag>> consumer) {
        HashMap<String, BinaryTag> tags = new HashMap<String, BinaryTag>(this.tags);
        consumer.accept(tags);
        return new CompoundBinaryTagImpl(new HashMap<String, BinaryTag>(tags));
    }

    public long @NotNull [] getLongArray(@NotNull String key) {
        if (!this.contains(key, BinaryTagTypes.LONG_ARRAY)) return new long[0];
        return ((LongArrayBinaryTag)this.tags.get(key)).value();
    }

    public void forEach(@NotNull Consumer<? super Map.Entry<String, ? extends BinaryTag>> action) {
        this.tags.entrySet().forEach(Objects.requireNonNull(action, "action"));
    }

    public long @NotNull [] getLongArray(@NotNull String key, long @NotNull [] defaultValue) {
        if (!this.contains(key, BinaryTagTypes.LONG_ARRAY)) return defaultValue;
        return ((LongArrayBinaryTag)this.tags.get(key)).value();
    }

    public short getShort(@NotNull String key, short defaultValue) {
        if (!this.contains(key, BinaryTagTypes.SHORT)) return defaultValue;
        return ((NumberBinaryTag)this.tags.get(key)).shortValue();
    }

    public int @NotNull [] getIntArray(@NotNull String key) {
        if (!this.contains(key, BinaryTagTypes.INT_ARRAY)) return new int[0];
        return ((IntArrayBinaryTag)this.tags.get(key)).value();
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"tags", this.tags));
    }

    public int getInt(@NotNull String key, int defaultValue) {
        if (!this.contains(key, BinaryTagTypes.INT)) return defaultValue;
        return ((NumberBinaryTag)this.tags.get(key)).intValue();
    }

    public int hashCode() {
        return this.hashCode;
    }

    @NotNull
    public ListBinaryTag getList(@NotNull String key, @NotNull BinaryTagType<? extends BinaryTag> expectedType, @NotNull ListBinaryTag defaultValue) {
        if (!this.contains(key, BinaryTagTypes.LIST)) return defaultValue;
        ListBinaryTag tag = (ListBinaryTag)this.tags.get(key);
        if (!expectedType.test(tag.elementType())) return defaultValue;
        return tag;
    }

    public byte @NotNull [] getByteArray(@NotNull String key, byte @NotNull [] defaultValue) {
        if (!this.contains(key, BinaryTagTypes.BYTE_ARRAY)) return defaultValue;
        return ((ByteArrayBinaryTag)this.tags.get(key)).value();
    }

    public long getLong(@NotNull String key, long defaultValue) {
        if (!this.contains(key, BinaryTagTypes.LONG)) return defaultValue;
        return ((NumberBinaryTag)this.tags.get(key)).longValue();
    }

    @NotNull
    public CompoundBinaryTag remove(@NotNull String key, @Nullable Consumer<? super BinaryTag> removed) {
        if (this.tags.containsKey(key)) return this.edit(map -> {
            BinaryTag tag = (BinaryTag)map.remove(key);
            if (removed == null) return;
            removed.accept(tag);
        });
        return this;
    }

    @NotNull
    public CompoundBinaryTag put(@NotNull Map<String, ? extends BinaryTag> tags) {
        return this.edit(map -> map.putAll(tags));
    }

    @NotNull
    public CompoundBinaryTag put(@NotNull String key, @NotNull BinaryTag tag) {
        return this.edit(map -> map.put(key, tag));
    }

    public boolean contains(@NotNull String key, @NotNull BinaryTagType<?> type) {
        @Nullable BinaryTag tag = this.tags.get(key);
        return tag != null && type.test(tag.type());
    }

    public int size() {
        return this.tags.size();
    }
}
