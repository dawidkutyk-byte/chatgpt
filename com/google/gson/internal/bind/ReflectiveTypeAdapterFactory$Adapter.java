/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Map;

public static final class ReflectiveTypeAdapterFactory.Adapter<T>
extends TypeAdapter<T> {
    private final ObjectConstructor<T> constructor;
    private final Map<String, ReflectiveTypeAdapterFactory.BoundField> boundFields;

    ReflectiveTypeAdapterFactory.Adapter(ObjectConstructor<T> constructor, Map<String, ReflectiveTypeAdapterFactory.BoundField> boundFields) {
        this.constructor = constructor;
        this.boundFields = boundFields;
    }

    @Override
    public void write(JsonWriter out, T value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        out.beginObject();
        try {
            for (ReflectiveTypeAdapterFactory.BoundField boundField : this.boundFields.values()) {
                if (!boundField.writeField(value)) continue;
                out.name(boundField.name);
                boundField.write(out, value);
            }
        }
        catch (IllegalAccessException e) {
            throw new AssertionError((Object)e);
        }
        out.endObject();
    }

    @Override
    public T read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        T instance = this.constructor.construct();
        try {
            in.beginObject();
            while (in.hasNext()) {
                String name = in.nextName();
                ReflectiveTypeAdapterFactory.BoundField field = this.boundFields.get(name);
                if (field == null || !field.deserialized) {
                    in.skipValue();
                    continue;
                }
                field.read(in, instance);
            }
        }
        catch (IllegalStateException e) {
            throw new JsonSyntaxException(e);
        }
        catch (IllegalAccessException e) {
            throw new AssertionError((Object)e);
        }
        in.endObject();
        return instance;
    }
}
