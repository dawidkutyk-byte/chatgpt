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

static class StructuralEvaluator.Parent
extends StructuralEvaluator {
    protected int cost() {
        return 2 * this.evaluator.cost();
    }

    public String toString() {
        return String.format("%s ", this.evaluator);
    }

    public boolean matches(Element root, Element element) {
        if (root == element) {
            return false;
        }
        Element parent = element.parent();
        while (parent != null) {
            if (this.memoMatches(root, parent)) {
                return true;
            }
            if (parent == root) {
                return false;
            }
            parent = parent.parent();
        }
        return false;
    }

    public StructuralEvaluator.Parent(Evaluator evaluator) {
        super(evaluator);
    }
}
