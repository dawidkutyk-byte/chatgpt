/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Index
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson;

import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Index;

final class IndexedSerializer<E>
extends TypeAdapter<E> {
    private final boolean throwOnUnknownKey;
    private final String name;
    private final Index<String, E> map;

    public static <E> TypeAdapter<E> strict(String name, Index<String, E> map) {
        return new IndexedSerializer<E>(name, map, true).nullSafe();
    }

    @Override
    public void write(JsonWriter out, E value) throws IOException {
        out.value((String)this.map.key(value));
    }

    private IndexedSerializer(String name, Index<String, E> map, boolean throwOnUnknownKey) {
        this.name = name;
        this.map = map;
        this.throwOnUnknownKey = throwOnUnknownKey;
    }

    public static <E> TypeAdapter<E> lenient(String name, Index<String, E> map) {
        return new IndexedSerializer<E>(name, map, false).nullSafe();
    }

    @Override
    public E read(JsonReader in) throws IOException {
        String string = in.nextString();
        Object value = this.map.value((Object)string);
        if (value != null) {
            return (E)value;
        }
        if (!this.throwOnUnknownKey) return null;
        throw new JsonParseException("invalid " + this.name + ":  " + string);
    }
}
