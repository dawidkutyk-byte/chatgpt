/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagIO$Compression
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagIO;

class BinaryTagIO.Compression.2
extends BinaryTagIO.Compression {
    public String toString() {
        return "Compression.GZIP";
    }

    @NotNull
    InputStream decompress(@NotNull InputStream is) throws IOException {
        return new GZIPInputStream(is);
    }

    @NotNull
    OutputStream compress(@NotNull OutputStream os) throws IOException {
        return new GZIPOutputStream(os);
    }

    BinaryTagIO.Compression.2() {
    }
}
