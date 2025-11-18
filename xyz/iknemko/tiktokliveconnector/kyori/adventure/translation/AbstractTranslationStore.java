/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.AbstractTranslationStore$Translation
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.TranslationStore
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.TriState
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.translation;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.AbstractTranslationStore;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.TranslationStore;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.TriState;

/*
 * Exception performing whole class analysis ignored.
 */
public abstract class AbstractTranslationStore<T>
implements Examinable,
TranslationStore<T> {
    private final Map<String, Translation> translations = new ConcurrentHashMap<String, Translation>();
    @NotNull
    private volatile Locale defaultLocale = Locale.US;
    @NotNull
    private final Key name;

    protected AbstractTranslationStore(@NotNull Key name) {
        this.name = Objects.requireNonNull(name, "name");
    }

    @NotNull
    public final String toString() {
        return Internals.toString((Examinable)this);
    }

    public final boolean contains(@NotNull String key) {
        return this.translations.containsKey(key);
    }

    public final void registerAll(@NotNull Locale locale, @NotNull Set<String> keys, Function<String, T> function) {
        IllegalArgumentException firstError = null;
        int errorCount = 0;
        for (String key : keys) {
            try {
                this.register(key, locale, function.apply(key));
            }
            catch (IllegalArgumentException e) {
                if (firstError == null) {
                    firstError = e;
                }
                ++errorCount;
            }
        }
        if (firstError == null) return;
        if (errorCount == 1) {
            throw firstError;
        }
        if (errorCount <= true) return;
        throw new IllegalArgumentException(String.format("Invalid key (and %d more)", errorCount - 1), firstError);
    }

    @NotNull
    public final Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"translations", this.translations));
    }

    public final boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AbstractTranslationStore)) {
            return false;
        }
        AbstractTranslationStore that = (AbstractTranslationStore)other;
        return this.name.equals(that.name) && this.translations.equals(that.translations) && this.defaultLocale.equals(that.defaultLocale);
    }

    @Nullable
    protected T translationValue(@NotNull String key, @NotNull Locale locale) {
        Translation translation = this.translations.get(Objects.requireNonNull(key, "key"));
        if (translation != null) return (T)Translation.access$000((Translation)translation, (Locale)Objects.requireNonNull(locale, "locale"));
        return null;
    }

    @NotNull
    public final Key name() {
        return this.name;
    }

    @NotNull
    public final TriState hasAnyTranslations() {
        return TriState.byBoolean((!this.translations.isEmpty() ? 1 : 0) != 0);
    }

    public final void registerAll(@NotNull Locale locale, @NotNull Map<String, T> translations) {
        this.registerAll(locale, translations.keySet(), translations::get);
    }

    public final void unregister(@NotNull String key) {
        this.translations.remove(key);
    }

    static /* synthetic */ Locale access$300(AbstractTranslationStore x0) {
        return x0.defaultLocale;
    }

    public final void defaultLocale(@NotNull Locale locale) {
        this.defaultLocale = Objects.requireNonNull(locale, "locale");
    }

    public final int hashCode() {
        return Objects.hash(this.name, this.translations, this.defaultLocale);
    }

    public final boolean canTranslate(@NotNull String key, @NotNull Locale locale) {
        Translation translation = this.translations.get(Objects.requireNonNull(key, "key"));
        if (translation != null) return Translation.access$000((Translation)translation, (Locale)Objects.requireNonNull(locale, "locale")) != null;
        return false;
    }

    public final boolean contains(@NotNull String key, @NotNull Locale locale) {
        Translation translation = this.translations.get(Objects.requireNonNull(key, "key"));
        if (translation != null) return Translation.access$100((Translation)translation).get(Objects.requireNonNull(locale, "locale")) != null;
        return false;
    }

    public final void register(@NotNull String key, @NotNull Locale locale, @NotNull T translation) {
        Translation.access$200((Translation)this.translations.computeIfAbsent(key, x$0 -> new Translation(this, x$0, null)), (Locale)locale, translation);
    }
}
