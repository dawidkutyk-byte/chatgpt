/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValue$Removed
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonDataComponentValueImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonDataComponentValueImpl;

static final class GsonDataComponentValueImpl.RemovedGsonComponentValueImpl
extends GsonDataComponentValueImpl
implements DataComponentValue.Removed {
    static final GsonDataComponentValueImpl.RemovedGsonComponentValueImpl INSTANCE = new GsonDataComponentValueImpl.RemovedGsonComponentValueImpl();

    private GsonDataComponentValueImpl.RemovedGsonComponentValueImpl() {
        super((JsonElement)JsonNull.INSTANCE);
    }
}
