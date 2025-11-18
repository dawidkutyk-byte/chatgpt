/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.title.TitleImpl$TimesImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.title;

import java.time.Duration;
import net.kyori.examination.Examinable;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.title.TitleImpl;

public static interface Title.Times
extends Examinable {
    @NotNull
    public Duration fadeOut();

    @NotNull
    public static Title.Times times(@NotNull Duration fadeIn, @NotNull Duration stay, @NotNull Duration fadeOut) {
        return new TitleImpl.TimesImpl(fadeIn, stay, fadeOut);
    }

    @NotNull
    public Duration fadeIn();

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    @NotNull
    public static Title.Times of(@NotNull Duration fadeIn, @NotNull Duration stay, @NotNull Duration fadeOut) {
        return Title.Times.times(fadeIn, stay, fadeOut);
    }

    @NotNull
    public Duration stay();
}
