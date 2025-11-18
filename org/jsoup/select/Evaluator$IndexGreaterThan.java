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

public static final class Evaluator.IndexGreaterThan
extends Evaluator.IndexEvaluator {
    public Evaluator.IndexGreaterThan(int index) {
        super(index);
    }

    public boolean matches(Element root, Element element) {
        return element.elementSiblingIndex() > this.index;
    }

    public String toString() {
        return String.format(":gt(%d)", this.index);
    }
}
