/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Element
 *  org.jsoup.select.Evaluator
 */
package org.jsoup.select;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.nodes.Element;
import org.jsoup.select.Evaluator;

public static final class Evaluator.MatchesWholeText
extends Evaluator {
    private final Pattern pattern;

    public boolean matches(Element root, Element element) {
        Matcher m = this.pattern.matcher(element.wholeText());
        return m.find();
    }

    protected int cost() {
        return 8;
    }

    public Evaluator.MatchesWholeText(Pattern pattern) {
        this.pattern = pattern;
    }

    public String toString() {
        return String.format(":matchesWholeText(%s)", this.pattern);
    }
}
