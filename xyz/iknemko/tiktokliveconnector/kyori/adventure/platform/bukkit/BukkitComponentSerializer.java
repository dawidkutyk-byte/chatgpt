/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Material
 *  org.bukkit.Server
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$Translator
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.SpigotFacet$Translator
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetComponentFlattener
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetComponentFlattener$Translator
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattener
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONOptions
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.legacyimpl.NBTLegacyHoverEventSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.util.Collection;
import java.util.function.Supplier;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.SpigotFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetComponentFlattener;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattener;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONOptions;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.legacyimpl.NBTLegacyHoverEventSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionState;

public final class BukkitComponentSerializer {
    static final ComponentFlattener FLATTENER;
    private static final boolean IS_1_13;
    private static final Collection<FacetComponentFlattener.Translator<Server>> TRANSLATORS;
    private static final GsonComponentSerializer GSON_SERIALIZER;
    private static final boolean IS_1_16;
    private static final LegacyComponentSerializer LEGACY_SERIALIZER;

    @NotNull
    public static LegacyComponentSerializer legacy() {
        return LEGACY_SERIALIZER;
    }

    @NotNull
    public static GsonComponentSerializer gson() {
        return GSON_SERIALIZER;
    }

    private BukkitComponentSerializer() {
    }

    static {
        IS_1_13 = MinecraftReflection.findEnum(Material.class, (String)"BLUE_ICE") != null;
        IS_1_16 = MinecraftReflection.findEnum(Material.class, (String)"NETHERITE_PICKAXE") != null;
        TRANSLATORS = Facet.of((Supplier[])new Supplier[]{SpigotFacet.Translator::new, CraftBukkitFacet.Translator::new});
        FLATTENER = FacetComponentFlattener.get((Object)Bukkit.getServer(), TRANSLATORS);
        GSON_SERIALIZER = IS_1_13 ? GsonComponentSerializer.builder().options((OptionState)JSONOptions.byDataVersion().at(Bukkit.getUnsafe().getDataVersion())).build() : GsonComponentSerializer.builder().legacyHoverEventSerializer(NBTLegacyHoverEventSerializer.get()).options((OptionState)JSONOptions.byDataVersion().at(0)).build();
        LEGACY_SERIALIZER = IS_1_16 ? LegacyComponentSerializer.builder().hexColors().useUnusualXRepeatedCharacterHexFormat().flattener(FLATTENER).build() : LegacyComponentSerializer.builder().character('\u00a7').flattener(FLATTENER).build();
    }
}
