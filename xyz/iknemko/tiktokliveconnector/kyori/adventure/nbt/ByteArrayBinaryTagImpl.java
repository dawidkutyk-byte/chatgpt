/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ArrayBinaryTagImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ByteArrayBinaryTag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.Debug;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ArrayBinaryTagImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ByteArrayBinaryTag;

@Debug.Renderer(text="\"byte[\" + this.value.length + \"]\"", childrenArray="this.value", hasChildren="this.value.length > 0")
final class ByteArrayBinaryTagImpl
extends ArrayBinaryTagImpl
implements ByteArrayBinaryTag {
    final byte[] value;

    public Iterator<Byte> iterator() {
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    public byte get(int index) {
        ByteArrayBinaryTagImpl.checkIndex((int)index, (int)this.value.length);
        return this.value[index];
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"value", (byte[])this.value));
    }

    public int size() {
        return this.value.length;
    }

    static byte[] value(ByteArrayBinaryTag tag) {
        return tag instanceof ByteArrayBinaryTagImpl ? ((ByteArrayBinaryTagImpl)tag).value : tag.value();
    }

    public byte @NotNull [] value() {
        return Arrays.copyOf(this.value, this.value.length);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (((Object)((Object)this)).getClass() != other.getClass()) {
            return false;
        }
        ByteArrayBinaryTagImpl that = (ByteArrayBinaryTagImpl)((Object)other);
        return Arrays.equals(this.value, that.value);
    }

    public int hashCode() {
        return Arrays.hashCode(this.value);
    }

    ByteArrayBinaryTagImpl(byte[] value) {
        this.value = Arrays.copyOf(value, value.length);
    }
}
