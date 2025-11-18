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

static class StructuralEvaluator.Is
extends StructuralEvaluator {
    protected int cost() {
        return 2 + this.evaluator.cost();
    }

    public String toString() {
        return String.format(":is(%s)", this.evaluator);
    }

    public boolean matches(Element root, Element element) {
        return this.evaluator.matches(root, element);
    }

    public StructuralEvaluator.Is(Evaluator evaluator) {
        super(evaluator);
    }
}
