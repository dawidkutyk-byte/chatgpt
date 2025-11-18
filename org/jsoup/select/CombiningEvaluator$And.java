/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.internal.StringUtil
 *  org.jsoup.nodes.Element
 *  org.jsoup.select.CombiningEvaluator
 *  org.jsoup.select.Evaluator
 */
package org.jsoup.select;

import java.util.Arrays;
import java.util.Collection;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.CombiningEvaluator;
import org.jsoup.select.Evaluator;

public static final class CombiningEvaluator.And
extends CombiningEvaluator {
    CombiningEvaluator.And(Collection<Evaluator> evaluators) {
        super(evaluators);
    }

    CombiningEvaluator.And(Evaluator ... evaluators) {
        this(Arrays.asList(evaluators));
    }

    public boolean matches(Element root, Element element) {
        int i = 0;
        while (i < this.num) {
            Evaluator s = (Evaluator)this.sortedEvaluators.get(i);
            if (!s.matches(root, element)) {
                return false;
            }
            ++i;
        }
        return true;
    }

    public String toString() {
        return StringUtil.join((Collection)this.evaluators, (String)"");
    }
}
