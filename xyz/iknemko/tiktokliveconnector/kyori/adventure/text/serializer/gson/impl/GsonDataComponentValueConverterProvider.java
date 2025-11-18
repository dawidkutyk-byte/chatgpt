/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  awe7
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValue$Removed
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValueConverterRegistry$Conversion
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValueConverterRegistry$Provider
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonDataComponentValue
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import java.util.Collections;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValueConverterRegistry;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonDataComponentValue;

@awe7(value={DataComponentValueConverterRegistry.Provider.class})
@ApiStatus.Internal
public final class GsonDataComponentValueConverterProvider
implements DataComponentValueConverterRegistry.Provider {
    private static final Key ID = Key.key((String)"adventure", (String)"serializer/gson");

    @NotNull
    public Iterable<DataComponentValueConverterRegistry.Conversion<?, ?>> conversions() {
        return Collections.singletonList(DataComponentValueConverterRegistry.Conversion.convert(DataComponentValue.Removed.class, GsonDataComponentValue.class, (k, removed) -> GsonDataComponentValue.gsonDataComponentValue((JsonElement)JsonNull.INSTANCE)));
    }

    @NotNull
    public Key id() {
        return ID;
    }
}
