/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language.bm;

import java.util.List;
import java.util.Map;
import org.apache.commons.codec.language.bm.PhoneticEngine;
import org.apache.commons.codec.language.bm.Rule;

private static final class PhoneticEngine.RulesApplication {
    private int i;
    private final CharSequence input;
    private final Map<String, List<Rule>> finalRules;
    private final int maxPhonemes;
    private boolean found;
    private final PhoneticEngine.PhonemeBuilder phonemeBuilder;

    public PhoneticEngine.RulesApplication invoke() {
        this.found = false;
        int patternLength = 1;
        List<Rule> rules = this.finalRules.get(this.input.subSequence(this.i, this.i + patternLength));
        if (rules != null) {
            for (Rule rule : rules) {
                String pattern = rule.getPattern();
                patternLength = pattern.length();
                if (!rule.patternAndContextMatches(this.input, this.i)) continue;
                this.phonemeBuilder.apply(rule.getPhoneme(), this.maxPhonemes);
                this.found = true;
                break;
            }
        }
        if (!this.found) {
            patternLength = 1;
        }
        this.i += patternLength;
        return this;
    }

    public boolean isFound() {
        return this.found;
    }

    public int getI() {
        return this.i;
    }

    public PhoneticEngine.RulesApplication(Map<String, List<Rule>> finalRules, CharSequence input, PhoneticEngine.PhonemeBuilder phonemeBuilder, int i, int maxPhonemes) {
        if (finalRules == null) {
            throw new NullPointerException("The finalRules argument must not be null");
        }
        this.finalRules = finalRules;
        this.phonemeBuilder = phonemeBuilder;
        this.input = input;
        this.i = i;
        this.maxPhonemes = maxPhonemes;
    }

    public PhoneticEngine.PhonemeBuilder getPhonemeBuilder() {
        return this.phonemeBuilder;
    }
}
