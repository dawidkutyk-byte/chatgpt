/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.select.Evaluator
 */
package org.jsoup.select;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import org.jsoup.select.Evaluator;

public abstract class CombiningEvaluator
extends Evaluator {
    final ArrayList<Evaluator> evaluators = new ArrayList();
    int cost = 0;
    private static final Comparator<Evaluator> costComparator = (o1, o2) -> o1.cost() - o2.cost();
    final ArrayList<Evaluator> sortedEvaluators = new ArrayList();
    int num = 0;

    @7\u015aCz Evaluator rightMostEvaluator() {
        return this.num > 0 ? this.evaluators.get(this.num - 1) : null;
    }

    protected void reset() {
        Iterator<Evaluator> iterator = this.evaluators.iterator();
        while (true) {
            if (!iterator.hasNext()) {
                super.reset();
                return;
            }
            Evaluator evaluator = iterator.next();
            evaluator.reset();
        }
    }

    void replaceRightMostEvaluator(Evaluator replacement) {
        this.evaluators.set(this.num - 1, replacement);
        this.updateEvaluators();
    }

    protected int cost() {
        return this.cost;
    }

    CombiningEvaluator(Collection<Evaluator> evaluators) {
        this();
        this.evaluators.addAll(evaluators);
        this.updateEvaluators();
    }

    CombiningEvaluator() {
    }

    void updateEvaluators() {
        this.num = this.evaluators.size();
        this.cost = 0;
        Iterator<Evaluator> iterator = this.evaluators.iterator();
        while (true) {
            if (!iterator.hasNext()) {
                this.sortedEvaluators.clear();
                this.sortedEvaluators.addAll(this.evaluators);
                Collections.sort(this.sortedEvaluators, costComparator);
                return;
            }
            Evaluator evaluator = iterator.next();
            this.cost += evaluator.cost();
        }
    }
}
