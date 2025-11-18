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

public static final class Evaluator.Id
extends Evaluator {
    private final String id;

    protected int cost() {
        return 2;
    }

    public String toString() {
        return String.format("#%s", this.id);
    }

    public boolean matches(Element root, Element element) {
        return this.id.equals(element.id());
    }

    public Evaluator.Id(String id) {
        this.id = id;
    }
}
