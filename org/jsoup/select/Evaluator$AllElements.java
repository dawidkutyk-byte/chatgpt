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

public static final class Evaluator.AllElements
extends Evaluator {
    protected int cost() {
        return 10;
    }

    public String toString() {
        return "*";
    }

    public boolean matches(Element root, Element element) {
        return true;
    }
}
