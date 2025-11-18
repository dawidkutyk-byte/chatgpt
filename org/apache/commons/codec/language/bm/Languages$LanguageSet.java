/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language.bm;

import java.util.Set;
import org.apache.commons.codec.language.bm.Languages;

public static abstract class Languages.LanguageSet {
    public abstract String getAny();

    public abstract Languages.LanguageSet restrictTo(Languages.LanguageSet var1);

    public abstract boolean isEmpty();

    public static Languages.LanguageSet from(Set<String> langs) {
        return langs.isEmpty() ? NO_LANGUAGES : new Languages.SomeLanguages(langs, null);
    }

    abstract Languages.LanguageSet merge(Languages.LanguageSet var1);

    public abstract boolean contains(String var1);

    public abstract boolean isSingleton();
}
