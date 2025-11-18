/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent$Action
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.IndexedSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Index
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson;

import com.google.gson.TypeAdapter;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.IndexedSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Index;

final class ClickEventActionSerializer {
    static final TypeAdapter<ClickEvent.Action> INSTANCE = IndexedSerializer.lenient((String)"click action", (Index)ClickEvent.Action.NAMES);

    private ClickEventActionSerializer() {
    }
}
