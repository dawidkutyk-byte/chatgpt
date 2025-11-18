/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.bungeecord.SelfSerializable
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.bungeecord;

import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.bungeecord.SelfSerializable;

static class SelfSerializable.AdapterFactory.SelfSerializableTypeAdapter<T>
extends TypeAdapter<T> {
    private final TypeToken<T> type;

    @Override
    public T read(JsonReader in) {
        throw new UnsupportedOperationException("Cannot load values of type " + this.type.getType().getTypeName());
    }

    SelfSerializable.AdapterFactory.SelfSerializableTypeAdapter(TypeToken<T> type) {
        this.type = type;
    }

    @Override
    public void write(JsonWriter out, T value) throws IOException {
        ((SelfSerializable)value).write(out);
    }
}
