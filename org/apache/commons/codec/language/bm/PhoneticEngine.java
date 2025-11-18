/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language.bm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import org.apache.commons.codec.language.bm.Lang;
import org.apache.commons.codec.language.bm.Languages;
import org.apache.commons.codec.language.bm.NameType;
import org.apache.commons.codec.language.bm.Rule;
import org.apache.commons.codec.language.bm.RuleType;

public class PhoneticEngine {
    private static final Map<NameType, Set<String>> NAME_PREFIXES = new EnumMap<NameType, Set<String>>(NameType.class);
    private final NameType nameType;
    private final Lang lang;
    private static final int DEFAULT_MAX_PHONEMES = 20;
    private final boolean concat;
    private final int maxPhonemes;
    private final RuleType ruleType;

    public NameType getNameType() {
        return this.nameType;
    }

    public PhoneticEngine(NameType nameType, RuleType ruleType, boolean concat, int maxPhonemes) {
        if (ruleType == RuleType.RULES) {
            throw new IllegalArgumentException("ruleType must not be " + (Object)((Object)RuleType.RULES));
        }
        this.nameType = nameType;
        this.ruleType = ruleType;
        this.concat = concat;
        this.lang = Lang.instance(nameType);
        this.maxPhonemes = maxPhonemes;
    }

    public String encode(String input) {
        Languages.LanguageSet languageSet = this.lang.guessLanguages(input);
        return this.encode(input, languageSet);
    }

    public PhoneticEngine(NameType nameType, RuleType ruleType, boolean concat) {
        this(nameType, ruleType, concat, 20);
    }

    public String encode(String input, Languages.LanguageSet languageSet) {
        Map<String, List<Rule>> rules = Rule.getInstanceMap(this.nameType, RuleType.RULES, languageSet);
        Map<String, List<Rule>> finalRules1 = Rule.getInstanceMap(this.nameType, this.ruleType, "common");
        Map<String, List<Rule>> finalRules2 = Rule.getInstanceMap(this.nameType, this.ruleType, languageSet);
        input = input.toLowerCase(Locale.ENGLISH).replace('-', ' ').trim();
        if (this.nameType == NameType.GENERIC) {
            if (input.length() >= 2 && input.substring(0, 2).equals("d'")) {
                String remainder = input.substring(2);
                String combined = "d" + remainder;
                return "(" + this.encode(remainder) + ")-(" + this.encode(combined) + ")";
            }
            for (String l : NAME_PREFIXES.get((Object)this.nameType)) {
                if (!input.startsWith(l + " ")) continue;
                String remainder = input.substring(l.length() + 1);
                String string = l + remainder;
                return "(" + this.encode(remainder) + ")-(" + this.encode(string) + ")";
            }
        }
        List<String> words = Arrays.asList(input.split("\\s+"));
        ArrayList<String> words2 = new ArrayList<String>();
        switch (this.nameType) {
            case SEPHARDIC: {
                for (String string : words) {
                    String[] parts = string.split("'");
                    String lastPart = parts[parts.length - 1];
                    words2.add(lastPart);
                }
                words2.removeAll((Collection)NAME_PREFIXES.get((Object)this.nameType));
                break;
            }
            case ASHKENAZI: {
                words2.addAll(words);
                words2.removeAll((Collection)NAME_PREFIXES.get((Object)this.nameType));
                break;
            }
            case GENERIC: {
                words2.addAll(words);
                break;
            }
            default: {
                throw new IllegalStateException("Unreachable case: " + (Object)((Object)this.nameType));
            }
        }
        if (this.concat) {
            input = PhoneticEngine.join(words2, " ");
        } else if (words2.size() == 1) {
            input = words.iterator().next();
        } else {
            StringBuilder result = new StringBuilder();
            Iterator iterator = words2.iterator();
            while (iterator.hasNext()) {
                String word = (String)iterator.next();
                result.append("-").append(this.encode(word));
            }
            return result.substring(1);
        }
        PhonemeBuilder phonemeBuilder = PhonemeBuilder.empty(languageSet);
        boolean bl = false;
        while (true) {
            int n;
            if (n >= input.length()) {
                phonemeBuilder = this.applyFinalRules(phonemeBuilder, finalRules1);
                phonemeBuilder = this.applyFinalRules(phonemeBuilder, finalRules2);
                return phonemeBuilder.makeString();
            }
            RulesApplication rulesApplication = new RulesApplication(rules, input, phonemeBuilder, n, this.maxPhonemes).invoke();
            n = rulesApplication.getI();
            phonemeBuilder = rulesApplication.getPhonemeBuilder();
        }
    }

    static {
        NAME_PREFIXES.put(NameType.ASHKENAZI, Collections.unmodifiableSet(new HashSet<String>(Arrays.asList("bar", "ben", "da", "de", "van", "von"))));
        NAME_PREFIXES.put(NameType.SEPHARDIC, Collections.unmodifiableSet(new HashSet<String>(Arrays.asList("al", "el", "da", "dal", "de", "del", "dela", "de la", "della", "des", "di", "do", "dos", "du", "van", "von"))));
        NAME_PREFIXES.put(NameType.GENERIC, Collections.unmodifiableSet(new HashSet<String>(Arrays.asList("da", "dal", "de", "del", "dela", "de la", "della", "des", "di", "do", "dos", "du", "van", "von"))));
    }

