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

public static final class Evaluator.Attribute
extends Evaluator {
    private final String key;

    public Evaluator.Attribute(String key) {
        this.key = key;
    }

    protected int cost() {
        return 2;
    }

    public String toString() {
        return String.format("[%s]", this.key);
    }

    public boolean matches(Element root, Element element) {
        return element.hasAttr(this.key);
    }
}
