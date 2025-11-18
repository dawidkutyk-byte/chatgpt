/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language.bm;

import java.util.Collections;
import java.util.Comparator;
import org.apache.commons.codec.language.bm.Languages;
import org.apache.commons.codec.language.bm.Rule;

public static final class Rule.Phoneme
implements Rule.PhonemeExpr {
    private final StringBuilder phonemeText;
    private final Languages.LanguageSet languages;
    public static final Comparator<Rule.Phoneme> COMPARATOR = new /* Unavailable Anonymous Inner Class!! */;

    @Deprecated
    public Rule.Phoneme join(Rule.Phoneme right) {
        return new Rule.Phoneme(this.phonemeText.toString() + right.phonemeText.toString(), this.languages.restrictTo(right.languages));
    }

    static /* synthetic */ StringBuilder access$000(Rule.Phoneme x0) {
        return x0.phonemeText;
    }

    public Rule.Phoneme(CharSequence phonemeText, Languages.LanguageSet languages) {
        this.phonemeText = new StringBuilder(phonemeText);
        this.languages = languages;
    }

    public String toString() {
        return this.phonemeText.toString() + "[" + this.languages + "]";
    }

    public Languages.LanguageSet getLanguages() {
        return this.languages;
    }

    public Rule.Phoneme(Rule.Phoneme phonemeLeft, Rule.Phoneme phonemeRight) {
        this(phonemeLeft.phonemeText, phonemeLeft.languages);
        this.phonemeText.append((CharSequence)phonemeRight.phonemeText);
    }

    public CharSequence getPhonemeText() {
        return this.phonemeText;
    }

    public Rule.Phoneme mergeWithLanguage(Languages.LanguageSet lang) {
        return new Rule.Phoneme(this.phonemeText.toString(), this.languages.merge(lang));
    }

    public Rule.Phoneme append(CharSequence str) {
        this.phonemeText.append(str);
        return this;
    }

    public Rule.Phoneme(Rule.Phoneme phonemeLeft, Rule.Phoneme phonemeRight, Languages.LanguageSet languages) {
        this(phonemeLeft.phonemeText, languages);
        this.phonemeText.append((CharSequence)phonemeRight.phonemeText);
    }

    @Override
    public Iterable<Rule.Phoneme> getPhonemes() {
        return Collections.singleton(this);
    }
}
