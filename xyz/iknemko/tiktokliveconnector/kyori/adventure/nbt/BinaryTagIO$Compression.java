/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.jetbrains.annotations.NotNull;

public static abstract class BinaryTagIO.Compression {
    public static final BinaryTagIO.Compression ZLIB;
    public static final BinaryTagIO.Compression GZIP;
    public static final BinaryTagIO.Compression NONE;

    static {
        NONE = new /* Unavailable Anonymous Inner Class!! */;
        GZIP = new /* Unavailable Anonymous Inner Class!! */;
        ZLIB = new /* Unavailable Anonymous Inner Class!! */;
    }

    @NotNull
    abstract InputStream decompress(@NotNull InputStream var1) throws IOException;

    @NotNull
    abstract OutputStream compress(@NotNull OutputStream var1) throws IOException;
}
