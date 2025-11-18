/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Element
 *  org.jsoup.nodes.Node
 *  org.jsoup.nodes.NodeIterator
 *  org.jsoup.select.Evaluator
 *  org.jsoup.select.StructuralEvaluator
 */
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.NodeIterator;
import org.jsoup.select.Evaluator;
import org.jsoup.select.StructuralEvaluator;

static class StructuralEvaluator.Has
extends StructuralEvaluator {
    static final ThreadLocal<NodeIterator<Element>> ThreadElementIter = ThreadLocal.withInitial(() -> new NodeIterator((Node)new Element("html"), Element.class));

    public boolean matches(Element root, Element element) {
        Element el;
        NodeIterator<Element> it = ThreadElementIter.get();
        it.restart((Node)element);
        do {
            if (!it.hasNext()) return false;
        } while ((el = (Element)it.next()) == element || !this.evaluator.matches(element, el));
        return true;
    }

    public String toString() {
        return String.format(":has(%s)", this.evaluator);
    }

    protected int cost() {
        return 10 * this.evaluator.cost();
    }

    public StructuralEvaluator.Has(Evaluator evaluator) {
        super(evaluator);
    }
}
