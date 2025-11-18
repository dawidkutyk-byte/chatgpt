/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language.bm;

import java.util.Set;
import java.util.regex.Pattern;

private static final class Lang.LangRule {
    private final Set<String> languages;
    private final boolean acceptOnMatch;
    private final Pattern pattern;

    static /* synthetic */ boolean access$100(Lang.LangRule x0) {
        return x0.acceptOnMatch;
    }

    private Lang.LangRule(Pattern pattern, Set<String> languages, boolean acceptOnMatch) {
        this.pattern = pattern;
        this.languages = languages;
        this.acceptOnMatch = acceptOnMatch;
    }

    static /* synthetic */ Set access$200(Lang.LangRule x0) {
        return x0.languages;
    }

    public boolean matches(String txt) {
        return this.pattern.matcher(txt).find();
    }
}
