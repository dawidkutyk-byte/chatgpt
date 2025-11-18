/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language.bm;

import java.util.List;
import org.apache.commons.codec.language.bm.Rule;

public static final class Rule.PhonemeList
implements Rule.PhonemeExpr {
    private final List<Rule.Phoneme> phonemes;

    public Rule.PhonemeList(List<Rule.Phoneme> phonemes) {
        this.phonemes = phonemes;
    }

    public List<Rule.Phoneme> getPhonemes() {
        return this.phonemes;
    }
}
