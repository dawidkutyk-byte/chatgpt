/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public final class JsonParser {
    public JsonElement parse(JsonReader json) throws JsonIOException, JsonSyntaxException {
        boolean lenient = json.isLenient();
        json.setLenient(true);
        try {
            JsonElement jsonElement = Streams.parse(json);
            return jsonElement;
        }
        catch (StackOverflowError e) {
            throw new JsonParseException("Failed parsing JSON source: " + json + " to Json", e);
        }
        catch (OutOfMemoryError e) {
            throw new JsonParseException("Failed parsing JSON source: " + json + " to Json", e);
        }
        finally {
            json.setLenient(lenient);
        }
    }

    public JsonElement parse(Reader json) throws JsonSyntaxException, JsonIOException {
        try {
            JsonReader jsonReader = new JsonReader(json);
            JsonElement element = this.parse(jsonReader);
            if (element.isJsonNull()) return element;
            if (jsonReader.peek() == JsonToken.END_DOCUMENT) return element;
            throw new JsonSyntaxException("Did not consume the entire document.");
        }
        catch (MalformedJsonException e) {
            throw new JsonSyntaxException(e);
        }
        catch (IOException e) {
            throw new JsonIOException(e);
        }
        catch (NumberFormatException e) {
            throw new JsonSyntaxException(e);
        }
    }

    public JsonElement parse(String json) throws JsonSyntaxException {
        return this.parse(new StringReader(json));
    }
}
