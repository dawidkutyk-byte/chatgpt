/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language;

import java.util.Arrays;

private static final class DaitchMokotoffSoundex.Rule {
    private final String[] replacementBeforeVowel;
    private final String[] replacementDefault;
    private final String pattern;
    private final String[] replacementAtStart;

    static /* synthetic */ String access$000(DaitchMokotoffSoundex.Rule x0) {
        return x0.pattern;
    }

    public String[] getReplacements(String context, boolean atStart) {
        if (atStart) {
            return this.replacementAtStart;
        }
        int nextIndex = this.getPatternLength();
        boolean nextCharIsVowel = nextIndex < context.length() ? this.isVowel(context.charAt(nextIndex)) : false;
        if (!nextCharIsVowel) return this.replacementDefault;
        return this.replacementBeforeVowel;
    }

    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    public String toString() {
        return String.format("%s=(%s,%s,%s)", this.pattern, Arrays.asList(this.replacementAtStart), Arrays.asList(this.replacementBeforeVowel), Arrays.asList(this.replacementDefault));
    }

    protected DaitchMokotoffSoundex.Rule(String pattern, String replacementAtStart, String replacementBeforeVowel, String replacementDefault) {
        this.pattern = pattern;
        this.replacementAtStart = replacementAtStart.split("\\|");
        this.replacementBeforeVowel = replacementBeforeVowel.split("\\|");
        this.replacementDefault = replacementDefault.split("\\|");
    }

    public int getPatternLength() {
        return this.pattern.length();
    }

    public boolean matches(String context) {
        return context.startsWith(this.pattern);
    }
}
