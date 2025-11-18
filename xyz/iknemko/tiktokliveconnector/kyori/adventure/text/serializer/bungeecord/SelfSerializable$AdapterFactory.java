/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.bungeecord.SelfSerializable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.bungeecord.SelfSerializable$AdapterFactory$SelfSerializableTypeAdapter
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.bungeecord;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.bungeecord.SelfSerializable;

public static class SelfSerializable.AdapterFactory
implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (SelfSerializable.class.isAssignableFrom(type.getRawType())) return new SelfSerializableTypeAdapter(type);
        return null;
    }

    static {
        SelfSerializableTypeAdapter.class.getName();
    }
}
