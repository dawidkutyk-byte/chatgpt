/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.TranslatableComponentRenderer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.GlobalTranslator
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.Translator
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.TriState
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.translation;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.TranslatableComponentRenderer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.GlobalTranslator;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.Translator;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.TriState;

final class GlobalTranslatorImpl
implements GlobalTranslator {
    private static final Key NAME = Key.key((String)"adventure", (String)"global");
    final TranslatableComponentRenderer<Locale> renderer = TranslatableComponentRenderer.usingTranslationSource((Translator)this);
    static final GlobalTranslatorImpl INSTANCE = new GlobalTranslatorImpl();
    private final Set<Translator> sources = Collections.newSetFromMap(new ConcurrentHashMap());

    public boolean addSource(@NotNull Translator source) {
        Objects.requireNonNull(source, "source");
        if (source != this) return this.sources.add(source);
        throw new IllegalArgumentException("GlobalTranslationSource");
    }

    @Nullable
    public Component translate(@NotNull TranslatableComponent component, @NotNull Locale locale) {
        Translator source;
        Component translation;
        Objects.requireNonNull(component, "component");
        Objects.requireNonNull(locale, "locale");
        Iterator<Translator> iterator = this.sources.iterator();
        do {
            if (!iterator.hasNext()) return null;
        } while ((translation = (source = iterator.next()).translate(component, locale)) == null);
        return translation;
    }

    @NotNull
    public TriState hasAnyTranslations() {
        if (this.sources.isEmpty()) return TriState.FALSE;
        return TriState.TRUE;
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"sources", this.sources));
    }

    public boolean removeSource(@NotNull Translator source) {
        Objects.requireNonNull(source, "source");
        return this.sources.remove(source);
    }

    @NotNull
    public Key name() {
        return NAME;
    }

    private GlobalTranslatorImpl() {
    }

    @Nullable
    public MessageFormat translate(@NotNull String key, @NotNull Locale locale) {
        Translator source;
        MessageFormat translation;
        Objects.requireNonNull(key, "key");
        Objects.requireNonNull(locale, "locale");
        Iterator<Translator> iterator = this.sources.iterator();
        do {
            if (!iterator.hasNext()) return null;
        } while ((translation = (source = iterator.next()).translate(key, locale)) == null);
        return translation;
    }

    @NotNull
    public Iterable<? extends Translator> sources() {
        return Collections.unmodifiableSet(this.sources);
    }
}
