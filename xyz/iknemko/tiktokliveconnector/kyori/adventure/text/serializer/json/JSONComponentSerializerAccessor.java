/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONComponentSerializer$Provider
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Services
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json;

import java.util.Optional;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Services;

final class JSONComponentSerializerAccessor {
    private static final Optional<JSONComponentSerializer.Provider> SERVICE = Services.serviceWithFallback(JSONComponentSerializer.Provider.class);

    private JSONComponentSerializerAccessor() {
    }

    static /* synthetic */ Optional access$000() {
        return SERVICE;
    }
}
