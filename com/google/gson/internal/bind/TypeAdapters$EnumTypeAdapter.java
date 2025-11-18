/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ib4o
 */
package com.google.gson.internal.bind;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

private static final class TypeAdapters.EnumTypeAdapter<T extends Enum<T>>
extends TypeAdapter<T> {
    private final Map<T, String> constantToName;
    private final Map<String, T> nameToConstant = new HashMap<String, T>();

    @Override
    public T read(JsonReader in) throws IOException {
        if (in.peek() != JsonToken.NULL) return (T)((Enum)this.nameToConstant.get(in.nextString()));
        in.nextNull();
        return null;
    }

    public TypeAdapters.EnumTypeAdapter(Class<T> classOfT) {
        this.constantToName = new HashMap<T, String>();
        try {
            Enum[] enumArray = (Enum[])classOfT.getEnumConstants();
            int n = enumArray.length;
            int n2 = 0;
            while (n2 < n) {
                Enum constant = enumArray[n2];
                String name = constant.name();
                ib4o annotation = classOfT.getField(name).getAnnotation(ib4o.class);
                if (annotation != null) {
                    name = annotation.value();
                    for (String alternate : annotation.alternate()) {
                        this.nameToConstant.put(alternate, constant);
                    }
                }
                this.nameToConstant.put(name, constant);
                this.constantToName.put(constant, name);
                ++n2;
            }
            return;
        }
        catch (NoSuchFieldException e) {
            throw new AssertionError((Object)e);
        }
    }

    @Override
    public void write(JsonWriter out, T value) throws IOException {
        out.value(value == null ? null : this.constantToName.get(value));
    }
}
