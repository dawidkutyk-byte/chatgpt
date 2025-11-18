/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.AbstractBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.IntBinaryTag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.Debug;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.AbstractBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.IntBinaryTag;

@Debug.Renderer(text="String.valueOf(this.value) + \"i\"", hasChildren="false")
final class IntBinaryTagImpl
extends AbstractBinaryTag
implements IntBinaryTag {
    private final int value;

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (((Object)((Object)this)).getClass() != other.getClass()) {
            return false;
        }
        IntBinaryTagImpl that = (IntBinaryTagImpl)((Object)other);
        return this.value == that.value;
    }

    public double doubleValue() {
        return this.value;
    }

    public int intValue() {
        return this.value;
    }

    public float floatValue() {
        return this.value;
    }

    public short shortValue() {
        return (short)(this.value & 0xFFFF);
    }

    public byte byteValue() {
        return (byte)(this.value & 0xFF);
    }

    IntBinaryTagImpl(int value) {
        this.value = value;
    }

    public int hashCode() {
        return Integer.hashCode(this.value);
    }

    @NotNull
    public Number numberValue() {
        return this.value;
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"value", (int)this.value));
    }

    public long longValue() {
        return this.value;
    }

    public int value() {
        return this.value;
    }
}
