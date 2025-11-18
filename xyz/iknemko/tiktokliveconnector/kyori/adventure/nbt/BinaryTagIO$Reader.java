/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagIO$Compression
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagIO;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag;

public static interface BinaryTagIO.Reader {
    public  @NotNull Map.Entry<String, CompoundBinaryTag> readNamed(@NotNull Path var1, @NotNull BinaryTagIO.Compression var2) throws IOException;

    @NotNull
    public CompoundBinaryTag read(@NotNull DataInput var1) throws IOException;

    public  @NotNull Map.Entry<String, CompoundBinaryTag> readNamed(@NotNull InputStream var1, @NotNull BinaryTagIO.Compression var2) throws IOException;

    default public  @NotNull Map.Entry<String, CompoundBinaryTag> readNamed(@NotNull Path path) throws IOException {
        return this.readNamed(path, BinaryTagIO.Compression.NONE);
    }

    @NotNull
    public CompoundBinaryTag read(@NotNull InputStream var1, @NotNull BinaryTagIO.Compression var2) throws IOException;

    @NotNull
    default public CompoundBinaryTag readNameless(@NotNull InputStream input) throws IOException {
        return this.readNameless(input, BinaryTagIO.Compression.NONE);
    }

    @NotNull
    public CompoundBinaryTag readNameless(@NotNull Path var1, @NotNull BinaryTagIO.Compression var2) throws IOException;

    @NotNull
    public CompoundBinaryTag read(@NotNull Path var1, @NotNull BinaryTagIO.Compression var2) throws IOException;

    @NotNull
    public CompoundBinaryTag readNameless(@NotNull DataInput var1) throws IOException;

    default public  @NotNull Map.Entry<String, CompoundBinaryTag> readNamed(@NotNull InputStream input) throws IOException {
        return this.readNamed(input, BinaryTagIO.Compression.NONE);
    }

    @NotNull
    public CompoundBinaryTag readNameless(@NotNull InputStream var1, @NotNull BinaryTagIO.Compression var2) throws IOException;

    @NotNull
    default public CompoundBinaryTag read(@NotNull Path path) throws IOException {
        return this.read(path, BinaryTagIO.Compression.NONE);
    }

    @NotNull
    default public CompoundBinaryTag readNameless(@NotNull Path path) throws IOException {
        return this.readNameless(path, BinaryTagIO.Compression.NONE);
    }

    @NotNull
    default public CompoundBinaryTag read(@NotNull InputStream input) throws IOException {
        return this.read(input, BinaryTagIO.Compression.NONE);
    }

    public  @NotNull Map.Entry<String, CompoundBinaryTag> readNamed(@NotNull DataInput var1) throws IOException;
}
