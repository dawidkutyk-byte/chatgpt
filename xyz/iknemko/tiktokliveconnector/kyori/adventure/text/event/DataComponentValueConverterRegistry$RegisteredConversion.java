/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValueConverterRegistry$Conversion
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValueConverterRegistry;

static final class DataComponentValueConverterRegistry.RegisteredConversion {
    final DataComponentValueConverterRegistry.Conversion<?, ?> conversion;
    final Key provider;
    static final DataComponentValueConverterRegistry.RegisteredConversion NONE = new DataComponentValueConverterRegistry.RegisteredConversion(null, null);

    DataComponentValueConverterRegistry.RegisteredConversion(Key provider, DataComponentValueConverterRegistry.Conversion<?, ?> conversion) {
        this.provider = provider;
        this.conversion = conversion;
    }
}
