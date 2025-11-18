/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Element
 *  org.jsoup.select.Evaluator
 */
package org.jsoup.select;

import java.util.IdentityHashMap;
import org.jsoup.nodes.Element;
import org.jsoup.select.Evaluator;

abstract class StructuralEvaluator
extends Evaluator {
    final ThreadLocal<IdentityHashMap<Element, IdentityHashMap<Element, Boolean>>> threadMemo = ThreadLocal.withInitial(IdentityHashMap::new);
    final Evaluator evaluator;

    public StructuralEvaluator(Evaluator evaluator) {
        this.evaluator = evaluator;
    }

    boolean memoMatches(Element root, Element element) {
        Boolean matches;
        IdentityHashMap<Element, IdentityHashMap<Element, Boolean>> rootMemo = this.threadMemo.get();
        IdentityHashMap<Object, Boolean> memo = rootMemo.get(root);
        if (memo == null) {
            memo = new IdentityHashMap();
            rootMemo.put(root, memo);
        }
        if ((matches = memo.get(element)) != null) return matches;
        matches = this.evaluator.matches(root, element);
        memo.put(element, matches);
        return matches;
    }

    protected void reset() {
        this.threadMemo.get().clear();
        super.reset();
    }
}
