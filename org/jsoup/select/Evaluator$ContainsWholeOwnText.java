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

public static final class Evaluator.ContainsWholeOwnText
extends Evaluator {
    private final String searchText;

    public Evaluator.ContainsWholeOwnText(String searchText) {
        this.searchText = searchText;
    }

    public boolean matches(Element root, Element element) {
        return element.wholeOwnText().contains(this.searchText);
    }

    public String toString() {
        return String.format(":containsWholeOwnText(%s)", this.searchText);
    }
}
