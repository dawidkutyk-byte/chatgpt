/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.ComponentSerializerImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializer$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializer$Provider
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializerImpl$BuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.SerializerFactory
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.LegacyHoverEventSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Services
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapterFactory;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.ComponentSerializerImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializerImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.SerializerFactory;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.LegacyHoverEventSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Services;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionState;

final class GsonComponentSerializerImpl
implements GsonComponentSerializer {
    private final @Nullable LegacyHoverEventSerializer legacyHoverSerializer;
    private final UnaryOperator<GsonBuilder> populator;
    static final Consumer<GsonComponentSerializer.Builder> BUILDER;
    private final OptionState flags;
    private static final Optional<GsonComponentSerializer.Provider> SERVICE;
    private final Gson serializer;

    @NotNull
    public JsonElement serializeToTree(@NotNull Component component) {
        return this.serializer().toJsonTree(component);
    }

    @NotNull
    public Component deserializeFromTree(@NotNull JsonElement input) {
        Component component = this.serializer().fromJson(input, Component.class);
        if (component != null) return component;
        throw ComponentSerializerImpl.notSureHowToDeserialize((Object)input);
    }

    static /* synthetic */ LegacyHoverEventSerializer access$200(GsonComponentSerializerImpl x0) {
        return x0.legacyHoverSerializer;
    }

    static {
        SERVICE = Services.service(GsonComponentSerializer.Provider.class);
        BUILDER = SERVICE.map(GsonComponentSerializer.Provider::builder).orElseGet(() -> builder -> {});
    }

    @NotNull
    public GsonComponentSerializer.Builder toBuilder() {
        return new BuilderImpl(this);
    }

    static /* synthetic */ OptionState access$100(GsonComponentSerializerImpl x0) {
        return x0.flags;
    }

    GsonComponentSerializerImpl(OptionState flags, @Nullable LegacyHoverEventSerializer legacyHoverSerializer) {
        this.flags = flags;
        this.legacyHoverSerializer = legacyHoverSerializer;
        this.populator = builder -> {
            builder.registerTypeAdapterFactory((TypeAdapterFactory)new SerializerFactory(flags, legacyHoverSerializer));
            return builder;
        };
        this.serializer = ((GsonBuilder)this.populator.apply(new GsonBuilder().disableHtmlEscaping())).create();
    }

    @NotNull
    public Gson serializer() {
        return this.serializer;
    }

    @NotNull
    public UnaryOperator<GsonBuilder> populator() {
        return this.populator;
    }

    @NotNull
    public String serialize(@NotNull Component component) {
        return this.serializer().toJson(component);
    }

    static /* synthetic */ Optional access$000() {
        return SERVICE;
    }

    @NotNull
    public Component deserialize(@NotNull String string) {
        Component component = this.serializer().fromJson(string, Component.class);
        if (component != null) return component;
        throw ComponentSerializerImpl.notSureHowToDeserialize((Object)string);
    }

    @Nullable
    public Component deserializeOr(@Nullable String input, @Nullable Component fallback) {
        if (input == null) {
            return fallback;
        }
        Component component = this.serializer().fromJson(input, Component.class);
        if (component != null) return component;
        return fallback;
    }
}
