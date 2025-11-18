/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent$Action
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent$Payload
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent$Payload$Custom
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent$Payload$Int
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent$Payload$Text
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent$Action
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent$ShowEntity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent$ShowItem
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEventSource
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.ShadowColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration$State
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.ComponentSerializerImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonHacks
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.SerializerFactory
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.StyleSerializer$1
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.TextColorWrapper
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONOptions
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONOptions$ClickEventValueMode
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONOptions$HoverEventValueMode
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONOptions$ShadowColorEmitMode
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.LegacyHoverEventSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.ARGBLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Codec$Decoder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Codec$Encoder
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.EnumSet;
import java.util.Map;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEventSource;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.ShadowColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.ComponentSerializerImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonHacks;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.SerializerFactory;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.StyleSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.TextColorWrapper;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONOptions;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.LegacyHoverEventSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.ARGBLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Codec;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionState;

final class StyleSerializer
extends TypeAdapter<Style> {
    private final boolean emitShadowColor;
    private final boolean emitValueFieldHover;
    private final boolean strictEventValues;
    private final LegacyHoverEventSerializer legacyHover;
    private final boolean emitSnakeCaseClick;
    private final boolean emitSnakeCaseHover;
    private static final TextDecoration[] DECORATIONS = new TextDecoration[]{TextDecoration.BOLD, TextDecoration.ITALIC, TextDecoration.UNDERLINED, TextDecoration.STRIKETHROUGH, TextDecoration.OBFUSCATED};
    private final Gson gson;
    private final boolean emitCamelCaseClick;
    private final boolean emitStringPage;
    private final boolean emitCamelCaseHover;

    private void serializeLegacyHoverEvent(HoverEvent<?> hoverEvent, JsonWriter out) throws IOException {
        if (hoverEvent.action() == HoverEvent.Action.SHOW_TEXT) {
            this.gson.toJson(hoverEvent.value(), (Type)SerializerFactory.COMPONENT_TYPE, out);
        } else if (hoverEvent.action() == HoverEvent.Action.SHOW_ACHIEVEMENT) {
            this.gson.toJson(hoverEvent.value(), (Type)((Object)String.class), out);
        } else if (this.legacyHover != null) {
            Component serialized = null;
            try {
                if (hoverEvent.action() == HoverEvent.Action.SHOW_ENTITY) {
                    serialized = this.legacyHover.serializeShowEntity((HoverEvent.ShowEntity)hoverEvent.value(), this.encoder());
                } else if (hoverEvent.action() == HoverEvent.Action.SHOW_ITEM) {
                    serialized = this.legacyHover.serializeShowItem((HoverEvent.ShowItem)hoverEvent.value());
                }
            }
            catch (IOException ex) {
                throw new JsonSyntaxException(ex);
            }
            if (serialized != null) {
                this.gson.toJson((Object)serialized, (Type)SerializerFactory.COMPONENT_TYPE, out);
            } else {
                out.nullValue();
            }
        } else {
            out.nullValue();
        }
    }

    @Override
    public Style read(JsonReader in) throws IOException {
        in.beginObject();
        Style.Builder style = Style.style();
        while (true) {
            Integer page;
            Key key;
            String value;
            ClickEvent.Action action;
            if (!in.hasNext()) {
                in.endObject();
                return style.build();
            }
            String fieldName = in.nextName();
            if (fieldName.equals("font")) {
                style.font((Key)this.gson.fromJson(in, (Type)SerializerFactory.KEY_TYPE));
                continue;
            }
            if (fieldName.equals("color")) {
                TextColorWrapper color = (TextColorWrapper)this.gson.fromJson(in, (Type)SerializerFactory.COLOR_WRAPPER_TYPE);
                if (color.color != null) {
                    style.color(color.color);
                    continue;
                }
                if (color.decoration == null) continue;
                style.decoration(color.decoration, TextDecoration.State.TRUE);
                continue;
            }
            if (fieldName.equals("shadow_color")) {
                style.shadowColor((ARGBLike)this.gson.fromJson(in, (Type)SerializerFactory.SHADOW_COLOR_TYPE));
                continue;
            }
            if (TextDecoration.NAMES.keys().contains(fieldName)) {
                style.decoration((TextDecoration)TextDecoration.NAMES.value((Object)fieldName), GsonHacks.readBoolean((JsonReader)in));
                continue;
            }
            if (fieldName.equals("insertion")) {
                style.insertion(in.nextString());
                continue;
            }
            if (fieldName.equals("click_event") || fieldName.equals("clickEvent")) {
                in.beginObject();
                action = null;
                value = null;
                key = null;
                page = null;
            } else {
                if (fieldName.equals("hover_event") || fieldName.equals("hoverEvent")) {
                    Object value2;
                    HoverEvent.Action action2;
                    JsonPrimitive serializedAction;
                    JsonObject hoverEventObject = (JsonObject)this.gson.fromJson(in, (Type)((Object)JsonObject.class));
                    if (hoverEventObject == null || (serializedAction = hoverEventObject.getAsJsonPrimitive("action")) == null || !(action2 = (HoverEvent.Action)this.gson.fromJson((JsonElement)serializedAction, SerializerFactory.HOVER_ACTION_TYPE)).readable()) continue;
                    Class actionType = action2.type();
                    if (hoverEventObject.has("contents")) {
                        @Nullable JsonElement rawValue = hoverEventObject.get("contents");
                        if (GsonHacks.isNullOrEmpty((JsonElement)rawValue)) {
                            if (this.strictEventValues) {
                                throw ComponentSerializerImpl.notSureHowToDeserialize((Object)rawValue);
                            }
                            value2 = null;
                        } else {
                            value2 = SerializerFactory.COMPONENT_TYPE.isAssignableFrom(actionType) ? (Object)this.gson.fromJson(rawValue, SerializerFactory.COMPONENT_TYPE) : (SerializerFactory.SHOW_ITEM_TYPE.isAssignableFrom(actionType) ? (Object)this.gson.fromJson(rawValue, SerializerFactory.SHOW_ITEM_TYPE) : (SerializerFactory.SHOW_ENTITY_TYPE.isAssignableFrom(actionType) ? (Object)this.gson.fromJson(rawValue, SerializerFactory.SHOW_ENTITY_TYPE) : null));
                        }
                    } else if (hoverEventObject.has("value")) {
                        JsonElement element = hoverEventObject.get("value");
                        if (GsonHacks.isNullOrEmpty((JsonElement)element)) {
                            if (this.strictEventValues) {
                                throw ComponentSerializerImpl.notSureHowToDeserialize((Object)element);
                            }
                            value2 = null;
                        } else if (SerializerFactory.COMPONENT_TYPE.isAssignableFrom(actionType)) {
                            Component rawValue = (Component)this.gson.fromJson(element, SerializerFactory.COMPONENT_TYPE);
                            value2 = this.legacyHoverEventContents(action2, rawValue);
                        } else {
                            value2 = SerializerFactory.STRING_TYPE.isAssignableFrom(actionType) ? (Object)this.gson.fromJson(element, SerializerFactory.STRING_TYPE) : null;
                        }
                    } else if (SerializerFactory.SHOW_ITEM_TYPE.isAssignableFrom(actionType)) {
                        value2 = this.gson.fromJson((JsonElement)hoverEventObject, SerializerFactory.SHOW_ITEM_TYPE);
                    } else if (SerializerFactory.SHOW_ENTITY_TYPE.isAssignableFrom(actionType)) {
                        value2 = this.gson.fromJson((JsonElement)hoverEventObject, SerializerFactory.SHOW_ENTITY_TYPE);
                    } else {
                        if (this.strictEventValues) {
                            throw ComponentSerializerImpl.notSureHowToDeserialize((Object)hoverEventObject);
                        }
                        value2 = null;
                    }
                    if (value2 == null) continue;
                    style.hoverEvent((HoverEventSource)HoverEvent.hoverEvent((HoverEvent.Action)action2, value2));
                    continue;
                }
                in.skipValue();
                continue;
            }
            while (in.hasNext()) {
                String clickEventField = in.nextName();
                if (clickEventField.equals("action")) {
                    action = (ClickEvent.Action)this.gson.fromJson(in, (Type)SerializerFactory.CLICK_ACTION_TYPE);
                    continue;
                }
                if (clickEventField.equals("page")) {
                    if (in.peek() == JsonToken.NUMBER) {
                        page = in.nextInt();
                        continue;
                    }
                    if (in.peek() == JsonToken.STRING) {
                        page = Integer.parseInt(in.nextString());
                        continue;
                    }
                    if (in.peek() == JsonToken.NULL) {
                        throw ComponentSerializerImpl.notSureHowToDeserialize((Object)clickEventField);
                    }
                    in.skipValue();
                    continue;
                }
                if (clickEventField.equals("value") || clickEventField.equals("url") || clickEventField.equals("path") || clickEventField.equals("command") || clickEventField.equals("payload")) {
                    if (in.peek() == JsonToken.NULL) {
                        if (this.strictEventValues) {
                            throw ComponentSerializerImpl.notSureHowToDeserialize((Object)clickEventField);
                        }
                        in.nextNull();
                        continue;
                    }
                    value = in.nextString();
                    continue;
                }
                if (clickEventField.equals("id")) {
                    key = Key.key((String)in.nextString());
                    continue;
                }
                in.skipValue();
            }
            if (action != null && action.readable()) {
                switch (1.$SwitchMap$net$kyori$adventure$text$event$ClickEvent$Action[action.ordinal()]) {
                    case 1: {
                        if (value == null) break;
                        style.clickEvent(ClickEvent.openUrl(value));
                        break;
                    }
                    case 2: {
                        if (value == null) break;
                        style.clickEvent(ClickEvent.runCommand(value));
                        break;
                    }
                    case 3: {
                        if (value == null) break;
                        style.clickEvent(ClickEvent.suggestCommand(value));
                        break;
                    }
                    case 4: {
                        if (page == null) break;
                        style.clickEvent(ClickEvent.changePage((int)page));
                        break;
                    }
                    case 5: {
                        if (value == null) break;
                        style.clickEvent(ClickEvent.copyToClipboard(value));
                        break;
                    }
                    case 6: {
                        if (key == null || value == null) break;
                        style.clickEvent(ClickEvent.custom(key, value));
                        break;
                    }
                }
            }
            in.endObject();
        }
    }

    static {
        EnumSet<TextDecoration> knownDecorations = EnumSet.allOf(TextDecoration.class);
        TextDecoration[] textDecorationArray = DECORATIONS;
        int n = textDecorationArray.length;
        int n2 = 0;
        while (true) {
            if (n2 >= n) {
                if (knownDecorations.isEmpty()) return;
                throw new IllegalStateException("Gson serializer is missing some text decorations: " + knownDecorations);
            }
            TextDecoration decoration = textDecorationArray[n2];
            knownDecorations.remove(decoration);
            ++n2;
        }
    }

    private Object legacyHoverEventContents(HoverEvent.Action<?> action, Component rawValue) {
        if (action == HoverEvent.Action.SHOW_TEXT) {
            return rawValue;
        }
        if (this.legacyHover == null) throw new UnsupportedOperationException();
        try {
            if (action == HoverEvent.Action.SHOW_ENTITY) {
                return this.legacyHover.deserializeShowEntity(rawValue, this.decoder());
            }
            if (action != HoverEvent.Action.SHOW_ITEM) throw new UnsupportedOperationException();
            return this.legacyHover.deserializeShowItem(rawValue);
        }
        catch (IOException ex) {
            throw new JsonParseException(ex);
        }
    }

    private Codec.Encoder<Component, String, JsonParseException> encoder() {
        return component -> this.gson.toJson(component, SerializerFactory.COMPONENT_TYPE);
    }

    static TypeAdapter<Style> create(@Nullable LegacyHoverEventSerializer legacyHover, OptionState features, Gson gson) {
        JSONOptions.HoverEventValueMode hoverMode = (JSONOptions.HoverEventValueMode)features.value(JSONOptions.EMIT_HOVER_EVENT_TYPE);
        JSONOptions.ClickEventValueMode clickMode = (JSONOptions.ClickEventValueMode)features.value(JSONOptions.EMIT_CLICK_EVENT_TYPE);
        return new StyleSerializer(legacyHover, hoverMode == JSONOptions.HoverEventValueMode.VALUE_FIELD || hoverMode == JSONOptions.HoverEventValueMode.ALL, hoverMode == JSONOptions.HoverEventValueMode.CAMEL_CASE || hoverMode == JSONOptions.HoverEventValueMode.ALL, hoverMode == JSONOptions.HoverEventValueMode.SNAKE_CASE || hoverMode == JSONOptions.HoverEventValueMode.ALL, clickMode == JSONOptions.ClickEventValueMode.CAMEL_CASE || clickMode == JSONOptions.ClickEventValueMode.BOTH, clickMode == JSONOptions.ClickEventValueMode.SNAKE_CASE || clickMode == JSONOptions.ClickEventValueMode.BOTH, (Boolean)features.value(JSONOptions.VALIDATE_STRICT_EVENTS), features.value(JSONOptions.SHADOW_COLOR_MODE) != JSONOptions.ShadowColorEmitMode.NONE, (Boolean)features.value(JSONOptions.EMIT_CHANGE_PAGE_CLICK_EVENT_PAGE_AS_STRING), gson).nullSafe();
    }

    @Override
    public void write(JsonWriter out, Style value) throws IOException {
        Key font;
        HoverEvent hoverEvent;
        ClickEvent clickEvent;
        String insertion;
        ShadowColor shadowColor;
        out.beginObject();
        for (TextDecoration decoration : DECORATIONS) {
            TextDecoration.State state = value.decoration(decoration);
            if (state == TextDecoration.State.NOT_SET) continue;
            String name = (String)TextDecoration.NAMES.key((Object)decoration);
            assert (name != null);
            out.name(name);
            out.value(state == TextDecoration.State.TRUE);
        }
        @Nullable TextColor color = value.color();
        if (color != null) {
            out.name("color");
            this.gson.toJson((Object)color, (Type)SerializerFactory.COLOR_TYPE, out);
        }
        if ((shadowColor = value.shadowColor()) != null && this.emitShadowColor) {
            out.name("shadow_color");
            this.gson.toJson((Object)shadowColor, (Type)SerializerFactory.SHADOW_COLOR_TYPE, out);
        }
        if ((insertion = value.insertion()) != null) {
            out.name("insertion");
            out.value(insertion);
        }
        if ((clickEvent = value.clickEvent()) != null) {
            ClickEvent.Action action = clickEvent.action();
            if (this.emitSnakeCaseClick) {
                out.name("click_event");
                out.beginObject();
                out.name("action");
                this.gson.toJson((Object)action, (Type)SerializerFactory.CLICK_ACTION_TYPE, out);
                if (action.readable()) {
                    ClickEvent.Payload payload = clickEvent.payload();
                    if (payload instanceof ClickEvent.Payload.Text) {
                        switch (1.$SwitchMap$net$kyori$adventure$text$event$ClickEvent$Action[action.ordinal()]) {
                            case 1: {
                                out.name("url");
                                break;
                            }
                            case 2: 
                            case 3: {
                                out.name("command");
                                break;
                            }
                            case 5: {
                                out.name("value");
                                break;
                            }
                        }
                        out.value(((ClickEvent.Payload.Text)payload).value());
                    } else if (payload instanceof ClickEvent.Payload.Custom) {
                        ClickEvent.Payload.Custom customPayload = (ClickEvent.Payload.Custom)payload;
                        out.name("id");
                        this.gson.toJson((Object)customPayload.key(), (Type)SerializerFactory.KEY_TYPE, out);
                        out.name("payload");
                        out.value(customPayload.data());
                    } else if (payload instanceof ClickEvent.Payload.Int) {
                        ClickEvent.Payload.Int intPayload = (ClickEvent.Payload.Int)payload;
                        out.name("page");
                        if (this.emitStringPage) {
                            out.value(String.valueOf(intPayload.integer()));
                        } else {
                            out.value(intPayload.integer());
                        }
                    }
                }
                out.endObject();
            }
            if (this.emitCamelCaseClick && action.payloadType() == ClickEvent.Payload.Text.class) {
                out.name("clickEvent");
                out.beginObject();
                out.name("action");
                this.gson.toJson((Object)action, (Type)SerializerFactory.CLICK_ACTION_TYPE, out);
                out.name("value");
                out.value(clickEvent.value());
                out.endObject();
            }
        }
        if ((hoverEvent = value.hoverEvent()) != null && ((this.emitSnakeCaseHover || this.emitCamelCaseHover) && hoverEvent.action() != HoverEvent.Action.SHOW_ACHIEVEMENT || this.emitValueFieldHover)) {
            HoverEvent.Action action = hoverEvent.action();
            if (this.emitSnakeCaseHover && action != HoverEvent.Action.SHOW_ACHIEVEMENT) {
                out.name("hover_event");
                out.beginObject();
                out.name("action");
                this.gson.toJson((Object)action, (Type)SerializerFactory.HOVER_ACTION_TYPE, out);
                if (action == HoverEvent.Action.SHOW_ITEM) {
                    for (Map.Entry<String, JsonElement> entry : this.gson.toJsonTree(hoverEvent.value(), SerializerFactory.SHOW_ITEM_TYPE).getAsJsonObject().entrySet()) {
                        out.name(entry.getKey());
                        this.gson.toJson(entry.getValue(), out);
                    }
                } else if (action == HoverEvent.Action.SHOW_ENTITY) {
                    for (Map.Entry<String, JsonElement> entry : this.gson.toJsonTree(hoverEvent.value(), SerializerFactory.SHOW_ENTITY_TYPE).getAsJsonObject().entrySet()) {
                        out.name(entry.getKey());
                        this.gson.toJson(entry.getValue(), out);
                    }
                } else {
                    if (action != HoverEvent.Action.SHOW_TEXT) throw new JsonParseException("Don't know how to serialize " + hoverEvent.value());
                    out.name("value");
                    this.gson.toJson(hoverEvent.value(), (Type)SerializerFactory.COMPONENT_TYPE, out);
                }
                out.endObject();
            }
            if (this.emitCamelCaseHover || this.emitValueFieldHover) {
                out.name("hoverEvent");
                out.beginObject();
                out.name("action");
                this.gson.toJson((Object)action, (Type)SerializerFactory.HOVER_ACTION_TYPE, out);
                if (this.emitCamelCaseHover && action != HoverEvent.Action.SHOW_ACHIEVEMENT) {
                    out.name("contents");
                    if (action == HoverEvent.Action.SHOW_ITEM) {
                        this.gson.toJson(hoverEvent.value(), (Type)SerializerFactory.SHOW_ITEM_TYPE, out);
                    } else if (action == HoverEvent.Action.SHOW_ENTITY) {
                        this.gson.toJson(hoverEvent.value(), (Type)SerializerFactory.SHOW_ENTITY_TYPE, out);
                    } else {
                        if (action != HoverEvent.Action.SHOW_TEXT) throw new JsonParseException("Don't know how to serialize " + hoverEvent.value());
                        this.gson.toJson(hoverEvent.value(), (Type)SerializerFactory.COMPONENT_TYPE, out);
                    }
                }
                if (this.emitValueFieldHover) {
                    out.name("value");
                    this.serializeLegacyHoverEvent(hoverEvent, out);
                }
                out.endObject();
            }
        }
        if ((font = value.font()) != null) {
            out.name("font");
            this.gson.toJson((Object)font, (Type)SerializerFactory.KEY_TYPE, out);
        }
        out.endObject();
    }

    private StyleSerializer(@Nullable LegacyHoverEventSerializer legacyHover, boolean emitValueFieldHover, boolean emitCamelCaseHover, boolean emitSnakeCaseHover, boolean emitCamelCaseClick, boolean emitSnakeCaseClick, boolean strictEventValues, boolean emitShadowColor, boolean emitStringPage, Gson gson) {
        this.legacyHover = legacyHover;
        this.emitValueFieldHover = emitValueFieldHover;
        this.emitCamelCaseHover = emitCamelCaseHover;
        this.emitSnakeCaseHover = emitSnakeCaseHover;
        this.emitCamelCaseClick = emitCamelCaseClick;
        this.emitSnakeCaseClick = emitSnakeCaseClick;
        this.strictEventValues = strictEventValues;
        this.emitShadowColor = emitShadowColor;
        this.emitStringPage = emitStringPage;
        this.gson = gson;
    }

    private Codec.Decoder<Component, String, JsonParseException> decoder() {
        return string -> (Component)this.gson.fromJson((String)string, SerializerFactory.COMPONENT_TYPE);
    }
}
