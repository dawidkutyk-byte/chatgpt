/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language.bm;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.commons.codec.language.bm.Languages;
import org.apache.commons.codec.language.bm.Rule;

static final class PhoneticEngine.PhonemeBuilder {
    private final Set<Rule.Phoneme> phonemes;

    public void apply(Rule.PhonemeExpr phonemeExpr, int maxPhonemes) {
        LinkedHashSet<Rule.Phoneme> newPhonemes = new LinkedHashSet<Rule.Phoneme>(maxPhonemes);
        for (Rule.Phoneme left : this.phonemes) {
            for (Rule.Phoneme right : phonemeExpr.getPhonemes()) {
                Languages.LanguageSet languages = left.getLanguages().restrictTo(right.getLanguages());
                if (languages.isEmpty()) continue;
                Rule.Phoneme join = new Rule.Phoneme(left, right, languages);
                if (newPhonemes.size() >= maxPhonemes) continue;
                newPhonemes.add(join);
                if (newPhonemes.size() < maxPhonemes) continue;
            }
        }
        this.phonemes.clear();
        this.phonemes.addAll(newPhonemes);
    }

    private PhoneticEngine.PhonemeBuilder(Set<Rule.Phoneme> phonemes) {
        this.phonemes = phonemes;
    }

    public Set<Rule.Phoneme> getPhonemes() {
        return this.phonemes;
    }

    public String makeString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Rule.Phoneme> iterator = this.phonemes.iterator();
        while (iterator.hasNext()) {
            Rule.Phoneme ph = iterator.next();
            if (sb.length() > 0) {
                sb.append("|");
            }
            sb.append(ph.getPhonemeText());
        }
        return sb.toString();
    }

    public static PhoneticEngine.PhonemeBuilder empty(Languages.LanguageSet languages) {
        return new PhoneticEngine.PhonemeBuilder(new Rule.Phoneme("", languages));
    }

    private PhoneticEngine.PhonemeBuilder(Rule.Phoneme phoneme) {
        this.phonemes = new LinkedHashSet<Rule.Phoneme>();
        this.phonemes.add(phoneme);
    }

    public void append(CharSequence str) {
        Iterator<Rule.Phoneme> iterator = this.phonemes.iterator();
        while (iterator.hasNext()) {
            Rule.Phoneme ph = iterator.next();
            ph.append(str);
        }
    }
}
