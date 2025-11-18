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

static class StructuralEvaluator.PreviousSibling
extends StructuralEvaluator {
    public String toString() {
        return String.format("%s ~ ", this.evaluator);
    }

    public StructuralEvaluator.PreviousSibling(Evaluator evaluator) {
        super(evaluator);
    }

    public boolean matches(Element root, Element element) {
        if (root == element) {
            return false;
        }
        Element sibling = element.firstElementSibling();
        while (sibling != null) {
            if (sibling == element) {
                return false;
            }
            if (this.memoMatches(root, sibling)) {
                return true;
            }
            sibling = sibling.nextElementSibling();
        }
        return false;
    }

    protected int cost() {
        return 3 * this.evaluator.cost();
    }
}
