/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.TagStringIO
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.api.BinaryTagHolder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent$ShowEntity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent$ShowItem
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.LegacyHoverEventSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Codec
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Codec$Decoder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Codec$Encoder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.legacyimpl;

import java.io.IOException;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.TagStringIO;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.api.BinaryTagHolder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.LegacyHoverEventSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Codec;

final class NBTLegacyHoverEventSerializerImpl
implements LegacyHoverEventSerializer {
    static final String ENTITY_TYPE = "type";
    static final String ITEM_TAG = "tag";
    private static final TagStringIO SNBT_IO;
    static final String ENTITY_ID = "id";
    static final String ITEM_TYPE = "id";
    private static final Codec<CompoundBinaryTag, String, IOException, IOException> SNBT_CODEC;
    static final NBTLegacyHoverEventSerializerImpl INSTANCE;
    static final String ITEM_COUNT = "Count";
    static final String ENTITY_NAME = "name";

    @NotNull
    public Component serializeShowItem(// Could not load outer class - annotation placement on inner may be incorrect
     @NotNull HoverEvent.ShowItem input) throws IOException {
        CompoundBinaryTag.Builder builder = (CompoundBinaryTag.Builder)((CompoundBinaryTag.Builder)CompoundBinaryTag.builder().putString("id", input.item().asString())).putByte(ITEM_COUNT, (byte)input.count());
        @Nullable BinaryTagHolder nbt = input.nbt();
        if (nbt == null) return Component.text((String)((String)SNBT_CODEC.encode((Object)builder.build())));
        builder.put(ITEM_TAG, (BinaryTag)nbt.get(SNBT_CODEC));
        return Component.text((String)((String)SNBT_CODEC.encode((Object)builder.build())));
    }

    @NotNull
    public Component serializeShowEntity(// Could not load outer class - annotation placement on inner may be incorrect
     @NotNull HoverEvent.ShowEntity input, Codec.Encoder<Component, String, ? extends RuntimeException> componentCodec) throws IOException {
        CompoundBinaryTag.Builder builder = (CompoundBinaryTag.Builder)((CompoundBinaryTag.Builder)CompoundBinaryTag.builder().putString("id", input.id().toString())).putString(ENTITY_TYPE, input.type().asString());
        @Nullable Component name = input.name();
        if (name == null) return Component.text((String)((String)SNBT_CODEC.encode((Object)builder.build())));
        builder.putString(ENTITY_NAME, (String)componentCodec.encode((Object)name));
        return Component.text((String)((String)SNBT_CODEC.encode((Object)builder.build())));
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull HoverEvent.ShowItem deserializeShowItem(@NotNull Component input) throws IOException {
        NBTLegacyHoverEventSerializerImpl.assertTextComponent(input);
        CompoundBinaryTag contents = (CompoundBinaryTag)SNBT_CODEC.decode((Object)((TextComponent)input).content());
        CompoundBinaryTag tag = contents.getCompound(ITEM_TAG);
        return HoverEvent.ShowItem.showItem((Key)Key.key((String)contents.getString("id")), (int)contents.getByte(ITEM_COUNT, (byte)1), tag == CompoundBinaryTag.empty() ? null : BinaryTagHolder.encode((Object)tag, SNBT_CODEC));
    }

    private NBTLegacyHoverEventSerializerImpl() {
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull HoverEvent.ShowEntity deserializeShowEntity(@NotNull Component input, Codec.Decoder<Component, String, ? extends RuntimeException> componentCodec) throws IOException {
        NBTLegacyHoverEventSerializerImpl.assertTextComponent(input);
        CompoundBinaryTag contents = (CompoundBinaryTag)SNBT_CODEC.decode((Object)((TextComponent)input).content());
        return HoverEvent.ShowEntity.showEntity((Key)Key.key((String)contents.getString(ENTITY_TYPE)), (UUID)UUID.fromString(contents.getString("id")), (Component)((Component)componentCodec.decode((Object)contents.getString(ENTITY_NAME))));
    }

    private static void assertTextComponent(Component component) {
        if (!(component instanceof TextComponent)) throw new IllegalArgumentException("Legacy events must be single Component instances");
        if (component.children().isEmpty()) return;
        throw new IllegalArgumentException("Legacy events must be single Component instances");
    }

    static {
        INSTANCE = new NBTLegacyHoverEventSerializerImpl();
        SNBT_IO = TagStringIO.get();
        SNBT_CODEC = Codec.codec(arg_0 -> ((TagStringIO)SNBT_IO).asCompound(arg_0), arg_0 -> ((TagStringIO)SNBT_IO).asString(arg_0));
    }
}
