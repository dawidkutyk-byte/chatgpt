/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import org.jetbrains.annotations.Nullable;

final class GsonHacks {
    static String readString(JsonReader in) throws IOException {
        JsonToken peek = in.peek();
        if (peek == JsonToken.STRING) return in.nextString();
        if (peek == JsonToken.NUMBER) {
            return in.nextString();
        }
        if (peek != JsonToken.BOOLEAN) throw new JsonParseException("Token of type " + (Object)((Object)peek) + " cannot be interpreted as a string");
        return String.valueOf(in.nextBoolean());
    }

    private GsonHacks() {
    }

    static boolean isNullOrEmpty(@Nullable JsonElement element) {
        return element == null || element.isJsonNull() || element.isJsonArray() && element.getAsJsonArray().size() == 0 || element.isJsonObject() && element.getAsJsonObject().entrySet().isEmpty();
    }

    static boolean readBoolean(JsonReader in) throws IOException {
        JsonToken peek = in.peek();
        if (peek == JsonToken.BOOLEAN) {
            return in.nextBoolean();
        }
        if (peek == JsonToken.STRING) {
            return Boolean.parseBoolean(in.nextString());
        }
        if (peek != JsonToken.NUMBER) throw new JsonParseException("Token of type " + (Object)((Object)peek) + " cannot be interpreted as a boolean");
        return in.nextString().equals("1");
    }
}
