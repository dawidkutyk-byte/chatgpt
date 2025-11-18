/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;

final class KeySerializer
extends TypeAdapter<Key> {
    static final TypeAdapter<Key> INSTANCE = new KeySerializer().nullSafe();

    @Override
    public Key read(JsonReader in) throws IOException {
        return Key.key((String)in.nextString());
    }

    @Override
    public void write(JsonWriter out, Key value) throws IOException {
        out.value(value.asString());
    }

    private KeySerializer() {
    }
}
