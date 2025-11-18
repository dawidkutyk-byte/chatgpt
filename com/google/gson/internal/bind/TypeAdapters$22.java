/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

static final class TypeAdapters.22
extends TypeAdapter<URI> {
    TypeAdapters.22() {
    }

    @Override
    public URI read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        try {
            String nextString = in.nextString();
            return "null".equals(nextString) ? null : new URI(nextString);
        }
        catch (URISyntaxException e) {
            throw new JsonIOException(e);
        }
    }

    @Override
    public void write(JsonWriter out, URI value) throws IOException {
        out.value(value == null ? null : value.toASCIIString());
    }
}
