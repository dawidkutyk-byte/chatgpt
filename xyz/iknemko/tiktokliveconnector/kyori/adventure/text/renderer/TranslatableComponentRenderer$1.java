/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.TranslatableComponentRenderer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.Translator
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.TriState
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer;

import java.text.MessageFormat;
import java.util.Locale;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.TranslatableComponentRenderer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.Translator;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.TriState;

static class TranslatableComponentRenderer.1
extends TranslatableComponentRenderer<Locale> {
    final /* synthetic */ Translator val$source;

    TranslatableComponentRenderer.1(Translator translator) {
        this.val$source = translator;
    }

    @NotNull
    protected Component renderTranslatableInner(@NotNull TranslatableComponent component, @NotNull Locale context) {
        TriState anyTranslations = this.val$source.hasAnyTranslations();
        if (anyTranslations == TriState.FALSE) {
            return component;
        }
        Component translated = this.val$source.canTranslate(component.key(), context) ? this.val$source.translate(component, context) : null;
        return translated != null ? this.render(translated, context) : super.renderTranslatableInner(component, (Object)context);
    }

    @Nullable
    protected MessageFormat translate(@NotNull String key, @NotNull Locale context) {
        return this.val$source.translate(key, context);
    }
}
