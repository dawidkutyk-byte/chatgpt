/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Index
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar;

import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Index;

public static enum BossBar.Flag {
    DARKEN_SCREEN("darken_screen"),
    PLAY_BOSS_MUSIC("play_boss_music"),
    CREATE_WORLD_FOG("create_world_fog");

    public static final Index<String, BossBar.Flag> NAMES;
    private final String name;

    private BossBar.Flag(String name) {
        this.name = name;
    }

    static {
        NAMES = Index.create(BossBar.Flag.class, flag -> flag.name);
    }
}
