/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.bind.TypeAdapterRuntimeTypeWrapper;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

private static final class CollectionTypeAdapterFactory.Adapter<E>
extends TypeAdapter<Collection<E>> {
    private final TypeAdapter<E> elementTypeAdapter;
    private final ObjectConstructor<? extends Collection<E>> constructor;

    @Override
    public Collection<E> read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        Collection<E> collection = this.constructor.construct();
        in.beginArray();
        while (true) {
            if (!in.hasNext()) {
                in.endArray();
                return collection;
            }
            E instance = this.elementTypeAdapter.read(in);
            collection.add(instance);
        }
    }

    @Override
    public void write(JsonWriter out, Collection<E> collection) throws IOException {
        if (collection == null) {
            out.nullValue();
            return;
        }
        out.beginArray();
        Iterator<E> iterator = collection.iterator();
        while (true) {
            if (!iterator.hasNext()) {
                out.endArray();
                return;
            }
            E element = iterator.next();
            this.elementTypeAdapter.write(out, element);
        }
    }

    public CollectionTypeAdapterFactory.Adapter(Gson context, Type elementType, TypeAdapter<E> elementTypeAdapter, ObjectConstructor<? extends Collection<E>> constructor) {
        this.elementTypeAdapter = new TypeAdapterRuntimeTypeWrapper<E>(context, elementTypeAdapter, elementType);
        this.constructor = constructor;
    }
}
