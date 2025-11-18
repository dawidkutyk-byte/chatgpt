/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType;

public interface NumberBinaryTag
extends BinaryTag {
    @NotNull
    public BinaryTagType<? extends NumberBinaryTag> type();

    public byte byteValue();

    @NotNull
    public Number numberValue();

    public float floatValue();

    public short shortValue();

    public int intValue();

    public double doubleValue();

    public long longValue();
}
