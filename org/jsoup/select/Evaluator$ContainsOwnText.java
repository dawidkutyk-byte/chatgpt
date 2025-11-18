/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.internal.Normalizer
 *  org.jsoup.internal.StringUtil
 *  org.jsoup.nodes.Element
 *  org.jsoup.select.Evaluator
 */
package org.jsoup.select;

import org.jsoup.internal.Normalizer;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Evaluator;

public static final class Evaluator.ContainsOwnText
extends Evaluator {
    private final String searchText;

    public boolean matches(Element root, Element element) {
        return Normalizer.lowerCase((String)element.ownText()).contains(this.searchText);
    }

    public Evaluator.ContainsOwnText(String searchText) {
        this.searchText = Normalizer.lowerCase((String)StringUtil.normaliseWhitespace((String)searchText));
    }

    public String toString() {
        return String.format(":containsOwn(%s)", this.searchText);
    }
}
