/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.chat.BaseComponent
 *  net.md_5.bungee.chat.ComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.ComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer$AdapterComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.bungeecord.GsonInjections
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.bungeecord.SelfSerializable$AdapterFactory
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.bungeecord;

import com.google.gson.Gson;
import com.google.gson.TypeAdapterFactory;
import java.lang.reflect.Field;
import java.util.Objects;
import net.md_5.bungee.api.chat.BaseComponent;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.ComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.bungeecord.GsonInjections;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.bungeecord.SelfSerializable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

/*
 * Exception performing whole class analysis ignored.
 */
public final class BungeeComponentSerializer
implements ComponentSerializer<Component, Component, BaseComponent[]> {
    private static boolean SUPPORTED = true;
    private static final BungeeComponentSerializer MODERN;
    private final GsonComponentSerializer serializer;
    private static final BungeeComponentSerializer PRE_1_16;
    private final LegacyComponentSerializer legacySerializer;

    @NotNull
    public @NotNull BaseComponent @NotNull [] serialize(@NotNull Component component) {
        Objects.requireNonNull(component, "component");
        if (!SUPPORTED) return net.md_5.bungee.chat.ComponentSerializer.parse((String)((String)this.serializer.serialize(component)));
        return new BaseComponent[]{new AdapterComponent(this, component)};
    }

    public static BungeeComponentSerializer of(GsonComponentSerializer serializer, LegacyComponentSerializer legacySerializer) {
        if (serializer == null) return null;
        if (legacySerializer != null) return new BungeeComponentSerializer(serializer, legacySerializer);
        return null;
    }

    public static boolean isNative() {
        return SUPPORTED;
    }

    public static BungeeComponentSerializer get() {
        return MODERN;
    }

    @NotNull
    public Component deserialize(@NotNull @NotNull BaseComponent @NotNull [] input) {
        Objects.requireNonNull(input, "input");
        if (input.length != 1) return this.serializer.deserialize((Object)net.md_5.bungee.chat.ComponentSerializer.toString((BaseComponent[])input));
        if (!(input[0] instanceof AdapterComponent)) return this.serializer.deserialize((Object)net.md_5.bungee.chat.ComponentSerializer.toString((BaseComponent[])input));
        return AdapterComponent.access$000((AdapterComponent)((AdapterComponent)input[0]));
    }

    public static BungeeComponentSerializer legacy() {
        return PRE_1_16;
    }

    private BungeeComponentSerializer(GsonComponentSerializer serializer, LegacyComponentSerializer legacySerializer) {
        this.serializer = serializer;
        this.legacySerializer = legacySerializer;
    }

    static {
        BungeeComponentSerializer.bind();
        MODERN = new BungeeComponentSerializer(GsonComponentSerializer.gson(), LegacyComponentSerializer.builder().hexColors().useUnusualXRepeatedCharacterHexFormat().build());
        PRE_1_16 = new BungeeComponentSerializer(GsonComponentSerializer.builder().downsampleColors().emitLegacyHoverEvent().build(), LegacyComponentSerializer.legacySection());
    }

    private static void bind() {
        try {
            Field gsonField = GsonInjections.field(net.md_5.bungee.chat.ComponentSerializer.class, (String)"gson");
            BungeeComponentSerializer.inject((Gson)gsonField.get(null));
        }
        catch (Throwable error) {
            SUPPORTED = false;
        }
    }

    static /* synthetic */ GsonComponentSerializer access$200(BungeeComponentSerializer x0) {
        return x0.serializer;
    }

    public static boolean inject(Gson existing) {
        boolean result = GsonInjections.injectGson((Gson)Objects.requireNonNull(existing, "existing"), builder -> {
            GsonComponentSerializer.gson().populator().apply(builder);
            builder.registerTypeAdapterFactory((TypeAdapterFactory)new SelfSerializable.AdapterFactory());
        });
        SUPPORTED &= result;
        return result;
    }

    static /* synthetic */ LegacyComponentSerializer access$100(BungeeComponentSerializer x0) {
        return x0.legacySerializer;
    }
}
