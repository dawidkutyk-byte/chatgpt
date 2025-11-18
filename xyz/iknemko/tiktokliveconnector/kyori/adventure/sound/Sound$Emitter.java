/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.sound;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundImpl;

public static interface Sound.Emitter {
    @NotNull
    public static Sound.Emitter self() {
        return SoundImpl.EMITTER_SELF;
    }
}
