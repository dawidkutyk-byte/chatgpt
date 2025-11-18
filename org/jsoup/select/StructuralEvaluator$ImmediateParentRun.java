/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.internal.StringUtil
 *  org.jsoup.nodes.Element
 *  org.jsoup.select.Evaluator
 */
package org.jsoup.select;

import java.util.ArrayList;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Evaluator;

static class StructuralEvaluator.ImmediateParentRun
extends Evaluator {
    int cost = 2;
    final ArrayList<Evaluator> evaluators = new ArrayList();

    public String toString() {
        return StringUtil.join(this.evaluators, (String)" > ");
    }

    void add(Evaluator evaluator) {
        this.evaluators.add(evaluator);
        this.cost += evaluator.cost();
    }

    protected int cost() {
        return this.cost;
    }

    public StructuralEvaluator.ImmediateParentRun(Evaluator evaluator) {
        this.evaluators.add(evaluator);
        this.cost += evaluator.cost();
    }

    public boolean matches(Element root, Element element) {
        if (element == root) {
            return false;
        }
        int i = this.evaluators.size() - 1;
        while (i >= 0) {
            if (element == null) {
                return false;
            }
            Evaluator eval = this.evaluators.get(i);
            if (!eval.matches(root, element)) {
                return false;
            }
            element = element.parent();
            --i;
        }
        return true;
    }
}
