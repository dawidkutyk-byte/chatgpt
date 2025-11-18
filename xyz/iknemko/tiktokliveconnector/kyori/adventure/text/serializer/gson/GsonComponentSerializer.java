/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializer$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializerImpl$BuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializerImpl$Instances
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import java.util.function.UnaryOperator;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializerImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable;

public interface GsonComponentSerializer
extends JSONComponentSerializer,
Buildable<GsonComponentSerializer, Builder> {
    @NotNull
    public static GsonComponentSerializer colorDownsamplingGson() {
        return GsonComponentSerializerImpl.Instances.LEGACY_INSTANCE;
    }

    @NotNull
    public Component deserializeFromTree(@NotNull JsonElement var1);

    @NotNull
    public Gson serializer();

    public static Builder builder() {
        return new GsonComponentSerializerImpl.BuilderImpl();
    }

    @NotNull
    public static GsonComponentSerializer gson() {
        return GsonComponentSerializerImpl.Instances.INSTANCE;
    }

    @NotNull
    public JsonElement serializeToTree(@NotNull Component var1);

    @NotNull
    public UnaryOperator<GsonBuilder> populator();
}
