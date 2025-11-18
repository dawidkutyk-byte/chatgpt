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

public static final class Evaluator.IsOnlyChild
extends Evaluator {
    public String toString() {
        return ":only-child";
    }

    public boolean matches(Element root, Element element) {
        Element p = element.parent();
        return p != null && !(p instanceof Document) && element.siblingElements().isEmpty();
    }
}
