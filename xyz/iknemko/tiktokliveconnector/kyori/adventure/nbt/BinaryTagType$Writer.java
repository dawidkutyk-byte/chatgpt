/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.io.DataOutput;
import java.io.IOException;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag;

static interface BinaryTagType.Writer<T extends BinaryTag> {
    public void write(@NotNull T var1, @NotNull DataOutput var2) throws IOException;
}