    public Lang getLang() {
        return this.lang;
    }

    public RuleType getRuleType() {
        return this.ruleType;
    }

    public int getMaxPhonemes() {
        return this.maxPhonemes;
    }

    private static String join(Iterable<String> strings, String sep) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> si = strings.iterator();
        if (si.hasNext()) {
            sb.append(si.next());
        }
        while (si.hasNext()) {
            sb.append(sep).append(si.next());
        }
        return sb.toString();
    }

    private PhonemeBuilder applyFinalRules(PhonemeBuilder phonemeBuilder, Map<String, List<Rule>> finalRules) {
        if (finalRules == null) {
            throw new NullPointerException("finalRules can not be null");
        }
        if (finalRules.isEmpty()) {
            return phonemeBuilder;
        }
        TreeMap<Rule.Phoneme, Rule.Phoneme> phonemes = new TreeMap<Rule.Phoneme, Rule.Phoneme>(Rule.Phoneme.COMPARATOR);
        Iterator<Rule.Phoneme> iterator = phonemeBuilder.getPhonemes().iterator();
        block0: while (iterator.hasNext()) {
            Rule.Phoneme phoneme = iterator.next();
            PhonemeBuilder subBuilder = PhonemeBuilder.empty(phoneme.getLanguages());
            String phonemeText = phoneme.getPhonemeText().toString();
            int i = 0;
            while (i < phonemeText.length()) {
                RulesApplication rulesApplication = new RulesApplication(finalRules, phonemeText, subBuilder, i, this.maxPhonemes).invoke();
                boolean found = rulesApplication.isFound();
                subBuilder = rulesApplication.getPhonemeBuilder();
                if (!found) {
                    subBuilder.append(phonemeText.subSequence(i, i + 1));
                }
                i = rulesApplication.getI();
            }
            Iterator<Rule.Phoneme> iterator2 = subBuilder.getPhonemes().iterator();
            while (true) {
                if (!iterator2.hasNext()) continue block0;
                Rule.Phoneme newPhoneme = iterator2.next();
                if (phonemes.containsKey(newPhoneme)) {
                    Rule.Phoneme oldPhoneme = (Rule.Phoneme)phonemes.remove(newPhoneme);
                    Rule.Phoneme mergedPhoneme = oldPhoneme.mergeWithLanguage(newPhoneme.getLanguages());
                    phonemes.put(mergedPhoneme, mergedPhoneme);
                    continue;
                }
                phonemes.put(newPhoneme, newPhoneme);
            }
            break;
        }
        return new PhonemeBuilder(phonemes.keySet());
    }

    public boolean isConcat() {
        return this.concat;
    }

    static final class PhonemeBuilder {
        private final Set<Rule.Phoneme> phonemes;

        public static PhonemeBuilder empty(Languages.LanguageSet languages) {
            return new PhonemeBuilder(new Rule.Phoneme("", languages));
        }

        private PhonemeBuilder(Rule.Phoneme phoneme) {
            this.phonemes = new LinkedHashSet<Rule.Phoneme>();
            this.phonemes.add(phoneme);
        }

        private PhonemeBuilder(Set<Rule.Phoneme> phonemes) {
            this.phonemes = phonemes;
        }

        public void append(CharSequence str) {
            this.phonemes.forEach(ph -> ph.append(str));
        }

        public void apply(Rule.PhonemeExpr phonemeExpr, int maxPhonemes) {
            LinkedHashSet<Rule.Phoneme> newPhonemes = new LinkedHashSet<Rule.Phoneme>(Math.min(this.phonemes.size() * phonemeExpr.size(), maxPhonemes));
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

        public Set<Rule.Phoneme> getPhonemes() {
            return this.phonemes;
        }

        public String makeString() {
            return this.phonemes.stream().map(Rule.Phoneme::getPhonemeText).collect(Collectors.joining("|"));
        }
    }

    private static final class RulesApplication {
        private final Map<String, List<Rule>> finalRules;
        private final CharSequence input;
        private final PhonemeBuilder phonemeBuilder;
        private int i;
        private final int maxPhonemes;
        private boolean found;

        public RulesApplication(Map<String, List<Rule>> finalRules, CharSequence input, PhonemeBuilder phonemeBuilder, int i, int maxPhonemes) {
            Objects.requireNonNull(finalRules, "finalRules");
            this.finalRules = finalRules;
            this.phonemeBuilder = phonemeBuilder;
            this.input = input;
            this.i = i;
            this.maxPhonemes = maxPhonemes;
        }

        public int getI() {
            return this.i;
        }

        public PhonemeBuilder getPhonemeBuilder() {
            return this.phonemeBuilder;
        }

        public RulesApplication invoke() {
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
    }
}
