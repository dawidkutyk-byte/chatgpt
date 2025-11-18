/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.bungeecord;

import com.google.gson.stream.JsonWriter;
import java.io.IOException;

interface SelfSerializable {
    public void write(JsonWriter var1) throws IOException;
}
