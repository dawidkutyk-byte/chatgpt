/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.AbstractBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ByteBinaryTag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.Debug;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.AbstractBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ByteBinaryTag;

@Debug.Renderer(text="\"0x\" + Integer.toString(this.value, 16)", hasChildren="false")
final class ByteBinaryTagImpl
extends AbstractBinaryTag
implements ByteBinaryTag {
    private final byte value;

    public byte value() {
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
        ByteBinaryTagImpl that = (ByteBinaryTagImpl)((Object)other);
        return this.value == that.value;
    }

    public short shortValue() {
        return this.value;
    }

    public int hashCode() {
        return Byte.hashCode(this.value);
    }

    public int intValue() {
        return this.value;
    }

    public byte byteValue() {
        return this.value;
    }

    public long longValue() {
        return this.value;
    }

    public float floatValue() {
        return this.value;
    }

    @NotNull
    public Number numberValue() {
        return this.value;
    }

    ByteBinaryTagImpl(byte value) {
        this.value = value;
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"value", (byte)this.value));
    }

    public double doubleValue() {
        return this.value;
    }
}
