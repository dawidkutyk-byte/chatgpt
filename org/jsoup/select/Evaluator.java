/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Element
 */
package org.jsoup.select;

import java.util.function.Predicate;
import org.jsoup.nodes.Element;

public abstract class Evaluator {
    public abstract boolean matches(Element var1, Element var2);

    protected void reset() {
    }

    protected Evaluator() {
    }

    protected int cost() {
        return 5;
    }

    public Predicate<Element> asPredicate(Element root) {
        return element -> this.matches(root, (Element)element);
    }
}
