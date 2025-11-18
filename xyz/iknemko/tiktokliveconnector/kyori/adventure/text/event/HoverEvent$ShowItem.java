/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Keyed
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.api.BinaryTagHolder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValue
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValueConverterRegistry
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Keyed;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.api.BinaryTagHolder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValueConverterRegistry;

public static final class HoverEvent.ShowItem
implements Examinable {
    private final Map<Key, DataComponentValue> dataComponents;
    @Nullable
    private final BinaryTagHolder nbt;
    private final int count;
    private final Key item;

    @NotNull
    public Map<Key, DataComponentValue> dataComponents() {
        return this.dataComponents;
    }

    @Deprecated
    @Nullable
    public BinaryTagHolder nbt() {
        return this.nbt;
    }

    @Deprecated
    @NotNull
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    public static HoverEvent.ShowItem of(@NotNull Key item, @Range(from=0L, to=0x7FFFFFFFL) int count) {
        return HoverEvent.ShowItem.showItem((Keyed)item, count, Collections.emptyMap());
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (this.getClass() != other.getClass()) {
            return false;
        }
        HoverEvent.ShowItem that = (HoverEvent.ShowItem)other;
        return this.item.equals(that.item) && this.count == that.count && Objects.equals(this.nbt, that.nbt) && Objects.equals(this.dataComponents, that.dataComponents);
    }

    @NotNull
    public HoverEvent.ShowItem dataComponents(@NotNull Map<Key, DataComponentValue> holder) {
        if (!Objects.equals(this.dataComponents, holder)) return new HoverEvent.ShowItem(this.item, this.count, null, holder.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(new HashMap<Key, DataComponentValue>(holder)));
        return this;
    }

    @NotNull
    public static HoverEvent.ShowItem showItem(@NotNull Keyed item, @Range(from=0L, to=0x7FFFFFFFL) int count, @NotNull Map<Key, ? extends DataComponentValue> dataComponents) {
        return new HoverEvent.ShowItem(Objects.requireNonNull(item, "item").key(), count, null, dataComponents);
    }

    @NotNull
    public static HoverEvent.ShowItem showItem(@NotNull Keyed item, @Range(from=0L, to=0x7FFFFFFFL) int count) {
        return HoverEvent.ShowItem.showItem(item, count, Collections.emptyMap());
    }

    @Deprecated
    @NotNull
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    public static HoverEvent.ShowItem of(@NotNull Key item, @Range(from=0L, to=0x7FFFFFFFL) int count, @Nullable BinaryTagHolder nbt) {
        return new HoverEvent.ShowItem(Objects.requireNonNull(item, "item"), count, nbt, Collections.emptyMap());
    }

    @Deprecated
    @NotNull
    public HoverEvent.ShowItem nbt(@Nullable BinaryTagHolder nbt) {
        if (!Objects.equals(nbt, this.nbt)) return new HoverEvent.ShowItem(this.item, this.count, nbt, Collections.emptyMap());
        return this;
    }

    @NotNull
    public static HoverEvent.ShowItem showItem(@NotNull Key item, @Range(from=0L, to=0x7FFFFFFFL) int count) {
        return HoverEvent.ShowItem.showItem((Keyed)item, count, Collections.emptyMap());
    }

    @Deprecated
    @NotNull
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    public static HoverEvent.ShowItem of(@NotNull Keyed item, @Range(from=0L, to=0x7FFFFFFFL) int count, @Nullable BinaryTagHolder nbt) {
        return new HoverEvent.ShowItem(Objects.requireNonNull(item, "item").key(), count, nbt, Collections.emptyMap());
    }

    @NotNull
    public HoverEvent.ShowItem item(@NotNull Key item) {
        if (!Objects.requireNonNull(item, "item").equals(this.item)) return new HoverEvent.ShowItem(item, this.count, this.nbt, this.dataComponents);
        return this;
    }

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    @NotNull
    public static HoverEvent.ShowItem of(@NotNull Keyed item, @Range(from=0L, to=0x7FFFFFFFL) int count) {
        return HoverEvent.ShowItem.of(item, count, null);
    }

    public int hashCode() {
        int result = this.item.hashCode();
        result = 31 * result + Integer.hashCode(this.count);
        result = 31 * result + Objects.hashCode(this.nbt);
        result = 31 * result + Objects.hashCode(this.dataComponents);
        return result;
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"item", (Object)this.item), ExaminableProperty.of((String)"count", (int)this.count), ExaminableProperty.of((String)"nbt", (Object)this.nbt), ExaminableProperty.of((String)"dataComponents", this.dataComponents));
    }

    @NotNull
    public HoverEvent.ShowItem count(@Range(from=0L, to=0x7FFFFFFFL) int count) {
        if (count != this.count) return new HoverEvent.ShowItem(this.item, count, this.nbt, this.dataComponents);
        return this;
    }

    @NotNull
    public <V extends DataComponentValue> Map<Key, V> dataComponentsAs(@NotNull Class<V> targetType) {
        if (this.dataComponents.isEmpty()) {
            return Collections.emptyMap();
        }
        HashMap<Key, DataComponentValue> results = new HashMap<Key, DataComponentValue>(this.dataComponents.size());
        Iterator<Map.Entry<Key, DataComponentValue>> iterator = this.dataComponents.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Key, DataComponentValue> entry = iterator.next();
            results.put(entry.getKey(), DataComponentValueConverterRegistry.convert(targetType, (Key)entry.getKey(), (DataComponentValue)entry.getValue()));
        }
        return Collections.unmodifiableMap(results);
    }

    @NotNull
    public Key item() {
        return this.item;
    }

    @Deprecated
    @NotNull
    public static HoverEvent.ShowItem showItem(@NotNull Keyed item, @Range(from=0L, to=0x7FFFFFFFL) int count, @Nullable BinaryTagHolder nbt) {
        return new HoverEvent.ShowItem(Objects.requireNonNull(item, "item").key(), count, nbt, Collections.emptyMap());
    }

    @Deprecated
    @NotNull
    public static HoverEvent.ShowItem showItem(@NotNull Key item, @Range(from=0L, to=0x7FFFFFFFL) int count, @Nullable BinaryTagHolder nbt) {
        return new HoverEvent.ShowItem(Objects.requireNonNull(item, "item"), count, nbt, Collections.emptyMap());
    }

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    public @Range(from=0L, to=0x7FFFFFFFL) int count() {
        return this.count;
    }

    private HoverEvent.ShowItem(@NotNull Key item, @Range(from=0L, to=0x7FFFFFFFL) int count, @Nullable BinaryTagHolder nbt, @NotNull Map<Key, ? extends DataComponentValue> dataComponents) {
        this.item = item;
        this.count = count;
        this.nbt = nbt;
        this.dataComponents = Collections.unmodifiableMap(new HashMap<Key, DataComponentValue>(dataComponents));
    }
}
