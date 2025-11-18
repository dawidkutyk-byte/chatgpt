/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Element
 *  org.jsoup.select.Evaluator$CssNthEvaluator
 */
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.jsoup.select.Evaluator;

public static class Evaluator.IsNthLastOfType
extends Evaluator.CssNthEvaluator {
    protected int calculatePosition(Element root, Element element) {
        Element parent = element.parent();
        if (parent == null) {
            return 0;
        }
        int pos = 0;
        Element next = element;
        while (next != null) {
            if (next.normalName().equals(element.normalName())) {
                ++pos;
            }
            next = next.nextElementSibling();
        }
        return pos;
    }

    public Evaluator.IsNthLastOfType(int a, int b) {
        super(a, b);
    }

    protected String getPseudoClass() {
        return "nth-last-of-type";
    }
}
