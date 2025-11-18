/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ByteArrayBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ByteBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.DoubleBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.FloatBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.IntArrayBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.IntBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.LongArrayBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.LongBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ShortBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.StringBinaryTag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.util.Map;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ByteArrayBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ByteBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.DoubleBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.FloatBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.IntArrayBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.IntBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.LongArrayBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.LongBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ShortBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.StringBinaryTag;

public interface CompoundTagSetter<R> {
    @NotNull
    default public R remove(@NotNull String key) {
        return this.remove(key, null);
    }

    @NotNull
    default public R putString(@NotNull String key, @NotNull String value) {
        return this.put(key, (BinaryTag)StringBinaryTag.stringBinaryTag((String)value));
    }

    @NotNull
    default public R putByteArray(@NotNull String key, byte @NotNull [] value) {
        return this.put(key, (BinaryTag)ByteArrayBinaryTag.byteArrayBinaryTag((byte[])value));
    }

    @NotNull
    default public R putShort(@NotNull String key, short value) {
        return this.put(key, (BinaryTag)ShortBinaryTag.shortBinaryTag((short)value));
    }

    @NotNull
    default public R putFloat(@NotNull String key, float value) {
        return this.put(key, (BinaryTag)FloatBinaryTag.floatBinaryTag((float)value));
    }

    @NotNull
    default public R putDouble(@NotNull String key, double value) {
        return this.put(key, (BinaryTag)DoubleBinaryTag.doubleBinaryTag((double)value));
    }

    @NotNull
    public R remove(@NotNull String var1, @Nullable Consumer<? super BinaryTag> var2);

    @NotNull
    public R put(@NotNull Map<String, ? extends BinaryTag> var1);

    @NotNull
    default public R putBoolean(@NotNull String key, boolean value) {
        return this.put(key, (BinaryTag)(value ? ByteBinaryTag.ONE : ByteBinaryTag.ZERO));
    }

    @NotNull
    default public R putInt(@NotNull String key, int value) {
        return this.put(key, (BinaryTag)IntBinaryTag.intBinaryTag((int)value));
    }

    @NotNull
    default public R putByte(@NotNull String key, byte value) {
        return this.put(key, (BinaryTag)ByteBinaryTag.byteBinaryTag((byte)value));
    }

    @NotNull
    default public R putLongArray(@NotNull String key, long @NotNull [] value) {
        return this.put(key, (BinaryTag)LongArrayBinaryTag.longArrayBinaryTag((long[])value));
    }

    @NotNull
    default public R putLong(@NotNull String key, long value) {
        return this.put(key, (BinaryTag)LongBinaryTag.longBinaryTag((long)value));
    }

    @NotNull
    public R put(@NotNull CompoundBinaryTag var1);

    @NotNull
    default public R putIntArray(@NotNull String key, int @NotNull [] value) {
        return this.put(key, (BinaryTag)IntArrayBinaryTag.intArrayBinaryTag((int[])value));
    }

    @NotNull
    public R put(@NotNull String var1, @NotNull BinaryTag var2);
}
