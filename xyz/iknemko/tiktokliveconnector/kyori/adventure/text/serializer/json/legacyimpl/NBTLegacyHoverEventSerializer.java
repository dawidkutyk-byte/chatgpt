/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.LegacyHoverEventSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.legacyimpl.NBTLegacyHoverEventSerializerImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.legacyimpl;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.LegacyHoverEventSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.legacyimpl.NBTLegacyHoverEventSerializerImpl;

public interface NBTLegacyHoverEventSerializer
extends LegacyHoverEventSerializer {
    @NotNull
    public static LegacyHoverEventSerializer get() {
        return NBTLegacyHoverEventSerializerImpl.INSTANCE;
    }
}
