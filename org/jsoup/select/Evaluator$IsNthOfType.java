/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Element
 *  org.jsoup.nodes.Node
 *  org.jsoup.select.Evaluator$CssNthEvaluator
 */
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Evaluator;

public static class Evaluator.IsNthOfType
extends Evaluator.CssNthEvaluator {
    public Evaluator.IsNthOfType(int a, int b) {
        super(a, b);
    }

    protected int calculatePosition(Element root, Element element) {
        Element parent = element.parent();
        if (parent == null) {
            return 0;
        }
        int pos = 0;
        int size = parent.childNodeSize();
        int i = 0;
        while (i < size) {
            Node node = parent.childNode(i);
            if (node.normalName().equals(element.normalName())) {
                ++pos;
            }
            if (node == element) {
                return pos;
            }
            ++i;
        }
        return pos;
    }

    protected String getPseudoClass() {
        return "nth-of-type";
    }
}
