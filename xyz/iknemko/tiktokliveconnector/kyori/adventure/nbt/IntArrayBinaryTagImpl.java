/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ArrayBinaryTagImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.IntArrayBinaryTag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.util.Arrays;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.Debug;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ArrayBinaryTagImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.IntArrayBinaryTag;

@Debug.Renderer(text="\"int[\" + this.value.length + \"]\"", childrenArray="this.value", hasChildren="this.value.length > 0")
final class IntArrayBinaryTagImpl
extends ArrayBinaryTagImpl
implements IntArrayBinaryTag {
    final int[] value;

    public void forEachInt(@NotNull IntConsumer action) {
        int i = 0;
        int length = this.value.length;
        while (i < length) {
            action.accept(this.value[i]);
            ++i;
        }
    }

    static int[] value(IntArrayBinaryTag tag) {
        return tag instanceof IntArrayBinaryTagImpl ? ((IntArrayBinaryTagImpl)tag).value : tag.value();
    }

    public int hashCode() {
        return Arrays.hashCode(this.value);
    }

    public int @NotNull [] value() {
        return Arrays.copyOf(this.value, this.value.length);
    }

    public int size() {
        return this.value.length;
    }

    public  @NotNull Spliterator.OfInt spliterator() {
        return Arrays.spliterator(this.value);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (((Object)((Object)this)).getClass() != other.getClass()) {
            return false;
        }
        IntArrayBinaryTagImpl that = (IntArrayBinaryTagImpl)((Object)other);
        return Arrays.equals(this.value, that.value);
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"value", (int[])this.value));
    }

    public  @NotNull PrimitiveIterator.OfInt iterator() {
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    public int get(int index) {
        IntArrayBinaryTagImpl.checkIndex((int)index, (int)this.value.length);
        return this.value[index];
    }

    @NotNull
    public IntStream stream() {
        return Arrays.stream(this.value);
    }

    IntArrayBinaryTagImpl(int ... value) {
        this.value = Arrays.copyOf(value, value.length);
    }
}
