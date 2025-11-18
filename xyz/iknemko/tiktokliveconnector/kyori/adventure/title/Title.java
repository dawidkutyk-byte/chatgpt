/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.title.Title$Times
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.title.TitleImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.title.TitlePart
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Ticks
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.title;

import java.time.Duration;
import net.kyori.examination.Examinable;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.title.Title;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.title.TitleImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.title.TitlePart;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Ticks;

@ApiStatus.NonExtendable
public interface Title
extends Examinable {
    public static final Times DEFAULT_TIMES = Times.times((Duration)Ticks.duration((long)10L), (Duration)Ticks.duration((long)70L), (Duration)Ticks.duration((long)20L));

    public <T> @UnknownNullability T part(@NotNull TitlePart<T> var1);

    @NotNull
    public static Title title(@NotNull Component title, @NotNull Component subtitle) {
        return Title.title(title, subtitle, DEFAULT_TIMES);
    }

    @NotNull
    public static Title title(@NotNull Component title, @NotNull Component subtitle, @Nullable Times times) {
        return new TitleImpl(title, subtitle, times);
    }

    @Nullable
    public Times times();

    @NotNull
    public Component title();

    @NotNull
    public Component subtitle();
}
