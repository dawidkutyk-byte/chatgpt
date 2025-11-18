/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$Pos
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson;

import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent;

final class BlockNBTComponentPosSerializer
extends TypeAdapter<BlockNBTComponent.Pos> {
    static final TypeAdapter<BlockNBTComponent.Pos> INSTANCE = new BlockNBTComponentPosSerializer().nullSafe();

    @Override
    public BlockNBTComponent.Pos read(JsonReader in) throws IOException {
        String string = in.nextString();
        try {
            return BlockNBTComponent.Pos.fromString((String)string);
        }
        catch (IllegalArgumentException ex) {
            throw new JsonParseException("Don't know how to turn " + string + " into a Position");
        }
    }

    private BlockNBTComponentPosSerializer() {
    }

    @Override
    public void write(JsonWriter out, BlockNBTComponent.Pos value) throws IOException {
        out.value(value.asString());
    }
}
