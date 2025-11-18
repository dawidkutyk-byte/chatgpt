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

@Deprecated
static class StructuralEvaluator.ImmediateParent
extends StructuralEvaluator {
    public String toString() {
        return String.format("%s > ", this.evaluator);
    }

    public boolean matches(Element root, Element element) {
        Element parent = element.parent();
        if (root != element) return parent != null && this.memoMatches(root, parent);
        return false;
    }

    protected int cost() {
        return 1 + this.evaluator.cost();
    }

    public StructuralEvaluator.ImmediateParent(Evaluator evaluator) {
        super(evaluator);
    }
}
