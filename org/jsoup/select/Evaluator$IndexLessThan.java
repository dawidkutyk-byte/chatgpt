/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Element
 *  org.jsoup.select.Evaluator$IndexEvaluator
 */
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.jsoup.select.Evaluator;

public static final class Evaluator.IndexLessThan
extends Evaluator.IndexEvaluator {
    public boolean matches(Element root, Element element) {
        return root != element && element.elementSiblingIndex() < this.index;
    }

    public Evaluator.IndexLessThan(int index) {
        super(index);
    }

    public String toString() {
        return String.format(":lt(%d)", this.index);
    }
}
