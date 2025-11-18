/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$Pos
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgument
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent$Action
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent$Action
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent$ShowEntity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent$ShowItem
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.ShadowColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.BlockNBTComponentPosSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.ClickEventActionSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.ComponentSerializerImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.HoverEventActionSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.KeySerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.ShadowColorSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.ShowEntitySerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.ShowItemSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.StyleSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.TextColorSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.TextColorWrapper
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.TextColorWrapper$Serializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.TextDecorationSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.TranslationArgumentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.UUIDSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONOptions
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.LegacyHoverEventSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgument;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.ShadowColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.BlockNBTComponentPosSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.ClickEventActionSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.ComponentSerializerImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.HoverEventActionSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.KeySerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.ShadowColorSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.ShowEntitySerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.ShowItemSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.StyleSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.TextColorSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.TextColorWrapper;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.TextDecorationSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.TranslationArgumentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.UUIDSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONOptions;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.LegacyHoverEventSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionState;

final class SerializerFactory
implements TypeAdapterFactory {
    static final Class<BlockNBTComponent.Pos> BLOCK_NBT_POS_TYPE;
    static final Class<ClickEvent.Action> CLICK_ACTION_TYPE;
    static final Class<TranslationArgument> TRANSLATION_ARGUMENT_TYPE;
    static final Class<Style> STYLE_TYPE;
    static final Class<Key> KEY_TYPE;
    static final Class<HoverEvent.ShowItem> SHOW_ITEM_TYPE;
    static final Class<HoverEvent.ShowEntity> SHOW_ENTITY_TYPE;
    private final OptionState features;
    static final Class<TextColor> COLOR_TYPE;
    static final Class<HoverEvent.Action> HOVER_ACTION_TYPE;
    static final Class<TextColorWrapper> COLOR_WRAPPER_TYPE;
    static final Class<Component> COMPONENT_TYPE;
    static final Class<TextDecoration> TEXT_DECORATION_TYPE;
    static final Class<UUID> UUID_TYPE;
    static final Class<String> STRING_TYPE;
    static final Class<ShadowColor> SHADOW_COLOR_TYPE;
    private final LegacyHoverEventSerializer legacyHoverSerializer;

    SerializerFactory(OptionState features, @Nullable LegacyHoverEventSerializer legacyHoverSerializer) {
        this.features = features;
        this.legacyHoverSerializer = legacyHoverSerializer;
    }

    static {
        KEY_TYPE = Key.class;
        COMPONENT_TYPE = Component.class;
        STYLE_TYPE = Style.class;
        CLICK_ACTION_TYPE = ClickEvent.Action.class;
        HOVER_ACTION_TYPE = HoverEvent.Action.class;
        SHOW_ITEM_TYPE = HoverEvent.ShowItem.class;
        SHOW_ENTITY_TYPE = HoverEvent.ShowEntity.class;
        STRING_TYPE = String.class;
        COLOR_WRAPPER_TYPE = TextColorWrapper.class;
        COLOR_TYPE = TextColor.class;
        SHADOW_COLOR_TYPE = ShadowColor.class;
        TEXT_DECORATION_TYPE = TextDecoration.class;
        BLOCK_NBT_POS_TYPE = BlockNBTComponent.Pos.class;
        UUID_TYPE = UUID.class;
        TRANSLATION_ARGUMENT_TYPE = TranslationArgument.class;
    }

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<T> rawType = type.getRawType();
        if (COMPONENT_TYPE.isAssignableFrom(rawType)) {
            return ComponentSerializerImpl.create((OptionState)this.features, (Gson)gson);
        }
        if (KEY_TYPE.isAssignableFrom(rawType)) {
            return KeySerializer.INSTANCE;
        }
        if (STYLE_TYPE.isAssignableFrom(rawType)) {
            return StyleSerializer.create((LegacyHoverEventSerializer)this.legacyHoverSerializer, (OptionState)this.features, (Gson)gson);
        }
        if (CLICK_ACTION_TYPE.isAssignableFrom(rawType)) {
            return ClickEventActionSerializer.INSTANCE;
        }
        if (HOVER_ACTION_TYPE.isAssignableFrom(rawType)) {
            return HoverEventActionSerializer.INSTANCE;
        }
        if (SHOW_ITEM_TYPE.isAssignableFrom(rawType)) {
            return ShowItemSerializer.create((Gson)gson, (OptionState)this.features);
        }
        if (SHOW_ENTITY_TYPE.isAssignableFrom(rawType)) {
            return ShowEntitySerializer.create((Gson)gson, (OptionState)this.features);
        }
        if (COLOR_WRAPPER_TYPE.isAssignableFrom(rawType)) {
            return TextColorWrapper.Serializer.INSTANCE;
        }
        if (COLOR_TYPE.isAssignableFrom(rawType)) {
            return (Boolean)this.features.value(JSONOptions.EMIT_RGB) != false ? TextColorSerializer.INSTANCE : TextColorSerializer.DOWNSAMPLE_COLOR;
        }
        if (SHADOW_COLOR_TYPE.isAssignableFrom(rawType)) {
            return ShadowColorSerializer.create((OptionState)this.features);
        }
        if (TEXT_DECORATION_TYPE.isAssignableFrom(rawType)) {
            return TextDecorationSerializer.INSTANCE;
        }
        if (BLOCK_NBT_POS_TYPE.isAssignableFrom(rawType)) {
            return BlockNBTComponentPosSerializer.INSTANCE;
        }
        if (UUID_TYPE.isAssignableFrom(rawType)) {
            return UUIDSerializer.uuidSerializer((OptionState)this.features);
        }
        if (!TRANSLATION_ARGUMENT_TYPE.isAssignableFrom(rawType)) return null;
        return TranslationArgumentSerializer.create((Gson)gson);
    }
}
