/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.stream;

import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.stream.JsonReader;
import java.io.IOException;

static final class JsonReader.1
extends JsonReaderInternalAccess {
    @Override
    public void promoteNameToValue(JsonReader reader) throws IOException {
        if (reader instanceof JsonTreeReader) {
            ((JsonTreeReader)reader).promoteNameToValue();
            return;
        }
        int p = reader.peeked;
        if (p == 0) {
            p = reader.doPeek();
        }
        if (p == 13) {
            reader.peeked = 9;
        } else if (p == 12) {
            reader.peeked = 8;
        } else {
            if (p != 14) throw new IllegalStateException("Expected a name but was " + (Object)((Object)reader.peek()) + JsonReader.access$000((JsonReader)reader));
            reader.peeked = 10;
        }
    }

    JsonReader.1() {
    }
}
