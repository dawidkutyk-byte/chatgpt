/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Element
 *  org.jsoup.select.Evaluator
 *  org.jsoup.select.StructuralEvaluator
 */
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.jsoup.select.Evaluator;
import org.jsoup.select.StructuralEvaluator;

static class StructuralEvaluator.ImmediatePreviousSibling
extends StructuralEvaluator {
    public String toString() {
        return String.format("%s + ", this.evaluator);
    }

    public StructuralEvaluator.ImmediatePreviousSibling(Evaluator evaluator) {
        super(evaluator);
    }

    protected int cost() {
        return 2 + this.evaluator.cost();
    }

    public boolean matches(Element root, Element element) {
        Element prev = element.previousElementSibling();
        if (root != element) return prev != null && this.memoMatches(root, prev);
        return false;
    }
}
