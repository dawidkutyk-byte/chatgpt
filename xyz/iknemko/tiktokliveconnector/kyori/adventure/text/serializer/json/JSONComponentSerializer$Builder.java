/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.LegacyHoverEventSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState$Builder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json;

import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.LegacyHoverEventSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionState;

public static interface JSONComponentSerializer.Builder {
    @NotNull
    public JSONComponentSerializer.Builder options(@NotNull OptionState var1);

    @Deprecated
    @NotNull
    public JSONComponentSerializer.Builder emitLegacyHoverEvent();

    @NotNull
    public JSONComponentSerializer.Builder legacyHoverEventSerializer(@Nullable LegacyHoverEventSerializer var1);

    @NotNull
    public JSONComponentSerializer.Builder editOptions(@NotNull Consumer<OptionState.Builder> var1);

    @NotNull
    public JSONComponentSerializer build();

    @Deprecated
    @NotNull
    public JSONComponentSerializer.Builder downsampleColors();
}
