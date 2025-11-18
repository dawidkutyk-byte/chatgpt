/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.internal.bind.TypeAdapterRuntimeTypeWrapper;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;

public final class ArrayTypeAdapter<E>
extends TypeAdapter<Object> {
    private final TypeAdapter<E> componentTypeAdapter;
    private final Class<E> componentType;
    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory(){

        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            Type type = typeToken.getType();
            if (!(type instanceof GenericArrayType)) {
                if (!(type instanceof Class)) return null;
                if (!((Class)type).isArray()) {
                    return null;
                }
            }
            Type componentType = $Gson$Types.getArrayComponentType(type);
            TypeAdapter<?> componentTypeAdapter = gson.getAdapter(TypeToken.get(componentType));
            ArrayTypeAdapter arrayAdapter = new ArrayTypeAdapter(gson, componentTypeAdapter, $Gson$Types.getRawType(componentType));
            return arrayAdapter;
        }
    };

    @Override
    public Object read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        ArrayList<E> list = new ArrayList<E>();
        in.beginArray();
        while (in.hasNext()) {
            E instance = this.componentTypeAdapter.read(in);
            list.add(instance);
        }
        in.endArray();
        Object array = Array.newInstance(this.componentType, list.size());
        int i = 0;
        while (i < list.size()) {
            Array.set(array, i, list.get(i));
            ++i;
        }
        return array;
    }

    @Override
    public void write(JsonWriter out, Object array) throws IOException {
        if (array == null) {
            out.nullValue();
            return;
        }
        out.beginArray();
        int i = 0;
        int length = Array.getLength(array);
        while (true) {
            if (i >= length) {
                out.endArray();
                return;
            }
            Object value = Array.get(array, i);
            this.componentTypeAdapter.write(out, value);
            ++i;
        }
    }

    public ArrayTypeAdapter(Gson context, TypeAdapter<E> componentTypeAdapter, Class<E> componentType) {
        this.componentTypeAdapter = new TypeAdapterRuntimeTypeWrapper<E>(context, componentTypeAdapter, componentType);
        this.componentType = componentType;
    }
}
