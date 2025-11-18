/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Element
 *  org.jsoup.select.Evaluator
 */
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.jsoup.select.Evaluator;

public static final class Evaluator.ContainsWholeText
extends Evaluator {
    private final String searchText;

    public Evaluator.ContainsWholeText(String searchText) {
        this.searchText = searchText;
    }

    public String toString() {
        return String.format(":containsWholeText(%s)", this.searchText);
    }

    public boolean matches(Element root, Element element) {
        return element.wholeText().contains(this.searchText);
    }

    protected int cost() {
        return 10;
    }
}
