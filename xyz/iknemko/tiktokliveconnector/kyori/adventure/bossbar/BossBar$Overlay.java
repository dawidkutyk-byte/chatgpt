/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Index
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar;

import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Index;

public static enum BossBar.Overlay {
    PROGRESS("progress"),
    NOTCHED_6("notched_6"),
    NOTCHED_10("notched_10"),
    NOTCHED_12("notched_12"),
    NOTCHED_20("notched_20");

    public static final Index<String, BossBar.Overlay> NAMES;
    private final String name;

    private BossBar.Overlay(String name) {
        this.name = name;
    }

    static {
        NAMES = Index.create(BossBar.Overlay.class, overlay -> overlay.name);
    }
}
