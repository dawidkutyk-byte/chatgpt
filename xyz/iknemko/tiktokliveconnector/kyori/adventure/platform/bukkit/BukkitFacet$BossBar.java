/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.boss.BarColor
 *  org.bukkit.boss.BarFlag
 *  org.bukkit.boss.BarStyle
 *  org.bukkit.boss.BossBar
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar$Color
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar$Flag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar$Overlay
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet$Message
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$BossBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;

static class BukkitFacet.BossBar
extends BukkitFacet.Message<Player>
implements Facet.BossBar<Player> {
    protected final BossBar bar = Bukkit.createBossBar((String)"", (BarColor)BarColor.PINK, (BarStyle)BarStyle.SOLID, (BarFlag[])new BarFlag[0]);

    public void bossBarFlagsChanged(@NotNull xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar bar, @NotNull Set<BossBar.Flag> flagsAdded, @NotNull Set<BossBar.Flag> flagsRemoved) {
        BarFlag flag;
        for (BossBar.Flag removeFlag : flagsRemoved) {
            flag = this.flag(removeFlag);
            if (flag == null) continue;
            this.bar.removeFlag(flag);
        }
        Iterator<BossBar.Flag> iterator = flagsAdded.iterator();
        while (iterator.hasNext()) {
            BossBar.Flag addFlag = iterator.next();
            flag = this.flag(addFlag);
            if (flag == null) continue;
            this.bar.addFlag(flag);
        }
    }

    public void close() {
        this.bar.removeAll();
    }

    public void bossBarColorChanged(@NotNull xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar bar, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull BossBar.Color oldColor, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull BossBar.Color newColor) {
        BarColor color = this.color(newColor);
        if (color == null) return;
        this.bar.setColor(color);
    }

    @Nullable
    private BarStyle style(// Could not load outer class - annotation placement on inner may be incorrect
     @NotNull BossBar.Overlay overlay) {
        if (overlay == BossBar.Overlay.PROGRESS) {
            return BarStyle.SOLID;
        }
        if (overlay == BossBar.Overlay.NOTCHED_6) {
            return BarStyle.SEGMENTED_6;
        }
        if (overlay == BossBar.Overlay.NOTCHED_10) {
            return BarStyle.SEGMENTED_10;
        }
        if (overlay == BossBar.Overlay.NOTCHED_12) {
            return BarStyle.SEGMENTED_12;
        }
        if (overlay == BossBar.Overlay.NOTCHED_20) {
            return BarStyle.SEGMENTED_20;
        }
        Knob.logUnsupported((Object)((Object)this), (Object)overlay);
        return null;
    }

    @Nullable
    private BarColor color(// Could not load outer class - annotation placement on inner may be incorrect
     @NotNull BossBar.Color color) {
        if (color == BossBar.Color.PINK) {
            return BarColor.PINK;
        }
        if (color == BossBar.Color.BLUE) {
            return BarColor.BLUE;
        }
        if (color == BossBar.Color.RED) {
            return BarColor.RED;
        }
        if (color == BossBar.Color.GREEN) {
            return BarColor.GREEN;
        }
        if (color == BossBar.Color.YELLOW) {
            return BarColor.YELLOW;
        }
        if (color == BossBar.Color.PURPLE) {
            return BarColor.PURPLE;
        }
        if (color == BossBar.Color.WHITE) {
            return BarColor.WHITE;
        }
        Knob.logUnsupported((Object)((Object)this), (Object)color);
        return null;
    }

    public boolean isEmpty() {
        return !this.bar.isVisible() || this.bar.getPlayers().isEmpty();
    }

    @Nullable
    private BarFlag flag(// Could not load outer class - annotation placement on inner may be incorrect
     @NotNull BossBar.Flag flag) {
        if (flag == BossBar.Flag.DARKEN_SCREEN) {
            return BarFlag.DARKEN_SKY;
        }
        if (flag == BossBar.Flag.PLAY_BOSS_MUSIC) {
            return BarFlag.PLAY_BOSS_MUSIC;
        }
        if (flag == BossBar.Flag.CREATE_WORLD_FOG) {
            return BarFlag.CREATE_FOG;
        }
        Knob.logUnsupported((Object)((Object)this), (Object)flag);
        return null;
    }

    public void removeViewer(@NotNull Player viewer) {
        this.bar.removePlayer(viewer);
    }

    public void bossBarProgressChanged(@NotNull xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar bar, float oldPercent, float newPercent) {
        this.bar.setProgress((double)newPercent);
    }

    public void bossBarNameChanged(@NotNull xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar bar, @NotNull Component oldName, @NotNull Component newName) {
        if (this.bar.getPlayers().isEmpty()) return;
        this.bar.setTitle(this.createMessage((CommandSender)((Player)this.bar.getPlayers().get(0)), newName));
    }

    protected BukkitFacet.BossBar(@NotNull Collection<Player> viewers) {
        super(Player.class);
        this.bar.setVisible(false);
        Iterator<Player> iterator = viewers.iterator();
        while (iterator.hasNext()) {
            Player viewer = iterator.next();
            this.bar.addPlayer(viewer);
        }
    }

    public void addViewer(@NotNull Player viewer) {
        this.bar.addPlayer(viewer);
    }

    public void bossBarInitialized(@NotNull xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar bar) {
        super.bossBarInitialized(bar);
        this.bar.setVisible(true);
    }

    public void bossBarOverlayChanged(@NotNull xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar bar, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull BossBar.Overlay oldOverlay, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull BossBar.Overlay newOverlay) {
        BarStyle style = this.style(newOverlay);
        if (style == null) return;
        this.bar.setStyle(style);
    }
}
