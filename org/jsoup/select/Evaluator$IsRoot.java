/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Document
 *  org.jsoup.nodes.Element
 *  org.jsoup.select.Evaluator
 */
package org.jsoup.select;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Evaluator;

public static final class Evaluator.IsRoot
extends Evaluator {
    protected int cost() {
        return 1;
    }

    public boolean matches(Element root, Element element) {
        Element r = root instanceof Document ? root.firstElementChild() : root;
        return element == r;
    }

    public String toString() {
        return ":root";
    }
}
