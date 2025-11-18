/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType$Impl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType$Reader
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType$Writer
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType;

static class BinaryTagType.Impl.Numeric<T extends BinaryTag>
extends BinaryTagType.Impl<T> {
    boolean numeric() {
        return true;
    }

    public String toString() {
        return BinaryTagType.class.getSimpleName() + '[' + this.type.getSimpleName() + " " + this.id + " (numeric)]";
    }

    BinaryTagType.Impl.Numeric(Class<T> type, byte id, BinaryTagType.Reader<T> reader, @Nullable BinaryTagType.Writer<T> writer) {
        super(type, id, reader, writer);
    }
}
