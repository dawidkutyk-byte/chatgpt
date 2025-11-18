/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Index
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar;

import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Index;

public static enum BossBar.Color {
    PINK("pink"),
    BLUE("blue"),
    RED("red"),
    GREEN("green"),
    YELLOW("yellow"),
    PURPLE("purple"),
    WHITE("white");

    public static final Index<String, BossBar.Color> NAMES;
    private final String name;

    static {
        NAMES = Index.create(BossBar.Color.class, color -> color.name);
    }

    private BossBar.Color(String name) {
        this.name = name;
    }
}
