/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.LegacyHoverEventSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.legacyimpl.NBTLegacyHoverEventSerializerImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.legacyimpl.NBTLegacyHoverEventSerializer
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.legacyimpl;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.LegacyHoverEventSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.legacyimpl.NBTLegacyHoverEventSerializerImpl;

@Deprecated
@ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
public interface NBTLegacyHoverEventSerializer
extends xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.legacyimpl.NBTLegacyHoverEventSerializer,
LegacyHoverEventSerializer {
    @Deprecated
    @NotNull
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    public static LegacyHoverEventSerializer get() {
        return NBTLegacyHoverEventSerializerImpl.INSTANCE;
    }
}
