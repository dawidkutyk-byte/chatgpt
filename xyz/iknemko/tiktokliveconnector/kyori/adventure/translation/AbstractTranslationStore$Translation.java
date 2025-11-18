/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.AbstractTranslationStore
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.TranslationLocales
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.translation;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.AbstractTranslationStore;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.TranslationLocales;

/*
 * Exception performing whole class analysis ignored.
 */
private final class AbstractTranslationStore.Translation
implements Examinable {
    private final String key;
    private final Map<Locale, T> translations;

    public int hashCode() {
        return Objects.hash(this.key, this.translations);
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"key", (String)this.key), ExaminableProperty.of((String)"translations", this.translations));
    }

    static /* synthetic */ void access$200(AbstractTranslationStore.Translation x0, Locale x1, Object x2) {
        x0.register(x1, x2);
    }

    @Nullable
    private T translate(@NotNull Locale locale) {
        Object format = this.translations.get(Objects.requireNonNull(locale, "locale"));
        if (format != null) return format;
        format = this.translations.get(new Locale(locale.getLanguage()));
        if (format != null) return format;
        format = this.translations.get(AbstractTranslationStore.access$300((AbstractTranslationStore)AbstractTranslationStore.this));
        if (format != null) return format;
        format = this.translations.get(TranslationLocales.global());
        return format;
    }

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AbstractTranslationStore.Translation)) {
            return false;
        }
        AbstractTranslationStore.Translation that = (AbstractTranslationStore.Translation)other;
        return this.key.equals(that.key) && this.translations.equals(that.translations);
    }

    static /* synthetic */ Object access$000(AbstractTranslationStore.Translation x0, Locale x1) {
        return x0.translate(x1);
    }

    private AbstractTranslationStore.Translation(String key) {
        this.key = Objects.requireNonNull(key, "key");
        this.translations = new ConcurrentHashMap();
    }

    static /* synthetic */ Map access$100(AbstractTranslationStore.Translation x0) {
        return x0.translations;
    }

    private void register(@NotNull Locale locale, @NotNull T translation) {
        if (this.translations.putIfAbsent(Objects.requireNonNull(locale, "locale"), Objects.requireNonNull(translation, "translation")) == null) return;
        throw new IllegalArgumentException(String.format("Translation already exists: %s for %s", this.key, locale));
    }
}
