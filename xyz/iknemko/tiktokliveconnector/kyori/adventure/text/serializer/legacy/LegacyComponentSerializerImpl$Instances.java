/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattener
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer$Provider
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializerImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy;

import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattener;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializerImpl;

/*
 * Exception performing whole class analysis ignored.
 */
static final class LegacyComponentSerializerImpl.Instances {
    static final LegacyComponentSerializer AMPERSAND;
    static final LegacyComponentSerializer SECTION;

    LegacyComponentSerializerImpl.Instances() {
    }

    static {
        SECTION = LegacyComponentSerializerImpl.access$000().map(LegacyComponentSerializer.Provider::legacySection).orElseGet(() -> new LegacyComponentSerializerImpl('\u00a7', '#', null, false, false, ComponentFlattener.basic()));
        AMPERSAND = LegacyComponentSerializerImpl.access$000().map(LegacyComponentSerializer.Provider::legacyAmpersand).orElseGet(() -> new LegacyComponentSerializerImpl('&', '#', null, false, false, ComponentFlattener.basic()));
    }
}
