/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.AbstractBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ShortBinaryTag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.Debug;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.AbstractBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ShortBinaryTag;

@Debug.Renderer(text="String.valueOf(this.value) + \"s\"", hasChildren="false")
final class ShortBinaryTagImpl
extends AbstractBinaryTag
implements ShortBinaryTag {
    private final short value;

    public byte byteValue() {
        return (byte)(this.value & 0xFF);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (((Object)((Object)this)).getClass() != other.getClass()) {
            return false;
        }
        ShortBinaryTagImpl that = (ShortBinaryTagImpl)((Object)other);
        return this.value == that.value;
    }

    public short shortValue() {
        return this.value;
    }

    ShortBinaryTagImpl(short value) {
        this.value = value;
    }

    public short value() {
        return this.value;
    }

    public double doubleValue() {
        return this.value;
    }

    public long longValue() {
        return this.value;
    }

    public int intValue() {
        return this.value;
    }

    public float floatValue() {
        return this.value;
    }

    public int hashCode() {
        return Short.hashCode(this.value);
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"value", (short)this.value));
    }

    @NotNull
    public Number numberValue() {
        return this.value;
    }
}
