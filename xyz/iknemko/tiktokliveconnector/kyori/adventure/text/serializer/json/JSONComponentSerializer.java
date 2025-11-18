/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.ComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONComponentSerializer$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONComponentSerializerAccessor$Instances
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.ComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONComponentSerializerAccessor;

public interface JSONComponentSerializer
extends ComponentSerializer<Component, Component, String> {
    public static // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull JSONComponentSerializer.Builder builder() {
        return (Builder)JSONComponentSerializerAccessor.Instances.BUILDER_SUPPLIER.get();
    }

    @NotNull
    public static JSONComponentSerializer json() {
        return JSONComponentSerializerAccessor.Instances.INSTANCE;
    }
}
