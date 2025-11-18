/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.internal.Normalizer
 *  org.jsoup.nodes.Element
 *  org.jsoup.select.Evaluator$AttributeKeyPair
 */
package org.jsoup.select;

import org.jsoup.internal.Normalizer;
import org.jsoup.nodes.Element;
import org.jsoup.select.Evaluator;

public static final class Evaluator.AttributeWithValueContaining
extends Evaluator.AttributeKeyPair {
    public Evaluator.AttributeWithValueContaining(String key, String value) {
        super(key, value);
    }

    protected int cost() {
        return 6;
    }

    public boolean matches(Element root, Element element) {
        return element.hasAttr(this.key) && Normalizer.lowerCase((String)element.attr(this.key)).contains(this.value);
    }

    public String toString() {
        return String.format("[%s*=%s]", this.key, this.value);
    }
}
