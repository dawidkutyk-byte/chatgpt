/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattener
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable$Builder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy;

import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattener;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable;

public static interface LegacyComponentSerializer.Builder
extends AbstractBuilder<LegacyComponentSerializer>,
Buildable.Builder<LegacyComponentSerializer> {
    @NotNull
    public LegacyComponentSerializer.Builder extractUrls(@NotNull Pattern var1, @Nullable Style var2);

    @NotNull
    public LegacyComponentSerializer.Builder flattener(@NotNull ComponentFlattener var1);

    @NotNull
    public LegacyComponentSerializer.Builder character(char var1);

    @NotNull
    public LegacyComponentSerializer.Builder hexCharacter(char var1);

    @NotNull
    public LegacyComponentSerializer.Builder extractUrls();

    @NotNull
    public LegacyComponentSerializer.Builder hexColors();

    @NotNull
    public LegacyComponentSerializer.Builder extractUrls(@NotNull Pattern var1);

    @NotNull
    public LegacyComponentSerializer build();

    @NotNull
    public LegacyComponentSerializer.Builder useUnusualXRepeatedCharacterHexFormat();

    @NotNull
    public LegacyComponentSerializer.Builder extractUrls(@Nullable Style var1);
}
