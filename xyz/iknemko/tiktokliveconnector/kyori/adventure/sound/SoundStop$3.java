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

import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.Sound;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.sound.SoundStopImpl;

class SoundStop.3
extends SoundStopImpl {
    final /* synthetic */ Supplier val$sound;

    SoundStop.3(Sound.Source source, Supplier supplier) {
        this.val$sound = supplier;
        super(source);
    }

    @NotNull
    public Key sound() {
        return ((Sound.Type)this.val$sound.get()).key();
    }
}
