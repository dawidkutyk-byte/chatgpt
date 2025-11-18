/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.DummyJSONComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONComponentSerializer$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.LegacyHoverEventSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState$Builder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json;

import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.DummyJSONComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.LegacyHoverEventSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionState;

static final class DummyJSONComponentSerializer.BuilderImpl
implements JSONComponentSerializer.Builder {
    @Deprecated
    @NotNull
    public JSONComponentSerializer.Builder downsampleColors() {
        return this;
    }

    DummyJSONComponentSerializer.BuilderImpl() {
    }

    @NotNull
    public JSONComponentSerializer.Builder editOptions(@NotNull Consumer<OptionState.Builder> optionEditor) {
        return this;
    }

    @NotNull
    public JSONComponentSerializer.Builder options(@NotNull OptionState flags) {
        return this;
    }

    @NotNull
    public JSONComponentSerializer.Builder legacyHoverEventSerializer(@Nullable LegacyHoverEventSerializer serializer) {
        return this;
    }

    @Deprecated
    @NotNull
    public JSONComponentSerializer.Builder emitLegacyHoverEvent() {
        return this;
    }

    @NotNull
    public JSONComponentSerializer build() {
        return DummyJSONComponentSerializer.INSTANCE;
    }
}
