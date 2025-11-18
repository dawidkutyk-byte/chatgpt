/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.internal.Normalizer
 *  org.jsoup.nodes.Element
 *  org.jsoup.select.Evaluator
 */
package org.jsoup.select;

import org.jsoup.internal.Normalizer;
import org.jsoup.nodes.Element;
import org.jsoup.select.Evaluator;

public static final class Evaluator.ContainsData
extends Evaluator {
    private final String searchText;

    public String toString() {
        return String.format(":containsData(%s)", this.searchText);
    }

    public Evaluator.ContainsData(String searchText) {
        this.searchText = Normalizer.lowerCase((String)searchText);
    }

    public boolean matches(Element root, Element element) {
        return Normalizer.lowerCase((String)element.data()).contains(this.searchText);
    }
}
