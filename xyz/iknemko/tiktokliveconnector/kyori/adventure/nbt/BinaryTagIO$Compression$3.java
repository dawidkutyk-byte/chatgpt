/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagIO$Compression
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagIO;

class BinaryTagIO.Compression.3
extends BinaryTagIO.Compression {
    BinaryTagIO.Compression.3() {
    }

    @NotNull
    OutputStream compress(@NotNull OutputStream os) {
        return new DeflaterOutputStream(os);
    }

    @NotNull
    InputStream decompress(@NotNull InputStream is) {
        return new InflaterInputStream(is);
    }

    public String toString() {
        return "Compression.ZLIB";
    }
}
