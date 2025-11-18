/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language.bm;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.language.bm.Languages;
import org.apache.commons.codec.language.bm.NameType;
import org.apache.commons.codec.language.bm.RuleType;

public class Rule {
    public static final RPattern ALL_STRINGS_RMATCHER = new Rule(){
        private final int myLine;
        private final String loc;
        final /* synthetic */ int val$cLine;
        final /* synthetic */ String val$location;
        final /* synthetic */ String val$pat;
        final /* synthetic */ String val$lCon;
        final /* synthetic */ String val$rCon;
        {
            this.val$cLine = n;
            this.val$location = string;
            this.val$pat = string2;
            this.val$lCon = string3;
            this.val$rCon = string4;
            super(pattern, lContext, rContext, phoneme);
            this.myLine = this.val$cLine;
            this.loc = this.val$location;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Rule");
            sb.append("{line=").append(this.myLine);
            sb.append(", loc='").append(this.loc).append('\'');
            sb.append(", pat='").append(this.val$pat).append('\'');
            sb.append(", lcon='").append(this.val$lCon).append('\'');
            sb.append(", rcon='").append(this.val$rCon).append('\'');
            sb.append('}');
            return sb.toString();
        }
    };
    private static final String DOUBLE_QUOTE = "\"";
    private final PhonemeExpr phoneme;
    private final String pattern;
    private final RPattern lContext;
    public static final String ALL = "ALL";
    private static final String HASH_INCLUDE = "#include";
    private static final Map<NameType, Map<RuleType, Map<String, Map<String, List<Rule>>>>> RULES = new EnumMap<NameType, Map<RuleType, Map<String, Map<String, List<Rule>>>>>(NameType.class);
    private final RPattern rContext;

    public static List<Rule> getInstance(NameType nameType, RuleType rt, Languages.LanguageSet langs) {
        Map<String, List<Rule>> ruleMap = Rule.getInstanceMap(nameType, rt, langs);
        ArrayList<Rule> allRules = new ArrayList<Rule>();
        Iterator<List<Rule>> iterator = ruleMap.values().iterator();
        while (iterator.hasNext()) {
            List<Rule> rules = iterator.next();
            allRules.addAll(rules);
        }
        return allRules;
    }

    static /* synthetic */ boolean access$200(CharSequence x0, CharSequence x1) {
        return Rule.endsWith(x0, x1);
    }

