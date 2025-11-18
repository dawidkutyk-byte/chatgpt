/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.EntityNBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponentBuilder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.EntityNBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponentBuilder;

public static interface EntityNBTComponent.Builder
extends NBTComponentBuilder<EntityNBTComponent, EntityNBTComponent.Builder> {
    @NotNull
    @Contract(value="_ -> this")
    public EntityNBTComponent.Builder selector(@NotNull String var1);
}
