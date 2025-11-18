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

public static final class Evaluator.IsOnlyOfType
extends Evaluator {
    public boolean matches(Element root, Element element) {
        Element p = element.parent();
        if (p == null) return false;
        if (p instanceof Document) {
            return false;
        }
        int pos = 0;
        for (Element next = p.firstElementChild(); next != null; next = next.nextElementSibling()) {
            if (next.normalName().equals(element.normalName())) {
                ++pos;
            }
            if (pos > 1) return pos == 1;
        }
        return pos == 1;
    }

    public String toString() {
        return ":only-of-type";
    }
}
