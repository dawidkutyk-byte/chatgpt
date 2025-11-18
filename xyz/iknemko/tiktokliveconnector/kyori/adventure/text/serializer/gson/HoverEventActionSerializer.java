/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent$Action
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.IndexedSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Index
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson;

import com.google.gson.TypeAdapter;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.IndexedSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Index;

final class HoverEventActionSerializer {
    static final TypeAdapter<HoverEvent.Action<?>> INSTANCE = IndexedSerializer.lenient((String)"hover action", (Index)HoverEvent.Action.NAMES);

    private HoverEventActionSerializer() {
    }
}
