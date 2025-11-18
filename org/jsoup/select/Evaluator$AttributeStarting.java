/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.helper.Validate
 *  org.jsoup.internal.Normalizer
 *  org.jsoup.nodes.Attribute
 *  org.jsoup.nodes.Element
 *  org.jsoup.select.Evaluator
 */
package org.jsoup.select;

import java.util.Iterator;
import java.util.List;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.select.Evaluator;

public static final class Evaluator.AttributeStarting
extends Evaluator {
    private final String keyPrefix;

    public String toString() {
        return String.format("[^%s]", this.keyPrefix);
    }

    public boolean matches(Element root, Element element) {
        Attribute attribute;
        List values = element.attributes().asList();
        Iterator iterator = values.iterator();
        do {
            if (!iterator.hasNext()) return false;
        } while (!Normalizer.lowerCase((String)(attribute = (Attribute)iterator.next()).getKey()).startsWith(this.keyPrefix));
        return true;
    }

    protected int cost() {
        return 6;
    }

    public Evaluator.AttributeStarting(String keyPrefix) {
        Validate.notNull((Object)keyPrefix);
        this.keyPrefix = Normalizer.lowerCase((String)keyPrefix);
    }
}
