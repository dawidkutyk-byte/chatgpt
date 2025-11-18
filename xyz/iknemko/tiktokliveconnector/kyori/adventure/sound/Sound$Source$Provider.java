/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound$Source
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.sound;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound;

public static interface Sound.Source.Provider {
    @NotNull
    public Sound.Source soundSource();
}
