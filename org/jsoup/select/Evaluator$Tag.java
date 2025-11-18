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

public static final class Evaluator.Tag
extends Evaluator {
    private final String tagName;

    public Evaluator.Tag(String tagName) {
        this.tagName = tagName;
    }

    public String toString() {
        return String.format("%s", this.tagName);
    }

    protected int cost() {
        return 1;
    }

    public boolean matches(Element root, Element element) {
        return element.nameIs(this.tagName);
    }
}
