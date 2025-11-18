/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.AbstractBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.DoubleBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ShadyPines
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.Debug;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.AbstractBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.DoubleBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ShadyPines;

@Debug.Renderer(text="String.valueOf(this.value) + \"d\"", hasChildren="false")
final class DoubleBinaryTagImpl
extends AbstractBinaryTag
implements DoubleBinaryTag {
    private final double value;

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (((Object)((Object)this)).getClass() != other.getClass()) {
            return false;
        }
        DoubleBinaryTagImpl that = (DoubleBinaryTagImpl)((Object)other);
        return Double.doubleToLongBits(this.value) == Double.doubleToLongBits(that.value);
    }

    public byte byteValue() {
        return (byte)(ShadyPines.floor((double)this.value) & 0xFF);
    }

    public int hashCode() {
        return Double.hashCode(this.value);
    }

    public long longValue() {
        return (long)Math.floor(this.value);
    }

    DoubleBinaryTagImpl(double value) {
        this.value = value;
    }

    public float floatValue() {
        return (float)this.value;
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"value", (double)this.value));
    }

    public double value() {
        return this.value;
    }

    public int intValue() {
        return ShadyPines.floor((double)this.value);
    }

    @NotNull
    public Number numberValue() {
        return this.value;
    }

    public short shortValue() {
        return (short)(ShadyPines.floor((double)this.value) & 0xFFFF);
    }

    public double doubleValue() {
        return this.value;
    }
}
