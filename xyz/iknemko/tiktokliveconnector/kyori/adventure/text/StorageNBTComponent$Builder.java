/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponentBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.StorageNBTComponent
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponentBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.StorageNBTComponent;

public static interface StorageNBTComponent.Builder
extends NBTComponentBuilder<StorageNBTComponent, StorageNBTComponent.Builder> {
    @NotNull
    @Contract(value="_ -> this")
    public StorageNBTComponent.Builder storage(@NotNull Key var1);
}
