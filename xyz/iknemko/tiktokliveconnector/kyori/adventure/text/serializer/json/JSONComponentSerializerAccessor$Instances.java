/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.DummyJSONComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.DummyJSONComponentSerializer$BuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONComponentSerializer$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONComponentSerializer$Provider
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONComponentSerializerAccessor
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json;

import java.util.function.Supplier;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.DummyJSONComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONComponentSerializerAccessor;

/*
 * Exception performing whole class analysis ignored.
 */
static final class JSONComponentSerializerAccessor.Instances {
    static final Supplier<JSONComponentSerializer.Builder> BUILDER_SUPPLIER;
    static final JSONComponentSerializer INSTANCE;

    static {
        INSTANCE = JSONComponentSerializerAccessor.access$000().map(JSONComponentSerializer.Provider::instance).orElse(DummyJSONComponentSerializer.INSTANCE);
        BUILDER_SUPPLIER = JSONComponentSerializerAccessor.access$000().map(JSONComponentSerializer.Provider::builder).orElse(DummyJSONComponentSerializer.BuilderImpl::new);
    }

    JSONComponentSerializerAccessor.Instances() {
    }
}
