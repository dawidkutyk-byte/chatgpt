/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.api.BinaryTagHolder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValue
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValue$Removed
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent$ShowItem
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonDataComponentValue
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.SerializerFactory
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONOptions
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONOptions$ShowItemHoverDataMode
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.api.BinaryTagHolder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonDataComponentValue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.SerializerFactory;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONOptions;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionState;

final class ShowItemSerializer
extends TypeAdapter<HoverEvent.ShowItem> {
    private final Gson gson;
    private final JSONOptions.ShowItemHoverDataMode itemDataMode;
    private static final String DATA_COMPONENT_REMOVAL_PREFIX = "!";
    private static final String LEGACY_SHOW_ITEM_TAG = "tag";
    private final boolean emitDefaultQuantity;

    @Override
    public HoverEvent.ShowItem read(JsonReader in) throws IOException {
        in.beginObject();
        Key key = null;
        int count = 1;
        BinaryTagHolder nbt = null;
        HashMap<Key, DataComponentValue.Removed> dataComponents = null;
        while (true) {
            block14: {
                if (in.hasNext()) {
                    String fieldName = in.nextName();
                    if (fieldName.equals("id")) {
                        key = (Key)this.gson.fromJson(in, (Type)SerializerFactory.KEY_TYPE);
                        continue;
                    }
                    if (fieldName.equals("count")) {
                        count = in.nextInt();
                        continue;
                    }
                    if (fieldName.equals(LEGACY_SHOW_ITEM_TAG)) {
                        JsonToken token = in.peek();
                        if (token == JsonToken.STRING || token == JsonToken.NUMBER) {
                            nbt = BinaryTagHolder.binaryTagHolder((String)in.nextString());
                            continue;
                        }
                        if (token == JsonToken.BOOLEAN) {
                            nbt = BinaryTagHolder.binaryTagHolder((String)String.valueOf(in.nextBoolean()));
                            continue;
                        }
                        if (token != JsonToken.NULL) throw new JsonParseException("Expected tag to be a string");
                        in.nextNull();
                        continue;
                    }
                    if (fieldName.equals("components")) {
                        in.beginObject();
                        break block14;
                    } else {
                        in.skipValue();
                        continue;
                    }
                }
                if (key == null) {
                    throw new JsonParseException("Not sure how to deserialize show_item hover event");
                }
                in.endObject();
                if (dataComponents == null) return HoverEvent.ShowItem.showItem(key, (int)count, nbt);
                return HoverEvent.ShowItem.showItem(key, (int)count, dataComponents);
            }
            while (in.peek() != JsonToken.END_OBJECT) {
                boolean removed;
                Key id;
                String name = in.nextName();
                if (name.startsWith(DATA_COMPONENT_REMOVAL_PREFIX)) {
                    id = Key.key((String)name.substring(1));
                    removed = true;
                } else {
                    id = Key.key((String)name);
                    removed = false;
                }
                JsonElement tree = (JsonElement)this.gson.fromJson(in, (Type)((Object)JsonElement.class));
                if (dataComponents == null) {
                    dataComponents = new HashMap<Key, DataComponentValue.Removed>();
                }
                dataComponents.put(id, (DataComponentValue.Removed)(removed ? DataComponentValue.removed() : GsonDataComponentValue.gsonDataComponentValue((JsonElement)tree)));
            }
            in.endObject();
        }
    }

    @Override
    public void write(JsonWriter out, HoverEvent.ShowItem value) throws IOException {
        Map dataComponents;
        out.beginObject();
        out.name("id");
        this.gson.toJson((Object)value.item(), (Type)SerializerFactory.KEY_TYPE, out);
        int count = value.count();
        if (count != 1 || this.emitDefaultQuantity) {
            out.name("count");
            out.value(count);
        }
        if ((dataComponents = value.dataComponents()).isEmpty() || this.itemDataMode == JSONOptions.ShowItemHoverDataMode.EMIT_LEGACY_NBT) {
            if (this.itemDataMode != JSONOptions.ShowItemHoverDataMode.EMIT_DATA_COMPONENTS) {
                ShowItemSerializer.maybeWriteLegacy(out, value);
            }
        } else {
            out.name("components");
            out.beginObject();
            for (Map.Entry entry : value.dataComponentsAs(GsonDataComponentValue.class).entrySet()) {
                JsonElement el = ((GsonDataComponentValue)entry.getValue()).element();
                if (el instanceof JsonNull) {
                    out.name(DATA_COMPONENT_REMOVAL_PREFIX + ((Key)entry.getKey()).asString());
                    out.beginObject().endObject();
                    continue;
                }
                out.name(((Key)entry.getKey()).asString());
                this.gson.toJson(el, out);
            }
            out.endObject();
        }
        out.endObject();
    }

    private static void maybeWriteLegacy(JsonWriter out, HoverEvent.ShowItem value) throws IOException {
        @Nullable BinaryTagHolder nbt = value.nbt();
        if (nbt == null) return;
        out.name(LEGACY_SHOW_ITEM_TAG);
        out.value(nbt.string());
    }

    private ShowItemSerializer(Gson gson, boolean emitDefaultQuantity, JSONOptions.ShowItemHoverDataMode itemDataMode) {
        this.gson = gson;
        this.emitDefaultQuantity = emitDefaultQuantity;
        this.itemDataMode = itemDataMode;
    }

    static TypeAdapter<HoverEvent.ShowItem> create(Gson gson, OptionState opt) {
        return new ShowItemSerializer(gson, (Boolean)opt.value(JSONOptions.EMIT_DEFAULT_ITEM_HOVER_QUANTITY), (JSONOptions.ShowItemHoverDataMode)opt.value(JSONOptions.SHOW_ITEM_HOVER_DATA_MODE)).nullSafe();
    }
}
