/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagIO$Writer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.IOStreamUtil
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.io.BufferedOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagIO;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.IOStreamUtil;

final class BinaryTagWriterImpl
implements BinaryTagIO.Writer {
    static final BinaryTagIO.Writer INSTANCE = new BinaryTagWriterImpl();

    public void writeNameless(@NotNull CompoundBinaryTag tag, @NotNull OutputStream output, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull BinaryTagIO.Compression compression) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(compression.compress(IOStreamUtil.closeShield((OutputStream)output))));){
            this.writeNameless(tag, dos);
        }
    }

    public void write(@NotNull CompoundBinaryTag tag, @NotNull OutputStream output, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull BinaryTagIO.Compression compression) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(compression.compress(IOStreamUtil.closeShield((OutputStream)output))));){
            this.write(tag, dos);
        }
    }

    public void write(@NotNull CompoundBinaryTag tag, @NotNull Path path, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull BinaryTagIO.Compression compression) throws IOException {
        try (OutputStream os = Files.newOutputStream(path, new OpenOption[0]);){
            this.write(tag, os, compression);
        }
    }

    public void writeNamed( @NotNull Map.Entry<String, CompoundBinaryTag> tag, @NotNull Path path, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull BinaryTagIO.Compression compression) throws IOException {
        try (OutputStream os = Files.newOutputStream(path, new OpenOption[0]);){
            this.writeNamed(tag, os, compression);
        }
    }

    public void writeNamed( @NotNull Map.Entry<String, CompoundBinaryTag> tag, @NotNull DataOutput output) throws IOException {
        output.writeByte(BinaryTagTypes.COMPOUND.id());
        output.writeUTF(tag.getKey());
        BinaryTagTypes.COMPOUND.write((BinaryTag)tag.getValue(), output);
    }

    public void writeNameless(@NotNull CompoundBinaryTag tag, @NotNull DataOutput output) throws IOException {
        this.write(tag, output, false);
    }

    public void writeNamed( @NotNull Map.Entry<String, CompoundBinaryTag> tag, @NotNull OutputStream output, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull BinaryTagIO.Compression compression) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(compression.compress(IOStreamUtil.closeShield((OutputStream)output))));){
            this.writeNamed(tag, dos);
        }
    }

    BinaryTagWriterImpl() {
    }

    public void writeNameless(@NotNull CompoundBinaryTag tag, @NotNull Path path, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull BinaryTagIO.Compression compression) throws IOException {
        try (OutputStream os = Files.newOutputStream(path, new OpenOption[0]);){
            this.writeNameless(tag, os, compression);
        }
    }

    public void write(@NotNull CompoundBinaryTag tag, @NotNull DataOutput output) throws IOException {
        this.write(tag, output, true);
    }

    private void write(@NotNull CompoundBinaryTag tag, @NotNull DataOutput output, boolean named) throws IOException {
        output.writeByte(BinaryTagTypes.COMPOUND.id());
        if (named) {
            output.writeUTF("");
        }
        BinaryTagTypes.COMPOUND.write((BinaryTag)tag, output);
    }
}
