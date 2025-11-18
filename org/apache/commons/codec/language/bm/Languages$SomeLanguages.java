/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language.bm;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.codec.language.bm.Languages;

public static final class Languages.SomeLanguages
extends Languages.LanguageSet {
    private final Set<String> languages;

    @Override
    public boolean isSingleton() {
        return this.languages.size() == 1;
    }

    private Languages.SomeLanguages(Set<String> languages) {
        this.languages = Collections.unmodifiableSet(languages);
    }

    @Override
    public Languages.LanguageSet restrictTo(Languages.LanguageSet other) {
        if (other == NO_LANGUAGES) {
            return other;
        }
        if (other == ANY_LANGUAGE) {
            return this;
        }
        Languages.SomeLanguages sl = (Languages.SomeLanguages)other;
        HashSet<String> ls = new HashSet<String>(Math.min(this.languages.size(), sl.languages.size()));
        Iterator<String> iterator = this.languages.iterator();
        while (iterator.hasNext()) {
            String lang = iterator.next();
            if (!sl.languages.contains(lang)) continue;
            ls.add(lang);
        }
        return Languages.SomeLanguages.from(ls);
    }

    @Override
    public boolean contains(String language) {
        return this.languages.contains(language);
    }

    public Set<String> getLanguages() {
        return this.languages;
    }

    @Override
    public Languages.LanguageSet merge(Languages.LanguageSet other) {
        if (other == NO_LANGUAGES) {
            return this;
        }
        if (other == ANY_LANGUAGE) {
            return other;
        }
        Languages.SomeLanguages sl = (Languages.SomeLanguages)other;
        HashSet<String> ls = new HashSet<String>(this.languages);
        Iterator<String> iterator = sl.languages.iterator();
        while (iterator.hasNext()) {
            String lang = iterator.next();
            ls.add(lang);
        }
        return Languages.SomeLanguages.from(ls);
    }

    @Override
    public boolean isEmpty() {
        return this.languages.isEmpty();
    }

    public String toString() {
        return "Languages(" + this.languages.toString() + ")";
    }

    @Override
    public String getAny() {
        return this.languages.iterator().next();
    }
}
