/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagIO$Compression
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.io.InputStream;
import java.io.OutputStream;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagIO;

class BinaryTagIO.Compression.1
extends BinaryTagIO.Compression {
    BinaryTagIO.Compression.1() {
    }

    public String toString() {
        return "Compression.NONE";
    }

    @NotNull
    InputStream decompress(@NotNull InputStream is) {
        return is;
    }

    @NotNull
    OutputStream compress(@NotNull OutputStream os) {
        return os;
    }
}
