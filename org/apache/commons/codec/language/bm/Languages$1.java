/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language.bm;

import java.util.NoSuchElementException;
import org.apache.commons.codec.language.bm.Languages;

static final class Languages.1
extends Languages.LanguageSet {
    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public boolean contains(String language) {
        return false;
    }

    Languages.1() {
    }

    @Override
    public Languages.LanguageSet restrictTo(Languages.LanguageSet other) {
        return this;
    }

    @Override
    public Languages.LanguageSet merge(Languages.LanguageSet other) {
        return other;
    }

    public String toString() {
        return "NO_LANGUAGES";
    }

    @Override
    public String getAny() {
        throw new NoSuchElementException("Can't fetch any language from the empty language set.");
    }
}
