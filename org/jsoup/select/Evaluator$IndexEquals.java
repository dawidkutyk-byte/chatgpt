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

public static final class Evaluator.IndexEquals
extends Evaluator.IndexEvaluator {
    public Evaluator.IndexEquals(int index) {
        super(index);
    }

    public String toString() {
        return String.format(":eq(%d)", this.index);
    }

    public boolean matches(Element root, Element element) {
        return element.elementSiblingIndex() == this.index;
    }
}
