/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.LegacyHoverEventSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.LegacyHoverEventSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.legacyimpl.NBTLegacyHoverEventSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Codec$Decoder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Codec$Encoder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.legacyimpl;

import java.io.IOException;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.LegacyHoverEventSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.legacyimpl.NBTLegacyHoverEventSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Codec;

@Deprecated
final class NBTLegacyHoverEventSerializerImpl
implements LegacyHoverEventSerializer {
    static final NBTLegacyHoverEventSerializerImpl INSTANCE = new NBTLegacyHoverEventSerializerImpl();
    static final xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.LegacyHoverEventSerializer NEW_INSTANCE = NBTLegacyHoverEventSerializer.get();

    @NotNull
    public Component serializeShowEntity(// Could not load outer class - annotation placement on inner may be incorrect
     @NotNull HoverEvent.ShowEntity input, Codec.Encoder<Component, String, ? extends RuntimeException> componentCodec) throws IOException {
        return NEW_INSTANCE.serializeShowEntity(input, componentCodec);
    }

    private NBTLegacyHoverEventSerializerImpl() {
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull HoverEvent.ShowItem deserializeShowItem(@NotNull Component input) throws IOException {
        return NEW_INSTANCE.deserializeShowItem(input);
    }

    @NotNull
    public Component serializeShowItem(// Could not load outer class - annotation placement on inner may be incorrect
     @NotNull HoverEvent.ShowItem input) throws IOException {
        return NEW_INSTANCE.serializeShowItem(input);
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull HoverEvent.ShowEntity deserializeShowEntity(@NotNull Component input, Codec.Decoder<Component, String, ? extends RuntimeException> componentCodec) throws IOException {
        return NEW_INSTANCE.deserializeShowEntity(input, componentCodec);
    }
}
