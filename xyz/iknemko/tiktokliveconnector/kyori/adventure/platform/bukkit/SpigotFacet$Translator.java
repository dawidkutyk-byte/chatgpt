/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.chat.TranslationRegistry
 *  org.bukkit.Server
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetBase
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetComponentFlattener$Translator
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import net.md_5.bungee.chat.TranslationRegistry;
import org.bukkit.Server;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetBase;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetComponentFlattener;

static class SpigotFacet.Translator
extends FacetBase<Server>
implements FacetComponentFlattener.Translator<Server> {
    private static final boolean SUPPORTED = MinecraftReflection.hasClass((String[])new String[]{"net.md_5.bungee.chat.TranslationRegistry"});

    @NotNull
    public String valueOrDefault(@NotNull Server game, @NotNull String key) {
        return TranslationRegistry.INSTANCE.translate(key);
    }

    public boolean isSupported() {
        return super.isSupported() && SUPPORTED;
    }

    SpigotFacet.Translator() {
        super(Server.class);
    }
}
