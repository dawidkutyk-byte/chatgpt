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

public static final class Evaluator.TagEndsWith
extends Evaluator {
    private final String tagName;

    public String toString() {
        return String.format("%s", this.tagName);
    }

    public Evaluator.TagEndsWith(String tagName) {
        this.tagName = tagName;
    }

    public boolean matches(Element root, Element element) {
        return element.normalName().endsWith(this.tagName);
    }
}
