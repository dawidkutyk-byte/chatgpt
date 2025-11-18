/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.internal.Normalizer
 *  org.jsoup.nodes.Element
 *  org.jsoup.select.Evaluator
 */
package org.jsoup.select;

import java.util.regex.Pattern;
import org.jsoup.internal.Normalizer;
import org.jsoup.nodes.Element;
import org.jsoup.select.Evaluator;

public static final class Evaluator.AttributeWithValueMatching
extends Evaluator {
    final Pattern pattern;
    final String key;

    public boolean matches(Element root, Element element) {
        return element.hasAttr(this.key) && this.pattern.matcher(element.attr(this.key)).find();
    }

    public Evaluator.AttributeWithValueMatching(String key, Pattern pattern) {
        this.key = Normalizer.normalize((String)key);
        this.pattern = pattern;
    }

    public String toString() {
        return String.format("[%s~=%s]", this.key, this.pattern.toString());
    }

    protected int cost() {
        return 8;
    }
}
