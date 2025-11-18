/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.internal.Normalizer
 *  org.jsoup.nodes.Attributes
 */
package org.jsoup.parser;

import org.jsoup.internal.Normalizer;
import org.jsoup.nodes.Attributes;

public class ParseSettings {
    public static final ParseSettings htmlDefault = new ParseSettings(false, false);
    private final boolean preserveTagCase;
    public static final ParseSettings preserveCase = new ParseSettings(true, true);
    private final boolean preserveAttributeCase;

    static String normalName(String name) {
        return Normalizer.lowerCase((String)name.trim());
    }

    ParseSettings(ParseSettings copy) {
        this(copy.preserveTagCase, copy.preserveAttributeCase);
    }

    public String normalizeTag(String name) {
        name = name.trim();
        if (this.preserveTagCase) return name;
        name = Normalizer.lowerCase((String)name);
        return name;
    }

    public ParseSettings(boolean tag, boolean attribute) {
        this.preserveTagCase = tag;
        this.preserveAttributeCase = attribute;
    }

    @7\u015aCz Attributes normalizeAttributes(@7\u015aCz Attributes attributes) {
        if (attributes == null) return attributes;
        if (this.preserveAttributeCase) return attributes;
        attributes.normalize();
        return attributes;
    }

    public String normalizeAttribute(String name) {
        name = name.trim();
        if (this.preserveAttributeCase) return name;
        name = Normalizer.lowerCase((String)name);
        return name;
    }

    public boolean preserveAttributeCase() {
        return this.preserveAttributeCase;
    }

    public boolean preserveTagCase() {
        return this.preserveTagCase;
    }
}
