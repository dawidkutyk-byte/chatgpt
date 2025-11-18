/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagIO$Compression
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.io.DataOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagIO;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag;

public static interface BinaryTagIO.Writer {
    default public void write(@NotNull CompoundBinaryTag tag, @NotNull Path path) throws IOException {
        this.write(tag, path, BinaryTagIO.Compression.NONE);
    }

    public void writeNamed( @NotNull Map.Entry<String, CompoundBinaryTag> var1, @NotNull DataOutput var2) throws IOException;

    public void writeNamed( @NotNull Map.Entry<String, CompoundBinaryTag> var1, @NotNull Path var2, @NotNull BinaryTagIO.Compression var3) throws IOException;

    public void write(@NotNull CompoundBinaryTag var1, @NotNull Path var2, @NotNull BinaryTagIO.Compression var3) throws IOException;

    default public void writeNamed( @NotNull Map.Entry<String, CompoundBinaryTag> tag, @NotNull Path path) throws IOException {
        this.writeNamed(tag, path, BinaryTagIO.Compression.NONE);
    }

    default public void write(@NotNull CompoundBinaryTag tag, @NotNull OutputStream output) throws IOException {
        this.write(tag, output, BinaryTagIO.Compression.NONE);
    }

    default public void writeNameless(@NotNull CompoundBinaryTag tag, @NotNull Path path) throws IOException {
        this.writeNameless(tag, path, BinaryTagIO.Compression.NONE);
    }

    public void writeNameless(@NotNull CompoundBinaryTag var1, @NotNull DataOutput var2) throws IOException;

    public void writeNameless(@NotNull CompoundBinaryTag var1, @NotNull OutputStream var2, @NotNull BinaryTagIO.Compression var3) throws IOException;

    public void write(@NotNull CompoundBinaryTag var1, @NotNull OutputStream var2, @NotNull BinaryTagIO.Compression var3) throws IOException;

    public void writeNamed( @NotNull Map.Entry<String, CompoundBinaryTag> var1, @NotNull OutputStream var2, @NotNull BinaryTagIO.Compression var3) throws IOException;

    default public void writeNameless(@NotNull CompoundBinaryTag tag, @NotNull OutputStream output) throws IOException {
        this.writeNameless(tag, output, BinaryTagIO.Compression.NONE);
    }

    default public void writeNamed( @NotNull Map.Entry<String, CompoundBinaryTag> tag, @NotNull OutputStream output) throws IOException {
        this.writeNamed(tag, output, BinaryTagIO.Compression.NONE);
    }

    public void writeNameless(@NotNull CompoundBinaryTag var1, @NotNull Path var2, @NotNull BinaryTagIO.Compression var3) throws IOException;

    public void write(@NotNull CompoundBinaryTag var1, @NotNull DataOutput var2) throws IOException;
}
