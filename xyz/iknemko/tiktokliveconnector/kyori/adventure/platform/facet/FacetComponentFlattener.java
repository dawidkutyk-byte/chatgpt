/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetComponentFlattener$Translator
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattener
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattener$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.GlobalTranslator
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.TranslationRegistry
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.Translator
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetComponentFlattener;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattener;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.GlobalTranslator;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.TranslationRegistry;

@ApiStatus.Internal
public final class FacetComponentFlattener {
    private static final Pattern LOCALIZATION_PATTERN = Pattern.compile("%(?:(\\d+)\\$)?s");

    private FacetComponentFlattener() {
    }

    public static <V> ComponentFlattener get(V instance, Collection<? extends Translator<V>> candidates) {
        Translator translator = (Translator)Facet.of(candidates, instance);
        ComponentFlattener.Builder flattenerBuilder = (ComponentFlattener.Builder)ComponentFlattener.basic().toBuilder();
        flattenerBuilder.complexMapper(TranslatableComponent.class, (translatable, consumer) -> {
            String key = translatable.key();
            for (xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.Translator registry : GlobalTranslator.translator().sources()) {
                if (!(registry instanceof TranslationRegistry) || !((TranslationRegistry)registry).contains(key)) continue;
                consumer.accept(GlobalTranslator.render((Component)translatable, (Locale)Locale.getDefault()));
                return;
            }
            @NotNull String translated = translator == null ? key : translator.valueOrDefault(instance, key);
            Matcher matcher = LOCALIZATION_PATTERN.matcher(translated);
            List args = translatable.args();
            int argPosition = 0;
            int lastIdx = 0;
            while (true) {
                int idx;
                if (!matcher.find()) {
                    if (lastIdx >= translated.length()) return;
                    consumer.accept(Component.text((String)translated.substring(lastIdx)));
                    return;
                }
                if (lastIdx < matcher.start()) {
                    consumer.accept(Component.text((String)translated.substring(lastIdx, matcher.start())));
                }
                lastIdx = matcher.end();
                @Nullable String argIdx = matcher.group(1);
                if (argIdx != null) {
                    try {
                        idx = Integer.parseInt(argIdx) - 1;
                        if (idx >= args.size()) continue;
                        consumer.accept((Component)args.get(idx));
                    }
                    catch (NumberFormatException idx2) {}
                    continue;
                }
                if ((idx = argPosition++) >= args.size()) continue;
                consumer.accept((Component)args.get(idx));
            }
        });
        return (ComponentFlattener)flattenerBuilder.build();
    }
}
