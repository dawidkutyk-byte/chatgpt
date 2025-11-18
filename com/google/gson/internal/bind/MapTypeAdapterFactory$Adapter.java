/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Streams;
import com.google.gson.internal.bind.TypeAdapterRuntimeTypeWrapper;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

private final class MapTypeAdapterFactory.Adapter<K, V>
extends TypeAdapter<Map<K, V>> {
    private final TypeAdapter<V> valueTypeAdapter;
    private final ObjectConstructor<? extends Map<K, V>> constructor;
    private final TypeAdapter<K> keyTypeAdapter;

    public MapTypeAdapterFactory.Adapter(Gson context, Type keyType, TypeAdapter<K> keyTypeAdapter, Type valueType, TypeAdapter<V> valueTypeAdapter, ObjectConstructor<? extends Map<K, V>> constructor) {
        this.keyTypeAdapter = new TypeAdapterRuntimeTypeWrapper<K>(context, keyTypeAdapter, keyType);
        this.valueTypeAdapter = new TypeAdapterRuntimeTypeWrapper<V>(context, valueTypeAdapter, valueType);
        this.constructor = constructor;
    }

    private String keyToString(JsonElement keyElement) {
        if (!keyElement.isJsonPrimitive()) {
            if (!keyElement.isJsonNull()) throw new AssertionError();
            return "null";
        }
        JsonPrimitive primitive = keyElement.getAsJsonPrimitive();
        if (primitive.isNumber()) {
            return String.valueOf(primitive.getAsNumber());
        }
        if (primitive.isBoolean()) {
            return Boolean.toString(primitive.getAsBoolean());
        }
        if (!primitive.isString()) throw new AssertionError();
        return primitive.getAsString();
    }

    @Override
    public Map<K, V> read(JsonReader in) throws IOException {
        JsonToken peek = in.peek();
        if (peek == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        Map<K, V> map = this.constructor.construct();
        if (peek != JsonToken.BEGIN_ARRAY) {
            in.beginObject();
            while (in.hasNext()) {
                V value;
                JsonReaderInternalAccess.INSTANCE.promoteNameToValue(in);
                K key = this.keyTypeAdapter.read(in);
                V replaced = map.put(key, value = this.valueTypeAdapter.read(in));
                if (replaced == null) continue;
                throw new JsonSyntaxException("duplicate key: " + key);
            }
            in.endObject();
        } else {
            in.beginArray();
            while (in.hasNext()) {
                in.beginArray();
                K key = this.keyTypeAdapter.read(in);
                V value = this.valueTypeAdapter.read(in);
                V replaced = map.put(key, value);
                if (replaced != null) {
                    throw new JsonSyntaxException("duplicate key: " + key);
                }
                in.endArray();
            }
            in.endArray();
        }
        return map;
    }

    @Override
    public void write(JsonWriter out, Map<K, V> map) throws IOException {
        if (map == null) {
            out.nullValue();
            return;
        }
        if (!MapTypeAdapterFactory.this.complexMapKeySerialization) {
            out.beginObject();
            Iterator<Map.Entry<K, V>> iterator = map.entrySet().iterator();
            while (true) {
                if (!iterator.hasNext()) {
                    out.endObject();
                    return;
                }
                Map.Entry<K, V> entry = iterator.next();
                out.name(String.valueOf(entry.getKey()));
                this.valueTypeAdapter.write(out, entry.getValue());
            }
        }
        boolean hasComplexKeys = false;
        ArrayList<JsonElement> keys = new ArrayList<JsonElement>(map.size());
        ArrayList<V> values = new ArrayList<V>(map.size());
        for (Map.Entry<K, V> entry : map.entrySet()) {
            JsonElement keyElement = this.keyTypeAdapter.toJsonTree(entry.getKey());
            keys.add(keyElement);
            values.add(entry.getValue());
            hasComplexKeys |= keyElement.isJsonArray() || keyElement.isJsonObject();
        }
        if (hasComplexKeys) {
            out.beginArray();
            for (int i = 0; i < keys.size(); ++i) {
                out.beginArray();
                Streams.write((JsonElement)keys.get(i), out);
                this.valueTypeAdapter.write(out, values.get(i));
                out.endArray();
            }
            out.endArray();
        } else {
            out.beginObject();
            for (int i = 0; i < keys.size(); ++i) {
                JsonElement keyElement = (JsonElement)keys.get(i);
                out.name(this.keyToString(keyElement));
                this.valueTypeAdapter.write(out, values.get(i));
            }
            out.endObject();
        }
    }
}
