/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializer$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializerImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONOptions
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.LegacyHoverEventSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState$Builder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson;

import java.util.Objects;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializerImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONOptions;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.LegacyHoverEventSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionState;

/*
 * Exception performing whole class analysis ignored.
 */
static final class GsonComponentSerializerImpl.BuilderImpl
implements GsonComponentSerializer.Builder {
    private OptionState flags = JSONOptions.byDataVersion();
    private @Nullable LegacyHoverEventSerializer legacyHoverSerializer;

    @NotNull
    public GsonComponentSerializer.Builder legacyHoverEventSerializer(@Nullable LegacyHoverEventSerializer serializer) {
        this.legacyHoverSerializer = serializer;
        return this;
    }

    GsonComponentSerializerImpl.BuilderImpl() {
        GsonComponentSerializerImpl.BUILDER.accept(this);
    }

    @NotNull
    public GsonComponentSerializer.Builder editOptions(@NotNull Consumer<OptionState.Builder> optionEditor) {
        OptionState.Builder builder = JSONOptions.schema().stateBuilder().values(this.flags);
        Objects.requireNonNull(optionEditor, "flagEditor").accept(builder);
        this.flags = builder.build();
        return this;
    }

    @NotNull
    public GsonComponentSerializer.Builder options(@NotNull OptionState flags) {
        this.flags = Objects.requireNonNull(flags, "flags");
        return this;
    }

    GsonComponentSerializerImpl.BuilderImpl(GsonComponentSerializerImpl serializer) {
        this();
        this.flags = GsonComponentSerializerImpl.access$100((GsonComponentSerializerImpl)serializer);
        this.legacyHoverSerializer = GsonComponentSerializerImpl.access$200((GsonComponentSerializerImpl)serializer);
    }

    @NotNull
    public GsonComponentSerializer build() {
        return new GsonComponentSerializerImpl(this.flags, this.legacyHoverSerializer);
    }
}
