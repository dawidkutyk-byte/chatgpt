/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.AbstractTranslationStore
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.translation;

import java.text.MessageFormat;
import java.util.Locale;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.AbstractTranslationStore;

final class ComponentTranslationStore
extends AbstractTranslationStore<Component> {
    @Nullable
    public Component translate(@NotNull TranslatableComponent component, @NotNull Locale locale) {
        Component translatedComponent = (Component)this.translationValue(component.key(), locale);
        if (translatedComponent != null) return translatedComponent.append(component.children());
        return null;
    }

    ComponentTranslationStore(@NotNull Key name) {
        super(name);
    }

    @Nullable
    public MessageFormat translate(@NotNull String key, @NotNull Locale locale) {
        return null;
    }
}
