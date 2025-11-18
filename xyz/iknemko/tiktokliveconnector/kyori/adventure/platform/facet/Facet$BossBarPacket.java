/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar$Color
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar$Flag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar$Overlay
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$BossBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet;

import java.util.Iterator;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;

public static interface Facet.BossBarPacket<V>
extends Facet.BossBar<V> {
    public static final int ACTION_REMOVE = 1;
    public static final int ACTION_STYLE = 4;
    public static final int ACTION_ADD = 0;
    public static final int ACTION_TITLE = 3;
    public static final int ACTION_HEALTH = 2;
    public static final int ACTION_FLAG = 5;

    default public int createOverlay(// Could not load outer class - annotation placement on inner may be incorrect
     @NotNull BossBar.Overlay overlay) {
        if (overlay == BossBar.Overlay.PROGRESS) {
            return 0;
        }
        if (overlay == BossBar.Overlay.NOTCHED_6) {
            return 1;
        }
        if (overlay == BossBar.Overlay.NOTCHED_10) {
            return 2;
        }
        if (overlay == BossBar.Overlay.NOTCHED_12) {
            return 3;
        }
        if (overlay == BossBar.Overlay.NOTCHED_20) {
            return 4;
        }
        Knob.logUnsupported((Object)this, (Object)overlay);
        return 0;
    }

    default public byte createFlag(byte flagBit, @NotNull Set<BossBar.Flag> flagsAdded, @NotNull Set<BossBar.Flag> flagsRemoved) {
        byte bit = flagBit;
        for (BossBar.Flag flag : flagsAdded) {
            if (flag == BossBar.Flag.DARKEN_SCREEN) {
                bit = (byte)(bit | 1);
                continue;
            }
            if (flag == BossBar.Flag.PLAY_BOSS_MUSIC) {
                bit = (byte)(bit | 2);
                continue;
            }
            if (flag == BossBar.Flag.CREATE_WORLD_FOG) {
                bit = (byte)(bit | 4);
                continue;
            }
            Knob.logUnsupported((Object)this, (Object)flag);
        }
        Iterator<BossBar.Flag> iterator = flagsRemoved.iterator();
        while (iterator.hasNext()) {
            BossBar.Flag flag;
            flag = iterator.next();
            if (flag == BossBar.Flag.DARKEN_SCREEN) {
                bit = (byte)(bit & 0xFFFFFFFE);
                continue;
            }
            if (flag == BossBar.Flag.PLAY_BOSS_MUSIC) {
                bit = (byte)(bit & 0xFFFFFFFD);
                continue;
            }
            if (flag == BossBar.Flag.CREATE_WORLD_FOG) {
                bit = (byte)(bit & 0xFFFFFFFB);
                continue;
            }
            Knob.logUnsupported((Object)this, (Object)flag);
        }
        return bit;
    }

    default public int createColor(// Could not load outer class - annotation placement on inner may be incorrect
     @NotNull BossBar.Color color) {
        if (color == BossBar.Color.PURPLE) {
            return 5;
        }
        if (color == BossBar.Color.PINK) {
            return 0;
        }
        if (color == BossBar.Color.BLUE) {
            return 1;
        }
        if (color == BossBar.Color.RED) {
            return 2;
        }
        if (color == BossBar.Color.GREEN) {
            return 3;
        }
        if (color == BossBar.Color.YELLOW) {
            return 4;
        }
        if (color == BossBar.Color.WHITE) {
            return 6;
        }
        Knob.logUnsupported((Object)this, (Object)color);
        return 5;
    }
}
