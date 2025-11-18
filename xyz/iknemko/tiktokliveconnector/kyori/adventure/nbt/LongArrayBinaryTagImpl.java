/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ArrayBinaryTagImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.LongArrayBinaryTag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.util.Arrays;
import java.util.function.LongConsumer;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.Debug;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ArrayBinaryTagImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.LongArrayBinaryTag;

@Debug.Renderer(text="\"long[\" + this.value.length + \"]\"", childrenArray="this.value", hasChildren="this.value.length > 0")
final class LongArrayBinaryTagImpl
extends ArrayBinaryTagImpl
implements LongArrayBinaryTag {
    final long[] value;

    @NotNull
    public LongStream stream() {
        return Arrays.stream(this.value);
    }

    public long @NotNull [] value() {
        return Arrays.copyOf(this.value, this.value.length);
    }

    public int size() {
        return this.value.length;
    }

    public long get(int index) {
        LongArrayBinaryTagImpl.checkIndex((int)index, (int)this.value.length);
        return this.value[index];
    }

    public  @NotNull Spliterator.OfLong spliterator() {
        return Arrays.spliterator(this.value);
    }

    static long[] value(LongArrayBinaryTag tag) {
        return tag instanceof LongArrayBinaryTagImpl ? ((LongArrayBinaryTagImpl)tag).value : tag.value();
    }

    LongArrayBinaryTagImpl(long[] value) {
        this.value = Arrays.copyOf(value, value.length);
    }

    public  @NotNull PrimitiveIterator.OfLong iterator() {
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    public int hashCode() {
        return Arrays.hashCode(this.value);
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"value", (long[])this.value));
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (((Object)((Object)this)).getClass() != other.getClass()) {
            return false;
        }
        LongArrayBinaryTagImpl that = (LongArrayBinaryTagImpl)((Object)other);
        return Arrays.equals(this.value, that.value);
    }

    public void forEachLong(@NotNull LongConsumer action) {
        int i = 0;
        int length = this.value.length;
        while (i < length) {
            action.accept(this.value[i]);
            ++i;
        }
    }
}
