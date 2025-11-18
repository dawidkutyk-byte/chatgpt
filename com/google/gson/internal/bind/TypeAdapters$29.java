/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.internal.bind.TypeAdapters$36
 */
package com.google.gson.internal.bind;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Map;

static final class TypeAdapters.29
extends TypeAdapter<JsonElement> {
    @Override
    public JsonElement read(JsonReader in) throws IOException {
        switch (TypeAdapters.36.$SwitchMap$com$google$gson$stream$JsonToken[in.peek().ordinal()]) {
            case 3: {
                return new JsonPrimitive(in.nextString());
            }
            case 1: {
                String number = in.nextString();
                return new JsonPrimitive(new LazilyParsedNumber(number));
            }
            case 2: {
                return new JsonPrimitive(in.nextBoolean());
            }
            case 4: {
                in.nextNull();
                return JsonNull.INSTANCE;
            }
            case 5: {
                JsonArray array = new JsonArray();
                in.beginArray();
                while (true) {
                    if (!in.hasNext()) {
                        in.endArray();
                        return array;
                    }
                    array.add(this.read(in));
                }
            }
            case 6: {
                JsonObject object = new JsonObject();
                in.beginObject();
                while (true) {
                    if (!in.hasNext()) {
                        in.endObject();
                        return object;
                    }
                    object.add(in.nextName(), this.read(in));
                }
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void write(JsonWriter out, JsonElement value) throws IOException {
        if (value == null || value.isJsonNull()) {
            out.nullValue();
        } else if (value.isJsonPrimitive()) {
            JsonPrimitive primitive = value.getAsJsonPrimitive();
            if (primitive.isNumber()) {
                out.value(primitive.getAsNumber());
            } else if (primitive.isBoolean()) {
                out.value(primitive.getAsBoolean());
            } else {
                out.value(primitive.getAsString());
            }
        } else if (value.isJsonArray()) {
            out.beginArray();
            for (JsonElement e : value.getAsJsonArray()) {
                this.write(out, e);
            }
            out.endArray();
        } else {
            if (!value.isJsonObject()) throw new IllegalArgumentException("Couldn't write " + value.getClass());
            out.beginObject();
            for (Map.Entry<String, JsonElement> e : value.getAsJsonObject().entrySet()) {
                out.name(e.getKey());
                this.write(out, e.getValue());
            }
            out.endObject();
        }
    }

    TypeAdapters.29() {
    }
}
