/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound$Source
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound$Type
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundStopImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.sound;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundStopImpl;

class SoundStop.2
extends SoundStopImpl {
    final /* synthetic */ Sound.Type val$sound;

    @NotNull
    public Key sound() {
        return this.val$sound.key();
    }

    SoundStop.2(Sound.Source source, Sound.Type type) {
        this.val$sound = type;
        super(source);
    }
}
