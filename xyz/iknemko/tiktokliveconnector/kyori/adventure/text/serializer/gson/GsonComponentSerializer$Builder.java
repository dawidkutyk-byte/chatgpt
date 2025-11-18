/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.LegacyHoverEventSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONComponentSerializer$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONOptions
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONOptions$HoverEventValueMode
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.LegacyHoverEventSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState$Builder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson;

import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.LegacyHoverEventSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONOptions;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionState;

public static interface GsonComponentSerializer.Builder
extends AbstractBuilder<GsonComponentSerializer>,
Buildable.Builder<GsonComponentSerializer>,
JSONComponentSerializer.Builder {
    @Deprecated
    @NotNull
    default public GsonComponentSerializer.Builder legacyHoverEventSerializer(@Nullable LegacyHoverEventSerializer serializer) {
        return this.legacyHoverEventSerializer((xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.LegacyHoverEventSerializer)serializer);
    }

    @NotNull
    public GsonComponentSerializer.Builder options(@NotNull OptionState var1);

    @Deprecated
    @NotNull
    default public GsonComponentSerializer.Builder emitLegacyHoverEvent() {
        return this.editOptions(b -> b.value(JSONOptions.EMIT_HOVER_EVENT_TYPE, (Object)JSONOptions.HoverEventValueMode.ALL));
    }

    @NotNull
    default public GsonComponentSerializer.Builder downsampleColors() {
        return this.editOptions(features -> features.value(JSONOptions.EMIT_RGB, (Object)false));
    }

    @NotNull
    public GsonComponentSerializer.Builder legacyHoverEventSerializer(@Nullable xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.LegacyHoverEventSerializer var1);

    @NotNull
    public GsonComponentSerializer build();

    @NotNull
    public GsonComponentSerializer.Builder editOptions(@NotNull Consumer<OptionState.Builder> var1);
}
