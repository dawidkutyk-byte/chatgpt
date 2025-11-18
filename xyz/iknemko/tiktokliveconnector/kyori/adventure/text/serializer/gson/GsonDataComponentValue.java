/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValue
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonDataComponentValueImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonDataComponentValueImpl$RemovedGsonComponentValueImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import java.util.Objects;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonDataComponentValueImpl;

@ApiStatus.NonExtendable
public interface GsonDataComponentValue
extends DataComponentValue {
    @NotNull
    public JsonElement element();

    public static GsonDataComponentValue gsonDataComponentValue(@NotNull JsonElement data) {
        if (!(data instanceof JsonNull)) return new GsonDataComponentValueImpl(Objects.requireNonNull(data, "data"));
        return GsonDataComponentValueImpl.RemovedGsonComponentValueImpl.INSTANCE;
    }
}
