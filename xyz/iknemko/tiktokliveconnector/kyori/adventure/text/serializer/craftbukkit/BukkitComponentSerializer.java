/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.craftbukkit;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

@Deprecated
public final class BukkitComponentSerializer {
    @NotNull
    public static LegacyComponentSerializer legacy() {
        return xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitComponentSerializer.legacy();
    }

    private BukkitComponentSerializer() {
    }

    @NotNull
    public static GsonComponentSerializer gson() {
        return xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitComponentSerializer.gson();
    }
}
