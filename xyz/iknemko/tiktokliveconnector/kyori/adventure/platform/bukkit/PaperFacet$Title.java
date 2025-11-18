/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.destroystokyo.paper.Title
 *  com.destroystokyo.paper.Title$Builder
 *  net.md_5.bungee.api.chat.BaseComponent
 *  org.bukkit.entity.Player
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.SpigotFacet$Message
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Title
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import com.destroystokyo.paper.Title;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.SpigotFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;

static class PaperFacet.Title
extends SpigotFacet.Message<Player>
implements Facet.Title<Player, BaseComponent[], Title.Builder, Title> {
    private static final boolean SUPPORTED = MinecraftReflection.hasClass((String[])new String[]{"com.destroystokyo.paper.Title"});

    public void showTitle(@NotNull Player viewer, @NotNull Title title) {
        viewer.sendTitle(title);
    }

    public void contributeTimes(// Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Title.Builder coll, int inTicks, int stayTicks, int outTicks) {
        if (inTicks > -1) {
            coll.fadeIn(inTicks);
        }
        if (stayTicks > -1) {
            coll.stay(stayTicks);
        }
        if (outTicks <= -1) return;
        coll.fadeOut(outTicks);
    }

    public void contributeTitle(// Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Title.Builder coll, BaseComponent @NotNull [] title) {
        coll.title(title);
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Title.Builder createTitleCollection() {
        return Title.builder();
    }

    public void clearTitle(@NotNull Player viewer) {
        viewer.hideTitle();
    }

    public boolean isSupported() {
        return super.isSupported() && SUPPORTED;
    }

    public void resetTitle(@NotNull Player viewer) {
        viewer.resetTitle();
    }

    public void contributeSubtitle(// Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Title.Builder coll, BaseComponent @NotNull [] subtitle) {
        coll.subtitle(subtitle);
    }

    @Nullable
    public Title completeTitle(// Could not load outer class - annotation placement on inner may be incorrect
    @NotNull Title.Builder coll) {
        return coll.build();
    }

    protected PaperFacet.Title() {
        super(Player.class);
    }
}
