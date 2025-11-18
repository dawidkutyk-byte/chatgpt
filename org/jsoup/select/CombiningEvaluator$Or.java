/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.internal.StringUtil
 *  org.jsoup.nodes.Element
 *  org.jsoup.select.CombiningEvaluator
 *  org.jsoup.select.CombiningEvaluator$And
 *  org.jsoup.select.Evaluator
 */
package org.jsoup.select;

import java.util.Arrays;
import java.util.Collection;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.CombiningEvaluator;
import org.jsoup.select.Evaluator;

public static final class CombiningEvaluator.Or
extends CombiningEvaluator {
    public boolean matches(Element root, Element node) {
        int i = 0;
        while (i < this.num) {
            Evaluator s = (Evaluator)this.sortedEvaluators.get(i);
            if (s.matches(root, node)) {
                return true;
            }
            ++i;
        }
        return false;
    }

    public String toString() {
        return StringUtil.join((Collection)this.evaluators, (String)", ");
    }

    CombiningEvaluator.Or(Evaluator ... evaluators) {
        this(Arrays.asList(evaluators));
    }

    CombiningEvaluator.Or(Collection<Evaluator> evaluators) {
        if (this.num > 1) {
            this.evaluators.add(new CombiningEvaluator.And(evaluators));
        } else {
            this.evaluators.addAll(evaluators);
        }
        this.updateEvaluators();
    }

    public void add(Evaluator e) {
        this.evaluators.add(e);
        this.updateEvaluators();
    }

    CombiningEvaluator.Or() {
    }
}
