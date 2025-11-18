/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

public class DaitchMokotoffSoundex
implements StringEncoder {
    private static final Map<Character, Character> FOLDINGS;
    private static final String DOUBLE_QUOTE = "\"";
    private static final String RESOURCE_FILE = "org/apache/commons/codec/language/dmrules.txt";
    private static final String COMMENT = "//";
    private static final String MULTILINE_COMMENT_START = "/*";
    private static final Map<Character, List<Rule>> RULES;
    private static final String MULTILINE_COMMENT_END = "*/";
    private static final int MAX_LENGTH = 6;
    private final boolean folding;

    static {
        RULES = new HashMap<Character, List<Rule>>();
        FOLDINGS = new HashMap<Character, Character>();
        InputStream rulesIS = DaitchMokotoffSoundex.class.getClassLoader().getResourceAsStream(RESOURCE_FILE);
        if (rulesIS == null) {
            throw new IllegalArgumentException("Unable to load resource: org/apache/commons/codec/language/dmrules.txt");
        }
        Scanner scanner = new Scanner(rulesIS, "UTF-8");
        try {
            DaitchMokotoffSoundex.parseRules(scanner, RESOURCE_FILE, RULES, FOLDINGS);
        }
        finally {
            scanner.close();
        }
        Iterator<Map.Entry<Character, List<Rule>>> iterator = RULES.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Character, List<Rule>> rule = iterator.next();
            List<Rule> ruleList = rule.getValue();
            Collections.sort(ruleList, new Object(){});
        }
    }

    /*
     * Exception decompiling
     */
    private String[] soundex(String source, boolean branching) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [2[UNCONDITIONALDOLOOP]], but top level block is 8[SIMPLE_IF_TAKEN]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doClass(Driver.java:84)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:78)
         *     at the.bytecode.club.bytecodeviewer.decompilers.impl.CFRDecompiler.decompile(CFRDecompiler.java:89)
         *     at the.bytecode.club.bytecodeviewer.decompilers.impl.CFRDecompiler.decompileToZip(CFRDecompiler.java:133)
         *     at the.bytecode.club.bytecodeviewer.resources.ResourceDecompiling.decompileSaveAll(ResourceDecompiling.java:261)
         *     at the.bytecode.club.bytecodeviewer.resources.ResourceDecompiling.lambda$decompileSaveAll$0(ResourceDecompiling.java:111)
         *     at java.base/java.lang.Thread.run(Thread.java:1575)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    @Override
    public String encode(String source) {
        if (source != null) return this.soundex(source, false)[0];
        return null;
    }

    public String soundex(String source) {
        String[] branches = this.soundex(source, true);
        StringBuilder sb = new StringBuilder();
        int index = 0;
        String[] stringArray = branches;
        int n = stringArray.length;
        int n2 = 0;
        while (n2 < n) {
            String branch = stringArray[n2];
            sb.append(branch);
            if (++index < branches.length) {
                sb.append('|');
            }
            ++n2;
        }
        return sb.toString();
    }

    private static void parseRules(Scanner scanner, String location, Map<Character, List<Rule>> ruleMapping, Map<Character, Character> asciiFoldings) {
        int currentLine = 0;
        boolean inMultilineComment = false;
        while (scanner.hasNextLine()) {
            String[] parts;
            String rawLine;
            ++currentLine;
            String line = rawLine = scanner.nextLine();
            if (inMultilineComment) {
                if (!line.endsWith(MULTILINE_COMMENT_END)) continue;
                inMultilineComment = false;
                continue;
            }
            if (line.startsWith(MULTILINE_COMMENT_START)) {
                inMultilineComment = true;
                continue;
            }
            int cmtI = line.indexOf(COMMENT);
            if (cmtI >= 0) {
                line = line.substring(0, cmtI);
            }
            if ((line = line.trim()).length() == 0) continue;
            if (line.contains("=")) {
                parts = line.split("=");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Malformed folding statement split into " + parts.length + " parts: " + rawLine + " in " + location);
                }
                String leftCharacter = parts[0];
                String rightCharacter = parts[1];
                if (leftCharacter.length() != 1) throw new IllegalArgumentException("Malformed folding statement - patterns are not single characters: " + rawLine + " in " + location);
                if (rightCharacter.length() != 1) {
                    throw new IllegalArgumentException("Malformed folding statement - patterns are not single characters: " + rawLine + " in " + location);
                }
                asciiFoldings.put(Character.valueOf(leftCharacter.charAt(0)), Character.valueOf(rightCharacter.charAt(0)));
                continue;
            }
            parts = line.split("\\s+");
            if (parts.length != 4) {
                throw new IllegalArgumentException("Malformed rule statement split into " + parts.length + " parts: " + rawLine + " in " + location);
            }
            try {
                String pattern = DaitchMokotoffSoundex.stripQuotes(parts[0]);
                String replacement1 = DaitchMokotoffSoundex.stripQuotes(parts[1]);
                String replacement2 = DaitchMokotoffSoundex.stripQuotes(parts[2]);
                String replacement3 = DaitchMokotoffSoundex.stripQuotes(parts[3]);
                Rule r = new Rule(pattern, replacement1, replacement2, replacement3);
                char patternKey = r.pattern.charAt(0);
                List<Rule> rules = ruleMapping.get(Character.valueOf(patternKey));
                if (rules == null) {
                    rules = new ArrayList<Rule>();
                    ruleMapping.put(Character.valueOf(patternKey), rules);
                }
                rules.add(r);
            }
            catch (IllegalArgumentException e) {
                throw new IllegalStateException("Problem parsing line '" + currentLine + "' in " + location, e);
            }
        }
    }

    private static String stripQuotes(String str) {
        if (str.startsWith(DOUBLE_QUOTE)) {
            str = str.substring(1);
        }
        if (!str.endsWith(DOUBLE_QUOTE)) return str;
        str = str.substring(0, str.length() - 1);
        return str;
    }

    public DaitchMokotoffSoundex() {
        this(true);
    }

    public DaitchMokotoffSoundex(boolean folding) {
        this.folding = folding;
    }

    @Override
    public Object encode(Object obj) throws EncoderException {
        if (obj instanceof String) return this.encode((String)obj);
        throw new EncoderException("Parameter supplied to DaitchMokotoffSoundex encode is not of type java.lang.String");
    }

    private String cleanup(String input) {
        StringBuilder sb = new StringBuilder();
        char[] cArray = input.toCharArray();
        int n = cArray.length;
        int n2 = 0;
        while (n2 < n) {
            char ch = cArray[n2];
            if (!Character.isWhitespace(ch)) {
                ch = Character.toLowerCase(ch);
                if (this.folding && FOLDINGS.containsKey(Character.valueOf(ch))) {
                    ch = FOLDINGS.get(Character.valueOf(ch)).charValue();
                }
                sb.append(ch);
            }
            ++n2;
        }
        return sb.toString();
    }

    private static final class Branch {
        private final StringBuilder builder = new StringBuilder();
        private String cachedString = null;
        private String lastReplacement = null;

        private Branch() {
        }

        public Branch createBranch() {
            Branch branch = new Branch();
            branch.builder.append(this.toString());
            branch.lastReplacement = this.lastReplacement;
            return branch;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other instanceof Branch) return this.toString().equals(((Branch)other).toString());
            return false;
        }

        public void finish() {
            while (this.builder.length() < 6) {
                this.builder.append('0');
                this.cachedString = null;
            }
        }

        public int hashCode() {
            return this.toString().hashCode();
        }

        public void processNextReplacement(String replacement, boolean forceAppend) {
            boolean append;
            boolean bl = append = this.lastReplacement == null || !this.lastReplacement.endsWith(replacement) || forceAppend;
            if (append && this.builder.length() < 6) {
                this.builder.append(replacement);
                if (this.builder.length() > 6) {
                    this.builder.delete(6, this.builder.length());
                }
                this.cachedString = null;
            }
            this.lastReplacement = replacement;
        }

        public String toString() {
            if (this.cachedString != null) return this.cachedString;
            this.cachedString = this.builder.toString();
            return this.cachedString;
        }
    }

    private static final class Rule {
        private final String pattern;
        private final String[] replacementAtStart;
        private final String[] replacementBeforeVowel;
        private final String[] replacementDefault;

        protected Rule(String pattern, String replacementAtStart, String replacementBeforeVowel, String replacementDefault) {
            this.pattern = pattern;
            this.replacementAtStart = replacementAtStart.split("\\|");
            this.replacementBeforeVowel = replacementBeforeVowel.split("\\|");
            this.replacementDefault = replacementDefault.split("\\|");
        }

        public int getPatternLength() {
            return this.pattern.length();
        }

        public String[] getReplacements(String context, boolean atStart) {
            if (atStart) {
                return this.replacementAtStart;
            }
            int nextIndex = this.getPatternLength();
            boolean nextCharIsVowel = nextIndex < context.length() && this.isVowel(context.charAt(nextIndex));
            if (!nextCharIsVowel) return this.replacementDefault;
            return this.replacementBeforeVowel;
        }

        private boolean isVowel(char ch) {
            return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
        }

        public boolean matches(String context) {
            return context.startsWith(this.pattern);
        }

        public String toString() {
            return String.format("%s=(%s,%s,%s)", this.pattern, Arrays.asList(this.replacementAtStart), Arrays.asList(this.replacementBeforeVowel), Arrays.asList(this.replacementDefault));
        }
    }
}
