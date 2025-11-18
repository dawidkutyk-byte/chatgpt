/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Element
 *  org.jsoup.nodes.Node
 *  org.jsoup.nodes.PseudoTextElement
 *  org.jsoup.nodes.TextNode
 *  org.jsoup.parser.ParseSettings
 *  org.jsoup.parser.Tag
 *  org.jsoup.select.Evaluator
 */
package org.jsoup.select;

import java.util.Iterator;
import java.util.List;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.PseudoTextElement;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Tag;
import org.jsoup.select.Evaluator;

public static final class Evaluator.MatchText
extends Evaluator {
    public boolean matches(Element root, Element element) {
        if (element instanceof PseudoTextElement) {
            return true;
        }
        List textNodes = element.textNodes();
        Iterator iterator = textNodes.iterator();
        while (iterator.hasNext()) {
            TextNode textNode = (TextNode)iterator.next();
            PseudoTextElement pel = new PseudoTextElement(Tag.valueOf((String)element.tagName(), (String)element.tag().namespace(), (ParseSettings)ParseSettings.preserveCase), element.baseUri(), element.attributes());
            textNode.replaceWith((Node)pel);
            pel.appendChild((Node)textNode);
        }
        return false;
    }

    protected int cost() {
        return -1;
    }

    public String toString() {
        return ":matchText";
    }
}
