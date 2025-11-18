/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType$Reader
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType$Writer
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType;

static class BinaryTagType.Impl<T extends BinaryTag>
extends BinaryTagType<T> {
    private final BinaryTagType.Reader<T> reader;
    final Class<T> type;
    @Nullable
    private final BinaryTagType.Writer<T> writer;
    final byte id;

    boolean numeric() {
        return false;
    }

    public String toString() {
        return BinaryTagType.class.getSimpleName() + '[' + this.type.getSimpleName() + " " + this.id + "]";
    }

    public final void write(@NotNull T tag, @NotNull DataOutput output) throws IOException {
        if (this.writer == null) return;
        this.writer.write(tag, output);
    }

    public final byte id() {
        return this.id;
    }

    @NotNull
    public final T read(@NotNull DataInput input) throws IOException {
        return (T)this.reader.read(input);
    }

    BinaryTagType.Impl(Class<T> type, byte id, BinaryTagType.Reader<T> reader, @Nullable BinaryTagType.Writer<T> writer) {
        this.type = type;
        this.id = id;
        this.reader = reader;
        this.writer = writer;
    }
}
