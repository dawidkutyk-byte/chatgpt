/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Element
 *  org.jsoup.select.Evaluator
 */
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.jsoup.select.Evaluator;

static class StructuralEvaluator.Root
extends Evaluator {
    public String toString() {
        return "";
    }

    protected int cost() {
        return 1;
    }

    public boolean matches(Element root, Element element) {
        return root == element;
    }

    StructuralEvaluator.Root() {
    }
}
