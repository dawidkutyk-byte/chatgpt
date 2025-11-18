/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound$Source
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundStopImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.sound;

import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundStopImpl;

class SoundStop.4
extends SoundStopImpl {
    @Nullable
    public Key sound() {
        return null;
    }

    SoundStop.4(Sound.Source source) {
        super(source);
    }
}
