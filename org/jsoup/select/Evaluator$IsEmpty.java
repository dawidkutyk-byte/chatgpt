/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Comment
 *  org.jsoup.nodes.DocumentType
 *  org.jsoup.nodes.Element
 *  org.jsoup.nodes.Node
 *  org.jsoup.nodes.TextNode
 *  org.jsoup.nodes.XmlDeclaration
 *  org.jsoup.select.Evaluator
 */
package org.jsoup.select;

import java.util.Iterator;
import java.util.List;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.nodes.XmlDeclaration;
import org.jsoup.select.Evaluator;

public static final class Evaluator.IsEmpty
extends Evaluator {
    public String toString() {
        return ":empty";
    }

    public boolean matches(Element root, Element element) {
        Node n;
        List family = element.childNodes();
        Iterator iterator = family.iterator();
        do {
            if (!iterator.hasNext()) return true;
            n = (Node)iterator.next();
            if (!(n instanceof TextNode)) continue;
            return ((TextNode)n).isBlank();
        } while (n instanceof Comment || n instanceof XmlDeclaration || n instanceof DocumentType);
        return false;
    }
}
