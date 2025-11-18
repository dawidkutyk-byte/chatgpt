/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.InvalidKeyException
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent$ShowEntity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.SerializerFactory
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONOptions
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.InvalidKeyException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.SerializerFactory;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONOptions;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionState;

final class ShowEntitySerializer
extends TypeAdapter<HoverEvent.ShowEntity> {
    private final Gson gson;
    private final boolean emitKeyAsTypeAndUuidAsId;

    private ShowEntitySerializer(Gson gson, boolean emitKeyAsTypeAndUuidAsId) {
        this.gson = gson;
        this.emitKeyAsTypeAndUuidAsId = emitKeyAsTypeAndUuidAsId;
    }

    @Override
    public void write(JsonWriter out, HoverEvent.ShowEntity value) throws IOException {
        out.beginObject();
        out.name(this.emitKeyAsTypeAndUuidAsId ? "type" : "id");
        this.gson.toJson((Object)value.type(), (Type)SerializerFactory.KEY_TYPE, out);
        out.name(this.emitKeyAsTypeAndUuidAsId ? "id" : "uuid");
        this.gson.toJson((Object)value.id(), (Type)SerializerFactory.UUID_TYPE, out);
        @Nullable Component name = value.name();
        if (name != null) {
            out.name("name");
            this.gson.toJson((Object)name, (Type)SerializerFactory.COMPONENT_TYPE, out);
        }
        out.endObject();
    }

    @Override
    public HoverEvent.ShowEntity read(JsonReader in) throws IOException {
        in.beginObject();
        Key type = null;
        UUID id = null;
        Component name = null;
        block16: while (in.hasNext()) {
            String fieldName;
            switch (fieldName = in.nextName()) {
                case "id": {
                    if (in.peek() == JsonToken.BEGIN_ARRAY) {
                        id = (UUID)this.gson.fromJson(in, (Type)((Object)UUID.class));
                        continue block16;
                    }
                    String string = in.nextString();
                    if (string.contains(":")) {
                        type = Key.key((String)string);
                    }
                    try {
                        id = UUID.fromString(string);
                    }
                    catch (IllegalArgumentException ignored) {
                        try {
                            type = Key.key((String)string);
                        }
                        catch (InvalidKeyException invalidKeyException) {}
                    }
                    continue block16;
                }
                case "type": {
                    type = (Key)this.gson.fromJson(in, (Type)((Object)Key.class));
                    continue block16;
                }
                case "uuid": {
                    id = (UUID)this.gson.fromJson(in, (Type)((Object)UUID.class));
                    continue block16;
                }
                case "name": {
                    name = (Component)this.gson.fromJson(in, (Type)SerializerFactory.COMPONENT_TYPE);
                    continue block16;
                }
            }
            in.skipValue();
        }
        if (type == null) throw new JsonParseException("A show entity hover event needs type and id fields to be deserialized");
        if (id == null) {
            throw new JsonParseException("A show entity hover event needs type and id fields to be deserialized");
        }
        in.endObject();
        return HoverEvent.ShowEntity.showEntity(type, id, name);
    }

    static TypeAdapter<HoverEvent.ShowEntity> create(Gson gson, OptionState opt) {
        return new ShowEntitySerializer(gson, (Boolean)opt.value(JSONOptions.EMIT_HOVER_SHOW_ENTITY_KEY_AS_TYPE_AND_UUID_AS_ID)).nullSafe();
    }
}