    private static Scanner createScanner(NameType nameType, RuleType rt, String lang) {
        String resName = Rule.createResourceName(nameType, rt, lang);
        InputStream rulesIS = Languages.class.getClassLoader().getResourceAsStream(resName);
        if (rulesIS != null) return new Scanner(rulesIS, "UTF-8");
        throw new IllegalArgumentException("Unable to load resource: " + resName);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static {
        NameType[] nameTypeArray = NameType.values();
        int n = nameTypeArray.length;
        int n2 = 0;
        block8: while (n2 < n) {
            NameType s = nameTypeArray[n2];
            EnumMap rts = new EnumMap(RuleType.class);
            RuleType[] ruleTypeArray = RuleType.values();
            int n3 = ruleTypeArray.length;
            int n4 = 0;
            while (true) {
                Iterator<String> iterator;
                HashMap<String, Map<String, List<Rule>>> rs;
                RuleType rt;
                if (n4 < n3) {
                    rt = ruleTypeArray[n4];
                    rs = new HashMap<String, Map<String, List<Rule>>>();
                    Languages ls = Languages.getInstance(s);
                    iterator = ls.getLanguages().iterator();
                } else {
                    RULES.put(s, Collections.unmodifiableMap(rts));
                    ++n2;
                    continue block8;
                }
                while (iterator.hasNext()) {
                    String l = iterator.next();
                    Scanner scanner = Rule.createScanner(s, rt, l);
                    try {
                        rs.put(l, Rule.parseRules(scanner, Rule.createResourceName(s, rt, l)));
                    }
                    catch (IllegalStateException e) {
                        throw new IllegalStateException("Problem processing " + Rule.createResourceName(s, rt, l), e);
                    }
                    finally {
                        scanner.close();
                    }
                }
                if (!rt.equals((Object)RuleType.RULES)) {
                    Scanner scanner = Rule.createScanner(s, rt, "common");
                    try {
                        rs.put("common", Rule.parseRules(scanner, Rule.createResourceName(s, rt, "common")));
                    }
                    finally {
                        scanner.close();
                    }
                }
                rts.put(rt, Collections.unmodifiableMap(rs));
                ++n4;
            }
            break;
        }
        return;
    }

    public RPattern getRContext() {
        return this.rContext;
    }

    private static PhonemeExpr parsePhonemeExpr(String ph) {
        if (!ph.startsWith("(")) return Rule.parsePhoneme(ph);
        if (!ph.endsWith(")")) {
            throw new IllegalArgumentException("Phoneme starts with '(' so must end with ')'");
        }
        ArrayList<Phoneme> phs = new ArrayList<Phoneme>();
        String body = ph.substring(1, ph.length() - 1);
        for (String part : body.split("[|]")) {
            phs.add(Rule.parsePhoneme(part));
        }
        if (!body.startsWith("|")) {
            if (!body.endsWith("|")) return new PhonemeList(phs);
        }
        phs.add(new Phoneme("", Languages.ANY_LANGUAGE));
        return new PhonemeList(phs);
    }

    static /* synthetic */ boolean access$300(CharSequence x0, char x1) {
        return Rule.contains(x0, x1);
    }

    public PhonemeExpr getPhoneme() {
        return this.phoneme;
    }

    private static RPattern pattern(String regex) {
        boolean shouldMatch;
        boolean endsWith;
        boolean startsWith = regex.startsWith("^");
        String content = regex.substring(startsWith ? 1 : 0, (endsWith = regex.endsWith("$")) ? regex.length() - 1 : regex.length());
        boolean boxes = content.contains("[");
        if (!boxes) {
            if (startsWith && endsWith) {
                if (content.length() != 0) return new /* Unavailable Anonymous Inner Class!! */;
                return new /* Unavailable Anonymous Inner Class!! */;
            }
            if ((startsWith || endsWith) && content.length() == 0) {
                return ALL_STRINGS_RMATCHER;
            }
            if (startsWith) {
                return new /* Unavailable Anonymous Inner Class!! */;
            }
            if (!endsWith) return new /* Unavailable Anonymous Inner Class!! */;
            return new /* Unavailable Anonymous Inner Class!! */;
        }
        boolean startsWithBox = content.startsWith("[");
        boolean endsWithBox = content.endsWith("]");
        if (!startsWithBox) return new /* Unavailable Anonymous Inner Class!! */;
        if (!endsWithBox) return new /* Unavailable Anonymous Inner Class!! */;
        String boxContent = content.substring(1, content.length() - 1);
        if (boxContent.contains("[")) return new /* Unavailable Anonymous Inner Class!! */;
        boolean negate = boxContent.startsWith("^");
        if (negate) {
            boxContent = boxContent.substring(1);
        }
        String bContent = boxContent;
        boolean bl = shouldMatch = !negate;
        if (startsWith && endsWith) {
            return new /* Unavailable Anonymous Inner Class!! */;
        }
        if (startsWith) {
            return new /* Unavailable Anonymous Inner Class!! */;
        }
        if (!endsWith) return new /* Unavailable Anonymous Inner Class!! */;
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    private static String stripQuotes(String str) {
        if (str.startsWith(DOUBLE_QUOTE)) {
            str = str.substring(1);
        }
        if (!str.endsWith(DOUBLE_QUOTE)) return str;
        str = str.substring(0, str.length() - 1);
        return str;
    }

    private static String createResourceName(NameType nameType, RuleType rt, String lang) {
        return String.format("org/apache/commons/codec/language/bm/%s_%s_%s.txt", nameType.getName(), rt.getName(), lang);
    }

    public RPattern getLContext() {
        return this.lContext;
    }

    public static Map<String, List<Rule>> getInstanceMap(NameType nameType, RuleType rt, Languages.LanguageSet langs) {
        return langs.isSingleton() ? Rule.getInstanceMap(nameType, rt, langs.getAny()) : Rule.getInstanceMap(nameType, rt, "any");
    }

    public boolean patternAndContextMatches(CharSequence input, int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Can not match pattern at negative indexes");
        }
        int patternLength = this.pattern.length();
        int ipl = i + patternLength;
        if (ipl > input.length()) {
            return false;
        }
        if (!input.subSequence(i, ipl).equals(this.pattern)) {
            return false;
        }
        if (this.rContext.isMatch(input.subSequence(ipl, input.length()))) return this.lContext.isMatch(input.subSequence(0, i));
        return false;
    }

    private static Phoneme parsePhoneme(String ph) {
        int open = ph.indexOf("[");
        if (open < 0) return new Phoneme(ph, Languages.ANY_LANGUAGE);
        if (!ph.endsWith("]")) {
            throw new IllegalArgumentException("Phoneme expression contains a '[' but does not end in ']'");
        }
        String before = ph.substring(0, open);
        String in = ph.substring(open + 1, ph.length() - 1);
        HashSet<String> langs = new HashSet<String>(Arrays.asList(in.split("[+]")));
        return new Phoneme(before, Languages.LanguageSet.from(langs));
    }

    private static boolean startsWith(CharSequence input, CharSequence prefix) {
        if (prefix.length() > input.length()) {
            return false;
        }
        int i = 0;
        while (i < prefix.length()) {
            if (input.charAt(i) != prefix.charAt(i)) {
                return false;
            }
            ++i;
        }
        return true;
    }

    public String getPattern() {
        return this.pattern;
    }

    static /* synthetic */ boolean access$100(CharSequence x0, CharSequence x1) {
        return Rule.startsWith(x0, x1);
    }

    public Rule(String pattern, String lContext, String rContext, PhonemeExpr phoneme) {
        this.pattern = pattern;
        this.lContext = Rule.pattern(lContext + "$");
        this.rContext = Rule.pattern("^" + rContext);
        this.phoneme = phoneme;
    }

    private static boolean endsWith(CharSequence input, CharSequence suffix) {
        if (suffix.length() > input.length()) {
            return false;
        }
        int i = input.length() - 1;
        int j = suffix.length() - 1;
        while (j >= 0) {
            if (input.charAt(i) != suffix.charAt(j)) {
                return false;
            }
            --i;
            --j;
        }
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static Map<String, List<Rule>> parseRules(Scanner scanner, String location) {
        HashMap<String, List<Rule>> lines = new HashMap<String, List<Rule>>();
        int currentLine = 0;
        boolean inMultilineComment = false;
        while (scanner.hasNextLine()) {
            String rawLine;
            ++currentLine;
            String line = rawLine = scanner.nextLine();
            if (inMultilineComment) {
                if (!line.endsWith("*/")) continue;
                inMultilineComment = false;
                continue;
            }
            if (line.startsWith("/*")) {
                inMultilineComment = true;
                continue;
            }
            int cmtI = line.indexOf("//");
            if (cmtI >= 0) {
                line = line.substring(0, cmtI);
            }
            if ((line = line.trim()).length() == 0) continue;
            if (line.startsWith(HASH_INCLUDE)) {
                String incl = line.substring(HASH_INCLUDE.length()).trim();
                if (incl.contains(" ")) {
                    throw new IllegalArgumentException("Malformed import statement '" + rawLine + "' in " + location);
                }
                Scanner hashIncludeScanner = Rule.createScanner(incl);
                try {
                    lines.putAll(Rule.parseRules(hashIncludeScanner, location + "->" + incl));
                    continue;
                }
                finally {
                    hashIncludeScanner.close();
                    continue;
                }
            }
            String[] parts = line.split("\\s+");
            if (parts.length != 4) {
                throw new IllegalArgumentException("Malformed rule statement split into " + parts.length + " parts: " + rawLine + " in " + location);
            }
            try {
                String pat = Rule.stripQuotes(parts[0]);
                String lCon = Rule.stripQuotes(parts[1]);
                String rCon = Rule.stripQuotes(parts[2]);
                PhonemeExpr ph = Rule.parsePhonemeExpr(Rule.stripQuotes(parts[3]));
                int cLine = currentLine;
                RPattern r = new RPattern(pat, lCon, rCon, ph, cLine, location, pat, lCon, rCon){
                    final Pattern pattern;
                    final /* synthetic */ String val$regex;
                    {
                        this.val$regex = string;
                        this.pattern = Pattern.compile(this.val$regex);
                    }

                    @Override
                    public boolean isMatch(CharSequence input) {
                        Matcher matcher = this.pattern.matcher(input);
                        return matcher.find();
                    }
                };
                String patternKey = ((Rule)((Object)r)).pattern.substring(0, 1);
                ArrayList<2> rules = (ArrayList<2>)lines.get(patternKey);
                if (rules == null) {
                    rules = new ArrayList<2>();
                    lines.put(patternKey, rules);
                }
                rules.add(r);
            }
            catch (IllegalArgumentException e) {
                throw new IllegalStateException("Problem parsing line '" + currentLine + "' in " + location, e);
            }
        }
        return lines;
    }

    private static Scanner createScanner(String lang) {
        String resName = String.format("org/apache/commons/codec/language/bm/%s.txt", lang);
        InputStream rulesIS = Languages.class.getClassLoader().getResourceAsStream(resName);
        if (rulesIS != null) return new Scanner(rulesIS, "UTF-8");
        throw new IllegalArgumentException("Unable to load resource: " + resName);
    }

    public static List<Rule> getInstance(NameType nameType, RuleType rt, String lang) {
        return Rule.getInstance(nameType, rt, Languages.LanguageSet.from(new HashSet<String>(Arrays.asList(lang))));
    }

    private static boolean contains(CharSequence chars, char input) {
        int i = 0;
        while (i < chars.length()) {
            if (chars.charAt(i) == input) {
                return true;
            }
            ++i;
        }
        return false;
    }

    public static Map<String, List<Rule>> getInstanceMap(NameType nameType, RuleType rt, String lang) {
        Map<String, List<Rule>> rules = RULES.get((Object)nameType).get((Object)rt).get(lang);
        if (rules != null) return rules;
        throw new IllegalArgumentException(String.format("No rules found for %s, %s, %s.", nameType.getName(), rt.getName(), lang));
    }

    public static interface RPattern {
        public boolean isMatch(CharSequence var1);
    }

    public static interface PhonemeExpr {
        public Iterable<Phoneme> getPhonemes();

        default public int size() {
            return (int)Math.min(this.getPhonemes().spliterator().getExactSizeIfKnown(), Integer.MAX_VALUE);
        }
    }

    public static final class PhonemeList
    implements PhonemeExpr {
        private final List<Phoneme> phonemeList;

        public PhonemeList(List<Phoneme> phonemes) {
            this.phonemeList = phonemes;
        }

        public List<Phoneme> getPhonemes() {
            return this.phonemeList;
        }

        @Override
        public int size() {
            return this.phonemeList.size();
        }
    }

    public static final class Phoneme
    implements PhonemeExpr {
        public static final Comparator<Phoneme> COMPARATOR = (o1, o2) -> {
            int o1Length = o1.phonemeText.length();
            int o2Length = o2.phonemeText.length();
            int i = 0;
            while (true) {
                if (i >= o1Length) {
                    if (o1Length >= o2Length) return 0;
                    return -1;
                }
                if (i >= o2Length) {
                    return 1;
                }
                int c = o1.phonemeText.charAt(i) - o2.phonemeText.charAt(i);
                if (c != 0) {
                    return c;
                }
                ++i;
            }
        };
        private final StringBuilder phonemeText;
        private final Languages.LanguageSet languages;

        public Phoneme(CharSequence phonemeText, Languages.LanguageSet languages) {
            this.phonemeText = new StringBuilder(phonemeText);
            this.languages = languages;
        }

        public Phoneme(Phoneme phonemeLeft, Phoneme phonemeRight) {
            this(phonemeLeft.phonemeText, phonemeLeft.languages);
            this.phonemeText.append((CharSequence)phonemeRight.phonemeText);
        }

        public Phoneme(Phoneme phonemeLeft, Phoneme phonemeRight, Languages.LanguageSet languages) {
            this(phonemeLeft.phonemeText, languages);
            this.phonemeText.append((CharSequence)phonemeRight.phonemeText);
        }

        public Phoneme append(CharSequence str) {
            this.phonemeText.append(str);
            return this;
        }

        public Languages.LanguageSet getLanguages() {
            return this.languages;
        }

        @Override
        public Iterable<Phoneme> getPhonemes() {
            return Collections.singleton(this);
        }

        public CharSequence getPhonemeText() {
            return this.phonemeText;
        }

        @Deprecated
        public Phoneme join(Phoneme right) {
            return new Phoneme(this.phonemeText.toString() + right.phonemeText.toString(), this.languages.restrictTo(right.languages));
        }

        public Phoneme mergeWithLanguage(Languages.LanguageSet lang) {
            return new Phoneme(this.phonemeText.toString(), this.languages.merge(lang));
        }

        @Override
        public int size() {
            return 1;
        }

        public String toString() {
            return this.phonemeText.toString() + "[" + this.languages + "]";
        }
    }
}
