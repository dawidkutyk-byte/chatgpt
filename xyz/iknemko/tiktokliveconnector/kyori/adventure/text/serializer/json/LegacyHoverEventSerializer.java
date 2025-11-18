/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Codec$Decoder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Codec$Encoder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json;

import java.io.IOException;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Codec;

public interface LegacyHoverEventSerializer {
    @NotNull
    public Component serializeShowEntity(// Could not load outer class - annotation placement on inner may be incorrect
     @NotNull HoverEvent.ShowEntity var1, Codec.Encoder<Component, String, ? extends RuntimeException> var2) throws IOException;

    @NotNull
    public Component serializeShowItem(// Could not load outer class - annotation placement on inner may be incorrect
     @NotNull HoverEvent.ShowItem var1) throws IOException;

    public // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull HoverEvent.ShowItem deserializeShowItem(@NotNull Component var1) throws IOException;

    public // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull HoverEvent.ShowEntity deserializeShowEntity(@NotNull Component var1, Codec.Decoder<Component, String, ? extends RuntimeException> var2) throws IOException;
}
