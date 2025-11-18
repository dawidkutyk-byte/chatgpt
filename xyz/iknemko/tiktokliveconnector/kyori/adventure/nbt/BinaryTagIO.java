/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagIO$Compression
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagIO$Reader
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagIO$Writer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagReaderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagWriterImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagIO;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagReaderImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagWriterImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag;

public final class BinaryTagIO {
    @Deprecated
    @NotNull
    public static CompoundBinaryTag readDataInput(@NotNull DataInput input) throws IOException {
        return BinaryTagIO.reader().read(input);
    }

    @Deprecated
    @NotNull
    public static CompoundBinaryTag readCompressedPath(@NotNull Path path) throws IOException {
        return BinaryTagIO.reader().read(path, Compression.GZIP);
    }

    @NotNull
    public static Reader unlimitedReader() {
        return BinaryTagReaderImpl.UNLIMITED;
    }

    @Deprecated
    public static void writeOutputStream(@NotNull CompoundBinaryTag tag, @NotNull OutputStream output) throws IOException {
        BinaryTagIO.writer().write(tag, output);
    }

    @Deprecated
    public static void writeDataOutput(@NotNull CompoundBinaryTag tag, @NotNull DataOutput output) throws IOException {
        BinaryTagIO.writer().write(tag, output);
    }

    @Deprecated
    public static void writeCompressedPath(@NotNull CompoundBinaryTag tag, @NotNull Path path) throws IOException {
        BinaryTagIO.writer().write(tag, path, Compression.GZIP);
    }

    private BinaryTagIO() {
    }

    @Deprecated
    public static void writePath(@NotNull CompoundBinaryTag tag, @NotNull Path path) throws IOException {
        BinaryTagIO.writer().write(tag, path);
    }

    @NotNull
    public static Writer writer() {
        return BinaryTagWriterImpl.INSTANCE;
    }

    static {
        BinaryTagTypes.COMPOUND.id();
    }

    @Deprecated
    @NotNull
    public static CompoundBinaryTag readCompressedInputStream(@NotNull InputStream input) throws IOException {
        return BinaryTagIO.reader().read(input, Compression.GZIP);
    }

    @Deprecated
    public static void writeCompressedOutputStream(@NotNull CompoundBinaryTag tag, @NotNull OutputStream output) throws IOException {
        BinaryTagIO.writer().write(tag, output, Compression.GZIP);
    }

    @NotNull
    public static Reader reader(long sizeLimitBytes) {
        if (sizeLimitBytes > 0L) return new BinaryTagReaderImpl(sizeLimitBytes);
        throw new IllegalArgumentException("The size limit must be greater than zero");
    }

    @Deprecated
    @NotNull
    public static CompoundBinaryTag readPath(@NotNull Path path) throws IOException {
        return BinaryTagIO.reader().read(path);
    }

    @NotNull
    public static Reader reader() {
        return BinaryTagReaderImpl.DEFAULT_LIMIT;
    }

    @Deprecated
    @NotNull
    public static CompoundBinaryTag readInputStream(@NotNull InputStream input) throws IOException {
        return BinaryTagIO.reader().read(input);
    }
}
