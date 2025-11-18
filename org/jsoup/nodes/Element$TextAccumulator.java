/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Element
 *  org.jsoup.nodes.Node
 *  org.jsoup.nodes.TextNode
 *  org.jsoup.select.NodeVisitor
 */
package org.jsoup.nodes;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeVisitor;

/*
 * Exception performing whole class analysis ignored.
 */
private static class Element.TextAccumulator
implements NodeVisitor {
    private final StringBuilder accum;

    public void head(Node node, int depth) {
        if (node instanceof TextNode) {
            TextNode textNode = (TextNode)node;
            Element.access$000((StringBuilder)this.accum, (TextNode)textNode);
        } else {
            if (!(node instanceof Element)) return;
            Element element = (Element)node;
            if (this.accum.length() <= 0) return;
            if (!element.isBlock()) {
                if (!element.nameIs("br")) return;
            }
            if (TextNode.lastCharIsWhitespace((StringBuilder)this.accum)) return;
            this.accum.append(' ');
        }
    }

    public void tail(Node node, int depth) {
        if (!(node instanceof Element)) return;
        Element element = (Element)node;
        Node next = node.nextSibling();
        if (!element.isBlock()) return;
        if (!(next instanceof TextNode)) {
            if (!(next instanceof Element)) return;
            if (Element.access$100((Element)((Element)next)).formatAsBlock()) return;
        }
        if (TextNode.lastCharIsWhitespace((StringBuilder)this.accum)) return;
        this.accum.append(' ');
    }

    public Element.TextAccumulator(StringBuilder accum) {
        this.accum = accum;
    }
}
