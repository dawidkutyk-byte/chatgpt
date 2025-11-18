/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.api.BinaryTagHolder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValue
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.api.BinaryTagHolder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValue;

public static interface DataComponentValue.TagSerializable
extends DataComponentValue {
    @NotNull
    public BinaryTagHolder asBinaryTag();
}
