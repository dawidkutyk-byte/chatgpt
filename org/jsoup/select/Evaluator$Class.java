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

public static final class Evaluator.Class
extends Evaluator {
    private final String className;

    public String toString() {
        return String.format(".%s", this.className);
    }

    public boolean matches(Element root, Element element) {
        return element.hasClass(this.className);
    }

    public Evaluator.Class(String className) {
        this.className = className;
    }

    protected int cost() {
        return 6;
    }
}
