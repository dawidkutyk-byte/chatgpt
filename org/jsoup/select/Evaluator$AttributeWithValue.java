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

public static final class Evaluator.AttributeWithValue
extends Evaluator.AttributeKeyPair {
    public Evaluator.AttributeWithValue(String key, String value) {
        super(key, value);
    }

    protected int cost() {
        return 3;
    }

    public String toString() {
        return String.format("[%s=%s]", this.key, this.value);
    }

    public boolean matches(Element root, Element element) {
        return element.hasAttr(this.key) && this.value.equalsIgnoreCase(element.attr(this.key).trim());
    }
}
