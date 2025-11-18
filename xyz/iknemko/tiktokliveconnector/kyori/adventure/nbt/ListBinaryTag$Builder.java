/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListTagSetter
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListTagSetter;

public static interface ListBinaryTag.Builder<T extends BinaryTag>
extends ListTagSetter<ListBinaryTag.Builder<T>, T> {
    @NotNull
    public ListBinaryTag build();
}
