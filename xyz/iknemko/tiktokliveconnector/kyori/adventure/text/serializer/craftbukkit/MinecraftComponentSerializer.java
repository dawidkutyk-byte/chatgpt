/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.ComponentSerializer
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.craftbukkit;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.ComponentSerializer;

@Deprecated
public final class MinecraftComponentSerializer
implements ComponentSerializer<Component, Component, Object> {
    private static final MinecraftComponentSerializer INSTANCE = new MinecraftComponentSerializer();
    private final xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftComponentSerializer realSerial = xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftComponentSerializer.get();

    public static boolean isSupported() {
        return xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftComponentSerializer.isSupported();
    }

    @NotNull
    public static MinecraftComponentSerializer get() {
        return INSTANCE;
    }

    @NotNull
    public Object serialize(@NotNull Component component) {
        return this.realSerial.serialize(component);
    }

    @NotNull
    public Component deserialize(@NotNull Object input) {
        return this.realSerial.deserialize(input);
    }
}
