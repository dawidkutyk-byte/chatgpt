/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Element
 *  org.jsoup.select.Evaluator$AttributeKeyPair
 */
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.jsoup.select.Evaluator;

public static final class Evaluator.AttributeWithValueNot
extends Evaluator.AttributeKeyPair {
    protected int cost() {
        return 3;
    }

    public Evaluator.AttributeWithValueNot(String key, String value) {
        super(key, value);
    }

    public String toString() {
        return String.format("[%s!=%s]", this.key, this.value);
    }

    public boolean matches(Element root, Element element) {
        return !this.value.equalsIgnoreCase(element.attr(this.key));
    }
}
