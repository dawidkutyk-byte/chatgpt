/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  awe7
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONComponentSerializer$Provider
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Services$Fallback
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.impl;

import java.util.function.Supplier;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Services;

@ApiStatus.Internal
@awe7(value={JSONComponentSerializer.Provider.class})
public final class JSONComponentSerializerProviderImpl
implements JSONComponentSerializer.Provider,
Services.Fallback {
    @NotNull
    public @NotNull Supplier<// Could not load outer class - annotation placement on inner may be incorrect
    @NotNull JSONComponentSerializer.Builder> builder() {
        return GsonComponentSerializer::builder;
    }

    public String toString() {
        return "JSONComponentSerializerProviderImpl[GsonComponentSerializer]";
    }

    @NotNull
    public JSONComponentSerializer instance() {
        return GsonComponentSerializer.gson();
    }
}
