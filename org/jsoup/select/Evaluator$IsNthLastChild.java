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

public static final class Evaluator.IsNthLastChild
extends Evaluator.CssNthEvaluator {
    public Evaluator.IsNthLastChild(int a, int b) {
        super(a, b);
    }

    protected int calculatePosition(Element root, Element element) {
        if (element.parent() != null) return element.parent().childrenSize() - element.elementSiblingIndex();
        return 0;
    }

    protected String getPseudoClass() {
        return "nth-last-child";
    }
}
