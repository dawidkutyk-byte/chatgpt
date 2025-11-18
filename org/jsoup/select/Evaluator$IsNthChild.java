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

public static final class Evaluator.IsNthChild
extends Evaluator.CssNthEvaluator {
    protected String getPseudoClass() {
        return "nth-child";
    }

    public Evaluator.IsNthChild(int a, int b) {
        super(a, b);
    }

    protected int calculatePosition(Element root, Element element) {
        return element.elementSiblingIndex() + 1;
    }
}
