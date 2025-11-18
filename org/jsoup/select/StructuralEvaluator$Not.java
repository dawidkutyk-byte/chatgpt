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

static class StructuralEvaluator.Not
extends StructuralEvaluator {
    public String toString() {
        return String.format(":not(%s)", this.evaluator);
    }

    public boolean matches(Element root, Element element) {
        return !this.memoMatches(root, element);
    }

    protected int cost() {
        return 2 + this.evaluator.cost();
    }

    public StructuralEvaluator.Not(Evaluator evaluator) {
        super(evaluator);
    }
}
