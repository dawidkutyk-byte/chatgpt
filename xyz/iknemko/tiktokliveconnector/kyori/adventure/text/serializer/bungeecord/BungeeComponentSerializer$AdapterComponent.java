/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.chat.BaseComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.bungeecord.SelfSerializable
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.bungeecord;

import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import net.md_5.bungee.api.chat.BaseComponent;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.bungeecord.SelfSerializable;

/*
 * Exception performing whole class analysis ignored.
 */
class BungeeComponentSerializer.AdapterComponent
extends BaseComponent
implements SelfSerializable {
    private final Component component;
    private volatile String legacy;

    static /* synthetic */ Component access$000(BungeeComponentSerializer.AdapterComponent x0) {
        return x0.component;
    }

    public void write(JsonWriter out) throws IOException {
        BungeeComponentSerializer.access$200((BungeeComponentSerializer)BungeeComponentSerializer.this).serializer().getAdapter(Component.class).write(out, this.component);
    }

    BungeeComponentSerializer.AdapterComponent(Component component) {
        this.component = component;
    }

    @NotNull
    public BaseComponent duplicate() {
        return this;
    }

    public String toLegacyText() {
        if (this.legacy != null) return this.legacy;
        this.legacy = BungeeComponentSerializer.access$100((BungeeComponentSerializer)BungeeComponentSerializer.this).serialize(this.component);
        return this.legacy;
    }
}
