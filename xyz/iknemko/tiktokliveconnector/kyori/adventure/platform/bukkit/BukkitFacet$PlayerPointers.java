/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Pointers
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetPointers
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetPointers$Type
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.Translator
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.lang.invoke.MethodHandle;
import java.util.Locale;
import org.bukkit.entity.Player;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetPointers;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.Translator;

static final class BukkitFacet.PlayerPointers
extends BukkitFacet<Player>
implements Facet.Pointers<Player> {
    private static final MethodHandle LOCALE_SUPPORTED;

    static {
        MethodHandle asLocale = MinecraftReflection.findMethod(Player.class, (String)"getLocale", Locale.class, (Class[])new Class[0]);
        MethodHandle asString = MinecraftReflection.findMethod(Player.class, (String)"getLocale", String.class, (Class[])new Class[0]);
        LOCALE_SUPPORTED = asLocale != null ? asLocale : asString;
    }

    BukkitFacet.PlayerPointers() {
        super(Player.class);
    }

    public void contributePointers(Player viewer, Pointers.Builder builder) {
        builder.withDynamic(Identity.UUID, () -> ((Player)viewer).getUniqueId());
        builder.withDynamic(Identity.DISPLAY_NAME, () -> BukkitComponentSerializer.legacy().deserializeOrNull((Object)viewer.getDisplayName()));
        builder.withDynamic(Identity.LOCALE, () -> {
            if (LOCALE_SUPPORTED == null) return Locale.getDefault();
            try {
                Object result = LOCALE_SUPPORTED.invoke(viewer);
                return result instanceof Locale ? (Locale)result : Translator.parseLocale((String)((String)result));
            }
            catch (Throwable error) {
                Knob.logError((Throwable)error, (String)"Failed to call getLocale() for %s", (Object[])new Object[]{viewer});
            }
            return Locale.getDefault();
        });
        builder.withStatic(FacetPointers.TYPE, (Object)FacetPointers.Type.PLAYER);
        builder.withDynamic(FacetPointers.WORLD, () -> Key.key((String)viewer.getWorld().getName()));
    }
}
