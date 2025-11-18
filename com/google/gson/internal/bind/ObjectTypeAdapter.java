/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.ToNumberStrategy;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;

public final class ObjectTypeAdapter
extends TypeAdapter<Object> {
    private final Gson gson;
    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory(){
        final /* synthetic */ ToNumberStrategy val$toNumberStrategy;
        {
            this.val$toNumberStrategy = toNumberStrategy;
        }

        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (type.getRawType() != Object.class) return null;
            return new ObjectTypeAdapter(gson, this.val$toNumberStrategy, null);
        }
    };

    @Override
    public Object read(JsonReader in) throws IOException {
        JsonToken token = in.peek();
        switch (token) {
            case BEGIN_ARRAY: {
                ArrayList<Object> list = new ArrayList<Object>();
                in.beginArray();
                while (true) {
                    if (!in.hasNext()) {
                        in.endArray();
                        return list;
                    }
                    list.add(this.read(in));
                }
            }
            case BEGIN_OBJECT: {
                LinkedTreeMap<String, Object> map = new LinkedTreeMap<String, Object>();
                in.beginObject();
                while (true) {
                    if (!in.hasNext()) {
                        in.endObject();
                        return map;
                    }
                    map.put(in.nextName(), this.read(in));
                }
            }
            case STRING: {
                return in.nextString();
            }
            case NUMBER: {
                return in.nextDouble();
            }
            case BOOLEAN: {
                return in.nextBoolean();
            }
            case NULL: {
                in.nextNull();
                return null;
            }
        }
        throw new IllegalStateException();
    }

    @Override
    public void write(JsonWriter out, Object value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        TypeAdapter<?> typeAdapter = this.gson.getAdapter(value.getClass());
        if (typeAdapter instanceof ObjectTypeAdapter) {
            out.beginObject();
            out.endObject();
            return;
        }
        typeAdapter.write(out, value);
    }

    ObjectTypeAdapter(Gson gson) {
        this.gson = gson;
    }
}
