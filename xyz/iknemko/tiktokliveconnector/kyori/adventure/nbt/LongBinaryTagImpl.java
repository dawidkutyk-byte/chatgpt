/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.AbstractBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.LongBinaryTag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.Debug;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.AbstractBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.LongBinaryTag;

@Debug.Renderer(text="String.valueOf(this.value) + \"l\"", hasChildren="false")
final class LongBinaryTagImpl
extends AbstractBinaryTag
implements LongBinaryTag {
    private final long value;

    public long value() {
        return this.value;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (((Object)((Object)this)).getClass() != other.getClass()) {
            return false;
        }
        LongBinaryTagImpl that = (LongBinaryTagImpl)((Object)other);
        return this.value == that.value;
    }

    LongBinaryTagImpl(long value) {
        this.value = value;
    }

    public long longValue() {
        return this.value;
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"value", (long)this.value));
    }

    public int hashCode() {
        return Long.hashCode(this.value);
    }

    public float floatValue() {
        return this.value;
    }

    public int intValue() {
        return (int)this.value;
    }

    public short shortValue() {
        return (short)(this.value & 0xFFFFL);
    }

    public byte byteValue() {
        return (byte)(this.value & 0xFFL);
    }

    @NotNull
    public Number numberValue() {
        return this.value;
    }

    public double doubleValue() {
        return this.value;
    }
}
