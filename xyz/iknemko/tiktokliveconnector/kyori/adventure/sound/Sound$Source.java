/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Index
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.sound;

import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Index;

public static enum Sound.Source {
    MASTER("master"),
    MUSIC("music"),
    RECORD("record"),
    WEATHER("weather"),
    BLOCK("block"),
    HOSTILE("hostile"),
    NEUTRAL("neutral"),
    PLAYER("player"),
    AMBIENT("ambient"),
    VOICE("voice"),
    UI("ui");

    private final String name;
    public static final Index<String, Sound.Source> NAMES;

    static {
        NAMES = Index.create(Sound.Source.class, source -> source.name);
    }

    private Sound.Source(String name) {
        this.name = name;
    }
}
