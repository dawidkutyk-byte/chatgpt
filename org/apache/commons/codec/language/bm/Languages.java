/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language.bm;

import java.io.InputStream;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.codec.language.bm.NameType;

public class Languages {
    private static final Map<NameType, Languages> LANGUAGES = new EnumMap<NameType, Languages>(NameType.class);
    private final Set<String> languages;
    public static final LanguageSet ANY_LANGUAGE;
    public static final LanguageSet NO_LANGUAGES;
    public static final String ANY = "any";

    private Languages(Set<String> languages) {
        this.languages = languages;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Languages getInstance(String languagesResourceName) {
        HashSet<String> ls = new HashSet<String>();
        InputStream langIS = Languages.class.getClassLoader().getResourceAsStream(languagesResourceName);
        if (langIS == null) {
            throw new IllegalArgumentException("Unable to resolve required resource: " + languagesResourceName);
        }
        Scanner lsScanner = new Scanner(langIS, "UTF-8");
        try {
            boolean inExtendedComment = false;
            while (lsScanner.hasNextLine()) {
                String line = lsScanner.nextLine().trim();
                if (inExtendedComment) {
                    if (!line.endsWith("*/")) continue;
                    inExtendedComment = false;
                    continue;
                }
                if (line.startsWith("/*")) {
                    inExtendedComment = true;
                    continue;
                }
                if (line.length() <= 0) continue;
                ls.add(line);
            }
            return new Languages(Collections.unmodifiableSet(ls));
        }
        finally {
            lsScanner.close();
        }
    }

    private static String langResourceName(NameType nameType) {
        return String.format("org/apache/commons/codec/language/bm/%s_languages.txt", nameType.getName());
    }

    public static Languages getInstance(NameType nameType) {
        return LANGUAGES.get((Object)nameType);
    }

    public Set<String> getLanguages() {
        return this.languages;
    }

    static {
        NameType[] nameTypeArray = NameType.values();
        int n = nameTypeArray.length;
        int n2 = 0;
        while (true) {
            if (n2 >= n) {
                NO_LANGUAGES = new LanguageSet(){

                    @Override
                    public boolean contains(String language) {
                        return false;
                    }

                    @Override
                    public String getAny() {
                        throw new NoSuchElementException("Can't fetch any language from the empty language set.");
                    }

                    @Override
                    public boolean isEmpty() {
                        return true;
                    }

                    @Override
                    public boolean isSingleton() {
                        return false;
                    }

                    @Override
                    public LanguageSet merge(LanguageSet other) {
                        return other;
                    }

                    @Override
                    public LanguageSet restrictTo(LanguageSet other) {
                        return this;
                    }

                    public String toString() {
                        return "NO_LANGUAGES";
                    }
                };
                ANY_LANGUAGE = new LanguageSet(){

                    @Override
                    public boolean contains(String language) {
                        return true;
                    }

                    @Override
                    public String getAny() {
                        throw new NoSuchElementException("Can't fetch any language from the any language set.");
                    }

                    @Override
                    public boolean isEmpty() {
                        return false;
                    }

                    @Override
                    public boolean isSingleton() {
                        return false;
                    }

                    @Override
                    public LanguageSet merge(LanguageSet other) {
                        return other;
                    }

                    @Override
                    public LanguageSet restrictTo(LanguageSet other) {
                        return other;
                    }

                    public String toString() {
                        return "ANY_LANGUAGE";
                    }
                };
                return;
            }
            NameType s = nameTypeArray[n2];
            LANGUAGES.put(s, Languages.getInstance(Languages.langResourceName(s)));
            ++n2;
        }
    }

    public static abstract class LanguageSet {
        public static LanguageSet from(Set<String> langs) {
            return langs.isEmpty() ? NO_LANGUAGES : new SomeLanguages(langs);
        }

        public abstract boolean contains(String var1);

        public abstract String getAny();

        public abstract boolean isEmpty();

        public abstract boolean isSingleton();

        abstract LanguageSet merge(LanguageSet var1);

        public abstract LanguageSet restrictTo(LanguageSet var1);
    }

    public static final class SomeLanguages
    extends LanguageSet {
        private final Set<String> languages;

        private SomeLanguages(Set<String> languages) {
            this.languages = Collections.unmodifiableSet(languages);
        }

        @Override
        public boolean contains(String language) {
            return this.languages.contains(language);
        }

        @Override
        public String getAny() {
            return this.languages.iterator().next();
        }

        public Set<String> getLanguages() {
            return this.languages;
        }

        @Override
        public boolean isEmpty() {
            return this.languages.isEmpty();
        }

        @Override
        public boolean isSingleton() {
            return this.languages.size() == 1;
        }

        @Override
        public LanguageSet merge(LanguageSet other) {
            if (other == NO_LANGUAGES) {
                return this;
            }
            if (other == ANY_LANGUAGE) {
                return other;
            }
            SomeLanguages someLanguages = (SomeLanguages)other;
            HashSet<String> set = new HashSet<String>(this.languages);
            set.addAll(someLanguages.languages);
            return SomeLanguages.from(set);
        }

        @Override
        public LanguageSet restrictTo(LanguageSet other) {
            if (other == NO_LANGUAGES) {
                return other;
            }
            if (other == ANY_LANGUAGE) {
                return this;
            }
            SomeLanguages someLanguages = (SomeLanguages)other;
            return SomeLanguages.from(this.languages.stream().filter(lang -> someLanguages.languages.contains(lang)).collect(Collectors.toSet()));
        }

        public String toString() {
            return "Languages(" + this.languages.toString() + ")";
        }
    }
}
