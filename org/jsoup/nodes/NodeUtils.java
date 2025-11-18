/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.helper.Validate
 *  org.jsoup.helper.W3CDom
 *  org.jsoup.nodes.Document
 *  org.jsoup.nodes.Document$OutputSettings
 *  org.jsoup.nodes.Element
 *  org.jsoup.nodes.Node
 *  org.jsoup.nodes.NodeIterator
 *  org.jsoup.parser.HtmlTreeBuilder
 *  org.jsoup.parser.Parser
 *  org.jsoup.parser.TreeBuilder
 */
package org.jsoup.nodes;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.jsoup.helper.Validate;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.NodeIterator;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.Parser;
import org.jsoup.parser.TreeBuilder;
import org.w3c.dom.NodeList;

final class NodeUtils {
    NodeUtils() {
    }

    static Parser parser(Node node) {
        Document doc = node.ownerDocument();
        return doc != null && doc.parser() != null ? doc.parser() : new Parser((TreeBuilder)new HtmlTreeBuilder());
    }

    static <T extends Node> Stream<T> stream(Node start, Class<T> type) {
        NodeIterator iterator = new NodeIterator(start, type);
        Spliterator<T> spliterator = NodeUtils.spliterator(iterator);
        return StreamSupport.stream(spliterator, false);
    }

    static Document.OutputSettings outputSettings(Node node) {
        Document owner = node.ownerDocument();
        return owner != null ? owner.outputSettings() : new Document("").outputSettings();
    }

    static <T extends Node> List<T> selectXpath(String xpath, Element el, Class<T> nodeType) {
        Validate.notEmpty((String)xpath);
        Validate.notNull((Object)el);
        Validate.notNull(nodeType);
        W3CDom w3c = new W3CDom().namespaceAware(false);
        org.w3c.dom.Document wDoc = w3c.fromJsoup(el);
        org.w3c.dom.Node contextNode = w3c.contextNode(wDoc);
        NodeList nodeList = w3c.selectXpath(xpath, contextNode);
        return w3c.sourceNodes(nodeList, nodeType);
    }

    static <T extends Node> Spliterator<T> spliterator(Iterator<T> iterator) {
        return Spliterators.spliteratorUnknownSize(iterator, 273);
    }
}
