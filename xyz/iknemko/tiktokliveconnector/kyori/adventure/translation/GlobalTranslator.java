/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.TranslatableComponentRenderer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.GlobalTranslatorImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.Translator
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.translation;

import java.util.Locale;
import net.kyori.examination.Examinable;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.TranslatableComponentRenderer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.GlobalTranslatorImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.Translator;

public interface GlobalTranslator
extends Translator,
Examinable {
    @NotNull
    public Iterable<? extends Translator> sources();

    @NotNull
    public static Component render(@NotNull Component component, @NotNull Locale locale) {
        return GlobalTranslator.renderer().render(component, (Object)locale);
    }

    public boolean addSource(@NotNull Translator var1);

    @NotNull
    public static TranslatableComponentRenderer<Locale> renderer() {
        return GlobalTranslatorImpl.INSTANCE.renderer;
    }

    public boolean removeSource(@NotNull Translator var1);

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    @NotNull
    public static GlobalTranslator get() {
        return GlobalTranslatorImpl.INSTANCE;
    }

    @NotNull
    public static GlobalTranslator translator() {
        return GlobalTranslatorImpl.INSTANCE;
    }
}
