/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONComponentSerializer
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONComponentSerializer;

final class DummyJSONComponentSerializer
implements JSONComponentSerializer {
    static final JSONComponentSerializer INSTANCE = new DummyJSONComponentSerializer();
    private static final String UNSUPPORTED_MESSAGE = "No JsonComponentSerializer implementation found\n\nAre you missing an implementation artifact like adventure-text-serializer-gson?\nIs your environment configured in a way that causes ServiceLoader to malfunction?";

    DummyJSONComponentSerializer() {
    }

    @NotNull
    public String serialize(@NotNull Component component) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @NotNull
    public Component deserialize(@NotNull String input) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }
}
