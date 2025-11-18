/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language.bm;

import java.util.NoSuchElementException;
import org.apache.commons.codec.language.bm.Languages;

static final class Languages.2
extends Languages.LanguageSet {
    @Override
    public boolean contains(String language) {
        return true;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public Languages.LanguageSet restrictTo(Languages.LanguageSet other) {
        return other;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public String toString() {
        return "ANY_LANGUAGE";
    }

    @Override
    public Languages.LanguageSet merge(Languages.LanguageSet other) {
        return other;
    }

    @Override
    public String getAny() {
        throw new NoSuchElementException("Can't fetch any language from the any language set.");
    }

    Languages.2() {
    }
}
