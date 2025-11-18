/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagIO$Reader
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.IOStreamUtil
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.TrackingDataInput
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.io.BufferedInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagIO;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.IOStreamUtil;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.TrackingDataInput;

final class BinaryTagReaderImpl
implements BinaryTagIO.Reader {
    static final BinaryTagIO.Reader DEFAULT_LIMIT;
    static final BinaryTagIO.Reader UNLIMITED;
    private final long maxBytes;

    @NotNull
    public CompoundBinaryTag readNameless(@NotNull InputStream input, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull BinaryTagIO.Compression compression) throws IOException {
        try (DataInputStream dis = new DataInputStream(new BufferedInputStream(compression.decompress(IOStreamUtil.closeShield((InputStream)input))));){
            CompoundBinaryTag compoundBinaryTag = this.readNameless(dis);
            return compoundBinaryTag;
        }
    }

    @NotNull
    public CompoundBinaryTag readNameless(@NotNull Path path, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull BinaryTagIO.Compression compression) throws IOException {
        try (InputStream is = Files.newInputStream(path, new OpenOption[0]);){
            CompoundBinaryTag compoundBinaryTag = this.readNameless(is, compression);
            return compoundBinaryTag;
        }
    }

    @NotNull
    private CompoundBinaryTag read(@NotNull DataInput input, boolean named) throws IOException {
        if (!(input instanceof TrackingDataInput)) {
            input = new TrackingDataInput(input, this.maxBytes);
        }
        BinaryTagType type = BinaryTagType.binaryTagType((byte)input.readByte());
        BinaryTagReaderImpl.requireCompound((BinaryTagType<? extends BinaryTag>)type);
        if (!named) return (CompoundBinaryTag)BinaryTagTypes.COMPOUND.read(input);
        input.skipBytes(input.readUnsignedShort());
        return (CompoundBinaryTag)BinaryTagTypes.COMPOUND.read(input);
    }

    public  @NotNull Map.Entry<String, CompoundBinaryTag> readNamed(@NotNull Path path, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull BinaryTagIO.Compression compression) throws IOException {
        try (InputStream is = Files.newInputStream(path, new OpenOption[0]);){
            Map.Entry<String, CompoundBinaryTag> entry = this.readNamed(is, compression);
            return entry;
        }
    }

    public  @NotNull Map.Entry<String, CompoundBinaryTag> readNamed(@NotNull InputStream input, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull BinaryTagIO.Compression compression) throws IOException {
        try (DataInputStream dis = new DataInputStream(new BufferedInputStream(compression.decompress(IOStreamUtil.closeShield((InputStream)input))));){
            Map.Entry<String, CompoundBinaryTag> entry = this.readNamed(dis);
            return entry;
        }
    }

    static {
        UNLIMITED = new BinaryTagReaderImpl(-1L);
        DEFAULT_LIMIT = new BinaryTagReaderImpl(131082L);
    }

    BinaryTagReaderImpl(long maxBytes) {
        this.maxBytes = maxBytes;
    }

    @NotNull
    public CompoundBinaryTag read(@NotNull InputStream input, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull BinaryTagIO.Compression compression) throws IOException {
        try (DataInputStream dis = new DataInputStream(new BufferedInputStream(compression.decompress(IOStreamUtil.closeShield((InputStream)input))));){
            CompoundBinaryTag compoundBinaryTag = this.read(dis);
            return compoundBinaryTag;
        }
    }

    @NotNull
    public CompoundBinaryTag read(@NotNull DataInput input) throws IOException {
        return this.read(input, true);
    }

    public  @NotNull Map.Entry<String, CompoundBinaryTag> readNamed(@NotNull DataInput input) throws IOException {
        BinaryTagType type = BinaryTagType.binaryTagType((byte)input.readByte());
        BinaryTagReaderImpl.requireCompound((BinaryTagType<? extends BinaryTag>)type);
        String name = input.readUTF();
        return new AbstractMap.SimpleImmutableEntry<String, CompoundBinaryTag>(name, (CompoundBinaryTag)BinaryTagTypes.COMPOUND.read(input));
    }

    private static void requireCompound(BinaryTagType<? extends BinaryTag> type) throws IOException {
        if (type == BinaryTagTypes.COMPOUND) return;
        throw new IOException(String.format("Expected root tag to be a %s, was %s", BinaryTagTypes.COMPOUND, type));
    }

    @NotNull
    public CompoundBinaryTag readNameless(@NotNull DataInput input) throws IOException {
        return this.read(input, false);
    }

    @NotNull
    public CompoundBinaryTag read(@NotNull Path path, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull BinaryTagIO.Compression compression) throws IOException {
        try (InputStream is = Files.newInputStream(path, new OpenOption[0]);){
            CompoundBinaryTag compoundBinaryTag = this.read(is, compression);
            return compoundBinaryTag;
        }
    }
}
