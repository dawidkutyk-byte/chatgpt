/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.AbstractBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.FloatBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ShadyPines
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.Debug;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.AbstractBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.FloatBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ShadyPines;

@Debug.Renderer(text="String.valueOf(this.value) + \"f\"", hasChildren="false")
final class FloatBinaryTagImpl
extends AbstractBinaryTag
implements FloatBinaryTag {
    private final float value;

    public short shortValue() {
        return (short)(ShadyPines.floor((float)this.value) & 0xFFFF);
    }

    public long longValue() {
        return (long)this.value;
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"value", (float)this.value));
    }

    public double doubleValue() {
        return this.value;
    }

    FloatBinaryTagImpl(float value) {
        this.value = value;
    }

    public byte byteValue() {
        return (byte)(ShadyPines.floor((float)this.value) & 0xFF);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (((Object)((Object)this)).getClass() != other.getClass()) {
            return false;
        }
        FloatBinaryTagImpl that = (FloatBinaryTagImpl)((Object)other);
        return Float.floatToIntBits(this.value) == Float.floatToIntBits(that.value);
    }

    public int intValue() {
        return ShadyPines.floor((float)this.value);
    }

    @NotNull
    public Number numberValue() {
        return Float.valueOf(this.value);
    }

    public float value() {
        return this.value;
    }

    public int hashCode() {
        return Float.hashCode(this.value);
    }

    public float floatValue() {
        return this.value;
    }
}
