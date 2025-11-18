/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgument
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.SerializerFactory
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.TranslationArgumentSerializer$1
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgument;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.SerializerFactory;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.TranslationArgumentSerializer;

final class TranslationArgumentSerializer
extends TypeAdapter<TranslationArgument> {
    private final Gson gson;

    @Override
    public void write(JsonWriter out, TranslationArgument value) throws IOException {
        Object raw = value.value();
        if (raw instanceof Boolean) {
            out.value((Boolean)raw);
        } else if (raw instanceof Number) {
            out.value((Number)raw);
        } else {
            if (!(raw instanceof Component)) throw new IllegalStateException("Unable to serialize translatable argument of type " + raw.getClass() + ": " + raw);
            this.gson.toJson(raw, (Type)SerializerFactory.COMPONENT_TYPE, out);
        }
    }

    static TypeAdapter<TranslationArgument> create(Gson gson) {
        return new TranslationArgumentSerializer(gson).nullSafe();
    }

    @Override
    public TranslationArgument read(JsonReader in) throws IOException {
        switch (1.$SwitchMap$com$google$gson$stream$JsonToken[in.peek().ordinal()]) {
            case 1: {
                return TranslationArgument.bool((boolean)in.nextBoolean());
            }
            case 2: {
                return TranslationArgument.numeric((Number)((Number)this.gson.fromJson(in, (Type)((Object)Number.class))));
            }
        }
        return TranslationArgument.component((ComponentLike)((ComponentLike)this.gson.fromJson(in, (Type)SerializerFactory.COMPONENT_TYPE)));
    }

    private TranslationArgumentSerializer(Gson gson) {
        this.gson = gson;
    }
}
